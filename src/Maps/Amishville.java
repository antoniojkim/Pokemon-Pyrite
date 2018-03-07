/*
This is the map for Amishville 
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;
import Events.Teleport;


public class Amishville extends Map {

    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    public Amishville()
    {
        super("Amishville");
        shifts[0] = -10 * 32;
        shifts[1] = -6 * 32;

        eventArray[16][18] = new Teleport();
        eventArray[16][18].setCoord(11, 7, 14, 19);
        eventArray[6][25] = new Teleport();
        eventArray[6][25].setCoord(11, 5, 18, 22);

        eventArray[16][15] = new DumbNPC(20, 0);
        eventArray[16][15].addText("I couldn't quite remember how to throw a boomerang, but eventually it came back to me.");

        eventArray[18][26] = new Sign();
        eventArray[18][26].addText("CAUTION: Slippery when wet.");

        eventArray[16][12] = new Teleport();
        eventArray[16][12].setCoord(11, 3, 14, 22);

        eventArray[13][27] = new DumbNPC(27, 0);
        eventArray[13][27].addText("I'm reading a book about anti-gravity. It's impossible to put down.");

        eventArray[8][12] = new DumbNPC(10, 0);
        eventArray[8][12].addText("Did you hear about the guy whose whole left side was cut off? He's all right now.");

    }
}
