package esm.aoc.input;

import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Grid;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    public static List<String> parseMatches(final String line, final Pattern pattern) {
        final var matcher = pattern.matcher(line);
        final var result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group());
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

    public static CharGrid readCharGrid(final List<String> lines) {
        final Grid<String> grid = readGrid(lines, s -> s);
        return new CharGrid(grid.getMap());
    }

    public static <T> Grid<T> readGrid(final List<String> lines, final Function<String, T> function) {
        final Map<Coordinate, T> map = new LinkedHashMap<>();
        for (int y = 0; y < lines.size(); y++) {
            addToMap(map, lines.size() - y - 1, lines.get(y), function);
        }
        return new Grid<>(map);
    }

    private static <T> void addToMap(final Map<Coordinate, T> map, final int y, final String line, final Function<String, T> function) {
        final var bits = line.split("");
        for (int x = 0; x < bits.length; x++) {
            final var bit = bits[x];
            final var transformed = function.apply(bit);
            if (transformed != null) {
                map.put(new Coordinate(x, y), transformed);
            }
        }
    }
}
