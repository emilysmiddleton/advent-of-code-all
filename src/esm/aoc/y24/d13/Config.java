package esm.aoc.y24.d13;


import java.util.List;
import java.util.regex.Pattern;

import esm.aoc.structures.grid.Coordinate;

/**
 * Button A: X+69, Y+23
 * Button B: X+27, Y+71
 * Prize: X=18641, Y=10279
 */
public record Config(Coordinate buttonA, Coordinate buttonB, Coordinate prize) {

    private static final Pattern BUTTON_A = Pattern.compile("Button A: X(.*), Y(.*)");
    private static final Pattern BUTTON_B = Pattern.compile("Button B: X(.*), Y(.*)");
    private static final Pattern PRIZE = Pattern.compile("Prize: X=(.*), Y=(.*)");
    public static Config parse(final List<String> input) {
        final var matcherA = BUTTON_A.matcher(input.getFirst());
        final var matcherB = BUTTON_B.matcher(input.get(1));
        final var matcherC = PRIZE.matcher(input.get(2));
        if (!(matcherA.matches() && matcherB.matches() && matcherC.matches())) {
            throw new IllegalStateException();
        }
        return new Config(
                new Coordinate(Integer.parseInt(matcherA.group(1)), Integer.parseInt(matcherA.group(2))),
                new Coordinate(Integer.parseInt(matcherB.group(1)), Integer.parseInt(matcherB.group(2))),
                new Coordinate(Integer.parseInt(matcherC.group(1)), Integer.parseInt(matcherC.group(2)))
        );
    }

}
