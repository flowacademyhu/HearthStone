package Cards;

import java.util.ArrayList;
import java.util.List;

public class CardList {

    private List<Card> list = new ArrayList<>();

    public List<Card> cardList() {

        //10 simple minions
        list.add(new Minion("Orc Warrior", "", 3, 3,3,2, 2,2, false,"", false));
        list.add(new Minion("Human Warrior", "", 3, 2,2,3, 3,3,false,"", false));
        list.add(new Minion("Dwarf Warrior", "", 4, 3,3,3, 3,3,false,"", false));
        list.add(new Minion("Elf Warrior", "", 2, 2,2, 2,2,2,false,"", false));
        list.add(new Minion("Tauren Warrior", "", 5, 4, 4, 3, 3,3,false,"", false));
        list.add(new Minion("Orc Archer", "", 3, 4,4,1,1, 1,false,"", false));
        list.add(new Minion("Human Archer", "", 3, 4,4,1,1, 1,false,"", false));
        list.add(new Minion("Dwarf Archer", "", 4, 4,4,2,2, 2,false,"", false));
        list.add(new Minion("Elf Archer", "", 2, 3,3,1,1, 1,false,"", false));
        list.add(new Minion("Tauren Archer", "", 5, 5,5,2,2, 2,false,"", false));
        //5 taunt minions
        list.add(new Minion("Gate defender", "taunt", 3, 1,1,3,3, 3,false,"taunt", false));
        list.add(new Minion("Ready to die", "taunt", 0, 0,0,2,2, 2,false,"taunt", false));
        list.add(new Minion("Ready to die 2.0", "taunt", 0, 0,0,2,2, 2,false,"taunt", false));
        list.add(new Minion("Protector", "taunt", 5, 3,3,5,5, 5,false,"taunt", false));
        list.add(new Minion("FireWall", "taunt", 2, 3,3,3,3, 3,false,"taunt", false));
        //4 charge minions
        list.add(new Minion("Wolf rider", "charge", 3, 4,4,3,3, 3, true,"charge", false));
        list.add(new Minion("Tank", "charge", 4, 2,5, 2,5,5,true,"charge", false));
        list.add(new Minion("Goblin racer", "charge", 3, 3,3,4,4, 4,true,"charge", false));
        list.add(new Minion("Gnome racer", "charge", 2, 2,2,1,1, 1,true,"charge", false));
        //14 battlecry minioins
        list.add(new Minion("Warchief", "battlecry: gets +1/+1 after every unit", 5, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Warchier", "battlecry: gets +1/+1 after every unit", 5, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Soul eater", "battlecry: silences a minion", 3, 2,3,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Soul eater", "battlecry: silences a minion", 3, 2,3,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Leader", "battlecry: gets +1/+1 after every friendly unit", 4, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Leader", "battlecry: gets +1/+1 after every friendly unit", 4, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Medic", "battlecry: gives 4 hp", 5, 2,3,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Medic", "battlecry: gives 4 hp", 5, 2,3,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Gambler", "battlecry: draws 2 cards", 5, 3,3,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Gambler", "battlecry: draws 2 cards", 5, 3,3,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Hellraiser", "battlecry: gives 2 damage to every unit", 2, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Hellraiser", "battlecry: gives 2 damage to every unit", 2, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Giant hunter", "battlecry: destroys all minions above 3 damage", 2, 2,2,1,1, 1,false,"battlecry", false));
        list.add(new Minion("Giant hunter", "battlecry: destroys all minions above 3 damage", 2, 2,2,1,1, 1,false,"battlecry", false));
        //14 spells
        list.add(new Spell("Holy blessing", "gives +3/+3 to a minion",3, "holyblessing"));
        list.add(new Spell("Holy blessing", "gives +3/+3 to a minion",3, "holyblessing"));
        list.add(new Spell("Cavalry", "gives +1/+1 to neighbour minions",3, "cavalry"));
        list.add(new Spell("Cavalry", "gives +1/+1 to neighbour minions",3, "cavalry"));
        list.add(new Spell("Flee", "puts minion back to owner's hand",2, "flee"));
        list.add(new Spell("Flee", "puts minion back to owner's hand",2, "flee"));
        list.add(new Spell("Traitor", "steals an enemy minion",10, "traitor"));
        list.add(new Spell("Traitor", "steals an enemy minion",10, "traitor"));
        list.add(new Spell("Dragonfire", "deals 4 damage",4, "dragonfire"));
        list.add(new Spell("Dragonfire", "deals 4 damage",4, "dragonfire"));
        list.add(new Spell("Save the hero", "hero becomes immune this turn",5, "savethehero"));
        list.add(new Spell("Save the hero", "hero becomes immune this turn",5, "savethehero"));
        list.add(new Spell("Frost blast", "freeze a minion",3, "frostblast"));
        list.add(new Spell("Frost blast", "freeze a minion",3, "frostblast"));

        return list;

    }
}
