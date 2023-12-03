package esm.aoc.structures.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

public class Multimap<T, V> extends LinkedHashMap<T, List<V>> {

    @Override
    public List<V> get(final Object key) {
        return containsKey(key) ? super.get(key) : Collections.emptyList();
    }

    public void add(final T key, final V value) {
        if (!containsKey(key)) {
            put(key, new ArrayList<>());
        }
        get(key).add(value);
    }
}
