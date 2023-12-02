package esm.aoc.structures.tree;

import esm.aoc.utils.Utils;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstIterator implements Iterator<TreeNode> {

    private final Queue<TreeNode> queue = new ArrayDeque<>();

    public BreadthFirstIterator(final TreeNode root) {
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public TreeNode next() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("hasNext false");
        }
        final TreeNode currentNode = queue.poll();
        queue.addAll(currentNode.getChildren());
        return currentNode;
    }
}
