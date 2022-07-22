package com.letscode.app.repository;

import com.letscode.app.model.Movie;
import com.letscode.app.service.TreatmentService;
import com.letscode.app.validation.Validation;
import com.letscode.app.validation.ValidationPath;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieReadRepository implements BaseReadModel<Set<Movie>> {
        @Override
    public Set<Movie> read(Path path) throws IOException {
        new ValidationPath().validate(path);
        try {
            return Files.lines(path, StandardCharsets.UTF_8)
                    .skip(1)
                    .parallel()
                    .map(TreatmentService.getTreatmentMovie)
                    .collect(Collectors.toSet());
        } catch (IOException io){
            throw new IOException("Tratamento inválido.");
        }
    }
}
