package com.letscode.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

import repository.MovieRepository;
public class MelhoresPorAno {
    Set<Movie> filmes;
    private WhiteMovieService whiteMovieService;
    
    // TODO: interface para o service
    public MelhoresPorAno(Set<Movie> filmes) {
        this.filmes = filmes;
    }

    public void execute() {
        // Visualizar os filmes mais bem avaliados por ano
        Map<Integer, List<Movie>> filmes = this.filmes.stream()
            .sorted( (f1, f2) -> f1.getRating().compareTo(f2.getRating()) )
            .limit(50)
            .collect(
                Collectors.groupingBy(Movie::getYear)
            );

        // Foreach para cada ano
        filmes.forEach((year, movies) -> {
            System.out.println(year);
            whiteMovieService.writeFile(year, movies);
            
        });
    }

    public static void main(String[] args) throws IOException {

        MovieRepository repository = new MovieRepository();
        Set<Movie> filmes = repository.read();
        MelhoresPorAno melhoresPorAno = new MelhoresPorAno(filmes);
        melhoresPorAno.execute();
    }
}
