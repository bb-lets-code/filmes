package com.letscode.app.repository;

import java.io.IOException;

public interface BaseRepository<T, U> {
    T read() throws IOException;
    void write(T t, U u) throws IOException;
}
