package com.letscode.app.service;

import java.util.List;
import java.util.Map;

import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

public class WhiteMovieService {

    public void writeFile(Integer fileName, List<Movie> filmes) {
        // TODO: implementar o método writeFile
        System.out.println("Ano: " + fileName);
        for (Movie movie : filmes) {
            System.out.println(movie.getTitle() + " - " + movie.getRating());
        }
    }

}
