package esm.aoc.structures.tree;

import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface TreeNode<State> {

    State state();
    @NotNull
    List<TreeNode<State>> children();

}
