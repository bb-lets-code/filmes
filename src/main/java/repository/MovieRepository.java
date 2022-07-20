package repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.letscode.app.enums.TableCSV;

public class MovieRepository implements BaseModel<Set<String>> {


    @Override
    public Set<String> read() throws IOException {
        final Path path = Path.of("movies1.csv");
       if(!Files.exists(path))
            return null;

        // Stream que lê o arquivo o divide os 12 campos do arquivo de entrada
        // (é preciso pensar os gêneros do filme entre aspas
        
        Set<String> retorno = Files.lines(path, StandardCharsets.UTF_8)
                .map(m ->{
                    String[] linha = m.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    
                    
                    String [] genre = linha[TableCSV.Genre.ordinal()].startsWith("\"") ? 
                        linha[TableCSV.Genre.ordinal()].substring(1,linha[TableCSV.Genre.ordinal()].length()-1).split(","): new String[]{linha[TableCSV.Genre.ordinal()]};
                        
                    String [] actors = linha[TableCSV.Actors.ordinal()].startsWith("\"") ? 
                        linha[TableCSV.Actors.ordinal()].substring(1,linha[TableCSV.Actors.ordinal()].length()-1).split(",\\s|," ): new String[]{linha[TableCSV.Actors.ordinal()]};

                    
                    
                    
                    // Builder para montar o objeto Filme
                    return m;
                })
                
                .collect(Collectors.toSet());
        return retorno;
         
    }

    @Override
    public void write(Set<String> stringSet) {

    }
}
