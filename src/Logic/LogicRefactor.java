package Logic;

import Cards.*;
import Actions.Fight;
import Heroes.*;
import Player.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LogicRefactor {

    Scanner scan = new Scanner(System.in);

    Player player1;
    Player player2;
    Player player;
    Player otherPlayer;

    boolean endTurn = false;

    Hero hero1;
    Hero hero2;

    int mana = 10;

    boolean isPlayer1Turn = true;
    boolean isPlayer2Turn = false;

    boolean gameEnded = false;
    boolean heroPowerUsed = false;

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

    public LogicRefactor() {

            System.out.println("choose hero");

            System.out.println("player1:");

            s = scan.nextLine();

            if (s.equals("Hunter")) {
                hero1 = new Hunter("Hunter", false);
            } else if (s.equals("Mage")) {
                hero1 = new Mage("Mage", false);
            } else if (s.equals("Paladin")) {
                hero1 = new Paladin("Paladin", false);
            } else if (s.equals("Priest")) {
                hero1 = new Priest("Priest", false);
            } else if (s.equals("Warlock")) {
                hero1 = new Warlock("Warlock", false);
            } else {
                System.out.println("nincs ilyen hero");
                s = scan.nextLine();
            }

        System.out.println(hero1);

            System.out.println("player2:");

            s = scan.nextLine();

            if (s.equals("Hunter")) {
                hero2 = new Hunter("Hunter", false);
            } else if (s.equals("Mage")) {
                hero2 = new Mage("Mage", false);
            } else if (s.equals("Paladin")) {
                hero2 = new Paladin("Paladin", false);
            } else if (s.equals("Priest")) {
                hero2 = new Priest("Priest", false);
            } else if (s.equals("Warlock")) {
                hero2 = new Warlock("Warlock", false);
            } else {
                System.out.println("nincs ilyen hero");
                s = scan.nextLine();
            }

            System.out.println(hero2);


        hand1 = deck.draw(3, hand1, deck1, hero1);
        hand2 = deck.draw(4, hand2, deck2, hero2);

        player1 = new Player(deck1, hand1, board1, hero1);
        player2 = new Player(deck2, hand2, board2, hero2);

        while (!gameEnded) {

            //change player
            if (isPlayer1Turn) {
                player = player1;
                otherPlayer = player2;
            } else if (isPlayer2Turn) {
                player = player2;
                otherPlayer = player1;
            }
            endTurn = false;

            while (!endTurn) {

                //endGame
                if (player2.getHero().getHealth() <= 0) {
                    System.out.println("game over player1 won");
                    endTurn = true;
                    isPlayer1Turn = false;
                    isPlayer2Turn = false;
                    gameEnded = true;
                } else if (player1.getHero().getHealth() <= 0) {
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
                    System.out.println(player.getHand());
                    System.out.println("-----------------------------");
                    System.out.println(" your DECK: " + player.getDeck().size());
                    System.out.println("-----------------------------");
                    System.out.println("do something /board/ or /hero/ or /hand/ or /playcard/ or /attackminion/ or /attackhero/ or /draw/ or /mana/ or /heropower/ or /endturn/ ");


                    s = scan.nextLine();
                }

                //press endturn
                if(s.equals("endturn")) {
                    fight.makeCanNotAttack(player.getBoard());
                    fight.makeCanAttack((otherPlayer.getBoard()));
                    if (isPlayer1Turn){
                        isPlayer1Turn = false;
                        isPlayer2Turn = true;
                    } else {
                        isPlayer2Turn = false;
                        isPlayer1Turn = true;
                    }
                    player.getHero().setImmune(false);
                    mana = 10;
                    System.out.println("Next player's turn in 2 seconds");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    heroPowerUsed = false;
                    endTurn = true;
                }

                //play heropower
                if(s.equals("heropower")){
                    if(mana >= 2 && !heroPowerUsed) {
                        if(player.getHero().toString().equals("Hunter")){
                            player.getHero().heroPower(otherPlayer.getHero());
                        }else if(player.getHero().toString().equals("Priest")) {

                            System.out.println("use on /myhero/ or /enemyhero/ or /minion/");

                            s = scan.nextLine();

                            if (s.equals("myhero")) {
                                player.getHero().heroPower(player.getHero());
                            } else if (s.equals("enemyhero")) {
                                player.getHero().heroPower(otherPlayer.getHero());
                            } else if (s.equals("minion")) {

                                System.out.println("choose board /1/2/");
                                System.out.println("-----------------------------");
                                System.out.println(player.getBoard());
                                System.out.println("-----------------------------");
                                System.out.println(otherPlayer.getBoard());
                                System.out.println("-----------------------------");

                                s = scan.nextLine();

                                if (s.equals("1")) {

                                    System.out.println("minion number");

                                    s = scan.nextLine();

                                    player.getHero().heroPower(player.getBoard().get(Integer.parseInt(s)));

                                } else if (s.equals("2")) {

                                    System.out.println("minion number");

                                    s = scan.nextLine();

                                    player.getHero().heroPower(otherPlayer.getBoard().get(Integer.parseInt(s)));
                                }

                            }
                        } else if (player.getHero().toString().equals("Mage")){

                            System.out.println("use on /myhero/ or /enemyhero/ or /minion/");

                            s = scan.nextLine();

                            if (s.equals("myhero")) {
                                player.getHero().heroPower(player.getHero());
                            } else if (s.equals("enemyhero")) {
                                player.getHero().heroPower(otherPlayer.getHero());
                            } else if (s.equals("minion")) {

                                System.out.println("choose board /1/2/");
                                System.out.println("-----------------------------");
                                System.out.println(player.getBoard());
                                System.out.println("-----------------------------");
                                System.out.println(otherPlayer.getBoard());
                                System.out.println("-----------------------------");

                                s = scan.nextLine();

                                if (s.equals("1")) {

                                    System.out.println("minion number");

                                    s = scan.nextLine();

                                    player.getHero().heroPower(player.getBoard().get(Integer.parseInt(s)));

                                    //TODO FIGHT

                                    fight.isMinionDied(player.getBoard());

                                } else if (s.equals("2")) {

                                    System.out.println("minion number");

                                    s = scan.nextLine();

                                    player.getHero().heroPower(otherPlayer.getBoard().get(Integer.parseInt(s)));

                                    fight.isMinionDied(otherPlayer.getBoard());
                                }

                            }
                        }
                        else if (player.getHero().toString().equals("Paladin")) {
                            Minion recruit = new Minion("Recruit", "", 1, 1,1,1, 1, 1, false, "", false);
                            player.getBoard().add(recruit);
                        } else if ((player.getHero().toString().equals("Warlock"))) {
                            player.getHero().setHealth(player.getHero().getHealth() - 2);

                            List<Card> drawedCards = new ArrayList<>();
                            drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero());

                            System.out.println("DRAWED cards" + drawedCards);

                            player.getHand().addAll(drawedCards);

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
                    System.out.println(player.getBoard());
                    System.out.println(otherPlayer.getBoard());
                }

                //look at hand
                if(s.equals("hand")){
                    System.out.println(player.getHand());
                }

                //look at hero
                if(s.equals("hero")){
                    System.out.println("-----------------------------");
                    System.out.println(player.getHero());
                    System.out.println(player.getHero().getHealth());
                    System.out.println("-----------------------------");
                    System.out.println(otherPlayer.getHero());
                    System.out.println(otherPlayer.getHero().getHealth());
                    System.out.println("-----------------------------");
                }

                //draw card (number means how many)
                if(s.equals("draw")) {

                    List<Card> drawedCards = new ArrayList<>();
                    drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero());

                    System.out.println("DRAWED cards" + drawedCards);

                    player.getHand().addAll(drawedCards);

                    System.out.println("-----------------------------");
                    System.out.println(player.getHand());
                    System.out.println("-----------------------------");

                }

                //play card
                if (s.equals("playcard")) {

                    System.out.println("number of minion/spell");

                    s = scan.nextLine();

                    //play minion
                    if (player.getHand().get(Integer.parseInt(s)) instanceof Minion && player.getHand().get(Integer.parseInt(s)).getCost() <= mana && player.getBoard().size() < 5) {
                        //battlecry
                        if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: gets +1/+1 after every unit")) {
                            minion.addPlus1Plus1((Minion)player.getHand().get(Integer.parseInt(s)), player.getBoard(), otherPlayer.getBoard());
                        } else if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: silences a minion")) {
                            if (!otherPlayer.getBoard().isEmpty() && !player.getBoard().isEmpty()) {
                                //TODO write ifs, the method is ready
                                //minion.silence();
                            }
                        } else if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: gets +1/+1 after every friendly unit")) {
                            minion.addPlus1Plus1AfterFriendly((Minion) player.getHand().get(Integer.parseInt(s)), player.getBoard());
                        } else if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: gives 4 hp")) {
                            //TODO write ifs, the method is ready
                            //if target is a minion
                            //minion.add4HPMinion();
                            //if target is a hero
                            //minion.add4HpHero();
                        } else if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: draws 2 cards")) {

                            List<Card> drawedCards = new ArrayList<>();
                            drawedCards = deck.draw(2, drawedCards, player.getHand(), player.getHero());

                            System.out.println("DRAWED cards" + drawedCards);

                            player.getHand().addAll(drawedCards);
                        } else if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: gives 2 damage to every unit")) {
                            //TODO iterator
                            //minion.deal2();
                        } else if (player.getHand().get(Integer.parseInt(s)).getDescription().equals("battlecry: destroys all minions above 3 damage")){
                            //TODO iterator
                            //minion.destroyAbove3();
                        }
                        deck.addCardToBoard(player.getBoard(), (Minion) player.getHand().get(Integer.parseInt(s)));
                        mana -= ((Minion) player.getHand().get(Integer.parseInt(s))).getCost();
                        player.getHand().remove((Minion) player.getHand().get(Integer.parseInt(s)));

                        System.out.println(player.getBoard());
                        System.out.println("Remaining mana: " + mana);
                        System.out.println("-----------------------------");

                        //play spell
                    } else if (player.getHand().get(Integer.parseInt(s)) instanceof Spell && player.getHand().get(Integer.parseInt(s)).getCost() <= mana) {

                        System.out.println("target? /hero/ or /minion/");

                        String b = scan.nextLine();

                        mana -= ((Spell) player.getHand().get(Integer.parseInt(s))).getCost();

                        if (b.equals("minion")) {

                            System.out.println("choose board /1/2/");
                            System.out.println("-----------------------------");
                            System.out.println(player.getBoard());
                            System.out.println("-----------------------------");
                            System.out.println(otherPlayer.getBoard());
                            System.out.println("-----------------------------");

                            b = scan.nextLine();

                            if (b.equals("1")) {

                                System.out.println("number of minion");

                                int i = Integer.parseInt(scan.nextLine());

                                if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("holyblessing")) {

                                    spell.holyBlessing(i, player.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(player.getBoard());
                                    System.out.println("-----------------------------");


                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("cavalry")) {

                                    spell.cavalry(i, player.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(player.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("flee")) {

                                    spell.flee(i, player.getBoard(), player.getHand());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(player.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("traitor")) {

                                    spell.traitor(i, player.getBoard(), otherPlayer.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(player.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                    spell.dragonFireMinion(i, player.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(player.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("frostblast")) {

                                    spell.frostBlast(i, player.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(player.getBoard());
                                    System.out.println("-----------------------------");

                                }
                            } else if (b.equals("2")) {

                                System.out.println("number of minion");

                                int i = Integer.parseInt(scan.nextLine());

                                if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("holyblessing")) {

                                    spell.holyBlessing(i, otherPlayer.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(otherPlayer.getBoard());
                                    System.out.println("-----------------------------");


                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("cavalry")) {

                                    spell.cavalry(i, otherPlayer.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(otherPlayer.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("flee")) {

                                    spell.flee(i, otherPlayer.getBoard(), player.getHand());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(otherPlayer.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("traitor")) {

                                    spell.traitor(i, otherPlayer.getBoard(), player.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(otherPlayer.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("dragonfire")) {

                                    spell.dragonFireMinion(i, otherPlayer.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(otherPlayer.getBoard());
                                    System.out.println("-----------------------------");

                                } else if (((Spell) player.getHand().get(Integer.parseInt(s))).getEffect().equals("frostblast")) {

                                    spell.frostBlast(i, otherPlayer.getBoard());
                                    player.getHand().remove(i);

                                    System.out.println("-----------------------------");
                                    System.out.println(otherPlayer.getBoard());
                                    System.out.println("-----------------------------");

                                }
                            }
                        } else if (b.equals("hero")) {

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
                }
            }
        }
    }
}
