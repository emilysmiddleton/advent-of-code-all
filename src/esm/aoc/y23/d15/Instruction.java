package esm.aoc.y23.d15;

import java.util.List;
import java.util.regex.Pattern;

record Instruction(String label, int box, String instruction, int value) {

    static final Pattern PATTERN = Pattern.compile("(.*)([=-])(\\d*)");

    static Instruction parse(final List<String> parts) {
        return new Instruction(
                parts.get(0),
                hash(parts.get(0)),
                parts.get(1),
                parts.get(2).isEmpty() ? 0 : Integer.parseInt(parts.get(2))
        );
    }

    /**
     Determine the ASCII code for the current character of the string.
     Increase the current value by the ASCII code you just determined.
     Set the current value to itself multiplied by 17.
     Set the current value to the remainder of dividing itself by 256.
     */
    static int hash(final String string) {
        int value = 0;
        for (final char aChar : string.toCharArray()) {
            value += aChar;
            value *= 17;
            value = value % 256;
        }
        return value;
    }
}
