package esm.aoc.y24.d16;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import esm.aoc.structures.collections.Pair;
import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Coordinate;
import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.tree.TreeNode;

public class RaceNode implements TreeNode<RaceNode.State> {

    private final Map<Pair<Coordinate, Direction>, Long> visited;
    private final State state;
    private final CharGrid grid;

    public RaceNode(final Map<Pair<Coordinate, Direction>, Long> visited, final CharGrid grid, final State state) {
        this.visited = visited;
        this.state = state;
        this.grid = grid;
    }

    @Override
    public State state() {
        return state;
    }

    @Override
    public List<TreeNode<State>> children() {
        if ("E".equals(grid.get(state.position))) {
            return Collections.emptyList();
        }
        List<TreeNode<State>> children = new ArrayList<>();
        final var clockwise = new Pair<>(state.position, state.direction.clockwise().clockwise());
        final var anticlockwise = new Pair<>(state.position, clockwise.right().opposite());
        final var move = new Pair<>(state.position.move(state.direction), state.direction);
        addNext(children, clockwise, 1000);
        addNext(children, anticlockwise, 1000);
        if (!"#".equals(grid.get(move.left()))) {
            addNext(children, move, 1);
        }
        return children;
    }

    private void addNext(final List<TreeNode<State>> children, final Pair<Coordinate, Direction> next, long scoreIncrease) {
        final var nextScore = state.score + scoreIncrease;
        if (visited.containsKey(next)) {
            final var previous = visited.get(next);
            if (previous <= nextScore) {
                return;
            }
        }
        visited.put(next, nextScore);
        children.add(new RaceNode(visited, grid, new State(next.left(), next.right(), nextScore)));
    }

    public record State(Coordinate position, Direction direction, long score) {}
}
