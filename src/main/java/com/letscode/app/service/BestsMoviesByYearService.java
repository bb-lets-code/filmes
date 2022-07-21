package com.letscode.app.service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        Map<Integer, List<Movie>> map = movies.stream().collect(Collectors.groupingBy(Movie::getYear));
        map.forEach((year,moviesByYear) -> {

            // Ordenar os filmes por rating
                List<Movie> moviesSorted = moviesByYear.stream().sorted(Comparator.comparing(Movie::getRating).reversed()).collect(Collectors.toList());

                TreeSet<Movie> filmesByYear = new TreeSet<Movie>(Comparator.comparing( f -> {
                    return f.getRating();
                }));


                moviesSorted.stream().limit(50).forEach(f->{
                     filmesByYear.add(f);
                });
                
                // filmesByYear.addAll(moviesByYear.stream().collect(Collectors.toList()));
                // Set<Movie> filmesByYear = moviesByYear.stream().sorted( Comparator.comparing(Movie::getRating)).limit(50).collect(Collectors.toCollection(TreeSet::new));
                whiteMovieService.writeFile(year, filmesByYear);
                System.out.println("+----------------------------------------------------+");
            });
        
        return map;
        
    }

}
