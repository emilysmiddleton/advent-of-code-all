package esm.aoc.structures.grid;

public record Coordinate3D(long x, long y, long z) {

    public float getEuclideanDistance(final Coordinate3D other) {
        return (float) Math.sqrt(Math.pow(other.x - x, 2) + Math.pow(other.y - y, 2) + Math.pow(other.z - z, 2));
    }

    public static Coordinate3D parse(final String s) {
        final String[] parts = s.split(",");
        return new Coordinate3D(Long.parseLong(parts[0]), Long.parseLong(parts[1]), Long.parseLong(parts[2]));
    }
}
