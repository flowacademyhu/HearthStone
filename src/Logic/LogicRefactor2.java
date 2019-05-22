package Logic;

import Cards.*;
import Actions.Fight;
import Heroes.*;
import Player.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LogicRefactor2 {

    Scanner scan = new Scanner(System.in);

    Player player1;
    Player player2;
    Player player;
    Player otherPlayer;

    boolean endTurn = false;

    int mana = 10;

    Hero hero1 = new Hunter("Hunter", false);
    Hero hero2 = new Mage("Mage", false);

    boolean isPlayer1Turn = true;
    boolean isPlayer2Turn = false;

    boolean gameEnded = false;
    boolean heroPowerUsed = false;

    boolean spellPressed = false;
    boolean minionPressed = false;

    boolean holyblessing = false;
    boolean cavalry = false;
    boolean flee = false;
    boolean traitor = false;
    boolean dragonfire = false;
    boolean frostbalst = false;
    boolean savethehero = false;

    SpellEffects spell = new SpellEffects();
    MinionEffects minion = new MinionEffects();

    Deck deck = new Deck();
    private List<Card> deck1 = new ArrayList<>(deck.deckBuilder());
    private List<Card> deck2 = new ArrayList<>(deck.deckBuilder());

    private List<Card> hand1 = new ArrayList<>();
    private List<Card> hand2 = new ArrayList<>();

    private List<Minion> board1 = new ArrayList<>();
    private List<Minion> board2 = new ArrayList<>();

    Fight fight = new Fight();

    String s;

    /*Hero hero1 = chooseHero();
    Hero hero2 = chooseHero();

    public Hero chooseHero1() {

    }*/

    public LogicRefactor2() {

        hand1 = deck.draw(3, hand1, deck1, hero1);
        hand2 = deck.draw(4, hand2, deck2, hero2);

        player1 = new Player(deck1, hand1, board1, hero1);
        player2 = new Player(deck2, hand2, board2, hero2);
    }

    //Getters

    public int getMana() {
        return mana;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getOtherPlayer() {
        return otherPlayer;
    }

    public boolean isMinionPressed() {
        return minionPressed;
    }

    public void setMinionPressed(boolean minionPressed) {
        this.minionPressed = minionPressed;
    }

    public boolean isSpellPressed() {
        return spellPressed;
    }

    public void setSpellPressed(boolean spellPressed) {
        this.spellPressed = spellPressed;
    }

    //change player
    public void changePlayer() {
        if (isPlayer1Turn) {
            player = player1;
            otherPlayer = player2;
        } else if (isPlayer2Turn) {
            player = player2;
            otherPlayer = player1;
        }
        endTurn = false;
    }

    //endGame
    public boolean endGame() {
        if (player2.getHero().getHealth() <= 0) {
            endTurn = true;
            isPlayer1Turn = false;
            isPlayer2Turn = false;
            return true;
        } else if (player1.getHero().getHealth() <= 0) {
            endTurn = true;
            isPlayer1Turn = false;
            isPlayer2Turn = false;
            return true;
        }
        return false;
    }

    //press endturn
    public void endTurn() {
        fight.makeCanNotAttack(player.getBoard());
        fight.makeCanAttack((otherPlayer.getBoard()));
        if (isPlayer1Turn) {
            isPlayer1Turn = false;
            isPlayer2Turn = true;
        } else {
            isPlayer2Turn = false;
            isPlayer1Turn = true;
        }
        player.getHero().setImmune(false);
        mana = 10;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        heroPowerUsed = false;
    }

    //play heropower
    public void heroPower() {
        if (mana >= 2 && !heroPowerUsed) {
            if (player.getHero().toString().equals("Hunter")) {

                player.getHero().heroPower(otherPlayer.getHero());

            } else if (player.getHero().toString().equals("Priest")) {

                player.getHero().heroPower(player.getBoard().get(Integer.parseInt(s)));

            } else if (player.getHero().toString().equals("Mage")) {

                player.getHero().heroPower(player.getBoard().get(Integer.parseInt(s)));
                fight.isMinionDied(player.getBoard());

            } else if (player.getHero().toString().equals("Paladin")) {

                Minion recruit = new Minion("Recruit", "", 1, 1, 1, 1, 1, 1, false, "");
                player.getBoard().add(recruit);

            } else if ((player.getHero().toString().equals("Warlock"))) {

                player.getHero().setHealth(player.getHero().getHealth() - 2);
                List<Card> drawedCards = new ArrayList<>();
                drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero());
                player.getHand().addAll(drawedCards);

            }
            heroPowerUsed = true;
            mana -= 2;
        }
    }


    //play card
    public void playCard(int i) {
        //play minion
        if (player.getHand().get(i).getClass() == Minion.class && player.getHand().get(i).getCost() <= mana && player.getBoard().size() < 5) {
            //battlecry
            if (player.getHand().get(i).getDescription().equals("battlecry: gets +1/+1 after every unit")) {
                minion.addPlus1Plus1((Minion) player.getHand().get(i), player.getBoard(), otherPlayer.getBoard());
            } else if (player.getHand().get(i).getDescription().equals("battlecry: silences a minion")) {
                if (!otherPlayer.getBoard().isEmpty() && !player.getBoard().isEmpty()) {
                    //TODO write ifs, the method is ready
                    //minion.silence();
                }
            } else if (player.getHand().get(i).getDescription().equals("battlecry: gets +1/+1 after every friendly unit")) {
                minion.addPlus1Plus1AfterFriendly((Minion) player.getHand().get(i), player.getBoard());
            } else if ((player.getHand().get(i).getDescription().equals("battlecry: gives 4 hp"))) {
                //TODO write ifs, the method is ready
                //if target is a minion
                //minion.add4HPMinion();
                //if target is a hero
                //minion.add4HpHero();
            } else if ((player.getHand().get(i).getDescription().equals("battlecry: draws 2 cards"))) {

                List<Card> drawedCards = new ArrayList<>();
                drawedCards = deck.draw(2, drawedCards, player.getHand(), player.getHero());

                player.getHand().addAll(drawedCards);

            } else if ((player.getHand().get(i).getDescription().equals("battlecry: gives 2 damage to every unit"))) {

                minion.deal2(player.getBoard(), otherPlayer.getBoard());

            } else if ((player.getHand().get(i).getDescription().equals("battlecry: destroys all minions above 3 damage"))) {

                minion.destroyAbove3(player.getBoard(), otherPlayer.getBoard());
            }
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(i));
            mana -= player.getHand().get(i).getCost();
            player.getHand().remove(player.getHand().get(i));

            //play spell
        } else if (player.getHand().get(i) instanceof Spell && player.getHand().get(i).getCost() <= mana) {

            spellPressed = true;

            if(((Spell) player.getHand().get(i)).getEffect().equals("holyblessing")) {
                holyblessing = true;
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("cavalry")) {
                cavalry = true;
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("flee")){
                flee = true;
            } else if  (((Spell) player.getHand().get(i)).getEffect().equals("traitor")){
                traitor = true;
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("dragonfire")) {
                dragonfire = true;
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("frostblast")) {
                frostbalst = true;
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("savethehero")) {
                savethehero = true;
            }

            mana -= ((Spell) player.getHand().get(i)).getCost();
        }
    }

    public void castSpellOnOwnMinion(int i) {
//TODO
                    if (holyblessing) {

                        spell.holyBlessing(i, player.getBoard());
                        player.getHand().remove(i);
                        holyblessing = false;

                    } else if (cavalry) {

                        spell.cavalry(i, player.getBoard());
                        player.getHand().remove(i);
                        cavalry = false;

                    } else if (flee) {

                        spell.flee(i, player.getBoard(), player.getHand());
                        player.getHand().remove(i);
                        flee = false;

                    } else if (traitor) {

                        spell.traitor(i, player.getBoard(), otherPlayer.getBoard());
                        player.getHand().remove(i);
                        traitor = false;

                    } else if (dragonfire) {

                        spell.dragonFireMinion(i, player.getBoard());
                        player.getHand().remove(i);
                        dragonfire = false;

                    } else if (frostbalst) {

                        spell.frostBlast(i, player.getBoard());
                        player.getHand().remove(i);
                        frostbalst = false;
                    }

                    spellPressed = false;
    }

    public void castSpellOnEnemyMinion(int i) {
 //TODO otherplayer
        if (holyblessing) {

            spell.holyBlessing(i, player.getBoard());
            player.getHand().remove(i);
            holyblessing = false;

        } else if (cavalry) {

            spell.cavalry(i, player.getBoard());
            player.getHand().remove(i);
            cavalry = false;

        } else if (flee) {

            spell.flee(i, player.getBoard(), player.getHand());
            player.getHand().remove(i);
            flee = false;

        } else if (traitor) {

            spell.traitor(i, player.getBoard(), otherPlayer.getBoard());
            player.getHand().remove(i);
            traitor = false;

        } else if (dragonfire) {

            spell.dragonFireMinion(i, player.getBoard());
            player.getHand().remove(i);
            dragonfire = false;

        } else if (frostbalst) {

            spell.frostBlast(i, player.getBoard());
            player.getHand().remove(i);
            frostbalst = false;
        }

        spellPressed = false;
    }
/*
    public void castSpellOnHero() {

                System.out.println("which hero? /1/2/");

                b = scan.nextLine();

                if (b.equals("1")) {

                    if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                        spell.dragonFireHero(player.getHero(), gameEnded);

                        System.out.println("-----------------------------");
                        System.out.println(player.getHero());
                        System.out.println(player.getHero().getHealth());
                        System.out.println("-----------------------------");

                    } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("savethehero")) {

                        spell.saveTheHero(player.getHero());

                        System.out.println("-----------------------------");
                        System.out.println(player.getHero());
                        System.out.println("-----------------------------");
                    }
                } else if (b.equals("2")) {

                    if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                        spell.dragonFireHero(otherPlayer.getHero(), gameEnded);

                        System.out.println("-----------------------------");
                        System.out.println(otherPlayer.getHero());
                        System.out.println("-----------------------------");

                    } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("savethehero")) {

                        spell.saveTheHero(otherPlayer.getHero());

                        System.out.println("-----------------------------");
                        System.out.println(otherPlayer.getHero());
                        System.out.println("-----------------------------");
                    }
                }
    }
}


                //attack minion minion
                if (s.equals("attackminion") && !player.getBoard().isEmpty() && !otherPlayer.getBoard().isEmpty()) {
                    String d;
                    System.out.println(player.getBoard());
                    System.out.println("choose a minion (number) to attack with");
                    s = scan.nextLine();
                    if (player.getBoard().get(Integer.parseInt(s)).isCanAttack()) {
                        System.out.println("choose a minion (number) to be attacked");
                        d = scan.nextLine();

                        if (otherPlayer.getBoard().contains((Minion) otherPlayer.getBoard().get(Integer.parseInt(d))) && fight.isThereTaunt(otherPlayer.getBoard()) && otherPlayer.getBoard().get(Integer.parseInt(d)).getEffect().equals("taunt")) {
                            player.getBoard().get(Integer.parseInt(s)).setCanAttack(false);
                            fight.attackMinion((Minion) player.getBoard().get(Integer.parseInt(s)), (Minion) otherPlayer.getBoard().get(Integer.parseInt(d)));
                            fight.isMinionDied(player.getBoard());
                            fight.isMinionDied(otherPlayer.getBoard());
                        }
                        else if (otherPlayer.getBoard().contains((Minion) otherPlayer.getBoard().get(Integer.parseInt(d))) && !fight.isThereTaunt(otherPlayer.getBoard())) {
                            player.getBoard().get(Integer.parseInt(s)).setCanAttack(false);
                            fight.attackMinion((Minion) player.getBoard().get(Integer.parseInt(s)), (Minion) otherPlayer.getBoard().get(Integer.parseInt(d)));
                            fight.isMinionDied(player.getBoard());
                            fight.isMinionDied(otherPlayer.getBoard());
                        }


                        System.out.println(player.getBoard());
                        System.out.println(otherPlayer.getBoard());
                        System.out.println("-----------------------------");
                    } else {
                        System.err.println("this minion can not attack this turn");
                    }
                }
                //attack minion hero
                if (s.equals("attackhero") && !otherPlayer.getHero().isImmune()) {
                    String d;
                    System.out.println("number of minion");
                    s = scan.nextLine();
                    if(player.getBoard().get(Integer.parseInt(s)).isCanAttack() && !fight.isThereTaunt(otherPlayer.getBoard())) {
                        player.getBoard().get(Integer.parseInt(s)).setCanAttack(false);
                        fight.attackHero((Minion) player.getBoard().get(Integer.parseInt(s)), otherPlayer.getHero());
                        System.out.println(otherPlayer.getHero().getHeroName() + "'s hp: " + otherPlayer.getHero().getHealth());
                        System.out.println("-----------------------------");
                    } else {
                        System.err.println("this minion can not attack this turn cuz of taunt");
                    }
                } */
}