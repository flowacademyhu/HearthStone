package Logic;

public class PlayCardProcessor {
//play card
    PlayCardProcessor() {}
     private void playMinion() {
         if (card.getDescription().equals("battlecry: gets +1/+1 after every unit")) {

                minion.addPlus1Plus1((Minion) card, player.getBoard(), otherPlayer.getBoard());

            } else if (card.getDescription().equals("battlecry: gets +1/+1 after every friendly unit")) {

                minion.addPlus1Plus1AfterFriendly((Minion) card, player.getBoard());

            } else if ((card.getDescription().equals("battlecry: draws 2 cards"))) {

                List<Card> drawedCards = new ArrayList<>();
                drawedCards = deck.draw(2, drawedCards, player.getDeck(), player.getHero());

                player.getHand().addAll(drawedCards);

            } else if ((card.getDescription().equals("battlecry: gives 2 damage to every unit"))) {

                minion.deal2(player.getBoard(), otherPlayer.getBoard());

            } else if ((card.getDescription().equals("battlecry: destroys all minions above 3 damage"))) {

                minion.destroyAbove3(player.getBoard(), otherPlayer.getBoard());
            }
            deck.addCardToBoard(player.getBoard(), (Minion) card);
            mana -= card.getCost();
            steps.add(happenings.whatHappenedMinion((Minion)card));
            player.getHand().remove(card);

     }
     public void playSpecialMinion() {
         if (!otherPlayer.getBoard().isEmpty() && !player.getBoard().isEmpty()) {

                    healActive = true;
                    minionIndex = i;

        } else {
                    deck.addCardToBoard(player.getBoard(), (Minion) card);
                    mana -= card.getCost();
                    player.getHand().remove(card);
                }
         
     }
    public void process(int i) {
        minionIndex = i;
        //play minion
        var card = player.getHand().get(i);

       
        if (card.getClass() == Minion.class && card.getCost() <= mana && player.getBoard().size() < 5 && !card.getDescription().equals("battlecry: silences a minion") && !card.getDescription().equals("battlecry: gives 4 hp")) {
            //battlecry
            playMinion();
        }
        else if ((card.getDescription().equals("battlecry: gives 4 hp"))) {

                playSpecialMinion();
            } else if (card.getDescription().equals("battlecry: silences a minion")) {
                if (!otherPlayer.getBoard().isEmpty() && !player.getBoard().isEmpty()) {

                    silenceActive = true;
                    minionIndex = i;

                } else {
                    deck.addCardToBoard(player.getBoard(), (Minion) card);
                    mana -= card.getCost();
                    player.getHand().remove(card);
                }
            }
        //play spell
        else if (card instanceof Spell && card.getCost() <= mana) {

            spellPressed = true;
            spellIndex = i;


            if(((Spell) card).getEffect().equals("holyblessing")) {
                holyblessing = true;
                System.out.println("a");
            } else if (((Spell) card).getEffect().equals("cavalry")) {
                cavalry = true;
                System.out.println("b");
            } else if (((Spell) card).getEffect().equals("flee")){
                flee = true;
                System.out.println("c");
            } else if  (((Spell) card).getEffect().equals("traitor")){
                traitor = true;
                System.out.println("d");
            } else if (((Spell) card).getEffect().equals("dragonfire")) {
                dragonfire = true;
                System.out.println("e");
            } else if (((Spell) card).getEffect().equals("frostblast")) {
                frostbalst = true;
                System.out.println("f");
            } else if (((Spell) card).getEffect().equals("savethehero")) {
                savethehero = true;
                System.out.println("g");
            }

            steps.add(happenings.whatHappenedSpell((Spell)card));
            mana -= ((Spell) card).getCost();
        }
    }
}