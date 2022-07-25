package com.letscode.app.service;

import com.letscode.app.model.Movie;
import com.letscode.app.repository.MovieRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WriteMovieService {
    public void writeFile(Integer fileName, Set<Movie> filmes) throws IOException {
        MovieRepository repository = new MovieRepository();
        String file = fileName + ".csv";
        repository.write(filmes, Path.of(file));
    }

    static public List<String> parseWrite (Set<Movie> movies) {
        List<String> usersAsString = Collections.singletonList(movies.toString());
        usersAsString = usersAsString.stream()
                .map(s -> s.replace("[", ""))
                .map(s -> s.replace("]", ""))
                .map(s -> s.replace("\n, ", "\n"))
                .collect(Collectors.toList());
        return usersAsString;
    }
}


