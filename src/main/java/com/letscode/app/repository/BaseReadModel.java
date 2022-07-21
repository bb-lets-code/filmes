package com.letscode.app.repository;

import java.io.IOException;
import java.nio.file.Path;

public interface BaseReadModel<T> {
    T read(Path path) throws IOException;
}
