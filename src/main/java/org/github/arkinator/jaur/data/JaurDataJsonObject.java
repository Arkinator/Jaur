package org.github.arkinator.jaur.data;

import com.jsoniter.any.Any;
import lombok.Builder;
import lombok.Data;
import org.github.arkinator.jaur.SelectorUtils;

import java.util.function.Consumer;

@Data
@Builder
public class JaurDataJsonObject extends JaurBasic<JaurDataJsonObject> {
    private static final JaurDataJsonObject VOID_OBJECT = new JaurJsonVoidObject();
    private Any value;

    public JaurDataJsonObject(Any value) {
        super(computeType(value));
        this.value = value;
    }

    private static JaurType computeType(Any value) {
        if (value == null) {
            return JaurType.INVALID;
        }
        switch (value.valueType()) {
            case OBJECT:
                return JaurType.OBJECT;
            case ARRAY:
                return JaurType.ARRAY;
            case NULL:
                return JaurType.NULL;
            case NUMBER:
                return JaurType.NUMBER;
            case STRING:
                return JaurType.STRING;
            case BOOLEAN:
                return JaurType.BOOLEAN;
            case INVALID:
                return JaurType.INVALID;
            default:
                throw new RuntimeException("Should never happen");
        }
    }

    public JaurBasic get(String... query) {
        return capsulateReturnValue(value.get(SelectorUtils.getSplittedSelectorList(query).toArray()));
    }

    private JaurBasic capsulateReturnValue(Any any) {
        switch (any.valueType()) {
            case NULL:
            case INVALID:
                return getVoidObject();
            default:
                return new JaurDataJsonObject(any);
        }
    }

    @Override
    protected JaurDataJsonObject getVoidObject() {
        return VOID_OBJECT;
    }

    public double getValueAsDouble() {
        return value.toDouble();
    }

    public boolean getValueAsBoolean() {
        return value.toBoolean();
    }

    public String getValueAsString() {
        return value.toString();
    }

    @Override
    public boolean isPresent() {
        return true;
    }

    private static class JaurJsonVoidObject extends JaurDataJsonObject {
        public JaurJsonVoidObject() {
            super(null);
        }

        @Override
        public JaurDataJsonObject ifComplex(Consumer<JaurDataJsonObject> consumer) {
            return this;
        }

        @Override
        public JaurDataJsonObject ifData(Consumer<JaurDataJsonObject> consumer) {
            return this;
        }

        @Override
        protected JaurDataJsonObject getVoidObject() {
            return this;
        }

        @Override
        public boolean isPresent() {
            return false;
        }
    }
}
