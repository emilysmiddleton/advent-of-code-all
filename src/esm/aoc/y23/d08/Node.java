package esm.aoc.y23.d08;

import java.util.List;

public record Node(String name, String left, String right) {

    public static Node parse(final List<String> input) {
        return new Node(input.get(0), input.get(1), input.get(2));
    }
}
