package com.letscode.app.service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.letscode.app.model.Movie;
public class BestesByYearService {
    Set<Movie> movies;
    private WhiteMovieService whiteMovieService;
    
    // TODO: interface para o service
    public BestesByYearService(Set<Movie> movies) {
        this.movies = movies;
    }
    
    public void execute() {
        this.whiteMovieService = new WhiteMovieService();
        // Visualizar os filmes mais bem avaliados por ano
        Map<Integer, List<Movie>> filmes = this.movies.stream()
            .sorted( (f1, f2) -> f1.getRating().compareTo(f2.getRating()) )
            .limit(50)
            .collect(Collectors.groupingBy(f ->f.getYear()));

        // Foreach para cada ano
        
        filmes.forEach((year, movies) -> {
            TreeSet<Movie> filmesByYear = new TreeSet<Movie>(Comparator.comparing(Movie::getRating).reversed());
            filmesByYear.addAll(movies);
            whiteMovieService.writeFile(year, filmesByYear); 
            
        });
    }

}
