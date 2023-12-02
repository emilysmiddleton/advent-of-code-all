package esm.aoc.y15.d22;

import esm.aoc.structures.tree.NextStateFunction;
import java.util.List;
import java.util.stream.Collectors;

public class GameStateFunction implements NextStateFunction<GameState> {
    private int min = Integer.MAX_VALUE;

    @Override
    public List<GameState> apply(final GameState state) {
        if (state.isBossDead()) {
            min = Math.min(min, state.getManaTotal());
            System.out.println(min);
        }
        return state.getNextStates()
                    .stream()
                    .filter(s -> s.getManaTotal() <= min)
                    .collect(Collectors.toList());
    }
}
