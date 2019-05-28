package Cards;

import Actions.Fight;
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

        try {
            if (i == board.size()-1) {

                board.get(i - 1).setHealth(board.get(i - 1).getHealth() + 1);
                board.get(i - 1).setAttack(board.get(i - 1).getAttack() + 1);

            } else if (i != 0) {

                board.get(i - 1).setAttack(board.get(i - 1).getAttack() + 1);
                board.get(i - 1).setHealth(board.get(i - 1).getHealth() + 1);

                board.get(i + 1).setHealth(board.get(i + 1).getHealth() + 1);
                board.get(i + 1).setAttack(board.get(i + 1).getAttack() + 1);

            } //else if (board.size() == 1) { }
            else {

                board.get(i + 1).setHealth(board.get(i + 1).getHealth() + 1);
                board.get(i + 1).setAttack(board.get(i + 1).getAttack() + 1);

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("no use of that move");
        }
    }

    public void flee(int i, List<Minion> board, List<Card> hand){

        if(hand.size() < 10) {
            board.get(i).setHealth(board.get(i).getBasicHealth());
            board.get(i).setAttack(board.get(i).getBasicAttack());
            board.get(i).setCanAttack(false);

            hand.add(board.get(i));
        }
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
        board.get(i).setFreezed(true);
    }

    //spells on hero

    public void dragonFireHero(Hero hero, boolean endGame) {

        hero.setHealth(hero.getHealth() - 4);

        if(hero.getHealth() <= 0) {
            endGame = true;
        }
    }

    public void saveTheHero(Hero hero) {
         hero.setImmune(true);
    }
}
