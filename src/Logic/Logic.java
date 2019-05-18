package Logic;

import Cards.*;
import HappeningsOnBoard.Fight;
import Heroes.*;
import Player.Deck;
import Player.Player;
import Player.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Logic {

    Scanner scan = new Scanner(System.in);

    boolean start = true;
    boolean start2 = true;

    Player player1;
    Player player2;

    boolean endTurn = false;

    Hero hero1;
    Hero hero2;

    int mana = 10;

    boolean isPlayer1Turn = true;
    boolean isPlayer2Turn = false;

    boolean gameEnded = false;
    boolean heroPowerUsed = false;

    Hand hand = new Hand();

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

    public void startTheGame() {

        while (start) {

            System.out.println("choose hero");

            System.out.println("player1:");

            s = scan.nextLine();

            if (s.equals("Hunter")) {
                hero1 = new Hunter("Hunter", false);
                start = false;
            } else if (s.equals("Mage")) {
                hero1 = new Mage("Mage", false);
                start = false;
            } else if (s.equals("Paladin")) {
                hero1 = new Paladin("Paladin", false);
                start = false;
            } else if (s.equals("Priest")) {
                hero1 = new Priest("Priest", false);
                start = false;
            } else if (s.equals("Warlock")) {
                hero1 = new Warlock("Warlock", false);
                start = false;
            } else {
                System.out.println("nincs ilyen hero");
                s = scan.nextLine();
            }

        }

        System.out.println(hero1);

        while (start2) {

            System.out.println("player2:");

            s = scan.nextLine();

            if (s.equals("Hunter")) {
                hero2 = new Hunter("Hunter", false);
                start2 = false;
            } else if (s.equals("Mage")) {
                hero2 = new Mage("Mage", false);
                start2 = false;
            } else if (s.equals("Paladin")) {
                hero2 = new Paladin("Paladin", false);
                start2 = false;
            } else if (s.equals("Priest")) {
                hero2 = new Priest("Priest", false);
                start2 = false;
            } else if (s.equals("Warlock")) {
                hero2 = new Warlock("Warlock", false);
                start2 = false;
            } else {
                System.out.println("nincs ilyen hero");
                s = scan.nextLine();
            }

            System.out.println(hero2);

        }

        hand1 = deck.draw(3, hand1, deck1, hero1);
        hand2 = deck.draw(4, hand2, deck2, hero2);

        player1 = new Player(deck1, hand1, board1, hero1);
        player2 = new Player(deck2, hand2, board2, hero2);

        while (!gameEnded) {

            if (isPlayer1Turn) {

                while (!endTurn) {

                    //endGame
                    if (hero2.getHealth() <= 0) {
                        System.out.println("game over player1 won");
                        endTurn = true;
                        isPlayer1Turn = false;
                        isPlayer2Turn = false;
                        gameEnded = true;
                    } else if (hero1.getHealth() <= 0) {
                        System.out.println("game over player2 won");
                        endTurn = true;
                        isPlayer1Turn = false;
                        isPlayer2Turn = false;
                        gameEnded = true;
                    }

                    //help how to play
                    if(!endTurn) {

                        System.out.println("-----------------------------");
                        System.out.println("-----------------------------");
                        System.out.println("player one hand " + hand1);
                        System.out.println("-----------------------------");
                        System.out.println("DECK1: " + deck1.size());
                        System.out.println("-----------------------------");
                        System.out.println("player1 do something /board/ or /hero/ or /hand/ or /playcard/ or /attackminion/ or /attackhero/ or /draw/ or /mana/ or /heropower/ or /endturn/ ");


                        s = scan.nextLine();
                    }

                    //press endturn
                    if(s.equals("endturn")) {
                        isPlayer2Turn = false;
                        isPlayer2Turn = true;
                        hero2.setImmune(false);
                        mana = 10;
                        System.out.println("Next player's turn in 10 seconds");
                        try {
                            TimeUnit.SECONDS.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        endTurn = true;
                    }

                    //play heropower
                    if(s.equals("heropower")){
                        if(mana >= 2 && !heroPowerUsed) {
                            if(hero1.toString().equals("Hunter")){
                                hero1.heroPower(hero2);
                            }else if(hero1.toString().equals("Priest")) {

                                System.out.println("use on /myhero/ or /enemyhero/ or /minion/");

                                s = scan.nextLine();

                                if (s.equals("myhero")) {
                                    hero1.heroPower(hero1);
                                } else if (s.equals("enemyhero")) {
                                    hero1.heroPower(hero2);
                                } else if (s.equals("minion")) {

                                    System.out.println("choose board /1/2/");
                                    System.out.println("-----------------------------");
                                    System.out.println(board1);
                                    System.out.println("-----------------------------");
                                    System.out.println(board2);
                                    System.out.println("-----------------------------");

                                    s = scan.nextLine();

                                    if (s.equals("1")) {

                                        System.out.println("minion number");

                                        s = scan.nextLine();

                                        hero1.heroPower(board1.get(Integer.parseInt(s)));

                                    } else if (s.equals("2")) {

                                        System.out.println("minion number");

                                        s = scan.nextLine();

                                        hero1.heroPower(board2.get(Integer.parseInt(s)));
                                    }

                                }
                            } else if (hero1.toString().equals("Mage")){

                                System.out.println("use on /myhero/ or /enemyhero/ or /minion/");

                                s = scan.nextLine();

                                if (s.equals("myhero")) {
                                    hero1.heroPower(hero1);
                                } else if (s.equals("enemyhero")) {
                                    hero1.heroPower(hero2);
                                } else if (s.equals("minion")) {

                                    System.out.println("choose board /1/2/");
                                    System.out.println("-----------------------------");
                                    System.out.println(board1);
                                    System.out.println("-----------------------------");
                                    System.out.println(board2);
                                    System.out.println("-----------------------------");

                                    s = scan.nextLine();

                                    if (s.equals("1")) {

                                        System.out.println("minion number");

                                        s = scan.nextLine();

                                        hero1.heroPower(board1.get(Integer.parseInt(s)));

                                        //TODO FIGHT

                                        fight.isMinionDied(board1);

                                    } else if (s.equals("2")) {

                                        System.out.println("minion number");

                                        s = scan.nextLine();

                                        hero1.heroPower(board2.get(Integer.parseInt(s)));

                                        fight.isMinionDied(board2);
                                    }

                                }
                            }
                            else if (hero1.toString().equals("Paladin")) {
                                Minion recruit = new Minion("Recruit", "", 1, 1,1,1, 1, 1, false, "");
                                board1.add(recruit);
                            } else if ((hero1.toString().equals("Warlock"))) {
                                hero1.setHealth(hero1.getHealth() - 2);

                                List<Card> drawedCards = new ArrayList<>();
                                drawedCards = deck.draw(2, drawedCards, deck1, hero1);

                                System.out.println("DRAWED cards" + drawedCards);

                                hand1.addAll(drawedCards);

                            }
                                heroPowerUsed = true;
                                mana -= 2;
                        }
                    }

                    //look at mana
                    if(s.equals("mana")){
                        System.out.println("-----------------------------");
                        System.out.println("Remaining mana: " + mana);
                        System.out.println("-----------------------------");
                    }

                    //look at the board
                    if (s.equals("board")) {
                        System.out.println(board1);
                        System.out.println(board2);
                    }

                    //look at hand
                    if(s.equals("hand")){
                        System.out.println(hand1);
                    }

                    //look at hero
                    if(s.equals("hero")){
                        System.out.println("-----------------------------");
                        System.out.println(hero1);
                        System.out.println(hero1.getHealth());
                        System.out.println("-----------------------------");
                        System.out.println(hero2);
                        System.out.println(hero2.getHealth());
                        System.out.println("-----------------------------");
                    }

                    //draw card (number means how many)
                    if(s.equals("draw")) {

                        List<Card> drawedCards = new ArrayList<>();
                        drawedCards = deck.draw(2, drawedCards, deck1, hero1);

                        System.out.println("DRAWED cards" + drawedCards);

                        hand1.addAll(drawedCards);

                        System.out.println("-----------------------------");
                        System.out.println(hand1);
                        System.out.println("-----------------------------");

                    }

                    //play card
                    if (s.equals("playcard")) {

                        System.out.println("number of minion/spell");

                        s = scan.nextLine();

                        //play minion
                        if (hand1.get(Integer.parseInt(s)) instanceof Minion && hand1.get(Integer.parseInt(s)).getCost() <= mana) {
                            //battlecry
                            if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: gets +1/+1 after every unit")) {
                                minion.addPlus1Plus1((Minion)hand1.get(Integer.parseInt(s)), board1, board2);
                            } else if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: silences a minion")) {
                                if (!board2.isEmpty() && !board1.isEmpty()) {
                                    //TODO write ifs, the method is ready
                                    //minion.silence();
                                }
                            } else if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: gets +1/+1 after every friendly unit")) {
                                minion.addPlus1Plus1AfterFriendly((Minion) hand1.get(Integer.parseInt(s)), board1);
                            } else if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: gives 4 hp")) {
                                //TODO write ifs, the method is ready
                                //if target is a minion
                                //minion.add4HPMinion();
                                //if target is a hero
                                //minion.add4HpHero();
                            } else if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: draws 2 cards")) {

                                List<Card> drawedCards = new ArrayList<>();
                                drawedCards = deck.draw(2, drawedCards, deck1, hero1);

                                System.out.println("DRAWED cards" + drawedCards);

                                hand1.addAll(drawedCards);
                            } else if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: gives 2 damage to every unit")) {
                                //TODO iterator
                                //minion.deal2();
                            } else if (hand1.get(Integer.parseInt(s)).getDescription().equals("battlecry: destroys all minions above 3 damage")){
                                //TODO iterator
                                //minion.destroyAbove3();
                            }
                            hand.addCardToBoard(board1, (Minion) hand1.get(Integer.parseInt(s)));
                            mana -= ((Minion) hand1.get(Integer.parseInt(s))).getCost();
                            hand1.remove((Minion) hand1.get(Integer.parseInt(s)));

                            System.out.println(board1);
                            System.out.println("Remaining mana: " + mana);
                            System.out.println("-----------------------------");

                            //play spell
                        } else if (hand1.get(Integer.parseInt(s)) instanceof Spell && hand1.get(Integer.parseInt(s)).getCost() <= mana) {

                            System.out.println("target? /hero/ or /minion/");

                            String b = scan.nextLine();

                            mana -= ((Spell) hand1.get(Integer.parseInt(s))).getCost();

                            if (b.equals("minion")) {

                                System.out.println("choose board /1/2/");
                                System.out.println("-----------------------------");
                                System.out.println(board1);
                                System.out.println("-----------------------------");
                                System.out.println(board2);
                                System.out.println("-----------------------------");

                                b = scan.nextLine();

                                if (b.equals("1")) {

                                    System.out.println("number of minion");

                                    int i = Integer.parseInt(scan.nextLine());

                                    if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("holyblessing")) {

                                        spell.holyBlessing(i, board1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board1);
                                        System.out.println("-----------------------------");


                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("cavalry")) {

                                        spell.cavalry(i, board1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board1);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("flee")) {

                                        spell.flee(i, board1, hand1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board1);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("traitor")) {

                                        spell.traitor(i, board1, board2);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board1);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                        spell.dragonFireMinion(i, board1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board1);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("frostblast")) {

                                        spell.frostBlast(i, board1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board1);
                                        System.out.println("-----------------------------");

                                    }
                                } else if (b.equals("2")) {

                                    System.out.println("number of minion");

                                    int i = Integer.parseInt(scan.nextLine());

                                    if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("holyblessing")) {

                                        spell.holyBlessing(i, board2);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board2);
                                        System.out.println("-----------------------------");


                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("cavalry")) {

                                        spell.cavalry(i, board2);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board2);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("flee")) {

                                        spell.flee(i, board2, hand1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board2);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("traitor")) {

                                        spell.traitor(i, board2, board1);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board2);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                        spell.dragonFireMinion(i, board2);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board2);
                                        System.out.println("-----------------------------");

                                    } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("frostblast")) {

                                        spell.frostBlast(i, board2);
                                        hand1.remove(i);

                                        System.out.println("-----------------------------");
                                        System.out.println(board2);
                                        System.out.println("-----------------------------");

                                    }
                                }
                                } else if (b.equals("hero")) {

                                    System.out.println("which hero? /1/2/");

                                    b = scan.nextLine();

                                    if (b.equals("1")) {

                                        if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                            spell.dragonFireHero(hero1, gameEnded);

                                            System.out.println("-----------------------------");
                                            System.out.println(hero1);
                                            System.out.println(hero1.getHealth());
                                            System.out.println("-----------------------------");

                                        } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("savethehero")) {

                                            spell.saveTheHero(hero1);

                                            System.out.println("not working");

                                            System.out.println("-----------------------------");
                                            System.out.println(hero1);
                                            System.out.println("-----------------------------");
                                        }
                                    } else if (b.equals("2")) {

                                        if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                            spell.dragonFireHero(hero2, gameEnded);

                                            System.out.println("-----------------------------");
                                            System.out.println(hero2);
                                            System.out.println("-----------------------------");

                                        } else if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("savethehero")) {

                                            spell.saveTheHero(hero2);

                                            System.out.println("not working");

                                            System.out.println("-----------------------------");
                                            System.out.println(hero2);
                                            System.out.println("-----------------------------");
                                        }
                                    }
                                }
                            }
                        }
                        //attack minion minion
                        if (s.equals("attackminion") && !board1.isEmpty() && !board2.isEmpty()) {
                            String d;
                            System.out.println(board1);
                            System.out.println("choose a minion (number) to attack with");
                            s = scan.nextLine();
                            if (board1.get(Integer.parseInt(s)).isCanAttack()) {
                                System.out.println("choose a minion (number) to be attacked");
                                d = scan.nextLine();

                                if (board2.contains((Minion) board2.get(Integer.parseInt(d)))) {
                                    board1.get(Integer.parseInt(s)).setCanAttack(false);
                                    fight.attackMinion((Minion) board1.get(Integer.parseInt(s)), (Minion) board2.get(Integer.parseInt(d)));
                                    fight.isMinionDied(board1);
                                    fight.isMinionDied(board2);
                                }


                                System.out.println(board1);
                                System.out.println(board2);
                                System.out.println("-----------------------------");
                            } else {
                                System.err.println("this minion can not attack this turn");
                            }
                        }
                        //attack minion hero
                        if (s.equals("attackhero") && !hero2.isImmune()) {
                            String d;
                            System.out.println("number of minion");
                            s = scan.nextLine();
                            if(board1.get(Integer.parseInt(s)).isCanAttack()) {
                                board1.get(Integer.parseInt(s)).setCanAttack(false);
                                fight.attackHero((Minion) board1.get(Integer.parseInt(s)), hero2);
                                System.out.println(hero2.getHeroName() + "'s hp: " + hero2.getHealth());
                                System.out.println("-----------------------------");
                            } else {
                                System.err.println("this minion can not attack this turn");
                            }
                        }
                    }
                }

                if (isPlayer2Turn) {

                    System.out.println("player two hand " + hand2);
                    System.out.println("-----------------------------");
                    System.out.println("DECK2: " + deck2.size());
                    System.out.println("-----------------------------");
                    System.out.println("player2 do something /playminion/ or /attack(with minion or with heropower");

                }


        }


    }
}
