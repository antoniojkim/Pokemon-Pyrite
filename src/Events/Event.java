/*
Event Class. All methods are overriden and used by the subclasses
 */
package Events;

import Pokemon.Pokemon;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

public class Event {

    /*
    This is the constructor method. It is never called, but inherited.
    pre: none
    post: Event is initialized
    */
    public Event()
    {

    }

        /*
    This is the constructor method. It is never called, but inherited.
    pre: none
    post: Event is initialized
    */
    public Event(int a, int b)
    {

    }

    /*
    This is overridden for other events (NPCs, Sign, TPs). It is called when 
    the player trys to walk onto the tile with the event.
    pre: none
    post: none
    */
    public void activate()
    {

    }

    /*
    A method which is overriden and used to add text to NPCs and signs
    pre: none
    post: none
    */
    public void addText(String str)
    {

    }

    /*
    A method overriden and used for deleport events to set the destination
    */
    public void setCoord(int a, int b, int c, int d)
    {

    }

    /*
    This is overrriden and used by NPCs to render them at a set location.
    pre: none
    post: none
    */
    public void render(Graphics g, int x, int y)
    {

    }
    
    /*
    This is overriden by NPCs and called to get their sprite.
    pre: none
    post: returns null
    */
    public BufferedImage getSprite()
    {
        return null;
    }

    /*
    Used by Trainer NPC to set pokemon, overriden in TrainerNPC class
    pre: none
    post:none
    */
    public void setTrainerInfo(List<Pokemon> pokemon)
    {
        
    }
    
    

}
