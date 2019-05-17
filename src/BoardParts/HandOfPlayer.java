package BoardParts;

import Cards.Card;
import Cards.Minion;
import Cards.Spell;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HandOfPlayer {

   /* private JPanel displayHands(List<Card> hand) {

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
*/
}
