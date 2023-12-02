package esm.aoc.structures.collections;

import java.util.ArrayList;
import java.util.List;

public class Circle<T> extends ArrayList<T> {

    @SafeVarargs
    public static <T> Circle<T> of(T... things) {
        final Circle<T> circle = new Circle<>();
        circle.addAll(List.of(things));
        return circle;
    }

    @Override
    public T get(final int index) {
        int newIndex = index % size();
        if (newIndex < 0) {
            newIndex += size();
        }
        return super.get(newIndex);
    }

}
