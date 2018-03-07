/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Status;

import Pokemon.BattlePokemon;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Protect extends Status{
    
    public Protect(){
        setText = " protected itself!";
    }
    @Override
    public void duringEffect(BattlePokemon pokemon, List<List<String>> animations){
        
    }
    
    @Override
    public void endEffect(BattlePokemon pokemon,  List<List<String>> animations){
        counter=0;
    }
}
