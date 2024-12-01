package esm.aoc.structures.tree;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LazyTreeNode<State> implements TreeNode<State> {

    private final State state;
    private final Function<State, List<State>> childFunction;
    private List<TreeNode<State>> children = null;

    public LazyTreeNode(Function<State, List<State>> childFunction, State state) {
        this.childFunction = childFunction;
        this.state = state;
    }

    @Override
    public List<TreeNode<State>> children() {
        if (children == null) {
            children = childFunction.apply(state)
                                    .stream()
                                    .map(s -> new LazyTreeNode<>(childFunction, s))
                                    .collect(Collectors.toList());
        }
        return children;
    }

    @Override
    public State state() {
        return state;
    }
}
