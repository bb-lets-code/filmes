package com.letscode.app.repository;

import java.time.LocalDate;

public class Filme implements Comparable<Filme> {
    private LocalDate lancamento;
    

    public Filme(LocalDate lancamento) {
        this.lancamento = lancamento;
    }

    public LocalDate getLancamento() {
        return lancamento;
    }

    @Override
    public int compareTo(Filme filme) {
        return this.lancamento.compareTo(filme.getLancamento());
    }

    public String getTitulo() {
        return "Titulo";
    }

    @Override
    public String toString() {
        return "Filme [lancamento=" + lancamento + "]";
    }

}
