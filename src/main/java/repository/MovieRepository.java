package repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieRepository implements BaseModel<Set<String>> {

    @Override
    public Set<String> read() throws IOException {
        final Path path = Path.of("movies1.csv");
//        if(!Files.exists(path))
            return null;

        // Stream que lê o arquivo o divide os 12 campos do arquivo de entrada
        // (é preciso pensar os gêneros do filme entre aspas
        /*
        return Files.lines(path, StandardCharsets.UTF_8)
                .map(m -> m.split(","))
                .map(s -> new Movie(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11]))
                .collect(Collectors.toSet());

         */
    }

    @Override
    public void write(Set<String> stringSet) {

    }
}
