package com.letscode.app.service;
import java.io.IOException;
import java.time.LocalDateTime;

public class CloseService{
    public void closeApp(LocalDateTime begin) throws IOException {

        LocalDateTime end = LocalDateTime.now();

        WriteExecutionTimeService writeExecutionTimeService = new WriteExecutionTimeService();
        String texto = writeExecutionTimeService.timeToString(begin, end);
        writeExecutionTimeService.writeTXT(texto);

        System.out.println("Programa executado com sucesso!");
        System.out.println(texto);

    }
}