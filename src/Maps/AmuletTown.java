/*
This is the map for Amulet Town
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;
import Events.Teleport;
import java.util.Arrays;

public class AmuletTown extends Map {

    
    /*
    This loads the music string.
    pre: file exists
    post: music name returned
    */
    public String getMusic()
    {
        return "Reborn City.wav";
    }

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmuletTown()
    {
        super("Amulet Town");
        shifts[3] = 15 * 32;
        shifts[0] = -8 * 32;
        bLevel = 24;
        hLevel = 32;
        waterPokemon = Arrays.asList(new String[]{
            "Kyogre"
        });

//        eventArray[10][25] = new Sign();
//        eventArray[10][25].addText("PokeTech Industries (Totally not evil or anything. Ehnething. Eht Ehll!) Pay no attention to the man behing the curtain! NONE EHT EHLL!");
//        eventArray[3][19] = new Sign();
//        eventArray[3][19].addText("That meteor did the thing...");
        Sign.addSign(25, 10, "PokeTech Industries (Totally not evil or anything. Ehnething. Eht Ehll!)", eventArray);
        Sign.addSign(19, 3, "That meteor did the thing...", eventArray);

        eventArray[10][14] = new Teleport();
        eventArray[10][14].setCoord(9, 9, 13, 16);
        eventArray[4][14] = new Teleport();
        eventArray[4][14].setCoord(10, 8, 12, 16);
        eventArray[10][17] = new DumbNPC(5, 0);
        eventArray[10][17].addText("I'd tell you a chemistry joke but I know I wouldn't get a reaction.");
        eventArray[10][29] = new Teleport();
        eventArray[10][29].setCoord(10, 6, 21, 26);
        Sign.addSign(24, 1, "Beware: Pokemon will attack you.", eventArray);

    }
}
