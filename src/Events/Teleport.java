/*
The Teleport event changes the player's location
*/
package Events;

import Main.Main;

public class Teleport extends Event
{
    private int wX, wY, dX, dY;
    
    /*
    Instantiates the Telport class with default location (not an actual map)
    pre: none
    post: none
    */
    public Teleport ()
    {
        wX = 0;
        wY = 0;
        dX = 0;
        dY = 0;
    }
    
    /*
    Overrides the method to allow coordinates to be set for the Teleport. wX and wY
    determine the map, and dX and dY determine where on that map the player will go.
    */
    @Override
    public void setCoord (int wX, int wY, int dX, int dY)
    {
        this.wX = wX;
        this.wY = wY;
        this.dX = dX;
        this.dY = dY;
    }
    
    /*
    This overrides the activate which tiggers when trying to walk onto the location
    where the Teleport event sits. This triggers animations and sets the players 
    location to its destination.
    */
    @Override
    public void activate ()
    {
        Main.getMap().fadeOut(0.1, true);
        Main.getMap().setMap(wX, wY, dX, dY);
        Main.getMap().clear();
        Main.getMap().fadeIn(0.1);
        Main.getMap().movement(Main.getMap().getOrientation());
    }
    
    
}
