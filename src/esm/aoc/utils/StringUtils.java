package esm.aoc.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StringUtils {

    public static List<String> splitByLength(final String input, final int length) {
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i += length) {
            result.add(input.substring(i, Math.min(i + length, input.length())));
        }
        return result;
    }

}
