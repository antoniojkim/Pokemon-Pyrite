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
public class Toxic extends Poison{
    double toxic = 1.0/16.0;
    public Toxic(BattlePokemon pokemon){
        super(pokemon);
        setText = "was badly poisoned!";
        effectText = super.effectText;
        healText = super.healText;
        pokemon.setNonVolatileStatus(3);
    }
    
    
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate(pokemon, animations);
        Battle.BattleMoves.damager(pokemon, (int) Math.round(pokemon.getStats()[0][1]*toxic), animations);
        //each turn, toxic gets stronger
        toxic +=1.0/16.0;
    }
    
}
