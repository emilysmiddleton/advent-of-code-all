package esm.aoc.y23.d10;

import esm.aoc.structures.grid.Coordinate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class MoveOutsideFunction implements Function<Coordinate, List<Coordinate>> {

    private final Set<Coordinate> visited = new LinkedHashSet<>();
    private final ExpandedTileGrid grid;

    public MoveOutsideFunction(final ExpandedTileGrid grid) {
        this.grid = grid;
    }

    @Override
    public List<Coordinate> apply(final Coordinate coordinate) {
        if (visited.contains(coordinate)) {
            return Collections.emptyList();
        }
        visited.add(coordinate);
        return grid.grid().getAdjacent(coordinate, false)
                   .entrySet()
                   .stream()
                   .filter(e -> ".".equals(e.getValue()) || "_".equals(e.getValue()))
                   .map(Map.Entry::getKey)
                   .toList();
    }

    public Set<Coordinate> getVisited() {
        return visited;
    }
}
