/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Status;

import Battle.BattleMoves;
import Pokemon.BattlePokemon;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Burn extends Status{
    public Burn(BattlePokemon pokemon){
        setText = " was burned!";
        effectText = " was hurt by its burn.";
        healText = " was healed of its burn.";
        nonVolatile = true;
        pokemon.setNonVolatileStatus(0);
    }
    
    
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        //animate burn
        animate (pokemon, animations);
        BattleMoves.damager(pokemon, pokemon.getStats()[0][1]/8, animations);
    
    }
} 