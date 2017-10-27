package org.github.arkinator.jaur;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectorUtilsTest {
    @Test
    public void shouldSelectTwoSelectorsSeperatedByDot() {
        assertThat(SelectorUtils.getSplittedSelectorList("foo.bar"))
                .containsExactly("foo", "bar");
    }

    @Test
    public void shouldGenerateSingleSelectorIfCapsulatedBySquareBrackets() {
        assertThat(SelectorUtils.getSplittedSelectorList("[foobar]"))
                .containsExactly("foobar");
    }

    @Test
    public void shouldGenerateSingleSelectorIfCapsulatedBySquareBrackets_specialCharactersPrevalent() {
        String selector = "foobar;:_,.-!§$%&/())))))(/&%$§)";
        assertThat(SelectorUtils.getSplittedSelectorList("[" + selector + "]"))
                .containsExactly(selector);
    }

    @Test
    public void shouldSeperateMultipleSelectors() {
        assertThat(SelectorUtils.getSplittedSelectorList("[foo].bar.[foo.bar]"))
                .containsExactly("foo", "bar", "foo.bar");
    }

    @Test
    public void shouldConvertNumbersToInteger() {
        assertThat(SelectorUtils.getSplittedSelectorList("1"))
                .containsExactly(1);
    }

    @Test
    public void shouldConvertEscapedNumbersToInteger() {
        assertThat(SelectorUtils.getSplittedSelectorList("[1]"))
                .containsExactly(1);
    }

    @Test
    public void shouldHandleMixedSelectors() {
        assertThat(SelectorUtils.getSplittedSelectorList("foo.[1].bar.2"))
                .containsExactly("foo", 1, "bar", 2);
    }
}