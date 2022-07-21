package com.letscode.app;

import java.io.IOException;

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
        var test = repository.read();
    }
}
