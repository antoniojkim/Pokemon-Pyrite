/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Status;

import Main.p;
import Pokemon.BattlePokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Sleep extends Status{
    
    
    public Sleep(BattlePokemon pokemon){
        setText = " fell asleep.";
        effectText = " is asleep.";
        healText = " woke up.";
        nonVolatile = true;
        counter = p.randomint(1, 3);
        pokemon.setNonVolatileStatus(4);
    }
    
    @Override
    public void duringEffect(BattlePokemon pokemon,  List<List<String>> animations){
        counter--;
        if (counter>0){
            pokemon.setAbleToMove (false);
            animations.add(new ArrayList<>(Arrays.asList("0", pokemon.getName() + " is fast asleep.")));
        }
    }
    
    @Override
    public void endEffect (BattlePokemon pokemon,  List<List<String>> animations){
        
    }
    
}
