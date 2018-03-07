/*
Map for Route 2
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;
import Events.Teleport;
import java.util.Arrays;


public class Route2 extends Map
{
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Route2 ()
    {
        super ("Route 2");
        shifts [1] = -11*32;
        bLevel = 5;
        hLevel = 9;
        
        eventArray[21][39] = new Teleport();
        eventArray[21][39].setCoord(4, 2, 12, 21);
        
        eventArray[23][6] = new Sign();
        eventArray[23][6].addText("BEWARE OF SIGNS - They are dangerous. ");
        eventArray[16][24] = new DumbNPC(9, 0);
        eventArray[16][24].addText("I used to be a banker but I lost interest.");
        eventArray[15][22] = new Sign();
        eventArray[15][22].addText("Beware of Old-Man");
        eventArray[12][30] = new DumbNPC(1, 2);
        eventArray[12][30].addText("Hi! I like shorts! They're comfy and easy to wear.");
        
        grassPokemon = Arrays.asList(new String [] {"Caterpie", "Sentret", "Swablu", "Shinx", "Minccino", "Deerling"});
        waterPokemon = Arrays.asList(new String [] {"Dratini", "Horsea", "Horsea", "Horsea", "Horsea", "Horsea", "Horsea" });
        fishPokemon = Arrays.asList(new String [] {"Manaphy", "Finneon", "Feebas"});
    }
}
