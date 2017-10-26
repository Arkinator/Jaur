package org.github.arkinator.jaur;

import org.junit.Test;

public class BasicJsonParserTest {
    private Jaur jaur = Jaur.builder().build();

    @Test
    public void basicJson_shouldParseWithoutError() {
        jaur.parseJson("{\"field\":\"value\"}");
    }
}
