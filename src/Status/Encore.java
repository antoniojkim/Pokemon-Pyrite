/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Pokemon.BattlePokemon;

/**
 *
 * @author Katrina
 */
public class Encore extends Status{
    
    String lastmove;
    public Encore(String lastmove){
        counter=3;
        this.lastmove = lastmove;
    }
    
    public void startEffect(BattlePokemon pokemon){
        //prevents from choosing your move
        pokemon.setMove(pokemon.getLastMove());
    }
}
