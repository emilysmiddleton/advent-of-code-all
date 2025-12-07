package esm.aoc.structures.maths;

public enum Operator {

    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE;

    public static Operator parse(final String input) {
        return switch (input) {
            case "+" -> ADD;
            case "-" -> SUBTRACT;
            case "*" -> MULTIPLY;
            case "/" -> DIVIDE;
            default -> throw new IllegalArgumentException(STR."Unknown operator: \{input}");
        };
    }

    public long apply(final long a, final long b) {
        return switch (this) {
            case ADD -> a + b;
            case SUBTRACT -> a - b;
            case MULTIPLY -> a * b;
            case DIVIDE -> a / b;
        };
    }

    public long getIdentity() {
        return switch (this) {
            case ADD, SUBTRACT -> 0;
            case MULTIPLY, DIVIDE -> 1;
        };
    }
}
