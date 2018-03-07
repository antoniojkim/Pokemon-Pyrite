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
public class Ingrain extends Status{
    
    public Ingrain(BattlePokemon pokemon){
        pokemon.setTrapped(true);
        setText = " planted its roots!";
        effectText = " restored its hp with ingrain.";
    }
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate(pokemon, animations);
        BattleMoves.healer(pokemon, pokemon.getStats()[0][1]/8, animations);
    }
}
