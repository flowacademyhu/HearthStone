package Logic;

import Actions.Happenings;
import Cards.Card;
import Cards.Minion;
import Cards.MinionEffects;
import Cards.Spell;
import Player.Player;
import Player.Deck;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class PlayCardProcessor {
   private Card card ;
   private Player player;
   private MinionEffects minion;
    private Player otherPlayer;
    private Deck deck;
    private int mana;
    private List<String> steps;
    private Happenings happenings;
    private int minionIndex;
    private boolean healActive;
    private int i;
    private boolean silenceActive;
    private boolean holyblessing;
    private boolean cavalry;
    private boolean flee;
    private boolean traitor;
    private boolean dragonfire;
    private boolean frostbalst;
    private boolean savethehero;
    private boolean spellPressed;
    private int spellIndex;


    //play card
    PlayCardProcessor(Player player,Player otherPlayer,int i,int mana,List<String> steps,
        boolean healActive,boolean silenceActive,
        boolean holyblessing,boolean cavalry,boolean flee,boolean traitor,
        boolean dragonfire,boolean frostbalst,boolean savethehero,
        boolean spellPressed)
    {
        card = player.getHand().get(i);
        this.player = player;
        this.otherPlayer = otherPlayer;
        this.i = i;
        this.mana = mana;
        this.steps = steps;
        this.healActive = healActive;
        this.minionIndex = i;
        this.silenceActive = silenceActive;
        this.holyblessing = holyblessing;
        this.cavalry = cavalry;
        this.flee = flee;
        this.traitor = traitor;
        this.dragonfire = dragonfire;
        this.savethehero = savethehero;
        this.spellPressed = spellPressed;
        this.frostbalst = frostbalst;
        this.spellIndex = i;


    }
    public boolean compareDescription(String text) {
       return card.getDescription().equals(text);
    }

    public boolean compareEffect(String text) {
        return ((Spell)card).getEffect().equalsIgnoreCase(text);
    }

    private void playMinion() {


        if (compareDescription("battlecry: gets +1/+1 after every unit")) {

            minion.addPlus1Plus1((Minion) card, player.getBoard(), otherPlayer.getBoard());

        } else if (compareDescription("battlecry: gets +1/+1 after every friendly unit")) {

            minion.addPlus1Plus1AfterFriendly((Minion) card, player.getBoard());

        } else if (compareDescription("battlecry: draws 2 cards")) {

            List<Card> drawedCards = new ArrayList<>();
            drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero());

            player.getHand().addAll(drawedCards);

        } else if (compareDescription("battlecry: gives 2 damage to every unit")) {

            minion.deal2(player.getBoard(), otherPlayer.getBoard());

        } else if (compareDescription("battlecry: destroys all minions above 3 damage")) {

            minion.destroyAbove3(player.getBoard(), otherPlayer.getBoard());
        }
        deck.addCardToBoard(player.getBoard(), (Minion) card);
        mana -= card.getCost();
        steps.add(happenings.whatHappenedMinion((Minion) card));
        player.getHand().remove(card);

    }

    public void playSpecialMinion(int i) {
        if (!otherPlayer.getBoard().isEmpty() && !player.getBoard().isEmpty()) {

            healActive = true;
            minionIndex = i;

        } else {
            deck.addCardToBoard(player.getBoard(), (Minion) card);
            mana -= card.getCost();
            player.getHand().remove(card);
        }

    }

    public void process() {

        //play minion


        if (card.getClass() == Minion.class && card.getCost() <= mana && player.getBoard().size() < 5 && !card.getDescription().equals("battlecry: silences a minion") && !card.getDescription().equals("battlecry: gives 4 hp")) {
            //battlecry
            playMinion();
        } else if (compareDescription("battlecry: gives 4 hp")) {
            playSpecialMinion(i);
        } else if (compareDescription("battlecry: silences a minion")) {
            playSpell();
        }
        //play spell
        else if (card instanceof Spell && card.getCost() <= mana) {
            doSomething();
        }
    }

    private void playSpell() {
        if (!otherPlayer.getBoard().isEmpty() && !player.getBoard().isEmpty()) {

            silenceActive = true;
            minionIndex = i;

        } else {
            deck.addCardToBoard(player.getBoard(), (Minion) card);
            mana -= card.getCost();
            player.getHand().remove(card);
        }

    }

    private void doSomething() {

        spellPressed = true;
        spellIndex = i;


        if (compareEffect("holyblessing")) {
            holyblessing = true;
        } else if (compareEffect("cavalry")) {
            cavalry = true;
        } else if (compareEffect("flee")) {
            flee = true;
        } else if (compareEffect("traitor")) {
            traitor = true;
        } else if (compareEffect("dragonfire")) {
            dragonfire = true;
        } else if (compareEffect("frostblast")) {
            frostbalst = true;
        } else if (compareEffect("savethehero")) {
            savethehero = true;
        }

        steps.add(happenings.whatHappenedSpell((Spell) card));
        mana -= ((Spell) card).getCost();
    }



}