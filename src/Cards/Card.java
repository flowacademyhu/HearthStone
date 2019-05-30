package Cards;

import javax.swing.*;

public class Card extends JButton {

    private String name;
    private String description;
    private int cost;

    public Card(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "\n" + "Card{" +
                "name='" + name + "\n" +
                ", description='" + description + "\n" +
                ", cost=" + cost +
                '}';
    }
}
