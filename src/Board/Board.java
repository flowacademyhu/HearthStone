package Board;

import Cards.Card;
import Cards.Minion;
import Cards.Spell;
import Heroes.*;
import Player.Deck;
import Player.Player;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JFrame {

    boolean heroOnePressed = false;
    boolean heroTwoPressed = false;
    boolean minionPressed = false;
    boolean spellPressed = false;
    JButton pressedButton = new JButton();

    int mana1 = 10;
    int mana2 = 10;

    private Deck deck = new Deck();

    private Hero hero1 = new Mage("Mage", false);
    private Hero hero2 = new Priest("Priest", false);

    private List<Card> deck1 = new ArrayList<>(deck.deckBuilder());
    private List<Card> deck2 = new ArrayList<>(deck.deckBuilder());

    private List<Minion> board1 = new ArrayList<>();
    private List<Minion> board2 = new ArrayList<>();

    private List<Card> hand1 = new ArrayList<>();
    private List<Card> hand2 = new ArrayList<>();

    boolean usedHero1 = true;
    boolean usedHero2 = true;

    private List<JLabel> steps = new ArrayList<>();

    JPanel handOfPlayerOne = null;
    JPanel handOfPlayerTwo = null;
    JPanel board = null;
    JPanel whatHappened = null;
    JPanel endTurnAndHeroes = null;

    Player player1 = new Player(deck1, hand1, board1, hero1);
    Player player2 = new Player(deck2, hand2, board2, hero2);

    int turn = 1;

    public Board() throws HeadlessException {

        setTitle("FlowStone");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // "fullscreen"
        //setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        /*JPanel menu = menu();
        add(menu);*/

        hand1 = deck.draw(3, hand1, deck1, hero1);
        hand2 = deck.draw(4, hand2, deck2, hero2);

        gameBoard();

        setVisible(true);

    }

    //TODO
    /*private JPanel menu() {

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton playerVsPlayer = new JButton("Player vs Player");
        panel.add(playerVsPlayer, BorderLayout.NORTH);

        JButton exit = new JButton("Exit");
        panel.add(exit, BorderLayout.SOUTH);

        JLabel title = new JLabel("FlowStone");
        Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
        title.setBorder(border);
        title.setPreferredSize(new Dimension(1500, 1500));
        panel.add(title, BorderLayout.CENTER);

        return panel;
    }*/

    // entire gameboard
    private void gameBoard() {

        handOfPlayerOne = hands(hand1);
        add(handOfPlayerOne, BorderLayout.SOUTH);

        handOfPlayerTwo = hands(hand2);
        add(handOfPlayerTwo, BorderLayout.NORTH);

        endTurnAndHeroes = east();
        add(endTurnAndHeroes, BorderLayout.EAST);

        whatHappened = west();
        add(whatHappened, BorderLayout.WEST);

        board = centerBoard();

    }

    private JPanel west() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS) );

        for (JLabel step: steps) {
            panel.add(step);
        }
        return panel;
    }

    //place of played minions
    private JPanel centerBoard() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1));

        JLabel line = new JLabel("______________________________________________________________________________" +
                "__________________________________________________________________________________________________________________________________________________"+
                "__________________________________________________________________________________________________________________________________________________");

        panel.add(boardBuilder(board2));
        panel.add(line);
        panel.add(boardBuilder(board1));

        add(panel, BorderLayout.CENTER);

        repaint();
        revalidate();
        doLayout();

        return panel;
    }

    //played minions on the board
    private JPanel boardBuilder(List<Minion> board) {

        JPanel panel = new JPanel();

        panel.setLayout(new FlowLayout());

        for (Minion card:board) {

            //deletes dead minions
            if ( card.getHealth() <= 0){
                board.remove(card);

                whatHappenedMinionKill(card);

                //centerBoard();

                remove(this.board);
                this.board = centerBoard();
                add(this.board, BorderLayout.CENTER);
                this.board.repaint();
                this.board.revalidate();
                this.board.doLayout();
                repaint();
                revalidate();
            }

            card.removeActionListener(card.getActionListeners()[0]);

            card.addActionListener((ActionEvent e) -> {

                if (heroTwoPressed) {
                    System.out.println("priest is pressed");
                    System.out.println(hero2);
                    System.out.println(e.getSource());
                    hero2.heroPower(e.getSource());
                    whatHappenedMageOrPriest(hero2, e.getSource());
                    pressedButton = null;
                    ((Minion) e.getSource()).setText(((Minion) e.getSource()).getCost()+"/"+((Minion) e.getSource()).getName()+"/"+((Minion) e.getSource()).getAttack()+"/"+((Minion) e.getSource()).getHealth());
                    heroTwoPressed = false;
                } else if (heroOnePressed) {
                    hero1.heroPower(e.getSource());
                    whatHappenedMageOrPriest(hero1, e.getSource());
                    pressedButton = null;
                    ((Minion) e.getSource()).setText(((Minion) e.getSource()).getCost()+"/"+((Minion) e.getSource()).getName()+"/"+((Minion) e.getSource()).getAttack()+"/"+((Minion) e.getSource()).getHealth());
                    heroOnePressed = false;
                } else {
                    if (!minionPressed) {
                        minionPressed = true;
                        pressedButton = ((Minion) e.getSource());
                        System.out.println("asd");
                        //centerBoard();

                        remove(this.board);
                        this.board = centerBoard();
                        this.board.repaint();
                        this.board.revalidate();
                        this.board.doLayout();
                        revalidate();
                        repaint();
                    } else if (pressedButton.getClass() == Minion.class) {
                        attackMinion(((Minion)pressedButton), ((Minion)e.getSource()));
                        minionPressed = false;

                        //centerBoard();

                        remove(this.board);
                        this.board = centerBoard();
                        add(this.board, BorderLayout.CENTER);
                        this.board.repaint();
                        this.board.revalidate();
                        this.board.doLayout();
                        repaint();
                        revalidate();
                    }
                }

                centerBoard();

                remove(this.board);
                this.board = centerBoard();
                add(this.board, BorderLayout.CENTER);
                this.board.repaint();
                this.board.revalidate();
                this.board.doLayout();
                repaint();
                revalidate();


            });
            panel.add(card);
        }

        return panel;
    }

    //two hero powers, end turn
    private JPanel east() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(5, 1));

        Border manaBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        //manaCrystals of player two
        JLabel manaPlayerTwo = new JLabel("Mana: " + Integer.toString(mana2));
        manaPlayerTwo.setHorizontalAlignment(JLabel.CENTER);
        manaPlayerTwo.setBorder(manaBorder);
        panel.add(manaPlayerTwo, BorderLayout.EAST);

        //hero of player two
        hero2.setText(hero2.getHeroName() + "/" + hero2.getHealth());
        hero2.addActionListener((ActionEvent e) -> {
                    if(usedHero2 && !minionPressed) {
                        if (heroTwoPressed && pressedButton == hero2 && mana2 >= 2) {
                            hero2.heroPower(hero2);
                            whatHappenedMageOrPriest(hero2, hero2);

                            mana2 -= 2;
                            eastRepaint();

                            heroTwoPressed = false;
                            pressedButton = null;
                        } else if (heroTwoPressed && pressedButton == hero1 && mana2 >= 2) {
                            hero2.heroPower(hero1);
                            whatHappenedMageOrPriest(hero2, hero1);

                            mana2 -= 2;
                            eastRepaint();

                            pressedButton = null;
                        } else if (heroOnePressed) {
                            hero1.heroPower(hero2);
                            whatHappenedMageOrPriest(hero1, hero2);

                            mana1 -= 2;
                            eastRepaint();

                            heroOnePressed = false;
                            pressedButton = null;
                        }
                        //priest
                        else if (!minionPressed && e.getSource() instanceof Priest) {
                            heroTwoPressed = true;
                            pressedButton = hero2;
                            whatHappenedHero(hero2);
                        }
                        //mage
                        else if (!minionPressed && e.getSource() instanceof Mage) {
                            heroTwoPressed = true;
                            pressedButton = hero2;
                            whatHappenedHero(hero2);
                        }
                        //hunter
                        else if (!minionPressed && e.getSource() instanceof Hunter) {
                            ((Hunter) e.getSource()).heroPower(hero1);
                            whatHappenedHero(hero2);
                        }
                        //paladin
                        else if (!minionPressed && e.getSource() instanceof Paladin) {
                            remove(board);

                            whatHappenedHero(hero2);

                            Minion recruit = new Minion("Recruit", "", 1, 1,1,1, 1, 1, false, "", false);
                            recruit.setText(recruit.getCost() + "/" + recruit.getName() + "/" + recruit.getAttack() + "/" + recruit.getHealth());
                            Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
                            recruit.setBackground(Color.white);
                            recruit.setBorder(border);

                            board2.add(recruit);
                            board = centerBoard();
                            add(board, BorderLayout.CENTER);
                            board.repaint();
                            board.revalidate();
                            board.doLayout();
                            repaint();
                            revalidate();
                        }
                        //warlock
                        else if (!minionPressed && e.getSource() instanceof Warlock) {
                            remove(handOfPlayerTwo);

                            whatHappenedHero(hero2);

                            ((Warlock) e.getSource()).setHealth(((Warlock) e.getSource()).getHealth() - 2);

                            hand2.add(deck2.get(0));
                            hand2.add(deck2.get(1));
                            deck2.remove(0);
                            deck2.remove(0);

                            handOfPlayerTwo = hands(hand2);
                            add(handOfPlayerTwo, BorderLayout.NORTH);
                            handOfPlayerTwo.repaint();
                            handOfPlayerTwo.revalidate();
                            handOfPlayerTwo.doLayout();
                            pressedButton = (JButton) e.getSource();
                        }
                        usedHero2 = false;
                    }
                    if (minionPressed) {
                        attackHero(((Minion) pressedButton), hero2);
                        minionPressed = false;
                        pressedButton = null;
                    }

            hero1.setText(hero1.getHeroName() + "/" + hero1.getHealth());
            hero2.setText(hero2.getHeroName() + "/" + hero2.getHealth());
        });
        panel.add(hero2, BorderLayout.EAST);

        //end turn button
        JButton endTurn = new JButton("End Turn");
        endTurn.addActionListener((ActionEvent e) -> {
            remove(whatHappened);
            steps.add(new JLabel("--------New turn--------"));
            whatHappened = west();
            add(whatHappened, BorderLayout.WEST);
            whatHappened.repaint();
            whatHappened.revalidate();
            whatHappened.doLayout();
        });
        panel.add(endTurn, BorderLayout.EAST);

        //hero of player one
        hero1.setText(hero1.getHeroName() + "/" + hero1.getHealth());
        hero1.addActionListener((ActionEvent e) -> {
            if(usedHero1 && !minionPressed) {
                if (heroOnePressed && pressedButton == hero1 && mana1 >= 2) {
                    hero1.heroPower(hero1);
                    whatHappenedMageOrPriest(hero1, hero1);

                    mana1 -= 2;
                    eastRepaint();

                    heroOnePressed = false;
                    pressedButton = null;
                } else if (heroOnePressed && pressedButton == hero2 && mana1 >= 2) {
                    hero1.heroPower(hero2);
                    whatHappenedMageOrPriest(hero1, hero2);

                    mana1 -= 2;
                    eastRepaint();

                    pressedButton = null;
                } else if (heroTwoPressed) {
                    hero2.heroPower(hero1);
                    whatHappenedMageOrPriest(hero2, hero1);

                    mana2 -= 2;
                    eastRepaint();

                    heroTwoPressed = false;
                    pressedButton = null;
                }
                //priest
                else if (!minionPressed && e.getSource() instanceof Priest) {
                    heroOnePressed = true;
                    pressedButton = hero1;
                    whatHappenedHero(hero1);
                }
                //mage
                else if (!minionPressed && e.getSource() instanceof Mage) {
                    heroOnePressed = true;
                    pressedButton = hero1;
                    whatHappenedHero(hero1);
                }
                //hunter
                else if (!minionPressed && e.getSource() instanceof Hunter) {
                    ((Hunter) e.getSource()).heroPower(hero2);
                    whatHappenedHero(hero1);
                }
                //paladin
                else if (!minionPressed && e.getSource() instanceof Paladin) {
                    remove(board);

                    whatHappenedHero(hero1);

                    Minion recruit = new Minion("Recruit", "", 1, 1,1,1, 1, 1, false, "", false);
                    recruit.setText(recruit.getCost() + "/" + recruit.getName() + "/" + recruit.getAttack() + "/" + recruit.getHealth());
                    Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
                    recruit.setBackground(Color.white);
                    recruit.setBorder(border);
                    board1.add(recruit);

                    board = centerBoard();
                    add(board, BorderLayout.CENTER);
                    board.repaint();
                    board.revalidate();
                    board.doLayout();
                    repaint();
                    revalidate();
                    //centerBoard();
                }
                //warlock
                else if (!minionPressed && e.getSource() instanceof Warlock) {
                    remove(handOfPlayerOne);

                    whatHappenedHero(hero1);

                    ((Warlock) e.getSource()).setHealth(((Warlock) e.getSource()).getHealth() - 2);
                    hand1.add(deck1.get(0));
                    hand1.add(deck1.get(1));
                    deck1.remove(0);
                    deck1.remove(0);

                    handOfPlayerOne = hands(hand1);
                    add(handOfPlayerOne, BorderLayout.SOUTH);
                    handOfPlayerOne.repaint();
                    handOfPlayerOne.revalidate();
                    handOfPlayerOne.doLayout();

                    pressedButton = (JButton) e.getSource();
                }
                usedHero1 = false;
            }
            if (minionPressed) {
                attackHero(((Minion) pressedButton), hero1);
                minionPressed = false;
                pressedButton = null;
            }

            hero1.setText(hero1.getHeroName() + "/" + hero1.getHealth());
            hero2.setText(hero2.getHeroName() + "/" + hero2.getHealth());
        });
        panel.add(hero1, BorderLayout.EAST);

        //manaCrystals of player one
        JLabel manaPlayerOne = new JLabel("Mana: " + Integer.toString(mana1));
        manaPlayerOne.setHorizontalAlignment(JLabel.CENTER);
        manaPlayerOne.setBorder(manaBorder);
        panel.add(manaPlayerOne, BorderLayout.EAST);

        return panel;
    }

    //display hands of players
    private JPanel hands(List<Card> hand) {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        //display hands on board

        for (Card card : hand) {
            if (card instanceof Minion) {
                card.setText(card.getCost() + "/" + card.getName() + "/" + (((Minion) card).getAttack()) + "/" + ((Minion) card).getHealth());
                Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
                card.setBackground(Color.white);
                card.setBorder(border);
                //player one minions
                if(hand.equals(hand1)) {
                    ActionListener actionListener = (ActionEvent e) -> {
                        remove(board);

                        board1.add((Minion) e.getSource());
                        hand1.remove(e.getSource());

                        whatHappenedMinion((Minion)e.getSource());

                        board = centerBoard();
                        add(board, BorderLayout.CENTER);
                        board.repaint();
                        board.revalidate();
                        board.doLayout();
                        //centerBoard();
                    };
                    card.addActionListener(actionListener);
                }
                //player two minions
                else if(hand.equals(hand2)) {
                    ActionListener actionListener = (ActionEvent e) -> {
                        remove(board);

                        board2.add((Minion) e.getSource());
                        hand2.remove(e.getSource());

                        whatHappenedMinion((Minion)e.getSource());

                        board = centerBoard();
                        add(board, BorderLayout.CENTER);
                        board.repaint();
                        board.revalidate();
                        board.doLayout();
                        //centerBoard();
                    };
                    card.addActionListener(actionListener);
                }
                panel.add(card);
            } else if (card instanceof Spell){
                card.setText(card.getCost() + card.getName());
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                card.setBackground(Color.white);
                card.setBorder(border);

                //player one spells
                if(hand.equals(hand1)) {
                    ActionListener actionListener = (ActionEvent e) -> {
                        //TODO spell do the description
                        spellPressed = true;
                        pressedButton = (JButton)e.getSource();

                        //remove(board);

                        hand1.remove(e.getSource());

                        //TODO whatHappenedSpell((Spell)e.getSource());

                        System.out.println("spellpressed1111111111111");

                        board = centerBoard();
                        add(board, BorderLayout.CENTER);
                        board.repaint();
                        board.revalidate();
                        board.doLayout();
                        centerBoard();

                    };
                    card.addActionListener(actionListener);
                }

                //player two spells
                if(hand.equals(hand2)) {
                    ActionListener actionListener = (ActionEvent e) -> {
                        //TODO spell do the description
                        remove(handOfPlayerTwo);

                        spellPressed = true;
                        pressedButton = (JButton)e.getSource();

                        handOfPlayerTwo = hands(hand2);
                        add(handOfPlayerTwo, BorderLayout.NORTH);
                        handOfPlayerTwo.repaint();
                        handOfPlayerTwo.revalidate();
                        handOfPlayerTwo.doLayout();

                        remove(board);

                        hand2.remove(e.getSource());

                        //TODO whatHappenedSpell((Spell)e.getSource());

                        System.out.println("spellpressed22222222");

                        board = centerBoard();
                        add(board, BorderLayout.CENTER);
                        board.repaint();
                        board.revalidate();
                        board.doLayout();
                        centerBoard();

                    };
                    card.addActionListener(actionListener);
                }
            }
            panel.add(card);
        }
        return panel;
    }

    //attack, battle, fight
    private void attackMinion(Minion minionAttack, Minion minionDef) {
        minionAttack.setHealth(minionAttack.getHealth() - minionDef.getAttack());
        minionDef.setHealth(minionDef.getHealth() - minionAttack.getAttack());
        minionAttack.setText(minionAttack.getCost() + "/" + minionAttack.getName() + "/" + minionAttack.getAttack() + "/" + minionAttack.getHealth());
        minionDef.setText(minionDef.getCost() + "/" + minionDef.getName() + "/" + minionDef.getAttack() + "/" + minionDef.getHealth());
        setWhatHappenedMinionAttack(minionAttack, minionDef);
        repaint();
        revalidate();
    }

    //TODO harcot megírni
    //attack, battle, fight
    private void attackHero(Minion minion, Hero hero) {
        hero.setHealth(hero.getHealth() - minion.getAttack());
        setWhatHappenedMinionAttack(minion, hero);
        repaint();
        revalidate();
    }

    //the followings are whatHappenedMethods

    private void whatHappenedMinion(Minion button) {
        remove(whatHappened);
        steps.add(new JLabel("Summoned: " + ((Minion) button).getName()));
        whatHappened = west();
        add(whatHappened, BorderLayout.WEST);
        whatHappened.repaint();
        whatHappened.revalidate();
        whatHappened.doLayout();
    }

    private void whatHappenedHero(Hero button) {
        remove(whatHappened);
        steps.add(new JLabel(((Hero)button).getHeroName() + " used heropower"));
        whatHappened = west();
        add(whatHappened, BorderLayout.WEST);
        whatHappened.repaint();
        whatHappened.revalidate();
        whatHappened.doLayout();
    }

    private void whatHappenedMageOrPriest (Hero button1, Object button2) {
        remove(whatHappened);

        if (button1.toString().equals("Priest") && button2 instanceof Minion) {
            steps.add(new JLabel(((Hero) button1).getHeroName() + " healed " + ((Minion) button2).getName()));
        } else if (button1.toString().equals("Mage") && button2 instanceof Minion) {
            steps.add(new JLabel(((Hero) button1).getHeroName() + " damaged " + ((Minion) button2).getName()));
        }else if(button1.toString().equals("Priest")) {
            steps.add(new JLabel(((Hero) button1).getHeroName() + " healed " + button2));
        } else {
            steps.add(new JLabel(((Hero) button1).getHeroName() + " damaged " + button2));
        }
        whatHappened = west();
        add(whatHappened, BorderLayout.WEST);
        whatHappened.repaint();
        whatHappened.revalidate();
        whatHappened.doLayout();
    }

    private void whatHappenedMinionKill(Minion minion) {
        remove(whatHappened);

        steps.add(new JLabel(minion.getName() + " was killed"));

        whatHappened = west();
        add(whatHappened, BorderLayout.WEST);
        whatHappened.repaint();
        whatHappened.revalidate();
        whatHappened.doLayout();
    }

    private void setWhatHappenedMinionAttack(Minion minion, Object object) {
        remove(whatHappened);

        if(object.getClass() == Priest.class) {
            steps.add(new JLabel(minion.getName() + " attacked Priest"));
        } else if (object.getClass() == Hunter.class) {
            steps.add(new JLabel(minion.getName() + " attacked Hunter"));
        } else if (object.getClass() == Mage.class) {
            steps.add(new JLabel(minion.getName() + " attacked Mage"));
        } else if (object.getClass() == Paladin.class) {
            steps.add(new JLabel(minion.getName() + " attacked Paladin"));
        } else if (object.getClass() == Warlock.class) {
            steps.add(new JLabel(minion.getName() + " attacked Warlock"));
        } else if (object.getClass() == Minion.class && object instanceof Minion) {
            steps.add(new JLabel(minion.getName() + " attacked " + ((Minion) object).getName()));
        }

        whatHappened = west();
        add(whatHappened, BorderLayout.WEST);
        whatHappened.repaint();
        whatHappened.revalidate();
        whatHappened.doLayout();
    }

    //mana methods

    private void eastRepaint() {
        remove(endTurnAndHeroes);
        endTurnAndHeroes = east();
        add(endTurnAndHeroes, BorderLayout.EAST);
    }
}
