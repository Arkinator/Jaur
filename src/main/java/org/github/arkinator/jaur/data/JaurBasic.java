package org.github.arkinator.jaur.data;

import java.util.function.Consumer;

public abstract class JaurBasic<T extends JaurBasic> {
    public static final String WILDCARD_SELECTOR = "\\*";

    private final JaurType type;

    public JaurBasic(JaurType type) {
        this.type = type;
    }

    public T ifComplex(Consumer<T> consumer) {
        if (type.isComplex()) {
            consumer.accept((T) this);
            return getVoidObject();
        } else {
            return (T) this;
        }
    }

    abstract T getVoidObject();

    public T ifData(Consumer<T> consumer) {
        if (type.isData()) {
            consumer.accept((T) this);
            return getVoidObject();
        } else {
            return (T) this;
        }
    }

    public boolean isData() {
        return type.isData();
    }

    public JaurType getType() {
        return type;
    }

    public abstract double getValueAsDouble();

    public abstract boolean getValueAsBoolean();

    public abstract String getValueAsString();

    public abstract boolean isPresent();
}
