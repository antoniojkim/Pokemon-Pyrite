package Battle;


import Main.p;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author Antonio's Laptop
 */
public class Types {
    
    
    public static double getEffectiveness (String type, List <String> opponent){
        int index = move.indexOf(type.toLowerCase());
        double[] returnvalues = {2, 0.5, 0};
        double effectiveness=1;
        for (int a = 0; a<3; a++){
            for (int b = 0; b<receive[index][a].length; b++){
                for (int c = 0; c<opponent.size(); c++){
                    if (receive[index][a][b].equalsIgnoreCase(opponent.get(c))){
                        effectiveness*=returnvalues[a];
                    }
                }
            }
        }
        return effectiveness;
    }
    
    private static List<String>move = Arrays.asList("normal", "fire", "water", "grass", "electric", "ice", "fighting", "poison", "ground", "flying", "psychic", "bug", "rock", "ghost", "dragon", "dark", "steel");

    public static List<String> getMove() {
        return move;
    }
    
    public static int getIndex(String type){
        return move.indexOf(type.toLowerCase());
    }
    
    public static BufferedImage getTypeIcon (String type){
        return p.loadImage("./Graphics/Type Icons/"+type+" Type.png");
    }
    
    private static String[][][] receive = {
        /*Normal Attack*/
        {
            /*Super Effective Against*/      {},
            /*Not Very Effective Against*/ {"rock", "steel"},
            /*No Effect Against*/               {"ghost"},
        },
        /*Fire Attack*/
        {
            /*Super Effective Against*/      {"grass", "ice", "bug", "steel"},
            /*Not Very Effective Against*/ {"fire", "water", "rock", "dragon"},
            /*No Effect Against*/               {},
        },
        /*Water Attack*/
        {
            /*Super Effective Against*/      {"fire", "ground", "rock"},
            /*Not Very Effective Against*/ {"water", "grass", "dragon"},
            /*No Effect Against*/               {},
        },
        /*Grass Attack*/
        {
            /*Super Effective Against*/      {"water", "ground", "rock"},
            /*Not Very Effective Against*/ {"fire", "grass", "poison", "flying", "bug", "dragon", "steel"},
            /*No Effect Against*/               {},
        },
        /*Electric Attack*/
        {
            /*Super Effective Against*/      {"water", "flying"},
            /*Not Very Effective Against*/ {"grass", "electric", "dragon"},
            /*No Effect Against*/               {"ground"},
        },
        /*Ice Attack*/
        {
            /*Super Effective Against*/      {"grass", "flying", "dragon"},
            /*Not Very Effective Against*/ {"fire", "water", "ice", "steel"},
            /*No Effect Against*/               {},
        },
        /*Fighting Attack*/
        {
            /*Super Effective Against*/      {"normal", "ice", "rock", "dark", "steel"},
            /*Not Very Effective Against*/ {"poison", "flying", "psychic", "bug"},
            /*No Effect Against*/               {"ghost"},
        },
        /*Poison Attack*/
        {
            /*Super Effective Against*/      {"grass"},
            /*Not Very Effective Against*/ {"poison", "ground", "rock", "ghost"},
            /*No Effect Against*/               {"steel"},
        },
        /*Ground Attack*/
        {
            /*Super Effective Against*/      {"fire", "electric", "poison", "rock", "steel"},
            /*Not Very Effective Against*/ {"grass", "bug"},
            /*No Effect Against*/               {"flying"},
        },
        /*Flying Attack*/
        {
            /*Super Effective Against*/      {"grass", "fighting", "bug"},
            /*Not Very Effective Against*/ {"electric", "rock", "steel"},
            /*No Effect Against*/               {},
        },
        /*Psychic Attack*/
        {
            /*Super Effective Against*/      {"fighting", "poison"},
            /*Not Very Effective Against*/ {"psychic", "steel"},
            /*No Effect Against*/               {"dark"},
        },
        /*Bug Attack*/
        {
            /*Super Effective Against*/      {"grass", "psychic", "dark"},
            /*Not Very Effective Against*/ {"fire", "fighting", "poison", "flying", "ghost", "steel"},
            /*No Effect Against*/               {},
        },
        /*Rock Attack*/
        {
            /*Super Effective Against*/      {"fire", "ice", "flying", "bug"},
            /*Not Very Effective Against*/ {"fighting", "ground", "steel"},
            /*No Effect Against*/               {},
        },
        /*Ghost Attack*/
        {
            /*Super Effective Against*/      {"psychic", "ghost"},
            /*Not Very Effective Against*/ {"dark", "steel"},
            /*No Effect Against*/               {"normal"},
        },
        /*Dragon Attack*/
        {
            /*Super Effective Against*/      {"dragon"},
            /*Not Very Effective Against*/ {"steel"},
            /*No Effect Against*/               {},
        },
        /*Dark Attack*/
        {
            /*Super Effective Against*/      {"psychic", "ghost"},
            /*Not Very Effective Against*/ {"fighting", "dark", "steel"},
            /*No Effect Against*/               {},
        },
        /*Steel Attack*/
        {
            /*Super Effective Against*/      {"ice", "rock"},
            /*Not Very Effective Against*/ {"fire", "water", "electric", "steel"},
            /*No Effect Against*/               {},
        }
    };
    
}
