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
public class Curse extends Status{
    public Curse(){
        setText = " was cursed!";
        effectText = " was damaged by its curse!";
    }
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate (pokemon, animations);
        BattleMoves.damager (pokemon, pokemon.getStats()[0][1]/4, animations);
        counter--;
    }
    
    
}
