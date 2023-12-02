package esm.aoc.y15.d22;

public class Boss {

    private final int strength;
    private final int hitPoints;

    public Boss(final int hitPoints, final int strength) {
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public boolean isDead() {
        return hitPoints <= 0;
    }

    @Override
    public String toString() {
        return "hp" + hitPoints;
    }
}
