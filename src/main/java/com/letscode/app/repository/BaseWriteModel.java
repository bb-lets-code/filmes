package com.letscode.app.repository;

import java.io.IOException;
import java.nio.file.Path;

public interface BaseWriteModel<T> {
    void write(T t, Path path) throws IOException;
}
