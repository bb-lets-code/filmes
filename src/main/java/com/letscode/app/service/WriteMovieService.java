package com.letscode.app.service;

import com.letscode.app.model.Movie;
import com.letscode.app.repository.MovieReadRepository;
import com.letscode.app.repository.MovieWriteRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

public class WriteMovieService {
    public void writeFile(Integer fileName, Set<Movie> filmes) throws IOException {
        MovieWriteRepository repository = new MovieWriteRepository();
        String file = fileName + ".csv";
        // TODO: verificar como criar o arquivo com um novo nome
        repository.write(filmes, Path.of(file));
    }
}
