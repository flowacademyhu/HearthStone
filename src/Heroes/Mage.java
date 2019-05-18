package Heroes;

import Cards.Minion;

public class Mage extends Hero{


    public Mage(String heroName, boolean immune) {

        super(heroName, immune);
    }

    public void heroPower(Object object) {
        //minion
        if(object.getClass() == Minion.class){
            ((Minion) object).setHealth(((Minion) object).getHealth()-1);
        }
        //priest
        else if(object.getClass() == Priest.class) {
            ((Priest) object).setHealth(((Priest) object).getHealth() - 1);
        }
        //mage
        else if(object.getClass() == Mage.class) {
            ((Mage) object).setHealth(((Mage) object).getHealth() - 1);
        }
        //hunter
        else if(object.getClass() == Hunter.class) {
            ((Hunter) object).setHealth(((Hunter) object).getHealth() - 1);
        }
        //paladin
        else if(object.getClass() == Paladin.class) {
            ((Paladin) object).setHealth(((Paladin) object).getHealth() - 1);
        }
        //Warlock
        else if(object.getClass() == Warlock.class){
            ((Warlock) object).setHealth(((Warlock) object).getHealth()-1);
        }

    }

    @Override
    public String toString() {
        return "Mage";
    }
}
