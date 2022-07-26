package com.letscode.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.letscode.app.repository.MovieRepository;

import org.junit.Before;
import org.junit.Test;

import com.letscode.app.model.Movie;
import com.letscode.app.service.BestsHorrorMoviesService;
import com.letscode.app.service.BestsMoviesByYearService;



/**
* Unit test for simple App.
*/
public class AppTest
{

    private MovieRepository movieRepository = new MovieRepository();
    private Set<Movie> movies;
   /**
    * Rigorous Test :-)
    * @throws IOException
    */

    // Setup
    @Before
    public void setUp() throws IOException {
        this.movies = movieRepository.read();
    }

   @Test
   public void bestMoviesTest() throws IOException
   {



       BestsMoviesByYearService service = new BestsMoviesByYearService(this.movies);
       Map<Integer, List<Movie>> execute = service.execute();
       execute.forEach((year, movies) -> {
           System.out.println("+----------------------------------------------------+");
           System.out.println("Ano: " + year);
           System.out.println("+----------------------------------------------------+");
           int size = movies.size();
           for (int i = 1; i < size; i++) {
               if(movies.get(i-1).getRating().compareTo(movies.get(i).getRating()) <= 0) {

                   fail("Erro: " + movies.get(i).getTitle() + " " + movies.get(i).getRating() + " " + movies.get(i +1  ).getTitle() + " " + movies.get(i +1  ).getRating());
               } else {
                   System.out.println("+----------------------------------------------------+");
                   System.out.println("Filme: " + movies.get(i - 1).getTitle() + " " + movies.get(i - 1).getRating());
                   System.out.println("Filme(anterior): " + movies.get(i).getTitle() + " " + movies.get(i).getRating());

               }
           }
           assertTrue(movies.size() <= 50);

       });
   }

    @Test
    public void bestsMoviesServiceTest() {
        BestsHorrorMoviesService service = new BestsHorrorMoviesService(this.movies);
        Set<Movie> execute = service.execute();
        assertTrue(execute.size() <= 20);
        execute.stream().forEach(movie -> {
            System.out.println("+----------------------------------------------------+");
            System.out.println("Filme: " + movie.getTitle() + " " + movie.getRating().getRating() + " ");
            System.out.println("Genero: " + Stream.of(movie.getGenre()).collect(Collectors.joining(",")));
            System.out.println("+----------------------------------------------------+");
            boolean test = false;
            for (String genre : movie.getGenre()) {
                if (genre.equals("Horror")) {
                    test = true;
                }
            }
            assertTrue(test);

        });

        
    }
}
