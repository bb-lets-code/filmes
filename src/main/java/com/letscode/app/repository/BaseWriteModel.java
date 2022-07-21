package com.letscode.app.repository;

import java.io.IOException;

public interface BaseWriteModel<T> {
    void write(T t) throws IOException;
}
