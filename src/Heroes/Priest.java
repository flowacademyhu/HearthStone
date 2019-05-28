package Heroes;

public class Priest extends Hero {

    public Priest(String heroName, boolean immune) {

        super(heroName, immune);
    }

    public void heroPower(Object object) {
    }

    @Override
    public String toString() {
        return "Priest";
    }
}
