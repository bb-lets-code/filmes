package com.letscode.app.service;

import java.math.BigDecimal;
import java.util.function.Function;

import com.letscode.app.enums.TableCSV;
import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

public class ReadMovieService {
    public Function<String, Movie> getTreatmentMovie = m -> {
        String[] line = m.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        // Builder para montar o objeto Filme
        return new Movie(
                line[TableCSV.Title.ordinal()],
                Integer.valueOf(line[TableCSV.Year.ordinal()]),
                genres(line),
                actors(line),
                directors(line),
                descriptions(line)[0],
                actors(line),
                runtime(line),
                rating(line),
                revenue(line),
                metascore(line)

        );
    };

    private String[] genres(String[] line){
        return line[TableCSV.Genre.ordinal()].startsWith("\"") ?
                line[TableCSV.Genre.ordinal()].substring(1, line[TableCSV.Genre.ordinal()].length() - 1).split(",") :
                new String[]{line[TableCSV.Genre.ordinal()]};
    }

    private String[] actors(String[] line){
       return line[TableCSV.Actors.ordinal()].startsWith("\"") ?
                line[TableCSV.Actors.ordinal()].substring(1, line[TableCSV.Actors.ordinal()].length() - 1).split(",\\s|,") :
                new String[]{line[TableCSV.Actors.ordinal()]};
    }
    
    private String[] directors(String[] line){
        return line[TableCSV.Director.ordinal()].startsWith("\"") ?
                line[TableCSV.Director.ordinal()].substring(1, line[TableCSV.Director.ordinal()].length() - 1).split(",\\s|,") :
                new String[]{line[TableCSV.Director.ordinal()]};
    }

    private String[] descriptions(String[] line){
        return line[TableCSV.Description.ordinal()].startsWith("\"") ?
                line[TableCSV.Description.ordinal()].substring(1, line[TableCSV.Description.ordinal()].length() - 1).split(",\\s|,") :
                new String[]{line[TableCSV.Description.ordinal()]};
    }

    private int runtime(String[] line){
        String runtime = line[TableCSV.Runtime.ordinal()].trim();
        if(runtime.isEmpty())
            return 0;
        return Integer.parseInt(runtime);

    }

    private Rating rating(String[] line){
       return new Rating(
                Double.parseDouble(line[TableCSV.Rating.ordinal()]),
                Integer.parseInt(line[TableCSV.Votes.ordinal()]));
    }

    private BigDecimal revenue(String[] line){
        String value = line[TableCSV.Revenue.ordinal()];
        if (value.isEmpty())
            return BigDecimal.ZERO;
        return new BigDecimal(value);
    }

    private double metascore(String[] line){
      String value = line[TableCSV.Metascore.ordinal()].trim();
      if (value.isEmpty())
        return Double.valueOf(0);

      return Double.parseDouble(value);
    }
}