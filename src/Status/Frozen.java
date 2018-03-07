/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Status;

import Pokemon.BattlePokemon;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Frozen extends Status{
    
    public Frozen(BattlePokemon pokemon){
        nonVolatile = true;
        setText = " was frozen!";
        effectText = " is frozen.";
        healText = " was defrosted.";
        pokemon.setNonVolatileStatus(1);
    }
    
    @Override
    public void duringEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate(pokemon, animations);
        pokemon.setAbleToMove(false);
    }
    
}
