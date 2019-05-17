package Cards;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Minion extends Card {

    private final String id;
    private int attack;
    private int health;
    private int maxHealth;
    private String effect;
    private boolean canAttack;
    //TODO private String type; (pl murloc);


    public Minion(String name, String description, int cost, int attack, int health, int maxHealth, boolean canAttack, String effect) {
        super(name, description, cost);
        this.id = UUID.randomUUID().toString();
        this.attack = attack;
        this.health = health;
        this.effect = effect;
        this.canAttack = canAttack;
        this.maxHealth = maxHealth;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Minion minion = (Minion) o;
        return id.equals(minion.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return super.toString() + "Minion{" +
                "attack=" + attack +
                ", health=" + health +
                ", effect='" + effect + '\'' +
                '}';
    }


}
