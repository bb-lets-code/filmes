package com.letscode.app.repository;

import com.letscode.app.model.Movie;
import com.letscode.app.service.ReadMovieService;
import com.letscode.app.service.WriteMovieService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MovieRepository implements BaseRepository<Set<Movie>, Path> {
        @Override
    public Set<Movie> read() throws IOException {
        List<File> files = List.of(Objects.requireNonNull(new File("movies_files").listFiles(obj -> obj.isFile()
                && obj.getName().endsWith(".csv"))));
        //new ValidationPath().validate(path);

        return files.stream()
                .map(File::toPath)
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
                .map(ReadMovieService.getTreatmentMovie)
                .collect(Collectors.toSet());
        }

    @Override
    public void write(Set<Movie> movies, Path path) throws IOException {
        Files.write(path, WriteMovieService.parseWrite(movies));
    }
}
