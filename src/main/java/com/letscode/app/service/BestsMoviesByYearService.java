package com.letscode.app.service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.letscode.app.model.Movie;
public class BestsMoviesByYearService {
    Set<Movie> movies;
    private WriteMovieService whiteMovieService;
    
    
    public BestsMoviesByYearService(Set<Movie> movies) {
        this.movies = movies;
    }
    
    public void execute() {
        this.whiteMovieService = new WriteMovieService();
        // Visualizar os filmes mais bem avaliados por ano
         this.movies.stream().parallel()
            .collect(Collectors.groupingBy(Movie::getYear))
            .forEach((year,movies) -> {
                TreeSet<Movie> filmesByYear = new TreeSet<Movie>(Comparator.comparing(Movie::getRating).reversed());
                filmesByYear.addAll(movies.stream().limit(50).parallel().collect(Collectors.toList()));
                whiteMovieService.writeFile(year, filmesByYear);
            });
        
    }

}
