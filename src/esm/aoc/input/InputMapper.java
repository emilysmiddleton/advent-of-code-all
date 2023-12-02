package esm.aoc.input;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputMapper {

    public static List<String> matchPattern(final String line, final Pattern pattern) {
        final var matcher = pattern.matcher(line);
        final var result = new ArrayList<String>();
        if (matcher.matches()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result.add(matcher.group(i));
            }
        }
        return result;
    }

    public static List<Integer> getIntegerList(final List<String> lines) {
        return lines.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<Long> getLongList(final List<String> lines) {
        return lines.stream().map(Long::parseLong).collect(Collectors.toList());
    }

    public static List<List<Integer>> getIntegerGroup(final List<List<String>> groups) {
        return groups.stream().map(InputMapper::getIntegerList).collect(Collectors.toList());
    }

    public static List<String> getSeparatedStrings(final String string, final String separator) {
        return List.of(string.split(separator));
    }
}
