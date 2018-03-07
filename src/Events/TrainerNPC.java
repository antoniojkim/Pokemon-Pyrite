/*
A Trainer is an NPC which battles the player
 */
package Events;

import Battle.BattlePanel;
import Main.Main;
import Main.p;
import Pokemon.Pokemon;
import java.util.ArrayList;
import java.util.List;


public class TrainerNPC extends DumbNPC {

    private List<Pokemon> pokemon;
/*
    This instantiates the Trainer with the sprite, level range, and the pokemon it will have
    pre: type is between 0 and 28, orientation between 0 and 3, and the levels between
    1 and 100, with bLevel <= hLevel
    post: The trainer is instantiated
    */
    public TrainerNPC(int type, int orientation, int bLevel, int hLevel, List<String> pokeNames)
    {
        super(type, orientation);
        pokemon = new ArrayList<Pokemon>();
        for (int x = 0; x < pokeNames.size(); x++) {
            pokemon.add(new Pokemon(pokeNames.get(x), p.randomint(bLevel, hLevel)));
        }

    }

    /*
    This overrides the activate method so that it displays text and then starts
    battle
    pre: none
    post: Battle starts
    */
    @Override
    public void activate()
    {
        super.activate();
        p.delay(1000);
        for (Pokemon n: pokemon){
            n.pokeCentreHeal();
        }
        Main.getMap().trainerBattle(pokemon);

    }

    /*
    This allows the trainer's pokemon list to be set
    pre: none
    post: Pokemon list changed.
    */
    @Override
    public void setTrainerInfo(List<Pokemon> pokemon)
    {
        this.pokemon = pokemon;
    }

}
