package com.mkodo;

import com.mkodo.battle.AttackCommand;
import com.mkodo.battle.BattleCommand;
import com.mkodo.character.Character;
import com.mkodo.character.MeleeCharacter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InanimateGameObjectTest {
    @Test
    void shouldHaveHealth() {
        GameObject tree = new InanimateObject(1, 2000);
        assertThat(tree.getHealth()).isEqualTo(2000);
    }

    @Test
    void shouldBeAbleToBeTargeted() {
        GameObject tree = new InanimateObject(1, 2000);
        Character character = new MeleeCharacter(1, 100);
        BattleCommand attackCommand = new AttackCommand(character, tree);
        attackCommand.execute();
        assertThat(tree.getHealth()).isEqualTo(1900);
    }
}
