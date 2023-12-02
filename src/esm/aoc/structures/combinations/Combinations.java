package esm.aoc.structures.combinations;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Combinations {

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
            };
        }
        return result;
    }

}
