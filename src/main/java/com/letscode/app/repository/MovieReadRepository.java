package com.letscode.app.repository;

import com.letscode.app.model.Movie;
import com.letscode.app.service.TreatmentService;
import com.letscode.app.validation.ValidationPath;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieReadRepository implements BaseReadModel<Set<Movie>> {
        @Override
    public Set<Movie> read() throws IOException {
        List<File> files = List.of(Objects.requireNonNull(new File("movies_files").listFiles(obj -> obj.isFile() && obj.getName().endsWith(".csv"))));
        System.out.println(files);
        //new ValidationPath().validate(path);

        return files.stream()
                .map(File::toPath)
                .map(p -> {
                    try {
                        return Files.lines(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .flatMap(p -> p.distinct())
                .skip(1)
                .parallel()
                .map(TreatmentService.getTreatmentMovie)
                .collect(Collectors.toSet());
        }
}
