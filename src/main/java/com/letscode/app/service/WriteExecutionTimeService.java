package com.letscode.app.service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteExecutionTimeService {
    public static String timeToString(LocalDateTime begin, LocalDateTime end){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SS");

        double executionTime = Duration.between(begin,end).getNano();

        String texto =
                "Inicio do processamento: " + formatter.format(begin)+
                "\nFim do Processamento: " + formatter.format(end)+
                "\nTempo de Execução em milissegundos: "+ executionTime/1_000_000.0 +
                "\nTempo de Execução em segundos: " + executionTime/1_000_000_000.0;
        return texto;
    }

    public static void writeTXT(String texto) throws IOException {
        FileWriter writter = new FileWriter("output_csv\\executionTimeInformation.txt");
        writter.write(texto);
        writter.close();
    }

}
