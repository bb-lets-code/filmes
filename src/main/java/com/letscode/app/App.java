package com.letscode.app;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.letscode.app.model.Movie;
import com.letscode.app.service.BestsMoviesByYearService;

import com.letscode.app.model.Movie;
import com.letscode.app.repository.MovieReadRepository;
import com.letscode.app.service.BestesByYearService;
import com.letscode.app.validation.ValidationPath;


public class App {
    public static void main(String[] args) throws IOException
    {
        MovieReadRepository repository = new MovieReadRepository();
        var test = repository.read();
        int i=0;
        for(Movie movie : test){
            i++;
            System.out.println(i + ". '" + movie.getTitle() + "' (" + movie.getYear() + ") de " + Arrays.toString(movie.getDirectors()) +"");
        }
//        test.forEach(m -> System.out.println("'" + m.getTitle() + "' (" + m.getYear() + ") de " + Arrays.toString(m.getDirectors()) +""));

        BestesByYearService bestesByYearService = new BestesByYearService(test);
        bestesByYearService.execute();
    }
}
