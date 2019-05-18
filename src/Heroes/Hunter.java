package Heroes;

public class Hunter extends Hero{

    public Hunter(String heroName, boolean immune) {

        super(heroName, immune);
    }

    //deal 2 damage to opponent hero
    public void heroPower(Object object) {
        ((Hero)object).setHealth(((Hero)object).getHealth()-2);
    }

    @Override
    public String toString() {
        return "Hunter";
    }

}
