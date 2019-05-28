package Logic;

import Actions.Happenings;
import Cards.*;
import Actions.Fight;
import Heroes.*;
import Player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Logic {

    Player player1;
    Player player2;
    Player player;
    Player otherPlayer;

    int mana = 10;

    Hero hero1;
    Hero hero2;

    boolean isPlayer1Turn = true;
    boolean isPlayer2Turn = false;

    boolean gameEnded = false;
    boolean heroPowerUsed = false;

    boolean spellPressed = false;
    boolean minionPressed = false;
    boolean heroPriestPressed = false;
    boolean heroMagePressed = false;

    Integer spellIndex;
    Integer minionIndex;

    boolean silenceActive = false;
    boolean healActive = false;

    boolean holyblessing = false;
    boolean cavalry = false;
    boolean flee = false;
    boolean traitor = false;
    boolean dragonfire = false;
    boolean frostbalst = false;
    boolean savethehero = false;

    SpellEffects spell = new SpellEffects();
    MinionEffects minion = new MinionEffects();

    //List<String> steps = new ArrayList<>();
    private String newline = "\n";
    private String steps = "Commands" + newline + "-------------";

    Deck deck = new Deck();
    private List<Card> deck1 = new ArrayList<>(deck.deckBuilder());
    private List<Card> deck2 = new ArrayList<>(deck.deckBuilder());

    private List<Card> hand1 = new ArrayList<>();
    private List<Card> hand2 = new ArrayList<>();

    private List<Minion> board1 = new ArrayList<>();
    private List<Minion> board2 = new ArrayList<>();

    Fight fight = new Fight();
    Happenings happenings = new Happenings();

    String s;

    /*Hero hero1 = chooseHero();
    Hero hero2 = chooseHero();

    public Hero chooseHero1() {

    }*/

    public Logic(Hero heroOne, Hero heroTwo) {

        hero1 = heroOne;
        hero2 = heroTwo;

        hand1 = deck.draw(3, hand1, deck1, hero1, hand1);
        hand2 = deck.draw(4, hand2, deck2, hero2, hand2);

        player1 = new Player(deck1, hand1, board1, hero1);
        player2 = new Player(deck2, hand2, board2, hero2);
    }

    //Getters, setters


 /*   public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }*/

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

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

    public boolean isHeroPriestPressed() {
        return heroPriestPressed;
    }

    public void setHeroPriestPressed(boolean heroPriestPressed) {
        this.heroPriestPressed = heroPriestPressed;
    }

    public boolean isHeroMagePressed() {
        return heroMagePressed;
    }

    public void setHeroMagePressed(boolean heroMagePressed) {
        this.heroMagePressed = heroMagePressed;
    }

    public boolean isSilenceActive() {
        return silenceActive;
    }

    public void setSilenceActive(boolean silenceActive) {
        this.silenceActive = silenceActive;
    }

    public boolean isHealActive() {
        return healActive;
    }

    public void setHealActive(boolean healActive) {
        this.healActive = healActive;
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
        steps += newline + "------------"  + "\n";
        steps += happenings.nextTurn(player.getHero());
        steps += newline + "------------"  + "\n";
    }

    //endGame
    public void endGame() {
        if (player2.getHero().getHealth() <= 0) {
            isPlayer1Turn = false;
            isPlayer2Turn = false;

            steps += newline + "------------"  + "\n";
            steps += happenings.endGame(player1.getHero());
            steps += newline + "------------"  + "\n";

            fight.makeCanNotAttack(player.getBoard());
            gameEnded = true;
            mana = 0;

        } else if (player1.getHero().getHealth() <= 0) {
            isPlayer1Turn = false;
            isPlayer2Turn = false;

            steps += newline + "------------"  + "\n";
            steps += happenings.endGame(player2.getHero());
            steps += newline + "------------"  + "\n";

            fight.makeCanNotAttack(player.getBoard());
            gameEnded = true;
            mana = 0;
        }
    }

    //press endturn
    public void endTurn() {
        if (!gameEnded) {

            fight.makeCanNotAttack(player.getBoard());

            for (Minion minion : player.getBoard()) {
                if(minion.isFreezed()) {
                    minion.setFreezed(false);
                    minion.setCanAttack(false);
                } else {
                    minion.setCanAttack(false);
                }
            }

            for(Minion minion : otherPlayer.getBoard()){
                if(minion.isFreezed()){
                    minion.setCanAttack(false);
                } else {
                    minion.setCanAttack(true);
                }
            }

            if (otherPlayer.getHero().isImmune()) {
                otherPlayer.getHero().setImmune(false);
            }

            if (isPlayer1Turn) {
                isPlayer1Turn = false;
                isPlayer2Turn = true;
            } else {
                isPlayer2Turn = false;
                isPlayer1Turn = true;
            }

            List<Card> drawedCards = new ArrayList<>();
            drawedCards = deck.draw(1, drawedCards, otherPlayer.getDeck(), otherPlayer.getHero(), otherPlayer.getHand());
            otherPlayer.getHand().addAll(drawedCards);

            mana = 10;
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spellPressed = false;
            minionPressed = false;
            heroPriestPressed = false;
            heroMagePressed = false;
            spellIndex = null;
            minionIndex = null;
            silenceActive = false;
            healActive = false;
            holyblessing = false;
            cavalry = false;
            flee = false;
            traitor = false;
            dragonfire = false;
            frostbalst = false;
            savethehero = false;
            heroPowerUsed = false;
        }
    }

    //play heropower
    public void heroPower() {
        if (mana >= 2 && !heroPowerUsed) {
            if (player.getHero().toString().equals("Hunter")) {

                player.getHero().heroPower(otherPlayer.getHero());
                heroPowerUsed();

            } else if (player.getHero().toString().equals("Priest")) {

                heroPriestPressed = true;
                heroPowerUsed();

            } else if (player.getHero().toString().equals("Mage")) {

                heroMagePressed = true;
                heroPowerUsed();

            } else if (player.getHero().toString().equals("Paladin") && player.getBoard().size() < 5) {

                Minion recruit = new Minion("Recruit", "", 1, 1, 1, 1, 1, 1, false, "", false);
                player.getBoard().add(recruit);
                heroPowerUsed();

            } else if ((player.getHero().toString().equals("Warlock"))) {

                if(!player.getHero().isImmune()) {
                    player.getHero().setHealth(player.getHero().getHealth() - 2);
                }
                List<Card> drawedCards = new ArrayList<>();
                drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero(), player.getHand());
                player.getHand().addAll(drawedCards);
                heroPowerUsed();
            }

        }
    }

    public void heroPowerUsed() {

        steps += happenings.whatHappenedHero(player.getHero());
        heroPowerUsed = true;
        mana -= 2;

    }

    public void mageDamagesMyMinion(int i) {
        player.getBoard().get(i).setHealth(player.getBoard().get(i).getHealth() - 1);
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), player.getBoard().get(i));
        fight.isMinionDied(player.getBoard());
        steps += fight.getSteps();
        //fight.setSteps(new ArrayList<>());
        heroMagePressed = false;
    }

    public void mageDamagesEnemyMinion(int i) {
        otherPlayer.getBoard().get(i).setHealth(otherPlayer.getBoard().get(i).getHealth() - 1);
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), otherPlayer.getBoard().get(i));
        fight.isMinionDied(otherPlayer.getBoard());
        steps += fight.getSteps();
        //fight.setSteps(new ArrayList<>());
        heroMagePressed = false;
    }

    public void mageDamagesMyHero() {
        player.getHero().setHealth(player.getHero().getHealth() - 1);
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), player.getHero());
        heroMagePressed = false;
    }

    public void mageDamagesEnemyHero() {
        otherPlayer.getHero().setHealth(otherPlayer.getHero().getHealth() - 1);
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), otherPlayer.getHero());
        heroMagePressed = false;
    }

    public void priestHealsMyMinion(int i) {
        if(player.getBoard().get(i).getMaxHealth() >= player.getBoard().get(i).getHealth() + 2) {
            player.getBoard().get(i).setHealth(player.getBoard().get(i).getHealth() + 2);
        } else if(player.getBoard().get(i).getMaxHealth() >= player.getBoard().get(i).getHealth() + 1) {
            player.getBoard().get(i).setHealth(player.getBoard().get(i).getHealth() + 1);
        }
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), player.getBoard().get(i));
        heroPriestPressed = false;
    }

    public void priestHealsEnemyMinion(int i) {
        if(otherPlayer.getBoard().get(i).getMaxHealth() >= otherPlayer.getBoard().get(i).getHealth() + 2) {
            otherPlayer.getBoard().get(i).setHealth(otherPlayer.getBoard().get(i).getHealth() + 2);
        } else if(otherPlayer.getBoard().get(i).getMaxHealth() >= otherPlayer.getBoard().get(i).getHealth() + 1) {
            otherPlayer.getBoard().get(i).setHealth(otherPlayer.getBoard().get(i).getHealth() + 1);
        }
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), otherPlayer.getBoard().get(i));
        heroPriestPressed = false;
    }

    public void priestHealsMyHero() {
        if (player.getHero().getHealth() < 29) {
            player.getHero().setHealth(player.getHero().getHealth() + 2);
        } else if (player.getHero().getHealth() == 29) {
            player.getHero().setHealth(player.getHero().getHealth() + 1);
        }
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), player.getHero());
        heroPriestPressed = false;
    }

    public void priestHealsEnemyHero() {
        if (otherPlayer.getHero().getHealth() < 29) {
            otherPlayer.getHero().setHealth(otherPlayer.getHero().getHealth() + 2);
        } else if (otherPlayer.getHero().getHealth() == 29) {
            otherPlayer.getHero().setHealth(otherPlayer.getHero().getHealth() + 1);
        }
        steps += happenings.whatHappenedMageOrPriest(player.getHero(), otherPlayer.getHero());
        heroPriestPressed = false;
    }


    //play card
    public void playCard(int i) {
        minionIndex = i;
        //play minion
        if (player.getHand().get(i).getClass() == Minion.class && player.getHand().get(i).getCost() <= mana && player.getBoard().size() < 5 && !player.getHand().get(i).getDescription().equals("battlecry: silences a minion") && !player.getHand().get(i).getDescription().equals("battlecry: gives 4 hp")) {
            //battlecry
            if (player.getHand().get(i).getDescription().equals("battlecry: gets +1/+1 after every unit")) {

                minion.addPlus1Plus1((Minion) player.getHand().get(i), player.getBoard(), otherPlayer.getBoard());

            } else if (player.getHand().get(i).getDescription().equals("battlecry: gets +1/+1 after every friendly unit")) {

                minion.addPlus1Plus1AfterFriendly((Minion) player.getHand().get(i), player.getBoard());

            } else if ((player.getHand().get(i).getDescription().equals("battlecry: draws 2 cards"))) {

                List<Card> drawedCards = new ArrayList<>();
                drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero(), player.getHand());

                player.getHand().addAll(drawedCards);

            } else if ((player.getHand().get(i).getDescription().equals("battlecry: gives 2 damage to every unit"))) {

                minion.deal2(player.getBoard(), otherPlayer.getBoard());

            } else if ((player.getHand().get(i).getDescription().equals("battlecry: destroys all minions above 3 damage"))) {

                minion.destroyAbove3(player.getBoard(), otherPlayer.getBoard());
            }
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(i));
            mana -= player.getHand().get(i).getCost();
            steps += happenings.whatHappenedMinion((Minion)player.getHand().get(i));
            player.getHand().remove(player.getHand().get(i));


        }
        else if ((player.getHand().get(i).getDescription().equals("battlecry: gives 4 hp") && player.getHand().get(i).getCost() <= mana)) {

                if (!otherPlayer.getBoard().isEmpty() || !player.getBoard().isEmpty()) {

                    healActive = true;
                    minionIndex = i;

                } else {
                    deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(i));
                    mana -= player.getHand().get(i).getCost();
                    player.getHand().remove(player.getHand().get(i));
                }
            } else if (player.getHand().get(i).getDescription().equals("battlecry: silences a minion") && player.getHand().get(i).getCost() <= mana) {
                if (!otherPlayer.getBoard().isEmpty() || !player.getBoard().isEmpty()) {

                    silenceActive = true;
                    minionIndex = i;

                } else {
                    deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(i));
                    mana -= player.getHand().get(i).getCost();
                    player.getHand().remove(player.getHand().get(i));
                }
            }
        //play spell
        else if (player.getHand().get(i) instanceof Spell && player.getHand().get(i).getCost() <= mana) {

            spellPressed = true;
            spellIndex = i;


            if(((Spell) player.getHand().get(i)).getEffect().equals("holyblessing")) {
                holyblessing = true;
                System.out.println("a");
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("cavalry")) {
                cavalry = true;
                System.out.println("b");
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("flee")){
                flee = true;
                System.out.println("c");
            } else if  (((Spell) player.getHand().get(i)).getEffect().equals("traitor")){
                traitor = true;
                System.out.println("d");
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("dragonfire")) {
                dragonfire = true;
                System.out.println("e");
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("frostblast")) {
                frostbalst = true;
                System.out.println("f");
            } else if (((Spell) player.getHand().get(i)).getEffect().equals("savethehero")) {
                savethehero = true;
                System.out.println("g");
            }

            steps += happenings.whatHappenedSpell((Spell)player.getHand().get(i));
            mana -= ((Spell) player.getHand().get(i)).getCost();
        }
    }

    public void silenceMyMinion(int i) {

        if(player.getBoard().size() < 5) {
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(minionIndex));
            mana -= player.getHand().get(minionIndex).getCost();

            minion.silence(player.getBoard().get(i));
            player.getHand().remove(player.getHand().get(minionIndex));
        }
        silenceActive = false;
    }

    public void silenceEnemyMinion(int i) {

        if(player.getBoard().size() < 5) {
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(minionIndex));
            mana -= player.getHand().get(minionIndex).getCost();

            minion.silence(otherPlayer.getBoard().get(i));
            player.getHand().remove(player.getHand().get(minionIndex));
        }
        silenceActive = false;
    }

    public  void healMyMinion(int i) {

        if(player.getBoard().size() < 5) {
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(minionIndex));
            mana -= player.getHand().get(minionIndex).getCost();

            minion.add4HPMinion(player.getBoard().get(i));
            player.getHand().remove(player.getHand().get(minionIndex));
        }
        healActive = false;
    }

    public  void healEnemyMinion(int i) {

        if(player.getBoard().size() < 5) {
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(minionIndex));
            mana -= player.getHand().get(minionIndex).getCost();

            minion.add4HPMinion(otherPlayer.getBoard().get(i));
            player.getHand().remove(player.getHand().get(minionIndex));
        }
        healActive = false;
    }

    public void healMyHero() {

        if(player.getBoard().size() < 5) {
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(minionIndex));
            mana -= player.getHand().get(minionIndex).getCost();

            minion.add4HpHero(player.getHero());
            player.getHand().remove(player.getHand().get(minionIndex));
        }
        healActive = false;
    }

    public void healEnemyHero() {

        if(player.getBoard().size() < 5) {
            deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(minionIndex));
            mana -= player.getHand().get(minionIndex).getCost();

            minion.add4HpHero(otherPlayer.getHero());
            player.getHand().remove(player.getHand().get(minionIndex));
        }
        healActive = false;
    }

    public void deactivateSpells() {
        holyblessing = false;
        cavalry = false;
        flee = false;
        traitor = false;
        dragonfire = false;
        frostbalst = false;
        savethehero = false;
    }

    public void castSpellOnOwnMinion(int i) {

        int index = spellIndex.intValue();

                    if (holyblessing) {

                        spell.holyBlessing(i, player.getBoard());
                        player.getHand().remove(index);
                        holyblessing = false;

                    } else if (cavalry) {

                        spell.cavalry(i, player.getBoard());
                        player.getHand().remove(index);
                        cavalry = false;

                    } else if (flee) {

                        player.getHand().remove(index);
                        spell.flee(i, player.getBoard(), player.getHand());
                        flee = false;

                    } else if (traitor) {

                        traitor = false;

                    } else if (dragonfire) {

                        spell.dragonFireMinion(i, player.getBoard());
                        player.getHand().remove(index);
                        dragonfire = false;

                    } else if (frostbalst) {

                        spell.frostBlast(i, player.getBoard());
                        player.getHand().remove(index);
                        frostbalst = false;
                    }

                    spellPressed = false;
                    deactivateSpells();
    }

    public void castSpellOnEnemyMinion(int i) {

        int index = spellIndex.intValue();

        if (holyblessing) {

            spell.holyBlessing(i, otherPlayer.getBoard());
            player.getHand().remove(index);
            holyblessing = false;

        } else if (cavalry) {

            spell.cavalry(i, otherPlayer.getBoard());
            player.getHand().remove(index);
            cavalry = false;

        } else if (flee) {

            player.getHand().remove(index);
            spell.flee(i, otherPlayer.getBoard(), otherPlayer.getHand());
            flee = false;

        } else if (traitor) {

            spell.traitor(i, otherPlayer.getBoard(), player.getBoard());
            player.getHand().remove(index);
            traitor = false;

        } else if (dragonfire) {

            spell.dragonFireMinion(i, otherPlayer.getBoard());
            player.getHand().remove(index);
            dragonfire = false;

        } else if (frostbalst) {

            spell.frostBlast(i, otherPlayer.getBoard());
            player.getHand().remove(index);
            frostbalst = false;
        }

        spellPressed = false;
        deactivateSpells();
    }

    public void castSpellOnMyHero() {

        int index = spellIndex.intValue();

        if (dragonfire) {

            spell.dragonFireHero(player.getHero(), gameEnded);
            dragonfire = false;
            player.getHand().remove(player.getHand().get(index));

        } else if (savethehero) {

            spell.saveTheHero(player.getHero());
            savethehero = false;
            player.getHand().remove(player.getHand().get(index));

        }
        deactivateSpells();
    }

        public void castSpellOnEnemyHero() {

            int index = spellIndex.intValue();

            if (dragonfire) {

                spell.dragonFireHero(otherPlayer.getHero(), gameEnded);
                dragonfire = false;
                player.getHand().remove(player.getHand().get(index));

            } else if (savethehero) {

                spell.saveTheHero(otherPlayer.getHero());
                savethehero = false;
                player.getHand().remove(player.getHand().get(index));

            }
            deactivateSpells();
        }

        //attack minion
        public void selectAttackerMinion(int i) {

            if (player.getBoard().get(i).isCanAttack() && player.getBoard().get(i).getAttack() > 0) {

                minionPressed = true;

                minionIndex = i;

            }

        }

        public void attackMinion(int i) {

            if (otherPlayer.getBoard().contains(otherPlayer.getBoard().get(i)) && fight.isThereTaunt(otherPlayer.getBoard()) && otherPlayer.getBoard().get(i).getEffect().equals("taunt")) {
                player.getBoard().get(minionIndex).setCanAttack(false);
                fight.attackMinion((Minion) player.getBoard().get(minionIndex), (Minion) otherPlayer.getBoard().get(i));
                steps += happenings.setWhatHappenedMinionAttack((Minion) player.getBoard().get(minionIndex), (Minion) otherPlayer.getBoard().get(i));
                fight.isMinionDied(player.getBoard());
                fight.isMinionDied(otherPlayer.getBoard());
                steps += fight.getSteps();
                //fight.setSteps(new ArrayList<>());
            }
            else if (otherPlayer.getBoard().contains((Minion) otherPlayer.getBoard().get(i)) && !fight.isThereTaunt(otherPlayer.getBoard())) {
                player.getBoard().get(minionIndex).setCanAttack(false);
                fight.attackMinion((Minion) player.getBoard().get(minionIndex), (Minion) otherPlayer.getBoard().get(i));
                steps += happenings.setWhatHappenedMinionAttack((Minion) player.getBoard().get(minionIndex), (Minion) otherPlayer.getBoard().get(i));
                fight.isMinionDied(player.getBoard());
                fight.isMinionDied(otherPlayer.getBoard());
                steps += fight.getSteps();
                //fight.setSteps(new ArrayList<>());
            }
            minionPressed = false;
        }

        //attack hero
        public void attackHero() {

            if(!otherPlayer.getHero().isImmune() && !fight.isThereTaunt(otherPlayer.getBoard())) {

                player.getBoard().get(minionIndex).setCanAttack(false);
                steps += happenings.setWhatHappenedMinionAttack((Minion) player.getBoard().get(minionIndex), otherPlayer.getHero());
                fight.attackHero((Minion) player.getBoard().get(minionIndex), otherPlayer.getHero());

            }
            minionPressed = false;
        }
    }