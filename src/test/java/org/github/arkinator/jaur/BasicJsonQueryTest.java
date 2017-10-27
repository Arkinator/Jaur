package org.github.arkinator.jaur;

import org.github.arkinator.jaur.data.JaurDataJsonObject;
import org.github.arkinator.jaur.data.JaurType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicJsonQueryTest {
    private Jaur jaur = Jaur.builder().build();
    private JaurDataJsonObject basicParsedJsonObject;

    @Before
    public void setUp() {
        basicParsedJsonObject = jaur.parseJson("{" +
                "\"field\":\"value\"," +
                "\"integer\":123," +
                "\"float\":1.23," +
                "\"boolean\":true," +
                "\"array\":[11,12,13]," +
                "\"object\":{\"foo\":\"bar\"}" +
                "}");
    }

    @Test
    public void basicQuery_shouldReturnOptional() {
        assertThat(basicParsedJsonObject.get("field").isPresent()).isTrue();
    }

    @Test
    public void invalidQuery_optionalShouldBeEmpty() {
        assertThat(basicParsedJsonObject.get("invalidQuery").isPresent()).isFalse();
    }

    @Test
    public void basicQuery_shouldReturnCorrectType() {
        assertThat(basicParsedJsonObject.get("field")).isOfAnyClassIn(JaurDataJsonObject.class);
    }

    @Test
    public void basicQuery_shouldReturnCorrectValue() {
        assertThat(basicParsedJsonObject.get("field").getValueAsString()).isEqualTo("value");
    }

    @Test
    public void queryObject_shouldReturnValue() {
        assertThat(basicParsedJsonObject.get("object").isPresent()).isEqualTo(true);
    }

    @Test
    public void queryObject_shouldReturnComplex() {
        assertThat(basicParsedJsonObject.get("object").getType()).isEqualTo(JaurType.OBJECT);
    }

    @Test
    public void queryObjectInObject() {
        assertThat(basicParsedJsonObject.get("object", "foo").getValueAsString()).isEqualTo("bar");
    }

    @Test
    public void queryObjectInObject_differentSyntax() {
        assertThat(basicParsedJsonObject.get("object.foo").getValueAsString()).isEqualTo("bar");
    }

    @Test
    public void queryArrayInObject() {
        assertThat(basicParsedJsonObject.get("array").getType()).isEqualTo(JaurType.ARRAY);
    }

    @Test
    public void queryArrayValue() {
        assertThat(basicParsedJsonObject.get("array.1").getValueAsDouble()).isEqualTo(12.);
    }
}
