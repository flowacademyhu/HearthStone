package Board;



import Actions.Fight;
import Cards.Card;
import Cards.Minion;
import Logic.LogicRefactor2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Board1 extends JFrame {

    LogicRefactor2 logic = new LogicRefactor2();

    private JButton playerHero1Button = new JButton();
    private JButton playerHero2Button = new JButton();
    private JButton endTurnButton = new JButton();
    private JLabel mana = new JLabel();

    private List<JButton> handButtons1 = handButtonAdder();
    private List<JButton> handButtons2 = handButtonAdder();

    private List<JButton> boardButtons1 = boardButtonAdder();
    private List<JButton> boardButtons2 = boardButtonAdder();

    private List<JLabel> steps = stepLabelAdder();

    private List<JLabel> stepLabelAdder() {

        List list = new ArrayList<>();

        for(int i = 0; i < 100; i++) {

            JLabel label = new JLabel();
            label.setVisible(false);
            list.add(label);
        }

        return list;
    }

    private List<JButton> handButtonAdder() {

        List list = new ArrayList<>();

        for(int i = 0; i < 10; i++) {

            JButton button = new JButton();
            button.setVisible(false);
            button.addActionListener((ActionEvent e) -> {
                //TODO
                if(handButtons1.contains(e.getSource())) {
                    int index = list.indexOf(e.getSource());
                    logic.playCard(index);
                    update();
                }
            });
            list.add(button);
        }
        return list;
    }

    private List<JButton> boardButtonAdder() {

        List list = new ArrayList<>();

        for(int i = 0; i < 5; i++) {

            JButton button = new JButton();
            button.setVisible(false);
            button.addActionListener((ActionEvent e) -> {
                //TODO
                int index = list.indexOf(e.getSource());
                if(logic.isSpellPressed() && boardButtons1.contains(e.getSource())) {
                    logic.castSpellOnOwnMinion(index);
                } else if (logic.isSpellPressed() && boardButtons2.contains(e.getSource())){
                    logic.castSpellOnEnemyMinion(index);
                } else if (logic.isHeroMagePressed() && boardButtons1.contains(e.getSource())) {
                    logic.mageDamagesMyMinion(index);
                } else if (logic.isHeroMagePressed() && boardButtons2.contains(e.getSource())) {
                    logic.mageDamagesEnemyMinion(index);
                } else if (logic.isHeroPriestPressed() && boardButtons1.contains(e.getSource())) {
                    logic.priestHealsMyMinion(index);
                } else if (logic.isHeroPriestPressed() && boardButtons2.contains(e.getSource())) {
                    logic.priestHealsEnemyMinion(index);
                } else if (logic.isSilenceActive() && boardButtons1.contains(e.getSource())) {
                    logic.silenceMyMinion(index);
                } else if (logic.isSilenceActive() && boardButtons2.contains(e.getSource())) {
                    logic.silenceEnemyMinion(index);
                } else if (logic.isHealActive() && boardButtons1.contains((e.getSource()))) {
                    logic.healMyMinion(index);
                } else if (logic.isHealActive() && boardButtons2.contains((e.getSource()))) {
                    logic.healEnemyMinion(index);
                } else if (!logic.isMinionPressed() && boardButtons1.contains(e.getSource())) {
                    logic.selectAttackerMinion(index);
                } else if (logic.isMinionPressed() && boardButtons2.contains(e.getSource())) {
                    logic.attackMinion(index);
                }
                update();
                logic.endGame();
                update();
            });
            list.add(button);
        }
        return list;
    }

    public Board1() throws HeadlessException{

        setTitle("FlowStone");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // "fullscreen"
        //setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        gameBoard();

        setVisible(true);

    }

    private void gameBoard() {

        JPanel handOfPlayerOne = hands(handButtons1);
        add(handOfPlayerOne, BorderLayout.SOUTH);

        JPanel handOfPlayerTwo = hands(handButtons2);
        add(handOfPlayerTwo, BorderLayout.NORTH);

        heroesAndEndTurn();

        JPanel whatHappened = commands();
        add(whatHappened, BorderLayout.WEST);

        centerBoard();

        update();

    }

    private JPanel commands() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS) );

        JLabel commandTitle = new JLabel("Commands");

        panel.add(commandTitle);

        for (JLabel step: steps) {
            panel.add(step);
        }
        return panel;
    }

    private JPanel hands(List<JButton> buttons) {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        for (JButton button:buttons) {
            panel.add(button);
        }

        return panel;
    }

    private void heroesAndEndTurn() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(4, 1));

        Border manaBorder = BorderFactory.createLineBorder(Color.BLACK, 2);

        logic.changePlayer();

        playerHero1Button.setText(logic.getPlayer().getHero().getHeroName() + " " + logic.getPlayer().getHero().getHealth());
        playerHero2Button.setText(logic.getOtherPlayer().getHero().getHeroName() + " " + logic.getPlayer().getHero().getHealth());
        endTurnButton.setText("End turn");
        mana.setText("Mana: " + logic.getMana());
        mana.setBorder(manaBorder);
        mana.setHorizontalAlignment(JLabel.CENTER);

        playerHero1Button.addActionListener((ActionEvent e) -> {

            if(logic.isSpellPressed()){
               logic.castSpellOnMyHero();
            } else if (logic.isHeroMagePressed()){
                logic.mageDamagesMyHero();
            } else if (logic.isHeroPriestPressed()) {
                logic.priestHealsMyHero();
            } else if (logic.isHealActive()) {
                logic.healMyHero();
            } else {
                logic.heroPower();
            }
            update();
            logic.endGame();
            update();
        });

        playerHero2Button.addActionListener((ActionEvent e) -> {
            if(logic.isSpellPressed()){
                logic.castSpellOnEnemyHero();
            } else if (logic.isHeroMagePressed()){
                logic.mageDamagesEnemyHero();
            } else if (logic.isHeroPriestPressed()) {
                logic.priestHealsEnemyHero();
            }  else if (logic.isHealActive()) {
                logic.healEnemyHero();
            } else if (logic.isMinionPressed()) {
                logic.attackHero();
            }
            update();
            logic.endGame();
            update();
        });

        endTurnButton.addActionListener((ActionEvent e) -> {
            logic.endTurn();
            logic.changePlayer();
            update();

        });

        panel.add(playerHero2Button);
        panel.add(endTurnButton);
        panel.add(playerHero1Button);
        panel.add(mana);

        add(panel, BorderLayout.EAST);
    }

    private void centerBoard() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,5));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());

        JLabel line = new JLabel("______________________________________________________________________________" +
                "__________________________________________________________________________________________________________________________________________________"+
                "__________________________________________________________________________________________________________________________________________________");

        for (JButton button : boardButtons1) {
            panel1.add(button);
        }

        for (JButton button : boardButtons2) {
            panel2.add(button);
        }

        panel.add(panel2);
        panel.add(line);
        panel.add(panel1);

        add(panel, BorderLayout.CENTER);

    }

    private void update() {

        for (int i = logic.getPlayer().getHand().size(); i < 10; i++) {
            handButtons1.get(i).setVisible(false);
        }

        for (int i = logic.getOtherPlayer().getHand().size(); i < 10; i++) {
            handButtons2.get(i).setVisible(false);
        }

        for (int i = logic.getPlayer().getBoard().size(); i < 5; i++) {
            boardButtons1.get(i).setVisible(false);
        }

        for (int i = logic.getOtherPlayer().getBoard().size(); i < 5; i++) {
            boardButtons2.get(i).setVisible(false);
        }

        for (int i = 0; i < logic.getPlayer().getHand().size(); i++) {
            if(logic.getPlayer().getHand().get(i) instanceof Minion) {
                handButtons1.get(i).setText("Minion " + logic.getPlayer().getHand().get(i).getCost() + " " + logic.getPlayer().getHand().get(i).getName() + " " + logic.getPlayer().getHand().get(i).getDescription() + " " + ((Minion) logic.getPlayer().getHand().get(i)).getAttack() + " " + ((Minion) logic.getPlayer().getHand().get(i)).getHealth());
            } else {
                handButtons1.get(i).setText("Spell " + logic.getPlayer().getHand().get(i).getCost() + " " + logic.getPlayer().getHand().get(i).getName() + " " + logic.getPlayer().getHand().get(i).getDescription());
            }
            handButtons1.get(i).setVisible(true);
        }
        for (int i = 0; i < logic.getOtherPlayer().getHand().size(); i++) {
            handButtons2.get(i).setText("Opponent's Card");
            handButtons2.get(i).setVisible(true);
        }
        for (int i = 0; i < logic.getPlayer().getBoard().size(); i++) {
            boardButtons1.get(i).setText(logic.getPlayer().getBoard().get(i).getName()+ " "  + ((Minion) logic.getPlayer().getBoard().get(i)).getAttack() + " " + ((Minion) logic.getPlayer().getBoard().get(i)).getHealth() + " " + logic.getPlayer().getBoard().get(i).getEffect());
            boardButtons1.get(i).setVisible(true);
        }
        for (int i = 0; i < logic.getOtherPlayer().getBoard().size(); i++) {
            boardButtons2.get(i).setText(logic.getOtherPlayer().getBoard().get(i).getName()+ " " + logic.getOtherPlayer().getBoard().get(i).getAttack() + " " + logic.getOtherPlayer().getBoard().get(i).getHealth() + " " + logic.getOtherPlayer().getBoard().get(i).getEffect());
            boardButtons2.get(i).setVisible(true);
        }
        for (int i = 0; i < logic.getSteps().size(); i++) {
            steps.get(i).setText(logic.getSteps().get(i));
            steps.get(i).setVisible(true);
        }
        playerHero1Button.setText(logic.getPlayer().getHero().getHeroName() + " " + logic.getPlayer().getHero().getHealth());
        playerHero2Button.setText(logic.getOtherPlayer().getHero().getHeroName() + " " + logic.getOtherPlayer().getHero().getHealth());
        mana.setText("Mana: " + logic.getMana());

    }

}
