package Player;

import Cards.Card;
import Cards.CardList;
import Heroes.Hero;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private CardList cards;

    private List<Card> deck;
    private List<Card> allCards;
    private Random randomCard;

    //builds a 30 card deck
    public List<Card> deckBuilder() {

        cards = new CardList();

        allCards = new ArrayList<>(cards.cardList());

        deck = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            randomCard = new Random();
            int id = randomCard.nextInt(allCards.size());
            deck.add(allCards.get(id));
            allCards.remove(id);
        }

        return deck;
    }

    //draws x cards || if deck is empty, damages the hero || burns card if you have 10 cards
    public List<Card> draw(int number, List<Card>  hand,List<Card> deck, Hero hero) {

        for (int i = 0; i < number; i++) {
            if(deck.size() > 0 && hand.size() == 10) {
                deck.remove(0);
            } else if (deck.size() > 0) {
                hand.add(deck.get(0));
                deck.remove(0);
            } else {
                hero.setHealth(hero.getHealth() - 1);
            }
        }

        return hand;

    }

}
