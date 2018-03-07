/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Battle;

import Bag.Pokeballs;
import Main.Main;
import Main.p;
import Pokemon.BattlePokemon;
import Status.Status;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Katrina
 */
public class Turn{
    
    public static void takeTurn (BattlePokemon pokemon, BattlePokemon opponent, List<List<String>> animations){
        
        //Check for status conditions that would affect move and/or action selection
        startTurn (pokemon, animations);
        startTurn (opponent, animations);
        
        //Randomizes the opponent's move
        if (opponent.isAbleToMove()){
            
            if (opponent.getMultiTurnCount()<1){
                opponent.setMove(opponent.getMoveset().get(p.randomint(0, opponent.getMoveset().size()-1)));
            }
            else{
                opponent.setMove(opponent.getLastMove());
            }
        }
        //Turn progression: for each pokemon
        BattlePokemon faster, slower;
        if (Main.items.allpokeballs.contains(pokemon.getMove())||BattleMoves.getPriority(pokemon.getMove())>BattleMoves.getPriority(opponent.getMove())){
            faster=pokemon;
            slower=opponent;
        }
        else if (BattleMoves.getPriority(opponent.getMove())>BattleMoves.getPriority(pokemon.getMove())){
            faster=opponent;
            slower=pokemon;
        }
        //if move priorities are the same, compares speeds
        else if (pokemon.getStats()[5][0]>opponent.getStats()[5][0]){
            faster=pokemon;
            slower=opponent;
        }
        else{
            faster=opponent;
            slower=pokemon;
        }
        duringTurn(faster, slower, animations);
        duringTurn (slower, faster, animations);
        
        
        endTurn(pokemon, animations);
        endTurn(opponent, animations);
        
        
        Main.map.getBP().adjustHealth();
        
    }
    
    
    public static void startTurn(BattlePokemon pokemon,  List<List<String>> animations){
        //checks the pokemon for status conditions that impede abiltiy to move, such as confusion/infatuation
        // appropriate animate and text
        if (!pokemon.isFainted()){
            for (Status element: pokemon.getStatuses()){
                element.startEffect(pokemon, animations);
            }
        }
    }
    public static void duringTurn(BattlePokemon pokemon, BattlePokemon opponent, List<List<String>> animations){
        //------------------------------------------------
        //Move Sequence (for each Pokemon):
        if (!pokemon.isFainted()){
        
        ArrayList toRemove = new ArrayList<Status>();
        for (Status element: pokemon.getStatuses()){
            element.duringEffect(pokemon, animations);
            if (element.getCounter()==0){
                toRemove.add(element);
                pokemon.resetStatus(element);
            }
        }
        pokemon.getStatuses().removeAll(toRemove);
        
        if (Main.items.allpokeballs.contains(pokemon.getMove())){
            animations.add(new ArrayList<>(Arrays.asList("0", "")));
            animations.add(new ArrayList<>(Arrays.asList("0", Main.it.getName() + " threw a " + pokemon.getMove() + "!")));
            if (Main.getMap().getBP().isTrainerBattle()){
                animations.add(new ArrayList<>(Arrays.asList("0", "You can't catch someone else's pokémon!")));
            }
            else{
                if (Main.it.getParty().size()==6){
                    animations.add(new ArrayList<>(Arrays.asList("0", "You have too many pokémon already!")));
                }
                else{
                    
                    animations.add(new ArrayList<>(Arrays.asList("8", "./Audio/SE/throw.wav"))); 
                    int shakes = Pokeballs.isCaught(opponent, pokemon.getMove());
                    animations.add(new ArrayList<>(Arrays.asList("7", pokemon.getMove(), Integer.toString(shakes))));
                    for (int x=0; x<shakes; x++){
                        if (x<3){
                           animations.add(new ArrayList<>(Arrays.asList("8", "./Audio/SE/ballshake.wav")));  
                        }
                    }
                    if (shakes==4){
                        animations.add(new ArrayList<>(Arrays.asList("8", "./Audio/SE/balldrop.wav"))); 
                        animations.add(new ArrayList<>(Arrays.asList("0", opponent.getName() + " was caught!")));
                        Main.it.addToParty(opponent.getPokemon());
                        Main.getMap().getBP().getOpponentParty().remove(opponent);
                        opponent.setAbleToMove(false);
                        opponent.setDrawable(false);
                        animations.add(new ArrayList<>(Arrays.asList("6")));
                    }
                    else{
                        animations.add(new ArrayList<>(Arrays.asList("0", "The wild "+ opponent.getName()+  " broke free!")));
                    }
                }
            }
        }
        else if (pokemon.isAbleToMove()){
            //checks and displays the effect of the move
            MoveEffect.getEffect(pokemon, opponent, animations);
        }
        pokemon.setLastMove(pokemon.getMove());
        pokemon.setMove("");
    }
    }
//check for damage-inflicting status conditions such as burn, poison, toxic, curse,
    public static void endTurn(BattlePokemon pokemon,  List<List<String>> animations){
        //resets move priority for the next turn
        if (!pokemon.isFainted()){
            pokemon.setAbleToMove(true);
        
        //lowers the counter for a multi-turn move like rollout, and resets it if the move is done
        pokemon.lowerMultiTurnCount();
        
        ArrayList toRemove = new ArrayList<Status>();
        for (Status element: pokemon.getStatuses()){
            element.endEffect(pokemon, animations);
            if (element.getCounter()==0){
                toRemove.add(element);
                pokemon.resetStatus(element);
            }
        }
        pokemon.getStatuses().removeAll(toRemove);
        }
    }
}



