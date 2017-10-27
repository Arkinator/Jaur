package org.github.arkinator.jaur;

import java.util.ArrayList;
import java.util.List;

public class SelectorUtils {
    private static final String MATCH_POINTS_NOT_ENCLOSED_IN_SQUARE_BRACKETS = "[.](?![^\\[]*\\])";

    public static List<Object> getSplittedSelectorList(String... selectors) {
        List<Object> selectorList = new ArrayList<>();

        for (String selector : selectors) {
            for (String subSelector : selector.split(MATCH_POINTS_NOT_ENCLOSED_IN_SQUARE_BRACKETS)) {
                if (subSelector.startsWith("[") && subSelector.endsWith("]")) {
                    subSelector = subSelector.substring(1, subSelector.length() - 1);
                }
                selectorList.add(checkNumberAndConvertIfPossible(subSelector));
            }
        }
        return selectorList;
    }

    private static Object checkNumberAndConvertIfPossible(String subSelector) {
        try {
            return Integer.parseInt(subSelector);
        } catch (NumberFormatException e) {
            return subSelector;
        }
    }
}
