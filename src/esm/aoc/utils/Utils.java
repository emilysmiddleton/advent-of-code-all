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

    public static int sum(final Collection<Integer> values) {
        return values.stream().reduce(Integer::sum).orElse(0);
    }

    public static long sumLong(final Collection<Long> values) {
        return values.stream().reduce(Long::sum).orElse(0L);
    }

    public static long productLong(final Collection<Long> values) {
        return values.stream().reduce((a, b) -> a * b).orElse(0L);
    }

    public static <T> void reverseConsume(final List<T> things, final Consumer<T> consumer) {
        final List<T> reversed = new ArrayList<>(things);
        Collections.reverse(reversed);
        reversed.forEach(consumer);
    }

    public static <T> T getMiddle(final List<T> input) {
        return input.get(input.size() / 2);
    }

}
