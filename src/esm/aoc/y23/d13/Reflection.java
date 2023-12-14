package esm.aoc.y23.d13;

import esm.aoc.structures.grid.Axis;
import esm.aoc.structures.grid.CharGrid;

public record Reflection(CharGrid grid, Long line, Axis axis) {

    public long getValue() {
        if (axis == Axis.VERTICAL) {
            return line + 1;
        }
        if (axis == Axis.HORIZONTAL) {
            return (grid.getMaxY() - line) * 100;
        }
        throw new IllegalStateException();
    }

}
