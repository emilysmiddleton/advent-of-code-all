package esm.aoc.structures.grid;

import java.util.Map;

/**
 * AOC's favourite grids, usually hashes and dots.
 */
public class CharGrid extends Grid<String> {
    public CharGrid(final Map<Coordinate, String> map) {
        super(map);
    }
}
