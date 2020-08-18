package com.mkodo.character;

public class RangedCharacter extends Character {
    public RangedCharacter(int level, int damageAmount) {
        super(level, damageAmount);
    }

    @Override
    public int getRange() {
        return 20;
    }
}
