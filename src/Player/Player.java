package Player;

import Cards.Card;
import Cards.Minion;
import Heroes.Hero;

import java.util.List;

public class Player {

    private List<Card> deck;
    private List<Card> hand;
    private List<Minion> board;
    private Hero hero;

    public Player(List<Card> deck, List<Card> hand, List<Minion> board, Hero hero) {
        this.deck = deck;
        this.hand = hand;
        this.hero = hero;
        this.board = board;
    }

    public List<Minion> getBoard() {
        return board;
    }

    public void setBoard(List<Minion> board) {
        this.board = board;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public String toString() {
        return "Player{" +
                "deck=" + deck + "\n" +
                ", hand=" + hand + "\n" +
                ", hero=" + hero +
                '}';
    }
}
