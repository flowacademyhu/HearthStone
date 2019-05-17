package Logic;

import HappeningsOnBoard.Fight;
import Cards.Card;
import Cards.Minion;
import Cards.Spell;
import HappeningsOnBoard.SpellEffects;
import Heroes.*;
import Player.Deck;
import Player.Player;
import Player.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    Hand hand = new Hand();

    SpellEffects spell = new SpellEffects();

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
                hero1 = new Hunter("Hunter");
                start = false;
            } else if (s.equals("Mage")) {
                hero1 = new Mage("Mage");
                start = false;
            } else if (s.equals("Paladin")) {
                hero1 = new Paladin("Paladin");
                start = false;
            } else if (s.equals("Priest")) {
                hero1 = new Priest("Priest");
                start = false;
            } else if (s.equals("Warlock")) {
                hero1 = new Warlock("Warlock");
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
                hero2 = new Hunter("Hunter");
                start2 = false;
            } else if (s.equals("Mage")) {
                hero2 = new Mage("Mage");
                start2 = false;
            } else if (s.equals("Paladin")) {
                hero2 = new Paladin("Paladin");
                start2 = false;
            } else if (s.equals("Priest")) {
                hero2 = new Priest("Priest");
                start2 = false;
            } else if (s.equals("Warlock")) {
                hero2 = new Warlock("Warlock");
                start2 = false;
            } else {
                System.out.println("nincs ilyen hero");
                s = scan.nextLine();
            }

            System.out.println(hero2);

        }

        hand1 = deck.draw(3, hand1, deck1, hero1);
        hand2 = deck.draw(4, hand2, deck2, hero2);

        player1 = new Player(deck1, hand1, hero1);
        player2 = new Player(deck2, hand2, hero2);

        while (!gameEnded) {

            if (isPlayer1Turn) {

                while (!endTurn) {
                    System.out.println("player one hand " + hand1);
                    System.out.println("-----------------------------");
                    System.out.println("DECK1: " + deck1.size());
                    System.out.println("-----------------------------");
                    System.out.println("player1 do something /playcard/ or /attackMinion/ or /attackHero/ or /draw/");

                    s = scan.nextLine();

                    if(s.equals("draw")) {

                        List<Card> drawedCards = deck.draw(2, hand1, deck1, hero1);

                        //TODO

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
                        if (hand1.get(Integer.parseInt(s)) instanceof Minion) {
                            hand.addCardToBoard(board1, (Minion) hand1.get(Integer.parseInt(s)));
                            mana -= ((Minion) hand1.get(Integer.parseInt(s))).getCost();
                            hand1.remove((Minion) hand1.get(Integer.parseInt(s)));

                            System.out.println(board1);
                            System.out.println("Remaining mana: " + mana);
                            System.out.println("-----------------------------");

                            //play spell
                        } else if (hand1.get(Integer.parseInt(s)) instanceof Spell) {

                            System.out.println("target? /hero/ or /minion/");

                            String b = scan.nextLine();

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
                                } else if (b.equals("hero")) {

                                    System.out.println("which hero? /1/2/");

                                    b = scan.nextLine();

                                    if (b.equals("1")) {

                                        if (((Spell) hand1.get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                            spell.dragonFireHero(hero1);

                                            System.out.println("-----------------------------");
                                            System.out.println(hero1);
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

                                            spell.dragonFireHero(hero2);

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
                        if (s.equals("attackMinion") && !(board1.isEmpty()) && !(board2.isEmpty())) {
                            String d;
                            System.out.println(board1);
                            System.out.println("choose a minion (number) to attack with");
                            s = scan.nextLine();
                            System.out.println("choose a minion (number) to be attacked");
                            d = scan.nextLine();
                            fight.attackMinion((Minion) board1.get(Integer.parseInt(s)), (Minion) board2.get(Integer.parseInt(d)));
                            fight.isMinionDied(board1);
                            fight.isMinionDied(board2);

                            System.out.println(board1);
                            System.out.println(board2);
                            System.out.println("-----------------------------");
                        }
                        //attack minion hero
                        if (s.equals("attackHero")) {
                            String d;
                            System.out.println("number of minion");
                            s = scan.nextLine();
                            System.out.println("choose a hero to be attacked /heroName/");
                            d = scan.nextLine();
                            fight.attackHero((Minion) board1.get(Integer.parseInt(s)), hero2);
                            System.out.println(hero2.getHeroName() + "'s hp: " + hero2.getHealth());
                            System.out.println("-----------------------------");
                        }

                    }


                    isPlayer2Turn = false;
                    isPlayer2Turn = true;
                }

                if (isPlayer2Turn) {

                    System.out.println("player one hand " + hand2);
                    System.out.println("-----------------------------");
                    System.out.println("DECK2: " + deck2.size());
                    System.out.println("-----------------------------");
                    System.out.println("player2 do something /playminion/ or /attack(with minion or with heropower");

                }


            }


        }
    }
}
