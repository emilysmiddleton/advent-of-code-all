package esm.aoc.y24.d10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.grid.Grid;
import esm.aoc.structures.tree.TreeNode;

public class MapNode implements TreeNode<Coordinate> {

    private final Grid<Integer> grid;
    private final Coordinate current;

    public MapNode(final Grid<Integer> grid, Coordinate current) {
        this.grid = grid;
        this.current = current;
    }

    @Override
    public Coordinate state() {
        return current;
    }

    @Override
    public List<TreeNode<Coordinate>> children() {
        if (isLeaf()) {
            return Collections.emptyList();
        }
        final var children = Direction.getValues(false).stream()
                .map(current::move)
                .filter(c -> grid.contains(c) && grid.get(c).equals(grid.get(current) + 1))
                .map(c -> new MapNode(grid, c))
                .toList();
        return new ArrayList<>(children);
    }

    public boolean isLeaf() {
        return grid.get(current).equals(9);
    }
}
