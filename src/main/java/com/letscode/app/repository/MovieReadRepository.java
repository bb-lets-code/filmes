package com.letscode.app.repository;

import com.letscode.app.enums.TableCSV;
import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieReadRepository implements BaseReadModel<Set<Movie>> {
    @Override
    public Set<Movie> read(Path path) throws IOException {
        validateFiles(path);
        // Stream que lê o arquivo o divide os 12 campos do arquivo de entrada
        // (é preciso pensar os gêneros do filme entre aspas

        return Files.lines(path, StandardCharsets.UTF_8)
                .skip(1)
                .parallel()
                .map(m ->{
                        final String[] linha = m.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                        final String [] genre = linha[TableCSV.Genre.ordinal()].startsWith("\"") ?
                                linha[TableCSV.Genre.ordinal()].substring(1,linha[TableCSV.Genre.ordinal()].length()-1).split(","):
                                new String[]{linha[TableCSV.Genre.ordinal()]};

                        final String [] actors = linha[TableCSV.Actors.ordinal()].startsWith("\"") ?
                                linha[TableCSV.Actors.ordinal()].substring(1,linha[TableCSV.Actors.ordinal()].length()-1).split(",\\s|," ):
                                new String[]{linha[TableCSV.Actors.ordinal()]};

                        final String [] directors = linha[TableCSV.Director.ordinal()].startsWith("\"") ?
                                linha[TableCSV.Director.ordinal()].substring(1,linha[TableCSV.Director.ordinal()].length()-1).split(",\\s|," ):
                                new String[]{linha[TableCSV.Director.ordinal()]};

                        final String description = linha[TableCSV.Description.ordinal()].startsWith("\"") ?
                                linha[TableCSV.Description.ordinal()].substring(1,linha[TableCSV.Description.ordinal()].length()-1).split(",\\s|," )[0]:
                                linha[TableCSV.Description.ordinal()];

                        final BigDecimal revenue = BigDecimal.valueOf(Double.valueOf(linha[TableCSV.Revenue.ordinal()]));
                        final Integer runtime = Integer.valueOf(linha[TableCSV.Runtime.ordinal()]);
                        final Rating rating = new Rating(
                                Double.parseDouble(linha[TableCSV.Rating.ordinal()]),
                                Integer.parseInt(linha[TableCSV.Votes.ordinal()]));

                        final Double metascore = Double.valueOf(linha[TableCSV.Metascore.ordinal()]);
                        final String title = linha[TableCSV.Title.ordinal()];
                        final Integer year = Integer.valueOf(linha[TableCSV.Year.ordinal()]);
                                
                        // Builder para montar o objeto Filme

                        return new Movie(title,year,genre,actors,directors,description,actors,runtime,rating,revenue,metascore);
                })
                .collect(Collectors.toSet());
    }

    private void validateFiles(Path path) {
        if(Files.notExists(path))
            throw new RuntimeException("Arquivo inválido");
    }
}
