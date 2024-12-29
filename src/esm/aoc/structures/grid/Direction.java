package esm.aoc.structures.grid;

import java.util.List;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UP_LEFT,
    UP_RIGHT,
    DOWN_LEFT,
    DOWN_RIGHT;

    public static List<Direction> getValues(final boolean includeDiagonal) {
        if (includeDiagonal) {
            return List.of(values());
        }
        return List.of(UP, DOWN, LEFT, RIGHT);
    }

    public Direction opposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case UP_LEFT -> DOWN_RIGHT;
            case UP_RIGHT -> DOWN_LEFT;
            case DOWN_LEFT -> UP_RIGHT;
            case DOWN_RIGHT -> UP_LEFT;
        };
    }

    public Direction clockwise() {
        return switch (this) {
            case UP -> UP_RIGHT;
            case UP_RIGHT -> RIGHT;
            case RIGHT -> DOWN_RIGHT;
            case DOWN_RIGHT -> DOWN;
            case DOWN -> DOWN_LEFT;
            case DOWN_LEFT -> LEFT;
            case LEFT -> UP_LEFT;
            case UP_LEFT -> UP;
        };
    }

    public Direction anticlockwise() {
        return switch (this) {
            case UP -> UP_LEFT;
            case UP_LEFT -> LEFT;
            case LEFT -> DOWN_LEFT;
            case DOWN_LEFT -> DOWN;
            case DOWN -> DOWN_RIGHT;
            case DOWN_RIGHT -> RIGHT;
            case RIGHT -> UP_RIGHT;
            case UP_RIGHT -> UP;
        };
    }

    public static Direction parse(final String arrow) {
        return switch (arrow) {
            case ">" -> RIGHT;
            case "<" -> LEFT;
            case "^" -> UP;
            case "v" -> DOWN;
            default -> throw new IllegalArgumentException(arrow);
        };
    }

}
