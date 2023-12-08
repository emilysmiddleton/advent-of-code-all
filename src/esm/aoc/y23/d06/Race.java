package esm.aoc.y23.d06;

public record Race(long time, long distance) {

    public long getDistanceIfHeldFor(final long held) {
        long timeRacing = time - held;
        return timeRacing * held;
    }

    public boolean winsIfHeldFor(final long held) {
        return getDistanceIfHeldFor(held) > distance;
    }

    public boolean losesIfHeldFor(final long held) {
        return !winsIfHeldFor(held);
    }
}
