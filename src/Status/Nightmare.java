/*p
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
public class Nightmare extends Status{
    public Nightmare(){
        setText =  " was trapped in a nightmare!";
        effectText = " was hurt by its nightmare.";
    }
    
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate (pokemon, animations);
        BattleMoves.damager(pokemon, pokemon.getStats()[0][1]/4, animations);
    }
    
}
