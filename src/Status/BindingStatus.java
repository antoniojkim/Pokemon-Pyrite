/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Battle.BattleMoves;
import Main.p;
import Pokemon.BattlePokemon;
import java.util.List;

/**
 *
 * @author Katrina
 */
public abstract class BindingStatus extends Status{
    
    public BindingStatus(){
        counter = p.randomint(3, 5);
        setText = " was trapped by ";
        effectText = " was hurt by ";
        healText = " is no longer trapped by ";
    }
    
    
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
       animate (pokemon, animations);
       BattleMoves.damager (pokemon, pokemon.getStats()[0][1]/8, animations);
       counter--;
        
    }
}
