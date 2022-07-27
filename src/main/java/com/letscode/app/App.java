package com.letscode.app;

import java.io.IOException;
import java.time.LocalDateTime;

import com.letscode.app.repository.MovieRepository;
import com.letscode.app.service.BestsHorrorMoviesService;
import com.letscode.app.service.BestsMoviesByYearService;
import com.letscode.app.service.InitializationService;
import com.letscode.app.service.CloseService;

public class App {

    //TODO: JUNTAR BASE MODEL REPOSITORY; PASSAR A FUNÇÃO PARSEWRITE PARA A CLASSE WRITEMOVIESERVICE; MUDAR NOME TREATMENT SERVICE
    // SEPARAR ARQUIVOS ESCRITOS EM NOVA PASTA; REVISAR FUNÇÃO WRITE
    public static void main(String[] args) throws IOException
    {
        InitializationService initializationService = new InitializationService();
        LocalDateTime begin = initializationService.initializeApp();
        MovieRepository repository = new MovieRepository();
        var test = repository.read();

        BestsMoviesByYearService bestsMoviesByYearService = new BestsMoviesByYearService(test);
        bestsMoviesByYearService.execute();

        BestsHorrorMoviesService bestsHorrorMoviesService = new BestsHorrorMoviesService(test);
        bestsHorrorMoviesService.execute();

        CloseService closeService = new CloseService();
        closeService.closeApp(begin);
    }
}
