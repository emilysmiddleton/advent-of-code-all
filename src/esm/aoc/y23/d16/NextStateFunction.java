package esm.aoc.y23.d16;

import esm.aoc.structures.grid.CharGrid;
import esm.aoc.structures.grid.Direction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

class NextStateFunction implements Function<State, List<State>> {

    private Set<State> seen = new LinkedHashSet<>();
    private final CharGrid grid;

    NextStateFunction(final CharGrid grid) {
        this.grid = grid;
    }

    public Set<State> getVisited() {
        return seen;
    }

    @Override
    public List<State> apply(final State state) {
        if (seen.contains(state)) {
            return Collections.emptyList();
        }
        seen.add(state);
        final List<State> nextStates = new ArrayList<>();
        final var nextPosition = state.position().move(state.direction());
        if (!grid.getMap().containsKey(nextPosition)) {
            return Collections.emptyList();
        }
        final var tile = grid.get(nextPosition);
        switch (tile) {
            case ".": {
                nextStates.add(new State(nextPosition, state.direction()));
                break;
            }
            case "|": {
                if (state.direction() == Direction.RIGHT || state.direction() == Direction.LEFT) {
                    nextStates.add(new State(nextPosition, Direction.UP));
                    nextStates.add(new State(nextPosition, Direction.DOWN));
                } else {
                    nextStates.add(new State(nextPosition, state.direction()));
                }
                break;
            }
            case "-": {
                if (state.direction() == Direction.UP || state.direction() == Direction.DOWN) {
                    nextStates.add(new State(nextPosition, Direction.LEFT));
                    nextStates.add(new State(nextPosition, Direction.RIGHT));
                } else {
                    nextStates.add(new State(nextPosition, state.direction()));
                }
                break;
            }
            case "/": {
                switch (state.direction()) {
                    case UP -> nextStates.add(new State(nextPosition, Direction.RIGHT));
                    case DOWN -> nextStates.add(new State(nextPosition, Direction.LEFT));
                    case LEFT -> nextStates.add(new State(nextPosition, Direction.DOWN));
                    case RIGHT -> nextStates.add(new State(nextPosition, Direction.UP));
                }
                break;
            }
            case "\\": {
                switch (state.direction()) {
                    case UP -> nextStates.add(new State(nextPosition, Direction.LEFT));
                    case DOWN -> nextStates.add(new State(nextPosition, Direction.RIGHT));
                    case LEFT -> nextStates.add(new State(nextPosition, Direction.UP));
                    case RIGHT -> nextStates.add(new State(nextPosition, Direction.DOWN));
                }
                break;
            }
        }
        return nextStates;
    }
}
