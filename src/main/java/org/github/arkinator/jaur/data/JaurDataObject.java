package org.github.arkinator.jaur.data;

import java.util.Optional;

public interface JaurDataObject<T> {
    Optional<T> get(String... query);
}
