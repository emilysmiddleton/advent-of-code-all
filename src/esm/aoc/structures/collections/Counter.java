package esm.aoc.structures.collections;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Counter<T> {

    private final Map<T, Long> counts = new LinkedHashMap<>();
    public void add(final T value) {
        if (!counts.containsKey(value)) {
            counts.put(value, 0L);
        }
        counts.put(value, counts.get(value) + 1);
    }

    public Set<T> getKeys() {
        return counts.keySet();
    }

    public long getCount(final T value) {
        return counts.get(value);
    }

    @Override
    public String toString() {
        return counts.toString();
    }
}
