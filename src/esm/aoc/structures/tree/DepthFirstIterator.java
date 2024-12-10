package esm.aoc.structures.tree;

import esm.aoc.utils.Utils;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.Consumer;

public class DepthFirstIterator<State> implements Iterator<TreeNode<State>> {

    private final Stack<TreeNode<State>> stack = new Stack<>();
    private final Consumer<State> leafCallback;

    public DepthFirstIterator(final TreeNode<State> root) {
        this(root, state -> {});
        stack.push(root);
    }

    public DepthFirstIterator(final TreeNode<State> root, Consumer<State> leafCallback) {
        stack.push(root);
        this.leafCallback = leafCallback;
    }

    public void iterate() {
        while (hasNext()) {
            next();
        }
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
        final var children = currentNode.children();
        if (children.isEmpty()) {
            leafCallback.accept(currentNode.state());
        }
        Utils.reverseConsume(children, stack::push);
        return currentNode;
    }
}
