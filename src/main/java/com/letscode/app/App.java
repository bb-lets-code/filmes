package com.letscode.app;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.letscode.app.model.Movie;
import com.letscode.app.service.BestsMoviesByYearService;

import repository.MovieRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        MovieRepository repository = new MovieRepository();
        Set<Movie> test = repository.read();
        // test2.forEach(f -> test.add(f));
        // test3.forEach(f -> test.add(f));
        BestsMoviesByYearService service = new BestsMoviesByYearService(test);
        // for (Movie movie : test) {
        //     System.out.println(movie.getTitle() + " " + movie.getRating());
        // }
        Map<Integer, List<Movie>> execute = service.execute();
        

        

    }
}
