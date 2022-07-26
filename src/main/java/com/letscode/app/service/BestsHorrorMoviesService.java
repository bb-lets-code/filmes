package com.letscode.app.service;

import com.letscode.app.model.Movie;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BestsHorrorMoviesService {

    Set<Movie> movies;
    private WriteMovieService writeMovieService;


    public BestsHorrorMoviesService(Set<Movie> movies) {
        this.movies = movies;
    }

    public TreeSet<Movie> execute() {
        this.writeMovieService = new WriteMovieService();
        TreeSet<Movie> moviesHorrorGenre = new TreeSet<Movie>(Comparator.comparing( Movie::getRating).reversed());
        TreeSet<Movie> twentyMovies = new TreeSet<Movie>(Comparator.comparing( Movie::getRating).reversed());
        final Pattern pattern = Pattern.compile("(?=.*(Horror))");

        try {
            movies.forEach(movie -> {
                Matcher matcher = pattern.matcher(Arrays.toString(movie.getGenre()));

                if (matcher.find()) {
                    moviesHorrorGenre.add(movie);
                }
            });

            twentyMovies.addAll(moviesHorrorGenre.stream()
                    .sorted(Comparator.comparing(Movie::getRating).reversed())
                    .limit(20)
                    .collect(Collectors.toList())
            );

            writeMovieService.writeFile("HorrorMovies", twentyMovies);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return twentyMovies;
    }
}
