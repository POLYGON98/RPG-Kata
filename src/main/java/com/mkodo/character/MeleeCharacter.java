package com.mkodo.character;

public class MeleeCharacter extends Character {

    public MeleeCharacter(int level, int damageAmount) {
        super(level, damageAmount);
    }

    @Override
    public int getRange() {
        return 2;
    }
}
