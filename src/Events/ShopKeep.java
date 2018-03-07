/*
This the shopkeeper in the PokeMarts.
 */
package Events;

import Main.Main;


public class ShopKeep extends DumbNPC{
    
    /*
    The Shopkeeper is instantiated with a preset sprite and line.
    pre: sprite exists and the loader has been run.
    post: none
    */
    public ShopKeep()
    {
        super(18, 2);
        addText("Hi. We sell stuff. Please buy our stuff...Nobody else will...");
    }

    /*
    This outputs the line of the ShopKeeper and then opens the store window.
    pre: none
    post: Store window is open for player to interact with.
    */
    @Override
    public void activate()
    {
        super.activate();
        Main.getMap().setStore(true);
    }


    
}
