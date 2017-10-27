package org.github.arkinator.jaur;

import org.github.arkinator.jaur.data.JaurType;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonValueTest {
    private Jaur jaur = Jaur.builder().build();

    @Test
    public void stringValue_shouldBeData() {
        assertThat(jaur.parseJson("\"value\"")
                .isData()).isTrue();
    }

    @Test
    public void stringValue_shouldBeStringType() {
        assertThat(jaur.parseJson("\"value\"")
                .getType()).isEqualTo(JaurType.STRING);
    }

    @Test
    public void intValue_shouldBeNumberType() {
        assertThat(jaur.parseJson("3")
                .getType()).isEqualTo(JaurType.NUMBER);
    }

    @Test
    public void floatValue_shouldBeNumberType() {
        assertThat(jaur.parseJson("-3.456789")
                .getType()).isEqualTo(JaurType.NUMBER);
    }

    @Test
    public void floatValue_queryNumberValue_shouldReturnCorrectValue() {
        assertThat(jaur.parseJson("-3.456789")
                .getValueAsDouble()).isEqualTo(-3.456789);
    }

    @Test
    public void stringValue_queryNumberValue_shouldReturnCorrectValue() {
        assertThat(jaur.parseJson("\"-3.456789\"")
                .getValueAsDouble()).isEqualTo(-3.456789);
    }

    @Test
    public void booleanValue_shouldBeBooleanType() {
        assertThat(jaur.parseJson("true")
                .getType()).isEqualTo(JaurType.BOOLEAN);
    }

    @Test
    public void booleanValue_queryBooleanValue_shouldReturnCorrectValue() {
        assertThat(jaur.parseJson("true")
                .getValueAsBoolean()).isEqualTo(true);
    }

    @Test
    public void stringValue_queryBooleanValue_shouldReturnCorrectValue() {
        assertThat(jaur.parseJson("\"true\"")
                .getValueAsBoolean()).isEqualTo(true);
    }

    @Test
    public void stringValue_queryStringValue_shouldReturnCorrectValue() {
        assertThat(jaur.parseJson("\"foobar\"")
                .getValueAsString()).isEqualTo("foobar");
    }

    @Test
    public void floatValue_queryStringValue_shouldReturnCorrectValue() {
        assertThat(jaur.parseJson("3.14159")
                .getValueAsString()).isEqualTo("3.14159");
    }
}
