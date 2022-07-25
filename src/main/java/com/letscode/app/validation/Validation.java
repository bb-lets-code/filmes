package com.letscode.app.validation;

import java.nio.file.Path;

public interface Validation<T> {
    void validate(T t);
}
