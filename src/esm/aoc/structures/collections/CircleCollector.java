package esm.aoc.structures.collections;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class CircleCollector<T> implements Collector<T, Circle<T>, Circle<T>> {

    @Override
    public Supplier<Circle<T>> supplier() {
        return Circle::new;
    }

    @Override
    public BiConsumer<Circle<T>, T> accumulator() {
        return Circle::add;
    }

    @Override
    public BinaryOperator<Circle<T>> combiner() {
        return (circle1, circle2) -> {
            circle1.addAll(circle2);
            return circle1;
        };
    }

    @Override
    public Function<Circle<T>, Circle<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}