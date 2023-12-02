package esm.aoc.structures.register;

import java.util.List;
import java.util.Map;

public class Registers {

    private final Map<String, Register> registers;

    public Registers(final Map<String, Register> registers) {
        this.registers = registers;
    }

    public int getValue(final String register) {
        return registers.get(register).getValue();
    }
    public void apply(final RegistersOperation operation) {
        registers.get(operation.getRegister()).applyAction(operation.getOperation());
    }

}
