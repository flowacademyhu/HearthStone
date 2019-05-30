package Test;

import Cards.Minion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class FightTest {

    List<Minion> minionList = new ArrayList<>();

     Minion minion1 = new Minion("Orc Warrior", "", 3, 3,3,2, 2,2, false,"", false);
     Minion minion2 = new Minion("Human Warrior", "", 3, 2,2,3, 3,3,false,"", false);
     Minion minion3 = new Minion("Taunt minion", "taunt", 3, 2,2,3, 3,3,false,"taunt", false);

    @Test
    public void testIsThereTaunt() {

        minionList.add(minion1);
        minionList.add(minion2);
        minionList.add(minion3);



    }
}
