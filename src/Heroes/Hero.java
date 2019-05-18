package Heroes;

import javax.swing.*;

public abstract class Hero extends JButton {

    private String heroName;
    private int health;
    //TODO private int armor;

    public Hero(String heroName) {
        this.heroName = heroName;
        this.health = 1;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "health=" + health +
                '}';
    }

    public abstract void heroPower(Object object);
}
