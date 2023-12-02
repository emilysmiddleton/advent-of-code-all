package esm.aoc.y15.d22;

public enum Action {
    MAGIC_MISSILE(53, 4),
    DRAIN(73, 2),
    SHIELD(113, 0),
    POISON(173, 0),
    RECHARGE(229, 0);

    private final int cost;
    private final int immediateDamage;

    Action(final int cost, final int immediateDamage) {
        this.cost = cost;
        this.immediateDamage = immediateDamage;
    }

    public int getCost() {
        return cost;
    }

    public int getImmediateDamage() {
        return immediateDamage;
    }
}
