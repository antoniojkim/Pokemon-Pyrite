/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Battle.BattleMoves;
import Pokemon.BattlePokemon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Wish extends Status{
    
    public Wish(){
        counter = 2;
    }
    @Override
    public void endEffect(BattlePokemon pokemon,  List<List<String>> animations){
        counter--;
        if (counter==0){
            animations.add(new ArrayList(Arrays.asList("0", pokemon.getName() + " was healed by its wish!")));
            BattleMoves.healer(pokemon, pokemon.getStats()[0][1]/2, animations);
        }
    }
}
