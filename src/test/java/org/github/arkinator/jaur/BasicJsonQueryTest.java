package org.github.arkinator.jaur;

import org.github.arkinator.jaur.data.JaurValue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BasicJsonQueryTest {
    private Jaur jaur = Jaur.builder().build();

    @Test
    public void basicQuery_shouldReturnOptional() {
        assertThat(jaur
                .parseJson("{\"field\":\"value\"}")
                .get("field").isPresent()).isTrue();
    }

    @Test
    public void basicQuery_shouldFindCorrectValue() {
        assertThat(jaur
                .parseJson("{\"field\":\"foobar\"}")
                .get("field").get())
        .isOfAnyClassIn(JaurValue.class)
        .hasFieldOrPropertyWithValue("value", "foobar");
    }
}
