package com.mkodo;

import com.mkodo.character.Character;

import java.util.ArrayList;
import java.util.List;

public class Faction {
    private final String name;
    private final List<Character> members = new ArrayList();

    public Faction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Character> getMembers() {
        return members;
    }

    public void addMember(Character character) {
        members.add(character);
        character.addFaction(this);
    }

    public void removeMember(Character character) {
        members.remove(character);
        character.removeFaction(this);
    }
}
