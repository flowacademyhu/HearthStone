package GUI;

import Cards.Minion;
import Heroes.Hero;
import Logic.Logic;
import Music.DuelMusic;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.text.DefaultCaret.ALWAYS_UPDATE;

public class Board extends JFrame {

    private Logic logic;

    private JButton playerHero1Button = new JButton();
    private JButton playerHero2Button = new JButton();
    private JButton endTurnButton = new JButton();
    private JLabel mana = new JLabel();

    private List<JButton> handButtons1 = handButtonAdder();
    private List<JButton> handButtons2 = handButtonAdder();

    private List<JButton> boardButtons1 = boardButtonAdder();
    private List<JButton> boardButtons2 = boardButtonAdder();

    private JTextArea steps = new JTextArea("Commands");

    Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 3);
    Border greenBorder = BorderFactory.createLineBorder(Color.GREEN, 6);
    Border redBorder = BorderFactory.createLineBorder(Color.RED, 6);
    Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 6);
    Border greyBorder = BorderFactory.createLineBorder(Color.GRAY, 6);
    Border thinBlack = BorderFactory.createLineBorder(Color.BLACK, 1);

    Font fontHappens = new Font("SansSerif", Font.BOLD, 20);

    DuelMusic duelMusic = new DuelMusic();

    public Board(Hero hero1, Hero hero2) throws HeadlessException{

        setTitle("FlowStone");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        logic = new Logic(hero1, hero2);

        gameBoard();

        duelMusic.run();

        setVisible(true);

    }

    private List<JButton> handButtonAdder() {

        List list = new ArrayList<>();

        for(int i = 0; i < 10; i++) {

            JButton button = new JButton();
            button.setVisible(false);
            button.addActionListener((ActionEvent e) -> {
                if(handButtons1.contains(e.getSource())) {
                    int index = list.indexOf(e.getSource());
                    logic.playCard(index);
                    update();
                }
            });
            button.setPreferredSize(new Dimension(180, 230));
            button.setBackground(Color.WHITE);
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
            button.setPreferredSize(new Dimension(200, 150));
            list.add(button);
        }
        return list;
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
        steps.setText("Commands" + "\n" + "-------------------" + "\n");

        JScrollPane scrollPane = new JScrollPane(steps);
        steps.setCaretPosition(steps.getDocument().getLength() - 1);
        scrollPane.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        DefaultCaret caret = (DefaultCaret) steps.getCaret();
        caret.setUpdatePolicy(ALWAYS_UPDATE);

        steps.setEditable(false);
        steps.setFont(fontHappens);
        scrollPane.setPreferredSize(new Dimension(370,520));

        scrollPane.setBackground(Color.WHITE);
        panel.setBackground(Color.ORANGE);

        panel.add(scrollPane);

        return panel;
    }

    private JPanel hands(List<JButton> buttons) {

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        panel.setBackground(Color.ORANGE);

        for (JButton button:buttons) {
            panel.add(button);
        }

        return panel;
    }

    private void heroesAndEndTurn() {

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(4, 1));

        logic.changePlayer();

        playerHero1Button.setText(logic.getPlayer().getHero().getHeroName() + " " + logic.getPlayer().getHero().getHealth());
        playerHero2Button.setText(logic.getOtherPlayer().getHero().getHeroName() + " " + logic.getPlayer().getHero().getHealth());
        endTurnButton.setText("End turn");
        mana.setText("Mana: " + logic.getMana() + "Deck: " + logic.getPlayer().getDeck().size());
        mana.setBorder(blackBorder);
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
            if(logic.endGame()) {
                duelMusic.stop();
                HeroSelection heroSelection = new HeroSelection();
                setVisible(false);
            }

        });

        playerHero1Button.setPreferredSize(new Dimension(150, 150));
        playerHero2Button.setPreferredSize(new Dimension(150, 150));
        endTurnButton.setPreferredSize(new Dimension(150, 150));
        mana.setPreferredSize(new Dimension(150, 150));

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

        panel.setBackground(Color.ORANGE);
        panel1.setBackground(Color.ORANGE);
        panel2.setBackground(Color.ORANGE);

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
                handButtons1.get(i).setText(logic.getPlayer().getHand().get(i).getCost() + " " + logic.getPlayer().getHand().get(i).getName() + " " + ((Minion) logic.getPlayer().getHand().get(i)).getAttack() + " " + ((Minion) logic.getPlayer().getHand().get(i)).getHealth());
                handButtons1.get(i).setToolTipText("Minion" + " " + logic.getPlayer().getHand().get(i).getDescription());
            } else {
                handButtons1.get(i).setText(logic.getPlayer().getHand().get(i).getCost() + " " + logic.getPlayer().getHand().get(i).getName());
                handButtons1.get(i).setToolTipText("Spell" + " " + logic.getPlayer().getHand().get(i).getDescription());
            }
            if(logic.getPlayer().getHand().get(i).getCost() <= logic.getMana()){
                handButtons1.get(i).setBorder(greenBorder);
            } else {
                handButtons1.get(i).setBorder(thinBlack);
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
            if(logic.getPlayer().getBoard().get(i).isFreezed()){
                boardButtons1.get(i).setBorder(blueBorder);
            } else if (logic.getPlayer().getBoard().get(i).getEffect().equals("taunt") && !logic.getPlayer().getBoard().get(i).isCanAttack() || logic.getPlayer().getBoard().get(i).getEffect().equals("taunt") && logic.getPlayer().getBoard().get(i).getAttack() <= 0) {
                boardButtons1.get(i).setBorder(greyBorder);
            } else if (logic.getPlayer().getBoard().get(i).isCanAttack()) {
                boardButtons1.get(i).setBorder(greenBorder);
            }  else if (!logic.getPlayer().getBoard().get(i).isCanAttack()) {
                boardButtons1.get(i).setBorder(redBorder);
            }
        }
        for (int i = 0; i < logic.getOtherPlayer().getBoard().size(); i++) {
            boardButtons2.get(i).setText(logic.getOtherPlayer().getBoard().get(i).getName()+ " " + logic.getOtherPlayer().getBoard().get(i).getAttack() + " " + logic.getOtherPlayer().getBoard().get(i).getHealth() + " " + logic.getOtherPlayer().getBoard().get(i).getEffect());
            boardButtons2.get(i).setVisible(true);
            if(logic.getOtherPlayer().getBoard().get(i).isFreezed()){
                boardButtons2.get(i).setBorder(blueBorder);
            } else if (logic.getOtherPlayer().getBoard().get(i).getEffect().equals("taunt") && !logic.getOtherPlayer().getBoard().get(i).isCanAttack() || logic.getOtherPlayer().getBoard().get(i).getEffect().equals("taunt") && logic.getOtherPlayer().getBoard().get(i).getAttack() <= 0) {
                boardButtons2.get(i).setBorder(greyBorder);
            } else if (logic.getOtherPlayer().getBoard().get(i).isCanAttack()) {
                boardButtons2.get(i).setBorder(greenBorder);
            }  else if (!logic.getOtherPlayer().getBoard().get(i).isCanAttack()) {
                boardButtons2.get(i).setBorder(redBorder);
            }
        }
        playerHero1Button.setText(logic.getPlayer().getHero().getHeroName() + " " + logic.getPlayer().getHero().getHealth());
        if(logic.getPlayer().getHero().isImmune()){
            playerHero1Button.setBorder(blueBorder);
        } else {
            playerHero1Button.setBorder(thinBlack);
        }
        playerHero2Button.setText(logic.getOtherPlayer().getHero().getHeroName() + " " + logic.getOtherPlayer().getHero().getHealth());
        if(logic.getOtherPlayer().getHero().isImmune()){
            playerHero2Button.setBorder(blueBorder);
        } else {
            playerHero2Button.setBorder(thinBlack);
        }
        mana.setText("Mana: " + logic.getMana() + " Deck: " + logic.getPlayer().getDeck().size());
        if(logic.endGame()) {
            endTurnButton.setText("New Game");
            for(int i = 0; i < logic.getOtherPlayer().getHand().size(); i++) {

                if(logic.getOtherPlayer().getHand().get(i) instanceof Minion) {
                    handButtons2.get(i).setText(logic.getOtherPlayer().getHand().get(i).getCost() + " " + logic.getOtherPlayer().getHand().get(i).getName() + " " + ((Minion) logic.getOtherPlayer().getHand().get(i)).getAttack() + " " + ((Minion) logic.getOtherPlayer().getHand().get(i)).getHealth());
                    handButtons2.get(i).setToolTipText("Minion" + " " + logic.getOtherPlayer().getHand().get(i).getDescription());
                } else {
                    handButtons2.get(i).setText(logic.getOtherPlayer().getHand().get(i).getCost() + " " + logic.getOtherPlayer().getHand().get(i).getName());
                    handButtons2.get(i).setToolTipText("Spell" + " " + logic.getOtherPlayer().getHand().get(i).getDescription());
                }
            }
        }
        steps.setText("");
        steps.setText(steps.getText() + "\n" + logic.getSteps());
    }
}
