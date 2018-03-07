/*
A sign, the simplest Event which only outputs text.
 */
package Events;

import java.util.List;
import Main.Main;
import java.util.ArrayList;

public class Sign extends Event
{

    protected List<String> text;

    /*
    Constructor which instantiates the Sign object. This is always stored into an array
    of events in the maps.
    pre: none
    post: Sign is instatiated
    */
    public Sign()
    {
        text = new ArrayList<>();
    }

    /*
    This overrides the activate method so that interacting with the sign produces
    dialogue which the player sees
    pre: none
    post: Text is displayed on screen
    */
    @Override
    public void activate()
    {
        for (int i = 0; i < text.size();i++)
        {
            Main.map.addDialoguetext(text.get(i));
        }
    }

    /*
    This allows text to be added to the sign.
    pre: none
    post: String added to ArrayList
    */
    @Override
    public void addText(String str)
    {
        text.add(str);
    }
    
    /*
    A method used for easier instantiating of Signs.
    pre: x and y be within bounds of Event array
    post: Sign is initialized and stored in passed coordinates
    */
    public static void addSign (int x, int y, String str, Event [] [] e)
    {
        e [y] [x] = new Sign ();
        e [y] [x].addText(str);
    }
}
