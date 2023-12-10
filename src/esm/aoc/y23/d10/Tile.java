package esm.aoc.y23.d10;

import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.Grid;
import java.util.List;
import static esm.aoc.input.InputMapper.readGrid;

public record Tile(Grid<String> tile, String type) {

    public static Tile parseTile(final String type) {
        return new Tile(parseGrid(type), type);
    }

    private static Grid<String> parseGrid(final String type) {
        return switch (type) {
            case "." -> readGrid(List.of(
                    "___",
                    "___",
                    "___"),  s -> s);
            case "|" -> readGrid(List.of(
                    "_*_",
                    "_*_",
                    "_*_"),  s -> s);
            case "-" -> readGrid(List.of(
                    "___",
                    "***",
                    "___"),  s -> s);
            case "7" -> readGrid(List.of(
                    "___",
                    "**_",
                    "_*_"),  s -> s);
            case "F" -> readGrid(List.of(
                    "___",
                    "_**",
                    "_*_"),  s -> s);
            case "J" -> readGrid(List.of(
                    "_*_",
                    "**_",
                    "___"),  s -> s);
            case "L" -> readGrid(List.of(
                    "_*_",
                    "_**",
                    "___"),  s -> s);
            case "S" -> readGrid(List.of(
                    "_*_",
                    "***",
                    "_*_"),  s -> s);
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    public boolean connectsAtEdge(final Direction direction) {
        return switch (direction) {
            case LEFT ->  "*".equals(tile.get(0, 1));
            case RIGHT ->  "*".equals(tile.get(2, 1));
            case UP ->  "*".equals(tile.get(1, 2));
            case DOWN ->  "*".equals(tile.get(1, 0));
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    @Override
    public String toString() {
        return type;
    }
}
