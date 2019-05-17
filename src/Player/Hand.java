package Player;

import Cards.Card;
import Cards.Minion;
import Cards.Spell;
import Heroes.Hero;

import java.util.List;

public class Hand {

    //put minion on board
    public void addCardToBoard(List<Minion> board, Minion minion) {
        board.add(minion);
    }

}
