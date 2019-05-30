package GUI;

import Heroes.*;
import Music.InnkeeperSpeech;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class HeroSelection extends JFrame {

    private Hero hero1;
    private Hero hero2;

    private boolean playerOneReady = false;
    private boolean playerTwoReady = false;

    private JLabel icon = new JLabel();

    private JLabel player1 = new JLabel("Player One");
    private JLabel player2 = new JLabel("Player Two");
    private JButton hunter = new JButton("Hunter");
    private JButton mage = new JButton("Mage");
    private JButton warlock = new JButton("Warlock");
    private JButton paladin = new JButton("Paladin");
    private JButton priest = new JButton("Priest");
    private JButton hunterOther = new JButton("Hunter");
    private JButton mageOther = new JButton("Mage");
    private JButton warlockOther = new JButton("Warlock");
    private JButton paladinOther = new JButton("Paladin");
    private JButton priestOther = new JButton("Priest");

    List<JButton> buttons = new ArrayList<>();
    List<JButton> buttonsOther = new ArrayList<>();

    InnkeeperSpeech innkeeperSpeech = new InnkeeperSpeech();

    public HeroSelection(){

        setTitle("FlowStone");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        selectHeroes();

        setVisible(true);

        innkeeperSpeech.run();

    }

    private void selectHeroes() {

        JPanel heroes1 = heroes(player1, hunter, mage, warlock, paladin, priest);
        add(heroes1, BorderLayout.WEST);

        JPanel heroes2 = heroes(player2, hunterOther, mageOther, warlockOther, paladinOther, priestOther);
        add(heroes2, BorderLayout.EAST);

        JPanel icon = icon();
        add(icon, BorderLayout.CENTER);

        buttonEffectAdderPlayerOne();

        buttonEffectAdderPlayerTwo();

    }

    private JPanel heroes(JLabel player, JButton hunter, JButton mage, JButton warlock, JButton paladin, JButton priest) {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6,1));

        player.setPreferredSize(new Dimension(200, 200));
        hunter.setPreferredSize(new Dimension(200, 200));
        mage.setPreferredSize(new Dimension(200, 200));
        warlock.setPreferredSize(new Dimension(200, 200));
        paladin.setPreferredSize(new Dimension(200, 200));
        priest.setPreferredSize(new Dimension(200, 200));

        player.setHorizontalAlignment(JLabel.CENTER);

        panel.add(player);
        panel.add(hunter);
        panel.add(mage);
        panel.add(warlock);
        panel.add(paladin);
        panel.add(priest);

        return panel;
    }

    private JPanel icon() {

        JPanel panel = new JPanel();

        icon.setIcon(new ImageIcon("./src/Texture/hearthstone.png"));

        panel.add(icon);

        return panel;
    }

    private void buttonEffectAdderPlayerOne() {

        buttons.add(hunter);
        buttons.add(mage);
        buttons.add(warlock);
        buttons.add(paladin);
        buttons.add(priest);

        for (JButton button : buttons) {
            button.addActionListener((ActionEvent e) -> {
                if(((JButton)e.getSource()).getText().equals("Hunter")){
                    hero1 = new Hunter("Hunter", false);
                } else if(((JButton)e.getSource()).getText().equals("Mage")){
                    hero1 = new Mage("Mage", false);
                } else if(((JButton)e.getSource()).getText().equals("Warlock")){
                    hero1 = new Warlock("Warlock", false);
                } else if(((JButton)e.getSource()).getText().equals("Paladin")){
                    hero1 = new Paladin("Paladin", false);
                } else if(((JButton)e.getSource()).getText().equals("Priest")){
                    hero1 = new Priest("Priest", false);
                }
                playerOneReady = true;
                startTheGame();
            });
        }
    }

    private void buttonEffectAdderPlayerTwo() {

        buttonsOther.add(hunterOther);
        buttonsOther.add(mageOther);
        buttonsOther.add(warlockOther);
        buttonsOther.add(paladinOther);
        buttonsOther.add(priestOther);

        for (JButton button : buttonsOther) {
            button.addActionListener((ActionEvent e) -> {
                if(((JButton)e.getSource()).getText().equals("Hunter")){
                    hero2 = new Hunter("Hunter", false);
                } else if(((JButton)e.getSource()).getText().equals("Mage")){
                    hero2 = new Mage("Mage", false);
                } else if(((JButton)e.getSource()).getText().equals("Warlock")){
                    hero2 = new Warlock("Warlock", false);
                } else if(((JButton)e.getSource()).getText().equals("Paladin")){
                    hero2 = new Paladin("Paladin", false);
                } else if(((JButton)e.getSource()).getText().equals("Priest")){
                    hero2 = new Priest("Priest", false);
                }
                playerTwoReady = true;
                startTheGame();
            });
        }
    }

    private void startTheGame() {
        if(playerOneReady && playerTwoReady) {

            Board board = new Board(hero1, hero2);

            innkeeperSpeech.stopMenuMusic();

            setVisible(false);
        }
    }
}
