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
public class PerishSong extends Status{
    
    public PerishSong(){
        counter=3;
        setText = " will faint in 3 turns.";
        effectText = "' perish song count fell to " + counter;
    }
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        counter--;
        effectText = "'s perish song count fell to " + counter + ".";
        animate (pokemon, animations);
        if (counter==0){
            BattleMoves.damager (pokemon, pokemon.getStats()[0][0], animations);
        }
    }
}
