package com.letscode.app.repository;

import com.letscode.app.model.Movie;
import com.letscode.app.service.RegexTreatment;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieReadRepository implements BaseReadModel<Set<Movie>> {
        @Override
    public Set<Movie> read(Path path) throws IOException {
        validateFile(path);

        try {
            return Files.lines(path, StandardCharsets.UTF_8)
                    .skip(1)
                    .parallel()
                    .map(RegexTreatment.getTreatmentMovie)
                    .collect(Collectors.toSet());
        } catch (IOException io){
            throw new IOException("Tratamento inválido.");
        }
    }

    private void validateFile(Path path) {
        if(Files.notExists(path))
            throw new RuntimeException("Arquivo inválido");
    }
}
