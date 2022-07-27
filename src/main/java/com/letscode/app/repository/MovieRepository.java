package com.letscode.app.repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.letscode.app.model.Movie;
import com.letscode.app.service.ReadMovieService;
import com.letscode.app.service.WriteMovieService;

public class MovieRepository implements BaseRepository<Set<Movie>, Path> {
        @Override
    public Set<Movie> read() throws IOException {
        List<Path> paths = Files.list(Path.of("movies_files")).filter(obj -> Files.exists(obj)
                && obj.getFileName().toString().endsWith(".csv")).collect(Collectors.toList());

        return paths.stream()
                .map(p -> {
                    try {
                        return Files.lines(p, StandardCharsets.UTF_8);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(Stream::distinct)
                .skip(1)
                .parallel()
                .map(new ReadMovieService().getTreatmentMovie)
                .collect(Collectors.toSet());
        }

    @Override
    public void write(Set<Movie> movies, Path path) throws IOException {
        Files.write(path, new WriteMovieService().parseWrite(movies));
    }

}
