package Heroes;

import Cards.Card;
import Cards.Minion;

import java.util.List;

public class Paladin extends Hero{

    public Paladin(String heroName, boolean immune) {

        super(heroName, immune);
    }

    public void heroPower(Object object) {
    }

    @Override
    public String toString() {
        return "Paladin";
    }
}
