package com.letscode.app.service;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.letscode.app.repository.ProcessingTimeRepository;

public class ProcessingTimeService {
    private static LocalDateTime startTime;
    private static LocalDateTime endTime;
    public static String result = "";
    
    private static long milliseconds;
    
    public static void start() {
        // Format localdate
        
        
        startTime = LocalDateTime.now();
    }
    
    public static void end() throws IOException {
        endTime = LocalDateTime.now();
        result = getProcessingTime();
        ProcessingTimeRepository repository = new ProcessingTimeRepository();
        String file = "output_csv\\" + "processingTime" + ".txt";
        repository.write(result, Path.of( file));
    }
    
    
    private static String getProcessingTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS", Locale.getDefault());
        ZoneOffset offset = ZoneOffset.of("+03:00");
        long milliseconds = endTime.toInstant(offset).toEpochMilli() - startTime.toInstant(offset).toEpochMilli();
        long seconds = (endTime.toInstant(offset).toEpochMilli() - startTime.toInstant(offset).toEpochMilli()) / 1000;
        StringBuilder sb = new StringBuilder();

        
        sb.append("Inicio processamento: ");
        sb.append(startTime.format(formatter).toString());
        sb.append("\nFim processamento: ");
        sb.append(endTime.format(formatter).toString());
        sb.append("\nTempo em milisegundos: ");
        sb.append(milliseconds);
        sb.append("\nTempo em segundos: ");
        sb.append(seconds);

        result = sb.toString();
        return sb.toString();
    }
}