package esm.aoc.y24.d07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import esm.aoc.structures.collections.Utils;
import esm.aoc.structures.tree.TreeNode;

public class MathTreeNode implements TreeNode<Long> {

    private final boolean useConcat;
    private final long total;
    private final List<Long> list;

    public MathTreeNode(final boolean useConcat, long total, List<Long> list) {
        this.useConcat = useConcat;
        this.total = total;
        this.list = list;
    }

    @Override
    public Long state() {
        return total;
    }

    @Override
    public List<TreeNode<Long>> children() {
        if (isLeaf()) {
            return Collections.emptyList();
        }
        final var next = list.getFirst();
        final var nextList = Utils.removeFirst(list);
        final List<TreeNode<Long>> children = new ArrayList<>();
        children.add(new MathTreeNode(useConcat, total + next, nextList));
        children.add(new MathTreeNode(useConcat, total * next, nextList));
        if (useConcat) {
            children.add(new MathTreeNode(true, Long.parseLong(Long.toString(total) + Long.toString(next)), nextList));
        }
        return children;
    }

    public boolean isLeaf() {
        return list.isEmpty();
    }
}
