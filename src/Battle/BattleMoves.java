package Battle;

import Main.Main;
import Main.Music;
import Main.p;
import Pokemon.Pokemon;
import Pokemon.BattlePokemon;
import Pokemon.PokemonMenu;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author Katrina
 */


public class BattleMoves {
    
    /*
    stats[0][0] = current health
    stats[0][1] = total/max health
    stats[0][2] = health stage
    stats[1][0] = current attack
    stats[1][1] = total/max attack
    stats[1][2] = attack stage
    stats[2][0] = current defense
    stats[2][1] = total/max defense
    stats[2][2] = defense stage
    stats[3][0] = current special attack
    stats[3][1] = total/max special attack
    stats[3][2] = special attack stage
    stats[4][0] = current special defense
    stats[4][1] = total/max special defense
    stats[4][2] = special defense stage
    stats[5][0] = current speed
    stats[5][1] = total/max speed
    stats[5][2] = speed stageu
    stats[6][0] = current accuracy
    stats[6][1] = total/max accuracy (always 100)
    stats[6][2] = accuracy stage
    stats[7][0] = current evasion
    stats[7][1] = total/max evasion (always 100)
    stats[7][2] = evasion stage
    */
    
    // Movelist: type, physical/special,
    
    public static List<String> moveindex = new ArrayList<>();
    public static List<String> movedescriptions = new ArrayList<>();
    public static List<String[]> movelist = new ArrayList<>();
    public static List<String> multimovelist = new ArrayList<>();
    public static List<Integer> criticalmoves = new ArrayList<>();
    public static List<String[]> prioritylist = new ArrayList<>();
    public static List<String> tms = new ArrayList<>();
    public static List<String> hms = new ArrayList<>(Arrays.asList("Cut", "Fly", "Surf", "Strength", "Dive","Waterfall"));
    
    public BattleMoves(){
        try {
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Move List.txt")));
            String line = "";
            while(true){
                line = br.readLine();
                if (line==null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                moveindex.add(line);
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Move Details.txt")));
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                line = line.replaceAll("%", "");
                String[] details = line.split("	");
                if (moveindex.contains(details[0])){
                    String[] array = {details[1], details[2], details[3], details[4], details[5]};
                    movelist.add(array);
                }
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Move Descriptions.txt")));
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                line = line.replaceAll("%", "");
                String[] details = line.split("	");
                list.add(details[0]);
                if (details.length>1){
                    list2.add(details[1]);
                }
                else{
                    list2.add("");
                }
            }
            for (int a = 0; a<moveindex.size(); a++){
                int index = list.indexOf(moveindex.get(a));
                if (index != -1){
                    movedescriptions.add(list2.get(index));
                }
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Multiturn Moves.txt")));
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                multimovelist.add(line);
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Priority List.txt")));
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("\t");
                if (moveindex.contains(array[0])){
                    prioritylist.add(array);
                }
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Move Critical.txt")));
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                if (movelist.contains(array[0])){
                    criticalmoves.add(Integer.parseInt(array[1]));
                }
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("List of TMs.txt")));
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                tms.add(line);
            }
            tms.addAll(hms);
//            p.print(tms.toArray());
        } catch (FileNotFoundException ex) {}   catch(IOException e){}
    }
    
    public static int getPriority (String move){
        for (int x=0; x<prioritylist.size(); x++){
            if (prioritylist.get(x)[0].equals(move)){
                return Integer.parseInt(prioritylist.get(x)[1]);
            }
        }
        return 0;
    }
    
    
    
    //parameters: move (find base accuracy), accuracy of attacker, evasion of defender
    //this needs to be true for the attack to land
    public static boolean hit (String move, BattlePokemon attacker, BattlePokemon defender){
        double probability;
        if (move.equals("Sheer Cold")||move.equals("Guillotine")||move.equals("Horn Drill")||move.equals("Fissure")){
            probability = attacker.getPokemon().getLevel() - defender.getPokemon().getLevel() + 30;
        }
        else if (BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[4].equals("?")||move.equals("Aerial Ace") || move.equals("Shadow Punch")|| move.equals("Magical Leaf")||move.equals("Shock Wave")||move.equals("Aura Sphere")){
            return true;
        }
        else{
            double num = Double.parseDouble(BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[4]);
            probability = num*((double)attacker.getPokemon().getStats()[6][0]/(double)defender.getPokemon().getStats()[7][0]);
        }
        if (Math.random()*100<=probability){
            return true;
        }
        return false;
    }
    
    
    //parameters: which move is used, the attacking and defending pokemon
//    public int calcDamage(int level, int atk, int def, int base, String type, String [] attacker, String []defender){
    public static int calcDamage(String movename, Pokemon attacker, Pokemon defender, List<List<String>> animations){
        double types = Types.getEffectiveness(movelist.get(moveindex.indexOf(movename))[0], defender.getTypes());
        if (types>1){
            animations.add(new ArrayList<>(Arrays.asList("0", "It was super effective!")));
        }
        else if (types==0){
            animations.add(new ArrayList<>(Arrays.asList("0", "It had no effect.")));
        }
        else if (types<1){
            animations.add(new ArrayList<>(Arrays.asList("0", "It was not very effective.")));
        }
        
        double stab = 1;
        //if the type of the attack matches the type of the attacker, damage is multiplied by 1.5 x
        if (attacker.getTypes().contains(movelist.get(moveindex.indexOf(movename))[0])){
            stab = 1.5;
        }
        
        //critical hit chance is about 6.25% under normal circumstances, multiplies damage by 1.5x
        double critical = 1;
        if (Math.random()< 0.0625){//*criticalmoves.get(moveindex.indexOf(movename))){
            critical = 1.5;
            animations.add(new ArrayList<>(Arrays.asList("0", "It was a critical hit!")));
        }
        
        //modifier needs to be multiplied by "other" : ablities, items, double battle, weather/ battlefield, other effects which need to be hardcoded
        double modifier = types*stab*critical*(p.random(0.85, 1));
        if (movelist.get(moveindex.indexOf(movename))[2].equalsIgnoreCase("physical")){
            return (int)((((2.0*attacker.getLevel() + 10.0)/250.0)* ((double)attacker.getStats()[1][0]/(double)defender.getStats()[2][0]) * Integer.parseInt(movelist.get(moveindex.indexOf(movename))[3]) + 2)* modifier);
        }
        else {
            return (int)((((2.0*attacker.getLevel() + 10.0)/250.0)* ((double)attacker.getStats()[3][0]/(double)defender.getStats()[4][0]) * Integer.parseInt(movelist.get(moveindex.indexOf(movename))[3]) + 2)* modifier);
        }
    }
    
    
    public static void damager (String move, BattlePokemon attacker, BattlePokemon defender, List<List<String>> animations){
        int placeholder = animations.size();
        animations.add(new ArrayList(Arrays.asList("placeholder")));
        int damage = calcDamage(move, attacker.getPokemon(), defender.getPokemon(), animations);
        //make sprite flash
        if (damage!=0){
            if (defender.equals(BattlePanel.getInbattle())){
                animations.set(placeholder, new ArrayList(Arrays.asList("1", "inbattle")));
            }
            else if (defender.equals(BattlePanel.getOpponent())){
                animations.set(placeholder, new ArrayList(Arrays.asList("1", "opponent")));
            }
        }
        else{
           animations.remove(placeholder);
        }
        if (defender.getEndHealth()-damage <= 0){
            defender.setEndHealth(0);
        }
        else{
            defender.adjustEndHealth(-damage);
        }
        if (defender.equals(BattlePanel.getOpponent())){
           animations.add(new ArrayList(Arrays.asList("3", "opponent"))); 
        }
        else{
           animations.add(new ArrayList(Arrays.asList("3", "inbattle"))); 
        }
                
        defender.faint(animations); //checks for faint within this method and not before
    }
    
    public static void damager (BattlePokemon target, int damage, List<List<String>> animations){
        //make sprite flash
        if (target.equals(BattlePanel.getInbattle())){
            animations.add(new ArrayList(Arrays.asList("1", "inbattle")));
        }
        else if (target.equals(BattlePanel.getOpponent())){
            animations.add(new ArrayList(Arrays.asList("1", "opponent")));
        }
        if (target.getEndHealth()- damage <= 0){
            target.setEndHealth(0);
        }
        else{
            target.adjustEndHealth(-damage);
        }
        //animate health loss
        if (target.equals(BattlePanel.getOpponent())){
           animations.add(new ArrayList(Arrays.asList("3", "opponent"))); 
        }
        else{
           animations.add(new ArrayList(Arrays.asList("3", "inbattle"))); 
        }
        
        target.faint (animations);
    }
    
    public static void healer (BattlePokemon target, int heal, List<List<String>> animations){
        //stars animation
        if (target.getEndHealth()+heal >= target.getStats()[0][1]){
            target.setEndHealth(target.getStats()[0][1]);
        }
        else{
            target.adjustEndHealth(heal);
        }
        //animate health increase
        if (target.equals(BattlePanel.getOpponent())){
           animations.add(new ArrayList(Arrays.asList("3", "opponent"))); 
        }
        else{
           animations.add(new ArrayList(Arrays.asList("3", "inbattle"))); 
        }
    }
    
    
    
    
}
