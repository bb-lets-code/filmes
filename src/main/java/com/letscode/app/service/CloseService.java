package com.letscode.app.service;
import java.io.IOException;
import java.time.LocalDateTime;
import com.letscode.app.service.WriteExecutionTimeService;

public class CloseService{
    public static void closeApp(LocalDateTime begin) throws IOException {

        LocalDateTime end = LocalDateTime.now();

        String texto = WriteExecutionTimeService.timeToString(begin, end);
        WriteExecutionTimeService.writeTXT(texto);

        System.out.println("Programa executado com sucesso!");
        System.out.println(texto);

    }
}