package com.letscode.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.sun.nio.sctp.IllegalReceiveException;

public class InitializationService{
    public LocalDateTime initializeApp() throws IOException {
        LocalDateTime initializationTime = LocalDateTime.now();

        List<Path> paths = Files.list(Path.of("movies_files")).filter(obj -> Files.exists(obj)
                && obj.getFileName().toString().endsWith(".csv")).collect(Collectors.toList());

        if(paths.size() != 3){
            throw new IllegalReceiveException("São necessários 3 arquivos .csv para inicializar o programa!");
        }

        System.out.println("Programa inicializado!");

        return initializationTime;
    }
}