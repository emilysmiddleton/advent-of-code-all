package esm.aoc.structures.register;

import java.util.function.Function;

public record RegistersOperation(String register, Function<Integer, Integer> operation) {

}
