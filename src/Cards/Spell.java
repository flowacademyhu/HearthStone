package Cards;

public class Spell extends Card{

    private String effect;

    public Spell(String name, String description, int cost, String effect) {
        super(name, description, cost);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    @Override
    public String toString() {
        return super.toString() + "Spell{" +
                "effect='" + effect + '\'' +
                '}';
    }
}
