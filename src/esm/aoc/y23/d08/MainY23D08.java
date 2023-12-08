package esm.aoc.y23.d08;

import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Circle;
import java.util.regex.Pattern;

public class MainY23D08 {

    private static final Pattern PATTERN = Pattern.compile("(...) = \\((...), (...)\\)");
    public static void main(String[] args) {
        final var input = InputReader.readPattern("2023/day08_nodes.txt", PATTERN).stream().map(Node::parse).toList();
        final var lr = new Circle<>(InputReader.readSeparated("2023/day08_lr.txt", "").get(0));
        final var ghosts = new Ghosts(input, lr);
        final var start = ghosts.getNode("AAA");
        final var part1 = ghosts.getWhenReachesEnd(start);
        System.out.println("Part 1: " + part1);
        final var part2 = lr.size() * ghosts
                .getStartingNodes()
                .stream()
                .map(ghosts::getWhenReachesEnd)
                .mapToLong(n -> n / lr.size())
                .reduce((a, b) -> a * b)
                .orElseThrow();
        System.out.println("Part 2: " + part2);
    }

}

