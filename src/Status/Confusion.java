/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Battle.BattleMoves;
import Main.p;
import Pokemon.BattlePokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Confusion extends Status{
    String text3;
    
    public Confusion(){
        counter = p.randomint(1, 3);
        setText = " became confused!";
        effectText = " is confused.";
        healText = " was snapped out of its confusion!";
    }
    
    @Override
    public void duringEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate (pokemon, animations);
        if (Math.random()<0.5){
            animations.add(new ArrayList(Arrays.asList("0", pokemon.getName() + " hurt itself in its confusion.")));
            BattleMoves.damager(pokemon, pokemon.getStats()[0][1]/6, animations);
            pokemon.setAbleToMove (false);
        }
    }
    
}
