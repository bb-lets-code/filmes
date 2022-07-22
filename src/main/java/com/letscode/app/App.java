package com.letscode.app;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

import com.letscode.app.repository.MovieReadRepository;
import com.letscode.app.service.BestesByYearService;


public class App {
    public static void main(String[] args) throws IOException
    {
        MovieReadRepository repository = new MovieReadRepository();
        var test = repository.read(Path.of("movies2.csv"));
        //test.forEach(m -> System.out.println("'" + m.getTitle() + "' (" + m.getYear() + ") de " + Arrays.toString(m.getDirectors()) +""));

        BestesByYearService bestesByYearService = new BestesByYearService(test);
        bestesByYearService.execute();
    }
}
