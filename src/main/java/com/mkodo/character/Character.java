package com.mkodo.character;

import com.mkodo.Faction;
import com.mkodo.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class Character extends GameObject {

    private int damageAmount;
    private List<Faction> factions = new ArrayList<>();

    public Character(int level, int damageAmount) {
        super(level);
        this.health = 1000;
        this.damageAmount = damageAmount;
    }

    public abstract int getRange();

    public int getDamageAmount() {
        return damageAmount;
    }

    public void addHealth(int addedHealth) {
        this.health = getHealth() + addedHealth > 1000 ? 1000 : getHealth() + addedHealth;
    }

    public void addFaction(Faction faction) {
        factions.add(faction);
    }

    public void removeFaction(Faction faction) {
        factions.remove(faction);
    }

    public boolean isAlly(GameObject targetCharacter) {
        for (Faction faction :factions) {
            if (faction.getMembers().contains(targetCharacter))
                return true;
        }
        return false;
    }
}
