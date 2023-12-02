package esm.aoc.y15.d23;

import esm.aoc.input.InputReader;
import esm.aoc.structures.register.Register;
import esm.aoc.structures.register.Registers;
import java.util.Map;

public class MainY15D23 {

    public static void main(String[] args) {
        final var registers = new Registers(Map.of("a", new Register(1), "b", new Register(0)));
        final var operations = InputReader
                .readSeparated("2015/day23.txt", " ")
                .stream()
                .map(InstructionParser::toOperation)
                .toList();
        final var program = new Program(registers, operations);
        program.execute();
        System.out.println(registers.getValue("b"));
    }
}
