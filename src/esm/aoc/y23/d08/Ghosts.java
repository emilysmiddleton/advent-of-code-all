package esm.aoc.y23.d08;

import esm.aoc.structures.collections.Circle;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Ghosts {

    private final Map<String, Node> nodes = new LinkedHashMap<>();
    private final Circle<Function<Node, Node>> instructions;

    public Ghosts(final List<Node> input, final List<String> instructions) {
        input.forEach(node -> nodes.put(node.name(), node));
        final Function<Node, Node> left = node -> nodes.get(node.left());
        final Function<Node, Node> right = node -> nodes.get(node.right());
        this.instructions = new Circle<>(instructions.stream().map(s -> "L".equals(s) ? left : right).toList());
    }

    public List<Node> getStartingNodes() {
        return nodes.values().stream().filter(node -> node.name().endsWith("A")).toList();
    }

    public int getWhenReachesEnd(final Node node) {
        Node current = node;
        int index = 0;
        while (true) {
            current = instructions.get(index).apply(current);
            index++;
            if (current.name().endsWith("Z")) {
                return index;
            }
        }
    }

    public Node getNode(final String name) {
        return nodes.get(name);
    }

}
