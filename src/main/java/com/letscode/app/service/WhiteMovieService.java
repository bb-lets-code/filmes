package com.letscode.app.service;

import java.util.Set;

import com.letscode.app.model.Movie;

import repository.MovieRepository;

public class WhiteMovieService {

    private MovieRepository repository;

    public void writeFile(Integer fileName, Set<Movie> filmes) {
        
        

        this.repository = new MovieRepository();
        // TODO: verificar como criar o arquivo com um novo nome
        repository.write( filmes);
        


    }

}
