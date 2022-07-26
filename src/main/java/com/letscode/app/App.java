package com.letscode.app;

import java.io.IOException;
import java.util.Set;

import com.letscode.app.model.Movie;
import com.letscode.app.repository.MovieRepository;
import com.letscode.app.service.BestsHorrorMoviesService;
import com.letscode.app.service.BestsMoviesByYearService;


public class App {

    //TODO: JUNTAR BASE MODEL REPOSITORY; PASSAR A FUNÇÃO PARSEWRITE PARA A CLASSE WRITEMOVIESERVICE; MUDAR NOME TREATMENT SERVICE
    // SEPARAR ARQUIVOS ESCRITOS EM NOVA PASTA; REVISAR FUNÇÃO WRITE
    public static void main(String[] args) throws IOException
    {
        MovieRepository repository = new MovieRepository();
        Set<Movie> movies = repository.read();

        BestsMoviesByYearService bestsMoviesByYearService = new BestsMoviesByYearService(movies);
        bestsMoviesByYearService.execute();

        BestsHorrorMoviesService bestsHorrorMoviesService = new BestsHorrorMoviesService(movies);
        bestsHorrorMoviesService.execute();
    }
}
