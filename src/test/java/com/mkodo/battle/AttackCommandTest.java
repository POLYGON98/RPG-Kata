package com.mkodo.battle;

import com.mkodo.Faction;
import com.mkodo.character.Character;
import com.mkodo.character.MeleeCharacter;
import com.mkodo.character.RangedCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AttackCommandTest {
    private Character meleeCharacter;
    private Character rangedCharacter;
    private AttackCommand attackCommand;

    @BeforeEach
    void setUp() {
        meleeCharacter = new MeleeCharacter(1, 100);
        rangedCharacter = new RangedCharacter(1, 100);
        attackCommand = new AttackCommand(meleeCharacter, rangedCharacter);
    }

    @Test
    void shouldReduceTargetCharacterHealthWhenAttacking() {
        attackCommand.execute();
        assertThat(rangedCharacter.getHealth()).isEqualTo(900);
    }

    @Test
    void shouldReduceHealthToZeroAndTargetDies() {
        meleeCharacter = new MeleeCharacter(1, 1050);
        attackCommand = new AttackCommand(meleeCharacter, rangedCharacter);
        attackCommand.execute();
        assertThat(rangedCharacter.getHealth()).isZero();
        assertThat(rangedCharacter.isAlive()).isFalse();
    }

    @Test
    void shouldNotDealDamageToItself() {
        attackCommand.execute();
        assertThat(meleeCharacter.getHealth()).isEqualTo(1000);
    }

    @Test
    void shouldReduceDamageBy50PercentIfTargetIs5LevelsHigher() {
        Character levelSixCharacter = new MeleeCharacter(6, 100);
        attackCommand = new AttackCommand(meleeCharacter, levelSixCharacter);
        attackCommand.execute();
        assertThat(levelSixCharacter.getHealth()).isEqualTo(950);
    }

    @Test
    void shouldIncreaseDamageBy50PercentIfTargetIs5LevelsBelow() {
        Character levelSixCharacter = new MeleeCharacter(6, 100);
        attackCommand = new AttackCommand(levelSixCharacter, meleeCharacter);
        attackCommand.execute();
        assertThat(meleeCharacter.getHealth()).isEqualTo(850);
    }

    @Test
    void shouldNotDealDamageWhenOutOfMeleeRange() {
        rangedCharacter.setDistance(5);
        attackCommand.execute();
        assertThat(rangedCharacter.getHealth()).isEqualTo(1000);
    }

    @Test
    void shouldDealDamageWhenInMeleeRange() {
        rangedCharacter.setDistance(1);
        attackCommand.execute();
        assertThat(rangedCharacter.getHealth()).isEqualTo(900);
    }

    @Test
    void shouldNotDealDamageWhenOutOfRangedRange() {
        meleeCharacter.setDistance(25);
        attackCommand = new AttackCommand(rangedCharacter, meleeCharacter);
        attackCommand.execute();
        assertThat(meleeCharacter.getHealth()).isEqualTo(1000);
    }

    @Test
    void shouldDealDamageWhenInRangedRange() {
        meleeCharacter.setDistance(15);
        attackCommand = new AttackCommand(rangedCharacter, meleeCharacter);
        attackCommand.execute();
        assertThat(meleeCharacter.getHealth()).isEqualTo(900);
    }

    @Test
    void shouldNotDealDamageToAlly() {
        Character alliedCharacter = new RangedCharacter(1, 100);
        Faction faction = new Faction("MKodo");
        faction.addMember(meleeCharacter);
        faction.addMember(alliedCharacter);
        BattleCommand attackCommand = new AttackCommand(meleeCharacter, alliedCharacter);
        attackCommand.execute();
        assertThat(alliedCharacter.getHealth()).isEqualTo(1000);
    }
}
