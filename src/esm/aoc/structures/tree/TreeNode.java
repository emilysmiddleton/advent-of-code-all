package esm.aoc.structures.tree;

import com.sun.source.tree.Tree;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public interface TreeNode<State> {

    State getState();
    @NotNull
    List<TreeNode<State>> getChildren();

}
