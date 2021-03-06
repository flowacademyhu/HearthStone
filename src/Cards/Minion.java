package Cards;

import java.util.Objects;
import java.util.UUID;

public class Minion extends Card {

    private final String id;
    private int attack;
    private int health;
    private int basicAttack;
    private int basicHealth;
    private int maxHealth;
    private String effect;
    private boolean canAttack;
    private boolean freezed;

    public Minion(String name, String description, int cost, int attack, int basicAttack, int health, int basicHealth, int maxHealth, boolean canAttack, String effect, boolean freezed) {
        super(name, description, cost);
        this.id = UUID.randomUUID().toString();
        this.attack = attack;
        this.health = health;
        this.effect = effect;
        this.canAttack = canAttack;
        this.maxHealth = maxHealth;
        this.basicAttack = basicAttack;
        this.basicHealth = basicHealth;
        this.freezed = freezed;
    }

    public int getBasicAttack() {
        return basicAttack;
    }

    public int getBasicHealth() {
        return basicHealth;
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

    public boolean isFreezed() {
        return freezed;
    }

    public void setFreezed(boolean freezed) {
        this.freezed = freezed;
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
