package com.letscode.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.letscode.app.repository.Filme;
import com.letscode.app.repository.Repository;

public class MelhoresPorAno {
    Set<Filme> filmes;
    
    // TODO: interface para o service
    public MelhoresPorAno(Set<Filme> filmes) {
        this.filmes = filmes;
    }

    public void execute() {
        // Visualizar os filmes mais bem avaliados por ano
        Map<LocalDate, List<Filme>> filmes = this.filmes.stream()
            .sorted()
            .limit(50)
            .collect(
                Collectors.groupingBy(Filme::getLancamento)
                // TODO:Ordenar por ano
            );

        // Foreach para cada ano
        //  whiteMovieService.whiteFile("getYear.csv", filmes);
    }

    public static void main(String[] args) {

        Repository repository = new Repository();
        Set<Filme> filmes = repository.getFilmes();
        MelhoresPorAno melhoresPorAno = new MelhoresPorAno(filmes);
        melhoresPorAno.execute();
    }
}
