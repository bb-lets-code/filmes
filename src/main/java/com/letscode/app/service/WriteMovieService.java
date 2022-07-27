package com.letscode.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.letscode.app.model.Movie;
import com.letscode.app.repository.MovieRepository;

public class WriteMovieService {
    public void writeFile(String fileName, Set<Movie> filmes) throws IOException {
        MovieRepository repository = new MovieRepository();
        String directory = "output_csv";
        createOutputfolder(directory);
        String file = directory + "\\" + fileName + ".csv";
        repository.write(filmes, Path.of(file));
    }

    private void createOutputfolder(String path){
        try {
            if(Files.notExists(Path.of(path))){
                Files.createDirectory(Path.of(path));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> parseWrite (Set<Movie> movies) {
        return movies.stream()
                .map(Movie::toString)
                .map(s -> s.replace("[", ""))
                .map(s -> s.replace("]", ""))
                .map(s -> s.replace("\n, ", "\n"))
                .collect(Collectors.toList());
    }
}


