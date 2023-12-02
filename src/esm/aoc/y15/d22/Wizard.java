package esm.aoc.y15.d22;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public record Wizard(int mana, int hitPoints, int shieldEffect, int poisonEffect, int rechargeEffect) {

    public int getArmor() {
        return shieldEffect > 0 ? 7 : 0;
    }

    public boolean isDead() {
        return hitPoints <= 0;
    }

    public List<Action> getPlayableActions() {
        return Arrays.stream(Action.values()).filter(this::isPlayable).collect(Collectors.toList());
    }

    private boolean isPlayable(final Action action) {
        if (action.getCost() > mana) {
            return false;
        }
        return switch (action) {
            case POISON -> poisonEffect == 0;
            case SHIELD -> shieldEffect == 0;
            case RECHARGE -> rechargeEffect == 0;
            default -> true;
        };
    }

    @Override
    public String toString() {
        return String.format("m%s hp%s p%s s%s r%s", mana, hitPoints, poisonEffect, shieldEffect, rechargeEffect);
    }
}
