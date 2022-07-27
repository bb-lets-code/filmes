package com.letscode.app.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ProcessingTimeRepository implements BaseRepository<String, Path> {

    @Override
    public String read() throws IOException {
        return "";
    }

    @Override
    public void write(String timeString, Path path) throws IOException {
        Files.write(path,timeString.getBytes());
    }
    
}
