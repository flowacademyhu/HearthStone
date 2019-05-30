package Cards;

import Actions.Fight;
import Heroes.Hero;

import java.util.List;
import java.util.ListIterator;

public class MinionEffects {

    Fight fight = new Fight();

    //battlecries

    public void addPlus1Plus1(Minion minion, List<Minion> board1, List<Minion> board2){

        int plus = board1.size() + board2.size();
        minion.setHealth(minion.getHealth() + plus);
        minion.setAttack(minion.getAttack() + plus);
    }

    public void silence(Minion minion) {

        if(minion.getEffect().equals("taunt")) {
            minion.setEffect("");
        }

        minion.setAttack(minion.getBasicAttack());

        if(minion.getHealth() > minion.getBasicHealth()) {
            minion.setHealth(minion.getBasicHealth());
        }
    }

    public void addPlus1Plus1AfterFriendly(Minion minion, List<Minion> board) {

        int plus = board.size();
        minion.setHealth(minion.getHealth() + plus);
        minion.setAttack(minion.getAttack() + plus);
    }

    public void add4HPMinion(Minion minion) {

        if (minion.getHealth() <= minion.getBasicHealth() - 4) {
            minion.setHealth(minion.getHealth() + 4);
        } else if (minion.getHealth() == minion.getBasicHealth() - 3) {
            minion.setHealth(minion.getHealth() + 3);
        }  else if (minion.getHealth() == minion.getBasicHealth() - 2) {
            minion.setHealth(minion.getHealth() + 2);
        }  else if (minion.getHealth() == minion.getBasicHealth() - 1) {
            minion.setHealth(minion.getHealth() + 1);
        }
    }

    public void add4HpHero(Hero hero) {

        if (hero.getHealth() <= 26) {
            hero.setHealth(hero.getHealth() + 4);
        } else if (hero.getHealth() == 27) {
            hero.setHealth(hero.getHealth() + 3);
        } else if (hero.getHealth() == 28) {
            hero.setHealth(hero.getHealth() + 2);
        } else if (hero.getHealth() == 29) {
            hero.setHealth(hero.getHealth() + 1);
        }
    }

    public void deal2(List<Minion> board1, List<Minion> board2) {

        ListIterator<Minion> listIterator1 = board1.listIterator();

        while(listIterator1.hasNext()) {

            Minion minion = listIterator1.next();

            minion.setHealth(minion.getHealth() - 2);
        }

        ListIterator<Minion> listIterator2 = board2.listIterator();

        while(listIterator2.hasNext()) {

            Minion minion = listIterator2.next();

            minion.setHealth(minion.getHealth() - 2);
        }

        fight.isMinionDied(board1);
        fight.isMinionDied(board2);
    }

    public void destroyAbove3(List<Minion> board1, List<Minion> board2){

        ListIterator<Minion> listIterator1 = board1.listIterator();

        while(listIterator1.hasNext()) {

            Minion minion = listIterator1.next();

            if (minion.getAttack() > 3) {
                listIterator1.remove();
            }
        }

        ListIterator<Minion> listIterator2 = board2.listIterator();

        while(listIterator2.hasNext()) {

            Minion minion = listIterator2.next();

            if (minion.getAttack() > 3) {
                listIterator2.remove();
            }
        }
    }
}
