package esm.aoc.y23.d02;

import java.util.List;

public class Game {
    private final int id;
    private final List<Group> groups;

    public Game(final int id, final List<Group> groups) {
        this.id = id;
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Game{" +
               "id='" + id + '\'' +
               ", groups=" + groups +
               '}';
    }

    public boolean possible(final int red, final int green, final int blue) {
        return groups.stream().allMatch(group -> group.possible(red, green, blue));
    }

    public int getPower() {
        return getMaxForColour("red") * getMaxForColour("blue") * getMaxForColour("green");
    }

    private int getMaxForColour(final String colour) {
        return groups.stream().mapToInt(group -> group.getMaxForColour(colour)).max().orElseThrow();
    }

}
