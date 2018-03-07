/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Status;

import  Battle.BattleMoves;
import Pokemon.BattlePokemon;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class LeechSeed extends Status{
    BattlePokemon leechSeeder;
    public LeechSeed(BattlePokemon leechSeeder){
        this.leechSeeder = leechSeeder;
        setText = " was seeded!";
        effectText = "'s health was sapped by leech seed.";
    }
    
    
    @Override
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
        animate (pokemon, animations);
        int damage = pokemon.getStats()[0][1]/8;
        BattleMoves.damager (pokemon, damage, animations);
        BattleMoves.healer (leechSeeder, damage, animations);
    }
    
    
    
}
