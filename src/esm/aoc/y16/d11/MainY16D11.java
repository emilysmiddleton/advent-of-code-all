package esm.aoc.y16.d11;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Multimap;
import java.util.List;

public class MainY16D11 {

    public static void main(String[] args) {
        final var strings = InputReader.readSeparated("2016/day11.txt", ", ", MainY16D11::sanitise);
        final Multimap<Integer, List<Item>> items = new Multimap<>();
        for (int i = 0; i < strings.size(); i++) {
            items.add(i + 1, strings.get(i).stream().filter(s -> !s.isEmpty()).map(Item::parse).toList());
        }
        System.out.println(items);
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

}
