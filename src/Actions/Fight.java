package Actions;

import Cards.Minion;
import Heroes.Hero;

import java.util.List;
import java.util.ListIterator;

public class Fight {

    Happenings happenings = new Happenings();
    private String steps;

    public String getSteps() {
        return steps;
    }

    //minion vs minion
    public void attackMinion(Minion minionAttack, Minion minionDef) {
        minionAttack.setHealth(minionAttack.getHealth() - minionDef.getAttack());
        minionDef.setHealth(minionDef.getHealth() - minionAttack.getAttack());
    }

    //minion vs hero
    public void attackHero(Minion minion, Hero hero) {

        hero.setHealth(hero.getHealth() - minion.getAttack());
    }

    //minion dies
    public void isMinionDied(List<Minion> board){

        steps = "";

        ListIterator<Minion> listIterator = board.listIterator();

        while (listIterator.hasNext()) {

            Minion minion = listIterator.next();

            if (minion.getHealth() <= 0) {

                steps += happenings.whatHappenedMinionKill(minion) + "\n";
                listIterator.remove();

            }
        }
    }

    //taunt finder
    public boolean isThereTaunt(List<Minion> board) {

        for (Minion minion : board) {
            if (minion.getEffect().equals("taunt")) {
                return true;
            }
        }

        return false;

    }

    //make minions "can attack"
    public void makeCanAttack(List<Minion> board) {

        for (Minion minion : board) {
            minion.setCanAttack(true);
        }

    }

    //make minions "can not attack"
    public void makeCanNotAttack(List<Minion> board) {

        for (Minion minion : board) {
            minion.setCanAttack(false);
        }

    }
}
