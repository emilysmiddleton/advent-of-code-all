package esm.aoc.y24.d07;

import java.util.List;

import esm.aoc.input.InputMapper;
import esm.aoc.input.InputReader;
import esm.aoc.structures.collections.Utils;
import esm.aoc.structures.tree.DepthFirstIterator;

public class MainY24D07 {

    public static void main(String[] args) {
        final var list = InputReader.readSeparated("2024/day07.txt", ":? ").stream().map(InputMapper::getLongList).toList();
        System.out.println(list);
        System.out.println(STR."Part 1: \{solve1(list)}");
        System.out.println(STR."Part 2: \{solve2(list)}");
    }

    private static long solve1(final List<List<Long>> list) {
        return list.stream().mapToLong(line -> solveLine(false, line)).sum();
    }

    private static long solve2(List<List<Long>> list) {
        return list.stream().mapToLong(line -> solveLine(true, line)).sum();
    }

    private static long solveLine(final boolean useConcat, final List<Long> list) {
        final var target = list.getFirst();
        final var remaining = Utils.removeFirst(list);
        final var root = new MathTreeNode(useConcat, remaining.getFirst(), Utils.removeFirst(remaining));
        final var iterator = new DepthFirstIterator<>(root);
        while (iterator.hasNext()) {
            final MathTreeNode next = (MathTreeNode) iterator.next();
            if (next.isLeaf() && next.state().equals(target)) {
                return target;
            }
        }
        return 0;
    }

}
