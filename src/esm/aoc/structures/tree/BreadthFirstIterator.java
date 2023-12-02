package esm.aoc.structures.tree;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

public class BreadthFirstIterator<T> implements Iterator<TreeNode<T>> {

    private final Queue<TreeNode<T>> queue = new ArrayDeque<>();

    public BreadthFirstIterator(final TreeNode<T> root) {
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public TreeNode<T> next() {
        if (queue.isEmpty()) {
            throw new IllegalStateException("hasNext false");
        }
        final TreeNode<T> currentNode = queue.poll();
        queue.addAll(currentNode.children());
        return currentNode;
    }
}
