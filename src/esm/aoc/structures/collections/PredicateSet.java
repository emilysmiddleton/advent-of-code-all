package esm.aoc.structures.collections;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Predicate;

public class PredicateSet<T> {

    private final Predicate<T> filter;
    private final Set<T> items = new LinkedHashSet<>();

    public PredicateSet(Predicate<T> filter) {
        this.filter = filter;
    }

    public void add(final T item) {
        if (filter.test(item)) {
            items.add(item);
        }
    }

    public Set<T> getItems() {
        return items;
    }
}
