/*
This is the map of the PokeMart in Town 2
 */
package Maps;

import Events.ShopKeep;
import Events.Teleport;


public class T2PokeMart extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public T2PokeMart()
    {
        super("PokeMart");
        
        eventArray[15][13] = new ShopKeep();
        
        eventArray[20][14] = new Teleport();
        eventArray[20][14].setCoord(3, 3, 25, 15);
    }

}
