package com.mkodo;

import com.mkodo.character.Character;
import com.mkodo.character.MeleeCharacter;
import com.mkodo.character.RangedCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CharacterTest {

    private Character character;
    private Character targetCharacter;

    @BeforeEach
    void setUp() {
        character = new MeleeCharacter(1, 100);
        targetCharacter = new RangedCharacter(1, 100);
    }

    @Test
    void shouldCreateCharacterWith1000Health() {
        assertThat(character.getHealth()).isEqualTo(1000);
    }

    @Test
    void shouldCreateCharacterWithLevel1() {
        assertThat(character.getLevel()).isOne();
    }

    @Test
    void shouldCreateAliveCharacter() {
        assertThat(character.isAlive()).isTrue();
    }

    @Test
    void shouldCreateCharacterWithLevel5() {
        Character levelFiveCharacter = new MeleeCharacter(5, 100);
        assertThat(levelFiveCharacter.getLevel()).isEqualTo(5);
    }

    @Test
    void shouldCreateMeleeCharacterWithMaxRangeOf2() {
        Character meleeCharacter = new MeleeCharacter(1, 100);
        assertThat(meleeCharacter.getRange()).isEqualTo(2);
    }

    @Test
    void shouldCreateRangedCharacterWithMaxRangeOf20() {
        Character rangedCharacter = new RangedCharacter(1, 100);
        assertThat(rangedCharacter.getRange()).isEqualTo(20);
    }
}
