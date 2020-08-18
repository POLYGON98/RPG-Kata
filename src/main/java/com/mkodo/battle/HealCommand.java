package com.mkodo.battle;

import com.mkodo.character.Character;

public class HealCommand implements BattleCommand {
    private final Character performingCharacter;
    private final Character targetCharacter;

    public HealCommand(Character performingCharacter, Character targetCharacter) {
        this.performingCharacter = performingCharacter;
        this.targetCharacter = targetCharacter;
    }

    @Override
    public void execute() {
        if (isTargetItself() || isTargetAlly()) {
            if (targetCharacter.isAlive())
                targetCharacter.addHealth(100);
        }
    }

    private boolean isTargetAlly() {
        return performingCharacter.isAlly(targetCharacter);
    }

    private boolean isTargetItself() {
        return performingCharacter.equals(targetCharacter);
    }
}
