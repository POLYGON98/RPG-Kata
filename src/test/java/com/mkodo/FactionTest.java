package com.mkodo;

import com.mkodo.character.Character;
import com.mkodo.character.MeleeCharacter;
import com.mkodo.character.RangedCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FactionTest {

    private Faction faction;
    private Character character;

    @BeforeEach
    void setUp() {
        faction = new Faction("MKodo");
        character = new MeleeCharacter(1, 100);
    }

    @Test
    void shouldCreateFactionsWithDifferentNames() {
        Faction secondFaction = new Faction("Google");
        assertThat(faction.getName()).isEqualTo("MKodo");
        assertThat(secondFaction.getName()).isEqualTo("Google");
    }

    @Test
    void shouldHaveNoMembersAtTheBeginning() {
        assertThat(faction.getMembers().size()).isZero();
    }

    @Test
    void shouldAddMember() {
        faction.addMember(character);

        assertThat(faction.getMembers()).hasSize(1)
                                        .contains(character);
    }

    @Test
    void shouldAllowMemberToLeaveFaction() {
        faction.addMember(character);
        faction.removeMember(character);

        assertThat(faction.getMembers()).hasSize(0)
                                        .doesNotContain(character);
    }

    @Test
    void shouldAllowMemberToJoinMultipleFactions() {
        Faction secondFaction = new Faction("Google");
        Faction thirdFaction = new Faction("Google");

        faction.addMember(character);
        secondFaction.addMember(character);
        thirdFaction.addMember(character);

        assertThat(character).isIn(faction.getMembers())
                             .isIn(secondFaction.getMembers())
                             .isIn(thirdFaction.getMembers());
    }

    @Test
    void shouldTreatSameFactionMembersAsAlly() {
        Character alliedCharacter = new RangedCharacter(1, 100);
        faction.addMember(character);
        faction.addMember(alliedCharacter);

        assertThat(character.isAlly(alliedCharacter)).isTrue();
    }

    @Test
    void shouldNotTreatCharactersThatAreNotInTheSameFactionAsAlly() {
        Character noFactionCharacter = new RangedCharacter(1, 100);
        faction.addMember(character);

        assertThat(character.isAlly(noFactionCharacter)).isFalse();
    }

    @Test
    void shouldNotTreatCharacterThatLeftFactionAsAlly() {
        Character khoovi = new MeleeCharacter(1, 100);
        faction.addMember(character);
        faction.addMember(khoovi);
        assertThat(khoovi.isAlly(character)).isTrue();
        faction.removeMember(khoovi);
        assertThat(khoovi.isAlly(character)).isFalse();
    }
}
