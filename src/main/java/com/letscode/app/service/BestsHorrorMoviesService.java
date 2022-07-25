package com.letscode.app.service;

import com.letscode.app.model.Movie;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BestsHorrorMoviesService {

    Set<Movie> movies;
    private WriteMovieService writeMovieService;


    public BestsHorrorMoviesService(Set<Movie> movies) {
        this.movies = movies;
    }

    public void execute() {
        this.writeMovieService = new WriteMovieService();
        Set<Movie> moviesHorrorGenre = new TreeSet<Movie>(Comparator.comparing( Movie::getRating).reversed());

        movies.forEach(movie -> {
            Arrays.stream(movie.getGenre()).forEach(genre -> {
                if (genre.equals("Horror")) {
                    moviesHorrorGenre.add(movie);
                }
            });
        });

        moviesHorrorGenre.stream()
                .sorted(Comparator.comparing(Movie::getRating).reversed())
                .limit(20)
                .collect(Collectors.toList());

        try {
            writeMovieService.writeFile("HorrorMovies", moviesHorrorGenre);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
