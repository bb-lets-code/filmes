package com.letscode.app.repository;

import com.letscode.app.model.Movie;
import com.letscode.app.service.TreatmentService;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MovieWriteRepository implements BaseWriteModel<Set<Movie>> {
    @Override
    public void write(Set<Movie> movies, Path path) throws IOException {
        Files.write(path, TreatmentService.parseWrite(movies));
    }
}