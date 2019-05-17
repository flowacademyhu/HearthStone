package Cards;

import javax.swing.*;
import java.util.List;

public class Card extends JButton {

    private String name;
    private String description;
    private int cost;
    //TODO private String type;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
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
