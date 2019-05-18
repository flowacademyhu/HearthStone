package HappeningsOnBoard;

import Cards.Minion;
import Heroes.Hero;

import java.util.List;
import java.util.ListIterator;

public class Fight {

    //minion vs minion
    public void attackMinion(Minion minionAttack, Minion minionDef) {
        minionAttack.setHealth(minionAttack.getHealth() - minionDef.getAttack());
        minionDef.setHealth(minionDef.getHealth() - minionAttack.getAttack());
        /*minionAttack.setText(minionAttack.getCost() + "/" + minionAttack.getName() + "/" + minionAttack.getAttack() + "/" + minionAttack.getHealth());
        minionDef.setText(minionDef.getCost() + "/" + minionDef.getName() + "/" + minionDef.getAttack() + "/" + minionDef.getHealth());*/
    }

    //minion vs hero
    public void attackHero(Minion minion, Hero hero) {

        hero.setHealth(hero.getHealth() - minion.getAttack());
    }

    //minion dies
    public void isMinionDied(List<Minion> board){

        //TODO rosszat töröl pl.: 2 minion küzöl az első hal a másodikat törli, 1 minionnál nem működik

        ListIterator<Minion> listIterator = board.listIterator();

        while(listIterator.hasNext()) {
            if(listIterator.next().getHealth() <= 0){

                System.out.println(listIterator.next() + "died");

                listIterator.remove();

            }
        }
    }
}
