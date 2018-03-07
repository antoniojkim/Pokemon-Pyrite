/*
The Nurse is in the Pokemon Center and heal's the player's Pokemon
 */
package Events;

/**
 *
 * @author Julian
 */
public class Nurse extends DumbNPC {

    /*
    Instantiates the Nurse. This is the nurse in the Pokemon Center. She has a
    preset text for all Nurses, and heals the user's Pokemon
    pre: none
    post: Nurse instantiated
    */
    public Nurse()
    {
        super(15, 0);
        addText("Hi. We can make your pokemon undead. There! All healed!");
    }

    /*
    Overrides the activate method so that it outputs dialogue and heals the user's
    Pokemon
    pre: none
    post: party is healed
    */
    @Override
    public void activate()
    {
        super.activate();
        for (int i = 0; i < Main.Main.it.getParty().size(); i++) {
            Main.Main.it.getParty().get(i).pokeCentreHeal();
        }
    }

}
