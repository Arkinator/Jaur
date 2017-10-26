package org.github.arkinator.jaur.data;

import lombok.Data;

@Data
public class JaurValue<T> {
    private T value;
}
