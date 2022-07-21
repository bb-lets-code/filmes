package com.letscode.app.repository;

import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieReadRepository implements BaseReadModel<Set<String>> {

    @Override
    public Set<String> read(Path path) throws IOException {
        validateFiles(path);
        // Stream que lê o arquivo o divide os 12 campos do arquivo de entrada
        // (é preciso pensar os gêneros do filme entre aspas

//        String[] aux = new String[0];
//
//        Set<Movie> movies = Files.lines(path, StandardCharsets.UTF_8)
//                .map(m -> m.split(","))
//                .map(s -> new Movie(s[0], Integer.parseInt(s[1]), s[2].split(""), s[3], s[4], s[5], s[6], s[7], s[8], s[9], Double.parseDouble(s[10])))
//                .collect(Collectors.toSet());

        return null;
    }

    private void validateFiles(Path path) {
        if(Files.notExists(path))
            throw new RuntimeException("Arquivo inválido");
    }
}
