package com.mkodo.battle;

import com.mkodo.GameObject;
import com.mkodo.character.Character;

public class AttackCommand implements BattleCommand {

    private final Character performingCharacter;
    private final GameObject targetCharacter;

    public AttackCommand(Character performingCharacter, GameObject targetCharacter) {
        this.performingCharacter = performingCharacter;
        this.targetCharacter = targetCharacter;
    }

    public void execute() {
        handleAttack();
    }

    private void handleAttack() {
        if (isNotTargetingItself()) {
            calculateDamage();
            handleDeath();
        }
    }

    private boolean isNotTargetingItself() {
        return targetCharacter != performingCharacter;
    }

    private void calculateDamage() {
        if (isNotAlly()) {
            if (isInRange())
                targetCharacter.receiveDamage(calculateDamageBasedOnLevel());
        }
    }

    private boolean isNotAlly() {
        return !performingCharacter.isAlly(targetCharacter);
    }

    private boolean isInRange() {
        return performingCharacter.getRange() - targetCharacter.getDistance()  > 0;
    }

    private int calculateDamageBasedOnLevel() {
        int damageAmount = performingCharacter.getDamageAmount();
        if (isTargetFiveLevelsHigher())
            return damageAmount / 2;
        if (isTargetFiveLevelsBelow())
            return damageAmount + damageAmount / 2;
        return damageAmount;
    }

    private boolean isTargetFiveLevelsHigher() {
        return targetCharacter.getLevel() - performingCharacter.getLevel() >= 5;
    }

    private boolean isTargetFiveLevelsBelow() {
        return performingCharacter.getLevel() - targetCharacter.getLevel() >= 5;
    }

    private void handleDeath() {
        if (isHealthZeroOrBelow())
            targetCharacter.die();
    }

    private boolean isHealthZeroOrBelow() {
        return targetCharacter.getHealth() <= 0;
    }


}
