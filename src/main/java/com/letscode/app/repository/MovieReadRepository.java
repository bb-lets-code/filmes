package com.letscode.app.repository;

import com.letscode.app.enums.TableCSV;
import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

import java.io.IOException;
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
                                    Integer.parseInt(linha[TableCSV.Votes.ordinal()]))
                            , null, 0

                    );
                })
                .collect(Collectors.toSet());
    }

    private void validateFiles(Path path) {
        if(Files.notExists(path))
            throw new RuntimeException("Arquivo inválido");
    }
}
