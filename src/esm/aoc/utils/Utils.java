package esm.aoc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Utils {

    public static boolean even(final int value) {
        return value % 2 == 0;
    }

    public static boolean odd(final int value) {
        return value % 2 == 1;
    }

    public static int sum(final Collection<Integer> values) {
        return values.stream().reduce(Integer::sum).orElse(0);
    }

    public static long sumLong(final Collection<Long> values) {
        return values.stream().reduce(Long::sum).orElse(0L);
    }

    public static int product(final Collection<Integer> values) {
        return values.stream().reduce((a, b) -> a * b).orElse(0);
    }

    public static long productLong(final Collection<Long> values) {
        return values.stream().reduce((a, b) -> a * b).orElse(0L);
    }

    public static <T> void reverseConsume(final List<T> things, final Consumer<T> consumer) {
        final List<T> reversed = new ArrayList<>(things);
        Collections.reverse(reversed);
        reversed.forEach(consumer);
    }

    public static <T> List<T> removeFirst(final List<T> things) {
        return new ArrayList<>(things.subList(1, things.size()));
    }

    public static <T> List<T> remove(final List<T> things, final T thing) {
        final var list = new ArrayList<>(things);
        list.remove(thing);
        return list;
    }

}
