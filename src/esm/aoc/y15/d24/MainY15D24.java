package esm.aoc.y15.d24;

import esm.aoc.input.InputReader;
import esm.aoc.structures.combinations.Combinations;
import esm.aoc.utils.Utils;
import java.util.LinkedHashSet;
import java.util.Set;
import static esm.aoc.utils.Utils.sumLong;

public class MainY15D24 {

    public static void main(String[] args) {
        final var weights = new LinkedHashSet<>(InputReader.readLongs("2015/day24.txt"));
        System.out.println("Part 1:" + getMin(sumLong(weights) / 3, weights));
        System.out.println("Part 2:" + getMin(sumLong(weights) / 4, weights));
    }

    private static long getMin(final long target, final Set<Long> weights) {
        return findNSummingTo(target, weights).stream().mapToLong(Utils::productLong).min().orElseThrow();
    }

    private static Set<Set<Long>> findNSummingTo(final long target, final Set<Long> weights) {
        Set<Set<Long>> results;
        final var size1 = Combinations.chooseN(weights, 1);
        // Size 2
        results = checkCombos(target, 2, size1, size1);
        if (!results.isEmpty()) {
            return results;
        }
        // Size 3
        final var size2 = Combinations.chooseN(size1, weights);
        results = checkCombos(target, 3, size1, size2);
        if (!results.isEmpty()) {
            return results;
        }
        // Size 4
        results = checkCombos(target, 4, size2, size2);
        if (!results.isEmpty()) {
            return results;
        }
        // Size 5
        final var size3 = Combinations.chooseN(size2, weights);
        results = checkCombos(target, 5, size2, size3);
        if (!results.isEmpty()) {
            return results;
        }
        // Size 6
        results = checkCombos(target, 6, size3, size3);
        if (!results.isEmpty()) {
            return results;
        }
        throw new IllegalStateException();
    }

    private static Set<Set<Long>> checkCombos(final long target, final int size, final Set<Set<Long>> as, final Set<Set<Long>> bs) {
        final Set<Set<Long>> results = new LinkedHashSet<>();
        for (final var a : as) {
            for (final var b : bs) {
                final Set<Long> combined = new LinkedHashSet<>(a);
                combined.addAll(b);
                if (combined.size() == size && sumLong(combined) == target) {
                    results.add(combined);
                }
            }
        }
        return results;
    }

}
