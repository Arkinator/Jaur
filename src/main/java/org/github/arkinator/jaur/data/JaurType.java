package org.github.arkinator.jaur.data;

public enum JaurType {
    NULL,
    INVALID,

    STRING,
    NUMBER,
    BOOLEAN,

    ARRAY,
    OBJECT;

    public boolean isComplex() {
        switch (this) {
            case OBJECT:
            case ARRAY:
                return true;
            default:
                return false;
        }
    }

    public boolean isData() {
        switch (this) {
            case NUMBER:
            case STRING:
            case BOOLEAN:
                return true;
            default:
                return false;
        }
    }
}
