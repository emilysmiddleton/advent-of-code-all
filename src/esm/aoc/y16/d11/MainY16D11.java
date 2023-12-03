package esm.aoc.y16.d11;

import esm.aoc.input.InputReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MainY16D11 {

    public static void main(String[] args) {
        final var strings = InputReader.readSeparated("2016/day11.txt", ", ", MainY16D11::sanitise);
        for (final var s : strings) {
            System.out.println(s);
        }
    }

    private static String sanitise(final String line) {
        return line.replace("The first floor contains ", "")
                   .replace("The second floor contains ", "")
                   .replace("The third floor contains ", "")
                   .replace("The fourth floor contains ", "")
                   .replace("a ", "")
                   .replace("-compatible microchip", " microchip")
                   .replace(".", "")
                   .replace("nothing relevant", "")
                   .replace(", and", ",")
                   .replace(" and", ",")
                   .toUpperCase();
    }

    private static List<Thing> parse(final String line) {
        if (line.isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(line.split(", ")).map(s -> {
            final var split = s.split(" ");
            return new Thing(Type.valueOf(split[1]), Material.valueOf(split[0]));
        }).collect(Collectors.toList());
    }
}
