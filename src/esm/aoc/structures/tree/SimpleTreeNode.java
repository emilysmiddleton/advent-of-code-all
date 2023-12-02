package esm.aoc.structures.tree;

import java.util.List;

public class SimpleTreeNode<State> implements TreeNode<State> {

    private final State state;
    private final List<TreeNode<State>> children;

    public SimpleTreeNode(final State state, final List<TreeNode<State>> children) {
        this.state = state;
        this.children = children;
    }

    @Override
    public List<TreeNode<State>> getChildren() {
        return children;
    }

    @Override
    public State getState() {
        return state;
    }
}
