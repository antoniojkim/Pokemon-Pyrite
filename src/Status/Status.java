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
public abstract class Status {
    protected String setText = "", effectText = "", healText = "";
    public boolean nonVolatile;
    //of which each pokemon can only have 1
    
    protected int counter = -1;
    //if the status does not require a counter, this will never be +, having no effect

    
    public boolean equals(Status status) {
        return (this.setText.equals(status.setText));
    }
    
    public void startEffect(BattlePokemon pokemon, List<List<String>> animations){
        
    }
    public void duringEffect(BattlePokemon pokemon, List<List<String>> animations){
        
    }
    public void endEffect(BattlePokemon pokemon, List<List<String>> animations){
       counter--;
    }
    public void animate(BattlePokemon pokemon, List<List<String>> animations){
        animations.add(new ArrayList<>(Arrays.asList("0", pokemon.getName() + getEffectText())));
    }

    public int getCounter() {
        return counter;
    }

    public String getEffectText() {
        return effectText;
    }

    public String getHealText() {
        return healText;
    }

    public String getSetText() {
        return setText;
    }
    
    
    
}
