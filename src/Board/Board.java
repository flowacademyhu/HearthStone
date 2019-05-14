package Board;

import Cards.Card;
import Cards.Minion;
import Cards.Spell;
import Heroes.*;
import Player.Deck;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends JFrame {

    boolean heroOnePressed = false;
    boolean heroTwoPressed = false;
    JButton pressedButton = new JButton();

    int mana = 0;

    private Deck deck = new Deck();
    private Hero hero1 = new Mage("Mage");
    private Hero hero2 = new Warlock("Warlock");

    private List<Card> deck1 = new ArrayList<>(deck.deckBuilder());
    private List<Card> deck2 = new ArrayList<>(deck.deckBuilder());

    private List<Minion> board1 = new ArrayList<>();
    private List<Minion> board2 = new ArrayList<>();

    private List<Card> hand1 = new ArrayList<>();
    private List<Card> hand2 = new ArrayList<>();

    JPanel handOfPlayerOne = null;
    JPanel handOfPlayerTwo = null;
    JPanel board = null;

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

        JPanel endTurnAndHeroes = east();
        add(endTurnAndHeroes, BorderLayout.EAST);

        JLabel whatHappened = new JLabel("Mi történt");
        add(whatHappened, BorderLayout.WEST);

        board = centerBoard();

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
                centerBoard();
            }
            card.removeActionListener(card.getAction());
            card.addActionListener((ActionEvent e) -> {
                System.out.println();
                if (heroTwoPressed) {
                    hero2.heroPower(e.getSource());
                    pressedButton = null;
                    ((Minion) e.getSource()).setText(((Minion) e.getSource()).getCost()+"/"+((Minion) e.getSource()).getName()+"/"+((Minion) e.getSource()).getAttack()+"/"+((Minion) e.getSource()).getHealth());
                    heroTwoPressed = false;
                } else if (heroOnePressed) {
                    hero1.heroPower(e.getSource());
                    pressedButton = null;
                    ((Minion) e.getSource()).setText(((Minion) e.getSource()).getCost()+"/"+((Minion) e.getSource()).getName()+"/"+((Minion) e.getSource()).getAttack()+"/"+((Minion) e.getSource()).getHealth());
                    heroOnePressed = false;
                }

            });
            panel.add(card);
        }

        return panel;
    }

    //two hero powers, end turn
    private JPanel east() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(3, 1));

        //manaCrystals //TODO grid layout 5

        //hero of player two
        hero2.setText(hero2.getHeroName() + "/" + hero2.getHealth());
        hero2.addActionListener((ActionEvent e) -> {
            if (heroTwoPressed && pressedButton == hero2) {
                hero2.heroPower(hero2);
                heroTwoPressed = false;
                pressedButton = new JButton();
            } else if (heroTwoPressed && pressedButton == hero1) {
                hero2.heroPower(hero1);
                pressedButton = new JButton();
            } else if (heroOnePressed) {
                hero1.heroPower(hero2);
                heroOnePressed = false;
                pressedButton = new JButton();
            }
            //priest
            else if (e.getSource() instanceof Priest) {
                heroTwoPressed = true;
                pressedButton = hero2;
            }
            //mage
            else if (e.getSource() instanceof Mage) {
                heroTwoPressed = true;
                pressedButton = hero2;
            }
            //hunter
            else if (e.getSource() instanceof Hunter) {
                ((Hunter) e.getSource()).heroPower(hero1);
            }
            //paladin
            else if (e.getSource() instanceof Paladin) {
                Minion recruit = new Minion("Recruit", "", 1, 1, 1, 1, "");
                recruit.setText(recruit.getCost() + "/" + recruit.getName() + "/" + recruit.getAttack() + "/" + recruit.getHealth());
                Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
                recruit.setBackground(Color.white);
                recruit.setBorder(border);
                board2.add(recruit);
                centerBoard();
            }
            //warlock //TODO something goes wrong (duplicate)
            else if(e.getSource() instanceof Warlock){
                remove(handOfPlayerTwo);
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

            hero1.setText(hero1.getHeroName() + "/" + hero1.getHealth());
            hero2.setText(hero2.getHeroName() + "/" + hero2.getHealth());
        });
        panel.add(hero2, BorderLayout.EAST);

        //end turn button
        JButton endTurn = new JButton("End Turn");
        panel.add(endTurn, BorderLayout.EAST);

        //hero of player one
        hero1.setText(hero1.getHeroName() + "/" + hero1.getHealth());
        hero1.addActionListener((ActionEvent e) -> {
            if (heroOnePressed && pressedButton == hero1) {
                hero1.heroPower(hero1);
                heroOnePressed = false;
                pressedButton = new JButton();
            } else if (heroOnePressed && pressedButton == hero2) {
                hero1.heroPower(hero2);
                pressedButton = new JButton();
            } else if (heroTwoPressed) {
                hero2.heroPower(hero1);
                heroTwoPressed = false;
                pressedButton = new JButton();
            }
            //priest
            else if (e.getSource() instanceof Priest) {
                heroOnePressed = true;
                pressedButton = hero1;
            }
            //mage
            else if (e.getSource() instanceof Mage) {
                heroOnePressed = true;
                pressedButton = hero1;
            }
            //hunter
            else if (e.getSource() instanceof Hunter) {
                ((Hunter) e.getSource()).heroPower(hero2);
            }
            //paladin
            else if (e.getSource() instanceof Paladin) {
                Minion recruit = new Minion("Recruit", "", 1, 1, 1, 1, "");
                recruit.setText(recruit.getCost() + "/" + recruit.getName() + "/" + recruit.getAttack() + "/" + recruit.getHealth());
                Border border = BorderFactory.createLineBorder(Color.BLUE, 2);
                recruit.setBackground(Color.white);
                recruit.setBorder(border);
                board1.add(recruit);
                centerBoard();
            }
            //warlock //something goes wrong (duplicate cards)
            else if(e.getSource() instanceof Warlock){
                remove(handOfPlayerOne);
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

            hero1.setText(hero1.getHeroName() + "/" + hero1.getHealth());
            hero2.setText(hero2.getHeroName() + "/" + hero2.getHealth());
        });
        panel.add(hero1, BorderLayout.EAST);

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
                if(hand.equals(hand1)) {
                    ActionListener actionListener = (ActionEvent e) -> {
                        board1.add((Minion) e.getSource());
                        hand1.remove(e.getSource());
                        centerBoard();
                    };
                    card.addActionListener(actionListener);
                } if(hand.equals(hand2)) {
                    ActionListener actionListener = (ActionEvent e) -> {
                        board2.add((Minion) e.getSource());
                        hand2.remove(e.getSource());
                        centerBoard();
                    };
                    card.addActionListener(actionListener);
                }
                panel.add(card);
            } else {
                card.setText(card.getCost() + card.getName());
                Border border = BorderFactory.createLineBorder(Color.RED, 2);
                card.setBackground(Color.white);
                card.setBorder(border);
                card.addActionListener((ActionEvent e) -> {
                    //TODO spell do the description
                });

                panel.add(card);
            }
        }
        return panel;
    }
}