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
public class Infatuation extends Status{
    BattlePokemon inLove;
    String text3;
    
    public Infatuation(BattlePokemon inLove){
        this.inLove = inLove;
        setText = " fell in love.";
        effectText = " is in love with the opponent's " + inLove.getName() + ".";
        
        
    }
    
    public void duringTurn(BattlePokemon pokemon, BattlePokemon opponent, List<List<String>> animations){
        animate(pokemon, animations);
        if (inLove.equals(opponent)&&Math.random()<0.5){
            animations.add(new ArrayList(Arrays.asList("0", pokemon.getName() +" is immoblized by love." )));
            pokemon.setAbleToMove (false);
        }
    }
}
