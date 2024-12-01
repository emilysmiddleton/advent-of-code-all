package esm.aoc.structures.tree;

import java.util.List;

public interface TreeNode<State> {

    State state();
    List<TreeNode<State>> children();

}
