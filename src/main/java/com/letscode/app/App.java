package com.letscode.app;

import java.io.IOException;
import java.util.Arrays;

import com.letscode.app.model.Movie;

import com.letscode.app.repository.MovieRepository;
import com.letscode.app.service.BestesByYearService;


public class App {

    //TODO: JUNTAR BASE MODEL REPOSITORY; PASSAR A FUNÇÃO PARSEWRITE PARA A CLASSE WRITEMOVIESERVICE; MUDAR NOME TREATMENT SERVICE
    // SEPARAR ARQUIVOS ESCRITOS EM NOVA PASTA; REVISAR FUNÇÃO WRITE
    public static void main(String[] args) throws IOException
    {
        MovieRepository repository = new MovieRepository();
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
