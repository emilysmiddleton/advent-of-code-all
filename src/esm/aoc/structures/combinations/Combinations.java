package esm.aoc.structures.combinations;

import esm.aoc.structures.collections.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Combinations {

    public static long numberOfCombinations(final long n, final long k) {
        //  n! / [ (n-k)! k! ]
        return factorial(n) / ((factorial(n - k) * factorial(k)));
    }

    public static long factorial(final long n) {
        long result = 1;
        for (long i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }

    public static <T> Set<Set<T>> chooseN(final Set<T> things, int n) {
        if (n == 1) {
            return things.stream().map(Set::of).collect(Collectors.toSet());
        }
        return chooseN(chooseN(things, n-1), things);
    }

    public static <T> Set<Set<T>> chooseN(Set<Set<T>> existingGroup, final Set<T> things) {
        final Set<Set<T>> result = new LinkedHashSet<>();
        for (final T thing : things) {
            for (final Set<T> group : existingGroup) {
                if (!group.contains(thing)) {
                    final Set<T> newGroup = new LinkedHashSet<>(group);
                    newGroup.add(thing);
                    result.add(newGroup);
                }
            }
        }
        return result;
    }

    public static <T> List<Pair<T, T>> getPairs(final List<T> input) {
        if (input.size() < 2) {
            return Collections.emptyList();
        }
        final List<Pair<T, T>> pairs = new ArrayList<>();
        for (int i = 0; i < input.size() - 1; i++) {
            pairs.add(new Pair<>(input.get(i), input.get(i + 1)));
        }
        return pairs;
    }

    public static <T> Set<Pair<T, T>> getCombinations(final Collection<T> input) {
        final LinkedHashSet<Pair<T, T>> pairs = new LinkedHashSet<>();
        for (final var t1 : input) {
            for (final var t2 : input) {
                pairs.add(new Pair<>(t1, t2));
            }
        }
        return pairs;
    }

}
