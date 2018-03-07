/*
Map of the Trump City PokeMart
 */
package Maps;

import Events.ShopKeep;
import Events.Teleport;

public class TrumpPokeMart extends Map {

    public TrumpPokeMart()
    {
        /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
         */
        super("PokeMart");

        eventArray[15][13] = new ShopKeep();

        eventArray[20][14] = new Teleport();
        eventArray[20][14].setCoord(3, 5, 56, 48);

    }

}
