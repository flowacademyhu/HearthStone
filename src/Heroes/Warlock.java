package Heroes;

public class Warlock extends Hero {

    public Warlock(String heroName, boolean immune) {

        super(heroName, immune);
    }

    public void heroPower(Object object) {

    }

    @Override
    public String toString() {
        return "Warlock";
    }
}
