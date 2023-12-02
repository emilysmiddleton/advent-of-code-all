package esm.aoc.y23.d02;

public class Cube {
    private final int number;
    private final String colour;

    public Cube(final int number, final String colour) {
        this.number = number;
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public int getNumber() {
        return number;
    }

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
