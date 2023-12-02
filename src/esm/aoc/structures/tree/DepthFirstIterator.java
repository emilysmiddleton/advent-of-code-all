package esm.aoc.structures.tree;

import esm.aoc.utils.Utils;
import java.util.Iterator;
import java.util.Stack;

public class DepthFirstIterator<State> implements Iterator<TreeNode<State>> {

    private final Stack<TreeNode<State>> stack = new Stack<>();

    public DepthFirstIterator(final TreeNode<State> root) {
        stack.push(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public TreeNode<State> next() {
        if (!hasNext()) {
            throw new IllegalStateException("hasNext false");
        }
        final TreeNode<State> currentNode = stack.pop();
        Utils.reverseConsume(currentNode.children(), stack::push);
        return currentNode;
    }
}
