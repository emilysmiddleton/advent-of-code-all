package esm.aoc.input;

import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Grid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputReader {


    public static List<List<String>> readPattern(final String resourceName, final Pattern pattern) {
        return FileReader.readStrings(resourceName).stream().map(s -> InputMapper.matchPattern(s, pattern)).collect(Collectors.toList());
    }


    public static List<String> readStrings(final String resourceName) {
        return FileReader.readStrings(resourceName);
    }

    public static List<Integer> readIntegers(final String resourceName) {
        return InputMapper.getIntegerList(FileReader.readStrings(resourceName));
    }

    public static List<Long> readLongs(final String resourceName) {
        return InputMapper.getLongList(FileReader.readStrings(resourceName));
    }

    public static List<List<String>> readSeparated(final String resourceName, final String separator) {
        return readSeparated(resourceName, separator, s -> s);
    }

    public static List<List<String>> readSeparated(final String resourceName, final String separator, final Function<String, String> prepareFunction) {
        return FileReader.readStrings(resourceName)
                         .stream()
                         .map(prepareFunction)
                         .map(line -> InputMapper.getSeparatedStrings(line, separator))
                         .collect(Collectors.toList());
    }

    public static List<List<String>> readSpaceSeparated(final String resourceName) {
        return readSeparated(resourceName, " ");
    }

    public static <T> List<T> readGroups(final String resourceName, final Function<List<String>, T> function) {
        return FileReader.readGroups(resourceName).stream().map(function).collect(Collectors.toList());
    }

    public static <T> List<T> readSpaceSeparated(final String resourceName, final Function<List<String>, T> function) {
        return readSpaceSeparated(resourceName).stream().map(function).collect(Collectors.toList());
    }

    public static <T> Grid<T> readGrid(final String resourceName, final Function<String, T> function) {
        final var input = InputReader.readStrings(resourceName);
        final Map<Coordinate, T> map = new LinkedHashMap<>();
        for (int y = 0; y < input.size(); y++) {
            addToMap(map, input.size() - y - 1, input.get(y), function);
        }
        return new Grid<>(map);
    }

    private static <T> void addToMap(final Map<Coordinate, T> map, final int y, final String line, final Function<String, T> function) {
        final var bits = line.split("");
        for (int x = 0; x < bits.length; x++) {
            final var bit = bits[x];
            map.put(new Coordinate(x, y), function.apply(bit));
        }
    }

}
