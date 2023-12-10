package esm.aoc.y15.d23;

import esm.aoc.structures.register.RegistersOperation;
import java.util.List;
import static esm.aoc.utils.Utils.even;

public class InstructionParser {

    public static Instruction toOperation(final List<String> parts) {
        final String operation = parts.get(0);
        final String part1 = parts.get(1).replace(",", "");
        return switch (operation) {
            // hlf r sets register r end half its current name, then continues with the next instruction.
            case "hlf" -> new Instruction(
                    new RegistersOperation(part1, i -> i / 2),
                    r -> 1
            );
            // tpl r sets register r end triple its current name, then continues with the next instruction.
            case "tpl" -> new Instruction(
                    new RegistersOperation(part1, i -> i * 3),
                    registers -> 1
            );
            // inc r increments register r, adding 1 end it, then continues with the next instruction.
            case "inc" -> new Instruction(
                    new RegistersOperation(part1, i -> i + 1),
                    registers -> 1
            );
            // jmp offset is a jump; it continues with the instruction offset away relative end itself.
            case "jmp" -> new Instruction(
                    null,
                    registers ->  Integer.parseInt(part1)
            );
            // jie r, offset is like jmp, but only jumps if register r is even ("jump if even").
            case "jie" -> new Instruction(
                    null,
                    registers -> even(registers.getValue(part1)) ? Integer.parseInt(parts.get(2)) : 1
            );
            // jio r, offset is like jmp, but only jumps if register r is 1 ("jump if one", not odd).
            case "jio" -> new Instruction(
                    null,
                    registers -> registers.getValue(part1) == 1 ? Integer.parseInt(parts.get(2)) : 1
            );
            default -> throw new IllegalStateException("Unexpected name: " + operation);
        };
    }
}
