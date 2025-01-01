package esm.aoc.structures.collections;

import java.util.LinkedHashMap;
import java.util.function.BiFunction;

/**
 * A map where, if the key already exists, chooses which to use according to the provided function.
 */
public class BiFunctionMap<T, V> extends LinkedHashMap<T, V> {

    private final BiFunction<V, V, V> comparator;

    public BiFunctionMap(BiFunction<V, V, V> comparator) {
        super();
        this.comparator = comparator;
    }

    @Override
    public V put(T key, V value) {
        if (containsKey(key)) {
            final V previous = get(key);
            return super.put(key, comparator.apply(value, previous));
        } else {
            return super.put(key, value);
        }
    }
}
