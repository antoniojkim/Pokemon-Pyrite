/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Pokemon.BattlePokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Paralysis extends Status{
    String text3;
    public Paralysis(BattlePokemon pokemon){
        setText = " was paralyzed!";
        effectText = " is paralyzed.";
        healText = " was healed of its paralysis.";
        nonVolatile = true;
        pokemon.getStats()[5][0]/=4;
        pokemon.setNonVolatileStatus(2);
    }
    
    @Override
    public void duringEffect(BattlePokemon pokemon, List<List<String>> animations){
        if (Math.random()<0.5){
            pokemon.setAbleToMove(false);
            animations.add(new ArrayList(Arrays.asList("0", pokemon.getName() + " is unable to move.")));
        }
    }
    
}
