package Heroes;

import Board.Board;
import Cards.Card;
import Cards.Minion;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Priest extends Hero {

    public Priest(String heroName) {
        super(heroName);
    }

    //heals 2
    public void heroPower(Object object) {
        //minion
        if(object.getClass() == Minion.class && ((Minion) object).getMaxHealth() <= ((Minion) object).getHealth()-2){
            ((Minion) object).setHealth(((Minion) object).getHealth()+2);
        }else if(object.getClass() == Minion.class && ((Minion) object).getMaxHealth() <= ((Minion) object).getHealth()-1){
            ((Minion) object).setHealth(((Minion) object).getHealth()+1);
        //priest
        }else if(object.getClass() == Priest.class && ((Priest) object).getHealth() < 29){
            ((Priest) object).setHealth(((Priest) object).getHealth()+2);
        }else if(object.getClass() == Priest.class && ((Priest) object).getHealth() == 29){
            ((Priest) object).setHealth(((Priest) object).getHealth()+1);
        //mage
        } else if(object.getClass() == Mage.class && ((Mage) object).getHealth() < 29){
            ((Mage) object).setHealth(((Mage) object).getHealth()+2);
        } else if(object.getClass() == Mage.class && ((Mage) object).getHealth() == 29){
            ((Mage) object).setHealth(((Mage) object).getHealth()+1);
        //hunter
        } else if(object.getClass() == Hunter.class && ((Hunter) object).getHealth() < 29){
            ((Hunter) object).setHealth(((Hunter) object).getHealth()+2);
        } else if(object.getClass() == Hunter.class && ((Hunter) object).getHealth() == 29){
            ((Hunter) object).setHealth(((Hunter) object).getHealth()+1);
        //paladin
        } else if(object.getClass() == Paladin.class && ((Paladin) object).getHealth() < 29){
            ((Paladin) object).setHealth(((Paladin) object).getHealth()+2);
        } else if(object.getClass() == Paladin.class && ((Paladin) object).getHealth() == 29){
            ((Paladin) object).setHealth(((Paladin) object).getHealth()+1);
        //Warlock
        } else if(object.getClass() == Warlock.class && ((Warlock) object).getHealth() < 29){
            ((Warlock) object).setHealth(((Warlock) object).getHealth()+2);
        } else if(object.getClass() == Warlock.class && ((Warlock) object).getHealth() == 29){
            ((Warlock) object).setHealth(((Warlock) object).getHealth()+1);
        }
    }

    //TODO heropower
}
