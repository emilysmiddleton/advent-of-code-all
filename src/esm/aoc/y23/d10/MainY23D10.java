package esm.aoc.y23.d10;

import esm.aoc.input.InputReader;
import esm.aoc.structures.tree.DepthFirstIterator;
import esm.aoc.structures.tree.LazyTreeNode;
import java.util.Map;
import java.util.stream.Collectors;

public class MainY23D10 {

    public static void main(String[] args) {
        final var grid = new TileGrid(InputReader.readGrid("2023/day10.txt", Tile::parseTile));
        final var loop = grid.findLoop();
        System.out.println("Part 1 " + loop.size() / 2);
        final var expanded = ExpandedTileGrid.expand(grid);
        final var moveOutsideFunction = new MoveOutsideFunction(expanded);
        final var startNode = new LazyTreeNode<>(moveOutsideFunction, expanded.getStart());
        final var iterator = new DepthFirstIterator<>(startNode);
        while (iterator.hasNext()) {
            iterator.next();
        }
        final var outside = moveOutsideFunction.getVisited();
        final var inside = expanded.grid().getMap().entrySet().stream()
                .filter(e -> ".".equals(e.getValue()))
                .map(Map.Entry::getKey)
                .filter(c -> !outside.contains(c))
                .collect(Collectors.toSet());
        System.out.println("Part 2 " + inside.size() / 9);
    }

}
