package esm.aoc.structures.register;

import java.util.function.Function;

public class Register {

    private int value;

    public Register(final int value) {
        this.value = value;
    }

    public void applyAction(final Function<Integer, Integer> function) {
        value = function.apply(value);
    }

    public int getValue() {
        return value;
    }
}
