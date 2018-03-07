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
public class Poison extends Status{
   public Poison(BattlePokemon pokemon){
       setText = " was poisoned!";
       effectText = " is hurt by poison.";
       healText = " was cured of its poisoning.";
       nonVolatile = true;
       pokemon.setNonVolatileStatus(3);
   }
    
    
   @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate (pokemon, animations);
        BattleMoves.damager(pokemon, pokemon.getStats()[0][1]/8, animations);
    
    }
}
