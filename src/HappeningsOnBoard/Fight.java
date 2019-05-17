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
    public List<Minion> isMinionDied(List<Minion> board){

        /*for (Minion minion : board) {
            if(minion.getHealth()>=0){
                board.remove(minion);
                System.out.println(minion + "died");
            }
        }*/

        ListIterator<Minion> listIterator = board.listIterator();

        while(listIterator.hasNext()) {
            if(listIterator.next().getHealth() <= 0){

                System.out.println(listIterator.next() + "died");

                listIterator.remove();

            }
        }

        return board;

    }
}
