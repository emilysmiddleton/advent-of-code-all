package esm.aoc.structures.tree;

import java.util.List;
import java.util.function.Function;

public interface NextStateFunction<State> extends Function<State, List<State>> {
}
