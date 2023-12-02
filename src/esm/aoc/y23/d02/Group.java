package esm.aoc.y23.d02;

import java.util.List;

public class Group {

    private final List<Cube> cubes;

    public Group(final List<Cube> cubes) {
        this.cubes = cubes;
    }

    public boolean possible(final int red, final int green, final int blue) {
        return cubes.stream().allMatch(cube -> cube.possible(red, green, blue));
    }

    public int getMaxForColour(final String colour) {
        return cubes.stream()
                    .filter(cube -> cube.getColour().equals(colour))
                    .mapToInt(Cube::getNumber)
                    .max()
                    .orElse(0);
    }
}
