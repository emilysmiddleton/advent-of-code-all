package esm.aoc.structures.tree;

import java.util.List;

public record SimpleTreeNode<State>(State state, List<TreeNode<State>> children) implements TreeNode<State> {

}
