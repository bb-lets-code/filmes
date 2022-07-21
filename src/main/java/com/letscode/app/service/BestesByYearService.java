package com.letscode.app.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.letscode.app.model.Movie;
public class BestesByYearService {
    Set<Movie> movies;
    private WriteMovieService writeMovieService;
    
    // TODO: interface para o service
    public BestesByYearService(Set<Movie> movies) {
        this.movies = movies;
    }
    
    public void execute() {
        this.writeMovieService = new WriteMovieService();
        // Visualizar os filmes mais bem avaliados por ano
        Map<Integer, List<Movie>> filmes = this.movies.stream()
            .sorted( (f1, f2) -> f1.getRating().compareTo(f2.getRating()) )
            .limit(50)
            .collect(Collectors.groupingBy(Movie::getYear));

        // Foreach para cada ano
        
        filmes.forEach((year, movies) -> {
            Set<Movie> filmesByYear = new TreeSet<>(Comparator.comparing(Movie::getRating).reversed());
            filmesByYear.addAll(movies);
            try {
                writeMovieService.writeFile(year, filmesByYear);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
