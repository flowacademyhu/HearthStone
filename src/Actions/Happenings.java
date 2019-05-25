package Actions;

import Cards.Minion;
import Cards.Spell;
import Heroes.*;

public class Happenings {

    public  String whatHappenedSpell(Spell spell) {

        return "Casted: " + ((Spell) spell).getName();

    }

    public String whatHappenedMinion(Minion button) {

        return "Summoned: " + ((Minion) button).getName();

    }

    public String whatHappenedHero(Hero button) {

        return (((Hero)button).getHeroName() + " used heropower");

    }

    public String whatHappenedMageOrPriest (Hero button1, Object button2) {


        if (button1.toString().equals("Priest") && button2 instanceof Minion) {
           return (((Hero) button1).getHeroName() + " healed " + ((Minion) button2).getName());
        } else if (button1.toString().equals("Mage") && button2 instanceof Minion) {
            return (((Hero) button1).getHeroName() + " damaged " + ((Minion) button2).getName());
        }else if(button1.toString().equals("Priest")) {
            return (((Hero) button1).getHeroName() + " healed " + button2);
        } else {
             return (((Hero) button1).getHeroName() + " damaged " + button2);
        }
    }

    public String whatHappenedMinionKill(Minion minion) {

        return ((minion.getName() + " was killed"));

    }

    public String setWhatHappenedMinionAttack(Minion minion, Object object) {

        if(object.getClass() == Priest.class) {
            return ((minion.getName() + " attacked Priest"));
        } else if (object.getClass() == Hunter.class) {
            return ((minion.getName() + " attacked Hunter"));
        } else if (object.getClass() == Mage.class) {
            return ((minion.getName() + " attacked Mage"));
        } else if (object.getClass() == Paladin.class) {
            return ((minion.getName() + " attacked Paladin"));
        } else if (object.getClass() == Warlock.class) {
            return ((minion.getName() + " attacked Warlock"));
        } else if (object.getClass() == Minion.class && object instanceof Minion) {
            return ((minion.getName() + " attacked " + ((Minion) object).getName()));
        } else {
            return null;
        }

    }

    public String nextTurn(Hero hero) {
        return hero + "'s" + " turn";
    }

    public String endGame(Hero hero) {

        return hero.getHeroName() + " won the match!";

    }
}
