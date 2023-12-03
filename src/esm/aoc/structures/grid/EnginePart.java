package esm.aoc.structures.grid;

import java.util.LinkedHashMap;
import java.util.Map;

public class EnginePart {

    private final Map<Coordinate, String> adjacent = new LinkedHashMap<>();
    private String number = "";

    public void append(final String part, final Map<Coordinate, String> newAdjacent) {
        number += part;
        for (final var entry : newAdjacent.entrySet()) {
            if (!entry.getValue().matches("\\d")) {
                adjacent.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public boolean isNotEmpty() {
        return !number.isEmpty();
    }

    public boolean nextToSymbol() {
        return adjacent.values().stream().anyMatch(s -> !".".equals(s));
    }

    public long getNumber() {
        return Long.parseLong(number);
    }

    @Override
    public String toString() {
        return number + " " + adjacent.values();
    }

    public Map<Coordinate, String> getAdjacent() {
        return adjacent;
    }
}
