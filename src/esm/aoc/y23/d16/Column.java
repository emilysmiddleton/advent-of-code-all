package esm.aoc.y23.d16;

import java.util.List;

public record Column(String table, String name, String type, String index) {

    public static Column parse(final List<String> parts) {
        return new Column(parts.get(0), parts.get(1), parts.get(2), parts.get(3).replace("\"\"", ""));
    }
}
