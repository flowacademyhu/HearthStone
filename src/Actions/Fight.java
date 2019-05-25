package Actions;

import Cards.Minion;
import Heroes.Hero;
import Logic.LogicRefactor2;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Fight {

    Happenings happenings = new Happenings();
    List<String> steps = new ArrayList<>();

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
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

        ListIterator<Minion> listIterator = board.listIterator();

        while (listIterator.hasNext()) {

            Minion minion = listIterator.next();

            if (minion.getHealth() <= 0) {

                listIterator.remove();
                steps.add(happenings.whatHappenedMinionKill(minion));

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
