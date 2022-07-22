package com.letscode.app.service;

import com.letscode.app.enums.TableCSV;
import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class TreatmentService {
    static public Function<String, Movie> getTreatmentMovie = m -> {
        String[] linha = m.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

        String [] genre = linha[TableCSV.Genre.ordinal()].startsWith("\"") ?
                linha[TableCSV.Genre.ordinal()].substring(1,linha[TableCSV.Genre.ordinal()].length()-1).split(","):
                new String[]{linha[TableCSV.Genre.ordinal()]};
        String [] actors = linha[TableCSV.Actors.ordinal()].startsWith("\"") ?
                linha[TableCSV.Actors.ordinal()].substring(1,linha[TableCSV.Actors.ordinal()].length()-1).split(",\\s|," ):
                new String[]{linha[TableCSV.Actors.ordinal()]};
        String [] directors = linha[TableCSV.Director.ordinal()].startsWith("\"") ?
                linha[TableCSV.Director.ordinal()].substring(1,linha[TableCSV.Director.ordinal()].length()-1).split(",\\s|," ):
                new String[]{linha[TableCSV.Director.ordinal()]};
        String [] description = linha[TableCSV.Description.ordinal()].startsWith("\"") ?
                linha[TableCSV.Description.ordinal()].substring(1,linha[TableCSV.Description.ordinal()].length()-1).split(",\\s|," ):
                new String[]{linha[TableCSV.Description.ordinal()]};


        // Builder para montar o objeto Filme
        return new Movie(
                linha[TableCSV.Title.ordinal()],
                Integer.valueOf(linha[TableCSV.Year.ordinal()]),
                genre,
                actors,
                directors,
                description[0],
                actors,
                0,
                new Rating(
                        Double.parseDouble(linha[TableCSV.Rating.ordinal()]),
                        Integer.parseInt(linha[TableCSV.Votes.ordinal()])),
                        new BigDecimal(TableCSV.Revenue.ordinal()), (double) TableCSV.Metascore.ordinal()
        );};

    static public List<String> parseWrite (Set<Movie> movies) {
        List<String> usersAsString = Collections.singletonList(movies.toString());
        usersAsString = usersAsString.stream()
                .map(s -> s.replace("[", ""))
                .map(s -> s.replace("]", ""))
                .map(s -> s.replace("\n, ", "\n"))
                .toList();
        return usersAsString;
    }
}
