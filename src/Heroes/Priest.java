package Heroes;

import Board.Board;
import Cards.Card;
import Cards.Minion;
import Logic.LogicRefactor2;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Priest extends Hero {

    public Priest(String heroName, boolean immune) {

        super(heroName, immune);
    }

    public void heroPower(Object object) {
    }

    @Override
    public String toString() {
        return "Priest";
    }
}
