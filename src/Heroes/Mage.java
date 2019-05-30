package Heroes;

public class Mage extends Hero{


    public Mage(String heroName, boolean immune) {

        super(heroName, immune);
    }

    public void heroPower(Object object) {
    }

    @Override
    public String toString() {
        return "Mage";
    }
}
