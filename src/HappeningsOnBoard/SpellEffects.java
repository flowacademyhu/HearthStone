package HappeningsOnBoard;

import Cards.Card;
import Cards.Minion;
import Heroes.Hero;

import java.util.List;

public class SpellEffects {

    Fight fight = new Fight();

    //spells on minion

    public void holyBlessing(int i, List<Minion> board) {
            board.get(i).setHealth(board.get(i).getHealth() + 3);
            board.get(i).setAttack(board.get(i).getAttack() + 3);
    }

    public void cavalry(int i, List<Minion> board) {

        if (i != 0) {

            board.get(i - 1).setAttack(board.get(i - 1).getAttack() + 1);
            board.get(i + 1).setHealth(board.get(i + 1).getHealth() + 1);

        } else {

            board.get(i + 1).setHealth(board.get(i + 1).getHealth() + 1);
        }
    }

    public void flee(int i, List<Minion> board, List<Card> hand){

        hand.add(board.get(i));

        board.remove(board.get(i));

    }

    public void traitor(int i, List<Minion> board1, List<Minion> board2){

        board2.add(board1.get(i));

        board1.remove(board1.get(i));

    }

    public void dragonFireMinion(int i, List<Minion> board) {

        board.get(i).setHealth(board.get(i).getHealth() - 4);

        fight.isMinionDied(board);
    }



    public void frostBlast(int i, List<Minion> board) {
         //TODO
        board.get(i).setCanAttack(false);
    }

    //spells on hero

    public void dragonFireHero(Hero hero) {

        hero.setHealth(hero.getHealth() - 4);
        //TODO is dead?!
    }

    public void saveTheHero(Hero hero) {
        //TODO
    }
}
