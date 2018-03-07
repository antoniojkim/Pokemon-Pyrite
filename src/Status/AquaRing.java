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
public class AquaRing extends Status{
    
    public AquaRing(){
        counter=5;
        setText = " surrounded itself with a veil of water.";
        effectText = " healed its hp with aqua ring!";
        
    }
    
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate (pokemon, animations);
        BattleMoves.healer(pokemon, pokemon.getStats()[0][1]/8, animations);
        counter--;
    }
    
}
