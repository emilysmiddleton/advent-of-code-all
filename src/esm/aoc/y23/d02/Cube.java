package esm.aoc.y23.d02;

public record Cube(int number, String colour) {

    @Override
    public String toString() {
        return "Thing{" +
               "number=" + number +
               ", colour='" + colour + '\'' +
               '}';
    }

    public boolean possible(final int red, final int green, final int blue) {
        return switch (colour) {
            case "red" -> number <= red;
            case "green" -> number <= green;
            case "blue" -> number <= blue;
            default -> throw new IllegalStateException("Unexpected value: " + colour);
        };
    }
}
