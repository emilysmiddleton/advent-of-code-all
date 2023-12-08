package esm.aoc.structures.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class Circle<T> extends ArrayList<T> {

    public Circle() {
    }

    public Circle(@NotNull final Collection<? extends T> c) {
        super(c);
    }

    public Circle(final int initialCapacity) {
        super(initialCapacity);
    }

    @SafeVarargs
    public static <T> Circle<T> of(T... things) {
        final Circle<T> circle = new Circle<>();
        circle.addAll(List.of(things));
        return circle;
    }

    @Override
    public T get(final int index) {
        return super.get(adjust(index));
    }

    public int adjust(final int index) {
        int newIndex = index % size();
        if (newIndex < 0) {
            newIndex += size();
        }
        return newIndex;
    }

}
