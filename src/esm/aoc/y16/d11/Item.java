package esm.aoc.y16.d11;

public class Item {
    private final Type type;
    private final Material material;

    public static Item parse(final String input) {
        final var split = input.split(" ");
        return new Item(Type.valueOf(split[1]), Material.valueOf(split[0]));
    }

    public Item(final Type type, final Material material) {
        this.type = type;
        this.material = material;
    }

    @Override
    public String toString() {
        return material.name() + "_" + type.name().charAt(0);
    }
}
