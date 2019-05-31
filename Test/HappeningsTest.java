import Actions.Happenings;
import Cards.Minion;
import Cards.Spell;
import Heroes.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class HappeningsTest {

    Happenings happenings = new Happenings();
    Minion minion = new Minion("Tester", "test the game", 1, 2, 2,2,2, 2, false,"test", false);
    Hero hero1 = new Priest("Priest", false);
    Hero hero2 = new Mage("Mage", false);
    Hero hero3 = new Warlock("Warlock", false);
    Hero hero4 = new Hunter("Hunter", false);
    Hero hero5 = new Paladin("Paladin", false);

    String newLine = "\n";

    Spell spell = new Spell("Magic", "test the game", 1, "test");

    @Test
    public void testWhatHappenedSpell() {
        String returnValue = happenings.whatHappenedSpell(spell);
        assertEquals("Casted: " + spell.getName() + newLine, returnValue);
    }

    @Test
    public void testWhatHappenedMinion() {
        String returnValue = happenings.whatHappenedMinion(minion);
        assertEquals("Summoned: " + minion.getName() + newLine, returnValue);
    }

    @Test
    public void testWhatHappenedHero() {
        String returnValue = happenings.whatHappenedHero(hero1);
        assertEquals(hero1.getHeroName() + " used heropower" + newLine, returnValue);
    }

    @Test
    public void testWhatHappenedMageOrPriest() {
        String returnValuePriestHealsMinion = happenings.whatHappenedMageOrPriest(hero1, minion);
        assertEquals(hero1.getHeroName() + " healed " + minion.getName() + newLine, returnValuePriestHealsMinion);

         String returnValuePriestHealsHero = happenings.whatHappenedMageOrPriest(hero1, hero1);
        assertEquals(hero1.getHeroName() + " healed " + hero1.getHeroName() + newLine, returnValuePriestHealsHero);

        String returnValueMageDamagesMinion = happenings.whatHappenedMageOrPriest(hero2, minion);
        assertEquals(hero2.getHeroName() + " damaged " + minion.getName() + newLine, returnValueMageDamagesMinion);

        String returnValueMageDamagesHero = happenings.whatHappenedMageOrPriest(hero2, hero2);
        assertEquals(hero2.getHeroName() + " damaged " + hero2.getHeroName() + newLine, returnValueMageDamagesHero);
    }

    @Test
    public void testwhatHappenedMinionKill() {
        String returnValue = happenings.whatHappenedMinionKill(minion);
        assertEquals(minion.getName() + " was killed" + newLine, returnValue);
    }

    @Test
    public void setWhatHappenedMinionAttack() {
        String returnValueAttackMinion = happenings.setWhatHappenedMinionAttack(minion, minion);
        assertEquals(minion.getName() + " attacked " + minion.getName() + newLine, returnValueAttackMinion);

        String returnValueAttackPriest = happenings.setWhatHappenedMinionAttack(minion, hero1);
        assertEquals(minion.getName() + " attacked Priest"  + newLine, returnValueAttackPriest);

        String returnValueAttackMage = happenings.setWhatHappenedMinionAttack(minion, hero2);
        assertEquals(minion.getName() + " attacked Mage"  + newLine , returnValueAttackMage);

        String returnValueAttackWarlock = happenings.setWhatHappenedMinionAttack(minion, hero3);
        assertEquals(minion.getName() + " attacked Warlock" + newLine, returnValueAttackWarlock);

        String returnValueAttackHunter = happenings.setWhatHappenedMinionAttack(minion, hero4);
        assertEquals(minion.getName() + " attacked Hunter" + newLine, returnValueAttackHunter);

        String returnValueAttackPaladin = happenings.setWhatHappenedMinionAttack(minion, hero5);
        assertEquals(minion.getName() + " attacked Paladin" + newLine, returnValueAttackPaladin);
    }

    @Test
    public void testNextTurn() {
        String returnValue = happenings.nextTurn(hero1);
        assertEquals(hero1 + "'s" + " turn" + newLine, returnValue);
    }
    @Test
    public void testEndGame() {
        String returnValue = happenings.endGame(hero1);
        assertEquals(hero1.getHeroName() + " won the match!" + newLine, returnValue);
    }

}
