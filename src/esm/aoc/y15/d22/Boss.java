package esm.aoc.y15.d22;

public record Boss(int hitPoints, int strength) {

    public boolean isDead() {
        return hitPoints <= 0;
    }

    @Override
    public String toString() {
        return "hp" + hitPoints;
    }
}
