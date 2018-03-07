/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Battle;

import Pokemon.BattlePokemon;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 */
public class MoveEffect {
    public static void getEffect(BattlePokemon attacker, BattlePokemon defender, List<List<String>> animations){
        String move = attacker.getMove();
        
        if (BattleMoves.moveindex.contains(move)){
            String display = attacker.getName() + " used " + move + "!";
            if (!attacker.isMetronome()){
                attacker.getPp().get(attacker.getMoveset().indexOf(move))[0]--;
            }
            attacker.setMetronome(false);
            animations.add(new ArrayList<>(Arrays.asList("0", display)));
            
            int index = BattleMoves.moveindex.indexOf(move);
            
            if (BattleMoves.hit(move, attacker, defender)){
                attacker.setLastMove(move);
                if (index!=-1 && index<BattleMoves.moveindex.indexOf("Aromatherapy")){
                    Direct1.getEffect(move, attacker, defender, animations);
                }
                else if (index!=-1 && index>=BattleMoves.moveindex.indexOf("Aromatherapy")){
                    Direct2.getEffect(move, attacker, defender, animations);
                }
            }
            else {
                animations.add(new ArrayList<>(Arrays.asList("0", "The attack missed!")));
            }
        }
        
    }
    
}
