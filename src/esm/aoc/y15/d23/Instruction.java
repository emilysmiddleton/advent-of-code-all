package esm.aoc.y15.d23;

import esm.aoc.structures.register.Register;
import esm.aoc.structures.register.Registers;
import esm.aoc.structures.register.RegistersOperation;
import java.util.function.Function;

public class Instruction {
    private final RegistersOperation registersOperation;
    private final Function<Registers, Integer> offsetOperation;

    public Instruction(final RegistersOperation registersOperation, Function<Registers, Integer> offsetOperation) {
        this.registersOperation = registersOperation;
        this.offsetOperation = offsetOperation;
    }

    public Function<Registers, Integer> getOffsetOperation() {
        return offsetOperation;
    }

    public RegistersOperation getRegistersOperation() {
        return registersOperation;
    }
}
