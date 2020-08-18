package com.mkodo.battle;

import com.mkodo.Faction;
import com.mkodo.character.Character;
import com.mkodo.character.MeleeCharacter;
import com.mkodo.character.RangedCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HealCommandTest {

    private Character character;
    private Character targetCharacter;
    private HealCommand healCommand;
    private AttackCommand attackCommand;

    @BeforeEach
    void setUp() {
        character = new MeleeCharacter(1, 200);
        targetCharacter = new RangedCharacter(1, 100);
        healCommand = new HealCommand(targetCharacter, targetCharacter);
        attackCommand = new AttackCommand(character, targetCharacter);
    }

    @Test
    void shouldHealCharacter() {
        attackCommand.execute();
        healCommand.execute();
        assertThat(targetCharacter.getHealth()).isEqualTo(900);
    }

    @Test
    void shouldNotHealAbove1000() {
        healCommand.execute();
        assertThat(targetCharacter.getHealth()).isEqualTo(1000);
    }

    @Test
    void shouldNotHealDeadCharacter() {
        Character opCharacter = new MeleeCharacter(1, 1000);
        attackCommand = new AttackCommand(opCharacter, targetCharacter);
        attackCommand.execute();
        healCommand.execute();
        assertThat(targetCharacter.getHealth()).isZero();
        assertThat(targetCharacter.isAlive()).isFalse();
    }

    @Test
    void shouldNotHealOtherCharacters() {
        healCommand = new HealCommand(character, targetCharacter);
        attackCommand.execute();
        healCommand.execute();

        assertThat(targetCharacter.getHealth()).isEqualTo(800);
    }

    @Test
    void shouldHealAnAlly() {
        Character alliedCharacter = new RangedCharacter(1, 100);
        Character bullyCharacter = new RangedCharacter(1, 200);

        Faction faction = new Faction("MKodo");
        faction.addMember(character);
        faction.addMember(alliedCharacter);

        BattleCommand attackCommand = new AttackCommand(bullyCharacter, alliedCharacter);
        BattleCommand healCommands = new HealCommand(character, alliedCharacter);

        attackCommand.execute();
        healCommands.execute();

        assertThat(alliedCharacter.getHealth()).isEqualTo(900);
    }
}