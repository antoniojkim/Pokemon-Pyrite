/*
This is the map for the PokeMart in Amishville
 */
package Maps;

import Events.ShopKeep;
import Events.Teleport;


public class AmishMart extends Map {

        /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public AmishMart()
    {
        super("PokeMart");

        eventArray[20][14] = new Teleport();
        eventArray[20][14].setCoord(5, 2, 18, 17);

        eventArray[15][13] = new ShopKeep();
    }

}
