package Cards;

import java.util.List;
import java.util.UUID;

public class Spell extends Card{

    private final String id;
    private String effect;

    public Spell(String name, String description, int cost, String effect) {
        super(name, description, cost);
        this.id = UUID.randomUUID().toString();
        this.effect = effect;
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
    public String toString() {
        return super.toString() + "Spell{" +
                "effect='" + effect + '\'' +
                '}';
    }
}
