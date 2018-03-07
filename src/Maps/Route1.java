/*
 Map for Route 1
 */
package Maps;

import Events.TrainerNPC;
import java.util.Arrays;
import Pokemon.Pokemon;

public class Route1 extends Map
{

    /*
     This is the constructor which initialized the map. It calls upon the
     initializer of the Map class to load files. It fills any events, wild pokemon
     and loads collisions.
     pre: Files exist
     post: Map initiated
     */
    public Route1()
    {
        super("Route 1");
        shifts[1] = -15 * 32;
        shifts[3] = 9 * 32;
        bLevel = 2;
        hLevel = 6;

        eventArray[19][43] = new TrainerNPC(0, 0, 2, 2, Arrays.asList("Magikarp", "Electrike", "Barboach"));
        eventArray[19][43].addText("Fight me.");
        
        eventArray[29][12] = new TrainerNPC(5, 1, 2, 6, Arrays.asList("Pichu", "Pikachu","Pikachu","Pikachu","Pikachu"));
        eventArray[29][12].addText("I despise Pikachu.");
        
        eventArray[13][44] = new TrainerNPC(9, 3, 2, 4, Arrays.asList("Turtwig","Rattata", "Pidgey"));
        eventArray[13][44].addText("I've been standing here for 26 years...");
        eventArray[25][40] = new TrainerNPC(6, 0, 3, 7, Arrays.asList("Ekans", "Muk","Koffing"));
        eventArray[25][40].addText("My mom said that my behaviour was unacceptable and toxic.");
// eventArray[19][43].setTrainerInfo(Arrays.asList(new Pokemon("Magikarp", 3)));

        grassPokemon = Arrays.asList(new String[]
        {
            "Pidgey", "Pichu", "Nidoran M", "Nidoran F", "Ralts", "Blitzle"
        });
        waterPokemon = Arrays.asList(new String[]
        {
            "Magikarp", "Tirtouga", "Frillish"
        });
        fishPokemon = Arrays.asList(new String[]
        {
            "Magikarp"
        });

    }
}
