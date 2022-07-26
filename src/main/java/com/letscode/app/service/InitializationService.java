package com.letscode.app.service;

import com.sun.nio.sctp.IllegalReceiveException;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class InitializationService{
    public static LocalDateTime initializeApp(){
        LocalDateTime intializationTime = LocalDateTime.now();

        List<File> files = List.of(Objects.requireNonNull(new File("movies_files").listFiles(obj -> obj.isFile()
                && obj.getName().endsWith(".csv"))));

        //chain of responsability
        if(files.size() != 3){
            throw new IllegalReceiveException("São necessários 3 arquivos .csv para inicializar o programa!");
        }

        System.out.println("Programa inicializado!");

        return intializationTime;
    }
}