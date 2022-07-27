package com.letscode.app.service;

import com.sun.nio.sctp.IllegalReceiveException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InitializationService{
    public LocalDateTime initializeApp() throws IOException {
        LocalDateTime intializationTime = LocalDateTime.now();

        List<Path> paths = Files.list(Path.of("movies_files")).filter(obj -> Files.exists(obj)
                && obj.getFileName().toString().endsWith(".csv")).collect(Collectors.toList());

        if(paths.size() != 3){
            throw new IllegalReceiveException("São necessários 3 arquivos .csv para inicializar o programa!");
        }

        System.out.println("Programa inicializado!");

        return intializationTime;
    }
}