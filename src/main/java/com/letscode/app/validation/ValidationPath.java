package com.letscode.app.validation;

import java.nio.file.Files;
import java.nio.file.Path;

public class ValidationPath implements Validation<Path> {
    @Override
    public void validate(Path path) {
        if(Files.notExists(path))
            throw new RuntimeException("Arquivo inválido");
    }
}
