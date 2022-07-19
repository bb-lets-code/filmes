package com.letscode.app.repository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Repository {
    private Set<Filme> filmes = new HashSet<>();

    public Repository(){
        for (int i = 0; i < 10; i++) {
            filmes.add(new Filme(LocalDate.of(2000 + i, 1, 1)));
        }
        for (int i = 0; i < 10; i++) {
            filmes.add(new Filme(LocalDate.of(2000, 1, 1)));
        }
    }

    public Set<Filme> getFilmes() {
        return filmes;
    }

}
