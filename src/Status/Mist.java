/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Main.p;
import Pokemon.BattlePokemon;

/**
 *
 * @author Katrina
 */
public class Mist extends Status{
    
    public Mist(){
    counter = p.randomint(3,5);    
    }
    
    
    public void duringTurn (BattlePokemon pokemon){
        //prevents stats from being changed
        pokemon.setStatFix (true);
    }
    
}
