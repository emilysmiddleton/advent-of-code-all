package esm.aoc.y16.d11;

public class Thing {
    private final Type type;
    private final Material material;

    public Thing(final Type type, final Material material) {
        this.type = type;
        this.material = material;
    }

    @Override
    public String toString() {
        return "Thing{" +
               "type=" + type +
               ", material=" + material +
               '}';
    }
}
