package com.letscode.app.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.letscode.app.model.Movie;

import repository.MovieRepository;
public class BestesByYear {
    Set<Movie> movies;
    private WhiteMovieService whiteMovieService;
    
    // TODO: interface para o service
    public BestesByYear(Set<Movie> movies) {
        this.movies = movies;
    }

    public void execute() {
        // Visualizar os filmes mais bem avaliados por ano
        Map<Integer, List<Movie>> filmes = this.movies.stream()
            .sorted( (f1, f2) -> f1.getRating().compareTo(f2.getRating()) )
            .limit(50)
            .collect(Collectors.groupingBy(f ->f.getYear()));

        // Foreach para cada ano
        this.whiteMovieService = new WhiteMovieService();
        filmes.forEach((year, movies) -> {
            whiteMovieService.writeFile(year, movies); // Write file for each year
        });
    }

    public static void main(String[] args) throws IOException {

        MovieRepository repository = new MovieRepository();
        Set<Movie> movies = repository.read();
        BestesByYear melhoresPorAno = new BestesByYear(movies);
        melhoresPorAno.execute();
    }
}
