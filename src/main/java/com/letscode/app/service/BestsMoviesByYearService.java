package com.letscode.app.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
    
    public Map<Integer, List<Movie>> execute() {
        this.whiteMovieService = new WriteMovieService();
        // Visualizar os filmes mais bem avaliados por ano
        Map<Integer, List<Movie>> moviesGroupsByYear = movies.stream().collect(Collectors.groupingBy(Movie::getYear));

        moviesGroupsByYear
        .forEach((year,moviesByYear) -> {
                // Ordenar os filmes por rating
                final TreeSet<Movie> filmesByYear = new TreeSet<Movie>(Comparator.comparing( Movie::getRating).reversed());

                filmesByYear.addAll(
                    moviesByYear.stream()
                    .parallel()
                    .sorted(Comparator.comparing(Movie::getRating).reversed())
                    .limit(50)
                    .collect(Collectors.toList())
                );

            try {
                whiteMovieService.writeFile(year.toString(), filmesByYear);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        Map<Integer, List<Movie>> moviesSorted = moviesGroupsByYear.entrySet().stream().collect(
            Collectors.toMap(Entry::getKey, e-> e.getValue().stream().sorted(Comparator.comparing(Movie::getRating).reversed()).limit(50).collect(Collectors.toList()))
        );
        
        return moviesSorted;
        
    }

}
