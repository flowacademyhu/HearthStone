package HappeningsOnBoard;

import Cards.Minion;
import Heroes.*;

public class Happenings {

    private String step = "";

    private String whatHappenedMinion(Minion button) {

        return step = "Summoned: " + ((Minion) button).getName();

    }

    private String whatHappenedHero(Hero button) {

        return step = (((Hero)button).getHeroName() + " used heropower");

    }

    private String whatHappenedMageOrPriest (Hero button1, Object button2) {


        if (button1.toString().equals("Priest") && button2 instanceof Minion) {
           return step = (((Hero) button1).getHeroName() + " healed " + ((Minion) button2).getName());
        } else if (button1.toString().equals("Mage") && button2 instanceof Minion) {
            return step = (((Hero) button1).getHeroName() + " damaged " + ((Minion) button2).getName());
        }else if(button1.toString().equals("Priest")) {
            return step = (((Hero) button1).getHeroName() + " healed " + button2);
        } else {
             return step = (((Hero) button1).getHeroName() + " damaged " + button2);
        }
    }

    private String whatHappenedMinionKill(Minion minion) {

        return step = ((minion.getName() + " was killed"));

    }

    private String setWhatHappenedMinionAttack(Minion minion, Object object) {

        if(object.getClass() == Priest.class) {
            return step = ((minion.getName() + " attacked Priest"));
        } else if (object.getClass() == Hunter.class) {
            return step = ((minion.getName() + " attacked Hunter"));
        } else if (object.getClass() == Mage.class) {
            return step = ((minion.getName() + " attacked Mage"));
        } else if (object.getClass() == Paladin.class) {
            return step = ((minion.getName() + " attacked Paladin"));
        } else if (object.getClass() == Warlock.class) {
            return step = ((minion.getName() + " attacked Warlock"));
        } else if (object.getClass() == Minion.class && object instanceof Minion) {
            return step = ((minion.getName() + " attacked " + ((Minion) object).getName()));
        } else {
            return null;
        }

    }
}
