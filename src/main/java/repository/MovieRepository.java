package repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.letscode.app.enums.TableCSV;
import com.letscode.app.model.Movie;
import com.letscode.app.model.Rating;

public class MovieRepository implements BaseModel<Set<Movie>> {


    @Override
    public Set<Movie> read() throws IOException {
        final Path path = Path.of("movies1.csv");
       if(!Files.exists(path))
            return null;

        // Stream que lê o arquivo o divide os 12 campos do arquivo de entrada
        // (é preciso pensar os gêneros do filme entre aspas
        
        Set<Movie> retorno = Files.lines(path, StandardCharsets.UTF_8)
                .map(m ->{
                    String[] linha = m.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                    
                    
                    String [] genre = linha[TableCSV.Genre.ordinal()].startsWith("\"") ? 
                        linha[TableCSV.Genre.ordinal()].substring(1,linha[TableCSV.Genre.ordinal()].length()-1).split(","): new String[]{linha[TableCSV.Genre.ordinal()]};
                        
                    String [] actors = linha[TableCSV.Actors.ordinal()].startsWith("\"") ? 
                        linha[TableCSV.Actors.ordinal()].substring(1,linha[TableCSV.Actors.ordinal()].length()-1).split(",\\s|," ): new String[]{linha[TableCSV.Actors.ordinal()]};
                    String [] directors = linha[TableCSV.Director.ordinal()].startsWith("\"") ? 
                        linha[TableCSV.Director.ordinal()].substring(1,linha[TableCSV.Director.ordinal()].length()-1).split(",\\s|," ): new String[]{linha[TableCSV.Director.ordinal()]};
                    
                    
                    
                    // Builder para montar o objeto Filme
                    return new Movie(
                        linha[TableCSV.Title.ordinal()],
                       Integer.valueOf(linha[TableCSV.Year.ordinal()]), 
                       genre, 
                       actors, 
                       directors, 
                       linha[TableCSV.Description.ordinal()]
                       , actors, 0, 
                       new Rating(Double.valueOf(linha[TableCSV.Rating.ordinal()]), Integer.valueOf(linha[TableCSV.Votes.ordinal()]))
                       , null, 0
                        
                    );
                })
                
                .collect(Collectors.toSet());
        return retorno;
         
    }

    @Override
    public void write(Set<Movie> stringSet) {
        return ;
    }
}
