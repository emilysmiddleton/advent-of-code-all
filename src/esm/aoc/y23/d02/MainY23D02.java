package esm.aoc.y23.d02;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import java.util.List;
import java.util.stream.Collectors;

public class MainY23D02 {

    public static void main(String[] args) {
        final var input = InputReader.readSeparated("2023/day02.txt", ": ");
        final var games = input.stream().map(MainY23D02::readLine).toList();
        final var result1 = games.stream().filter(game -> game.possible(12, 13, 14))
                .mapToInt(Game::getId)
                .sum();
        System.out.println(result1);
        final var result2 = games.stream().mapToInt(Game::getPower).sum();
        System.out.println(result2);
    }

    private static Game readLine(final List<String> line) {
        final var groups = InputMapper.getSeparatedStrings(line.get(1), "; ")
                .stream()
                .map(MainY23D02::readThings)
                .map(Group::new)
                .collect(Collectors.toList());
        final var id = Integer.parseInt(line.get(0).split(" ")[1]);
        return new Game(id, groups);
    }

    private static List<Cube> readThings(final String input) {
        return InputMapper.getSeparatedStrings(input, ", ")
                          .stream()
                          .map(MainY23D02::readThing).collect(Collectors.toList());
    }

    private static Cube readThing(final String input) {
        final var split = input.split(" ");
        return new Cube(Integer.parseInt(split[0]), split[1]);
    }
}
