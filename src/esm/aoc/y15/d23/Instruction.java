package esm.aoc.y15.d23;

import esm.aoc.structures.register.Registers;
import esm.aoc.structures.register.RegistersOperation;
import java.util.function.Function;

public record Instruction(RegistersOperation registersOperation, Function<Registers, Integer> offsetOperation) {
}
