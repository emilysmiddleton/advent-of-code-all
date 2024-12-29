package esm.aoc.structures.grid.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import esm.aoc.structures.grid.Direction;
import esm.aoc.structures.tree.TreeNode;

public record GridTreeNode(GridState state) implements TreeNode<GridState> {

    @Override
    public List<TreeNode<GridState>> children() {
        if (state().isEnd()) {
            return Collections.emptyList();
        }
        final var next = Direction.getValues(false).stream()
                .map(state::getNextState)
                .filter(GridState::isValid)
                .map(GridTreeNode::new)
                .toList();
        return new ArrayList<>(next);
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
