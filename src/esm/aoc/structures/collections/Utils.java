package esm.aoc.structures.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {
    public static <T> Set<T> union(final Set<T> left, final Set<T> right) {
        return left.stream().filter(right::contains).collect(Collectors.toSet());
    }

    public static <T, V> List<V> zip(final List<List<T>> ts, Function<List<T>, V> function) {
        final List<V> result = new ArrayList<>();
        final int size = ts.get(0).size();
        for (int i = 0; i < size; i++) {
            final int index = i;
            result.add(function.apply(ts.stream().map(t -> t.get(index)).toList()));
        }
        return result;
    }

    public static <T> List<T> replace(final List<T> list, final T replacement, final int index) {
        final var copy = new ArrayList<>(list);
        copy.set(index, replacement);
        return copy;
    }

    public static <T> List<T> removeFirst(final List<T> list) {
        final var copy = new ArrayList<>(list);
        copy.removeFirst();
        return copy;
    }

    public static <T> List<T> addAndCopy(final List<T> list, final T thing) {
        final var copy = new ArrayList<>(list);
        copy.add(thing);
        return copy;
    }

}
