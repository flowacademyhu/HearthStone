package Heroes;

import javax.swing.*;

public abstract class Hero extends JButton {

    private String heroName;
    private int health;
    private boolean immune;

    public Hero(String heroName, boolean immune) {
        this.heroName = heroName;
        this.health = 30;
        this.immune = immune;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    public String getHeroName() {
        return heroName;
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
