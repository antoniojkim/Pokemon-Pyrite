/*
Map of Trump City
 */
package Maps;

import Events.DumbNPC;
import Events.Sign;
import Events.Teleport;
import java.util.Arrays;


public class TrumpCity extends Map
{
    /*
    This is the constructor which initialized the map. It calls upon the
    initializer of the Map class to load files. It fills any events, wild pokemon
    and loads collisions.
    pre: Files exist
    post: Map initiated
    */
    TrumpCity()
    {
        super ("Trump City");
        shifts[2] = -20*32;
        shifts[3] = 24*32;
        
        eventArray[47][56] = new Teleport();
        eventArray[47][56].setCoord(9, 1, 14, 19);
        eventArray[47][61] = new Teleport();
        eventArray[47][61].setCoord(10, 2, 18, 22);
        eventArray[27][66] = new Teleport();
        eventArray[27][66].setCoord(11, 9, 14, 22);
        eventArray[37][43] = new DumbNPC(0, 0);
        eventArray[37][43].addText("I was going to buy a book on phobias, but I was afraid it wouldn't help me.");
        eventArray[20][57] = new DumbNPC(23, 0);
        eventArray[20][57].addText("I will build a great wall. It will be a great, great wall and I will make Kanto pay for that wall.      Mark my words");
        eventArray[55][48] = new Sign();
        eventArray[55][48].addText("Eviction Notice: Rent not paid for 20 years. ");
        eventArray[55][36] = new Sign();
        eventArray[55][36].addText("Don't drown. It's bad...and we'd get sued...");
        eventArray[21][60] = new Sign();
        eventArray[21][60].addText("Chump Tower: Can't stump the Chump! Chump 2016");
        eventArray[37][46] = new Sign();
        eventArray[37][46].addText("Better luck next time, chump.");
        eventArray[35][59] = new DumbNPC(9, 0);
        eventArray[35][59].addText("Trump City is dominated by big Pokemon. I seek the change that. It is time that the other 99% start getting fair treatment.");
        eventArray[51][36] = new DumbNPC(25, 0);
        eventArray[51][36].addText("I'M KAYLA PANDA AND I AM GR8 COUNCIL PRESIDENT!");
        eventArray[41][28] = new DumbNPC(4, 0);
        eventArray[41][28].addText("Hi, I'm Lincoln Chafee and I was wondering: Do you feel the chafe?");
        eventArray[55][26] = new DumbNPC(27, 1);
        eventArray[55][26].addText("I wonder if Mr. G has marked Wall-E yet?");
        eventArray[62][31] = new DumbNPC(11, 0);
        eventArray[62][31].addText("...");
        
        
        bLevel = 22;
        hLevel = 30;
        waterPokemon = Arrays.asList("Magikarp", "Tentacool", "Carvanha", "Carvanha", "Carvanha", "Carvanha", "Carvanha");
        
    }
}
