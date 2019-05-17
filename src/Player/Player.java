package Player;

import Cards.Card;
import Heroes.Hero;

import java.util.List;

//TODO do I need this?? player1 and player2??? (for turns)

public class Player {

    private List<Card> deck;
    private List<Card> hand;
    private Hero hero;

    public Player(List<Card> deck, List<Card> hand, Hero hero) {
        this.deck = deck;
        this.hand = hand;
        this.hero = hero;
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
