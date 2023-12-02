package esm.aoc.y15.d22;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Wizard {

    private final int mana;
    private final int hitPoints;
    private final int shieldEffect;
    private final int poisonEffect;
    private final int rechargeEffect;

    public Wizard(final int mana, final int hitPoints, final int shieldEffect, final int poisonEffect, final int rechargeEffect) {
        this.mana = mana;
        this.hitPoints = hitPoints;
        this.shieldEffect = shieldEffect;
        this.poisonEffect = poisonEffect;
        this.rechargeEffect = rechargeEffect;
    }

    public int getMana() {
        return mana;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getArmor() {
        return shieldEffect > 0 ? 7 : 0;
    }

    public int getShieldEffect() {
        return shieldEffect;
    }

    public int getPoisonEffect() {
        return poisonEffect;
    }

    public int getRechargeEffect() {
        return rechargeEffect;
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
        };
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
