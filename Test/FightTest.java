import Actions.Fight;
import Cards.Minion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FightTest {

    Fight fight = new Fight();

    List<Minion> minionList = new ArrayList<>();
    List<Minion> minionListNoTaunt = new ArrayList<>();

    Minion minion1 = new Minion("Orc Warrior", "", 3, 3,3,2, 2,2, false,"", false);
    Minion minion2 = new Minion("Human Warrior", "", 3, 2,2,3, 3,3,false,"", false);
    Minion minion3 = new Minion("Taunt minion", "taunt", 3, 2,2,3, 3,3,false,"taunt", false);

    @Test
    public void testIsThereTaunt() {

        minionList.add(minion1);
        minionList.add(minion2);
        minionList.add(minion3);

        minionListNoTaunt.add(minion1);
        minionListNoTaunt.add(minion2);

        assertTrue(fight.isThereTaunt(minionList));
        assertFalse(fight.isThereTaunt(minionListNoTaunt));
    }
}
