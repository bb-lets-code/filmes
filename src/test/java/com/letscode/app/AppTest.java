package com.letscode.app;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.letscode.app.model.Movie;
import com.letscode.app.service.BestsMoviesByYearService;

import repository.MovieRepository;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * @throws IOException
     */
    @Test
    public void bestMoviesTest() throws IOException
    {
        

        MovieRepository repository = new MovieRepository();
        Set<Movie> test2 = repository.read();

        BestsMoviesByYearService service = new BestsMoviesByYearService(test2);
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
}
