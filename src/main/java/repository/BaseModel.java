package repository;

import java.io.IOException;

public interface BaseModel<T> {
    T read() throws IOException;
    void write(T t);
}
