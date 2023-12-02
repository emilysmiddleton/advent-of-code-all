package esm.aoc.y15.d23;

import esm.aoc.structures.register.Registers;
import java.util.List;

public class Program {

    private final Registers registers;
    private final List<Instruction> instructions;
    private int pointer = 0;

    public Program(final Registers registers, final List<Instruction> instructions) {
        this.registers = registers;
        this.instructions = instructions;
    }

    public void execute() {
        while (pointer >= 0 && pointer < instructions.size()) {
            act();
        }
    }

    private void act() {
        final var instruction = instructions.get(pointer);
        if (instruction.registersOperation() != null) {
            registers.apply(instruction.registersOperation());
        }
        pointer = pointer + instruction.offsetOperation().apply(registers);
    }

}
