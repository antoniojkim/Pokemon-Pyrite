package Bag;


import Main.Main;
import Pokemon.PokemonMenu;
import java.io.BufferedReader;
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
 * @author Antonio's Laptop
 */
public class Items {
    
    public Items(){
        BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Item Details.txt")));
        String line = "";
        try {
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                String[] array = line.split("	");
                if (array.length == 3){
                    if (array[1].equals("Pokeballs")){
                        allpokeballs.add(array[0].trim());
                        pokeballdescriptions.add(array[2]);
                    }
                    else{
                        allitems.add(array[0].trim());
                        itemdescriptions.add(array[2]);
                        itemtype.add(array[1]);
                    }
                }
                else{
                    System.out.println(line);
                    break;
                }
            }
            allitems.add("Ultimate Repel");
            itemdescriptions.add("An item that prevents weak wild Pokemon from appearing for 1000 steps after its use.");
            itemtype.add("General items");
        } catch (IOException ex) {        }
    }
    
    public static final List<String> allitems = new ArrayList<>();
    public static final List<String> itemtype = new ArrayList<>();
    public static final List<String> itemdescriptions = new ArrayList<>();
    public static final List<String> allpokeballs = new ArrayList<>();
    public static final List<String> pokeballdescriptions = new ArrayList<>();
    public static final  List<String> allkeyitems = new ArrayList<>(Arrays.asList(
//            "bicycle",
            "town map",
            ""
    ));
    
    public void run(){
        
    }
    
    public static void use(String string){
        use = string;
        if (Items.allitems.contains(use)){
            if(Items.itemtype.get(Items.allitems.indexOf(use)).equalsIgnoreCase("Medicine")){
                Main.it.getBag().fadeOut();
                PokemonMenu pm = new PokemonMenu(3);
                try{
                    if (Main.map.getBP().battling){
                        pm = new PokemonMenu(2);
                    }
                }catch(NullPointerException e){}
                Thread thread = new Thread (pm);
                Main.w.Switch(Main.it.getBag(), pm);
                thread.start();
            }
            else if(Items.itemtype.get(Items.allitems.indexOf(use)).equalsIgnoreCase("General Items")){
                if (Main.map.getRepelSteps() <= 0){
                    if (use.equalsIgnoreCase("Repel")){
                        Main.map.setRepel(50);
                        Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Repel"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Repel"))-1);
                    }
                    else if (use.equalsIgnoreCase("Super Repel")){
                        Main.map.setRepel(150);
                        Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Super Repel"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Super Repel"))-1);
                    }
                    else if (use.equalsIgnoreCase("Max Repel")){
                        Main.map.setRepel(250);
                        Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Max Repel"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Max Repel"))-1);
                    }
                    else if (use.equalsIgnoreCase("Ultimate Repel")){
                        Main.map.setRepel(1000);
                        Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Ultimate Repel"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Ultimate Repel"))-1);
                    }
                }
            }
        }
        else if (Items.allpokeballs.contains(use)){
            if (use.equalsIgnoreCase("Poke Ball") || use.equalsIgnoreCase("Great Ball") || use.equalsIgnoreCase("Ultra Ball") || use.equalsIgnoreCase("Master Ball")){
                Main.it.getBag().numpokeballs.set (Main.it.getBag().pokeballs.indexOf(use), Main.it.getBag().numpokeballs.get(Main.it.getBag().pokeballs.indexOf(use))-1);
                Main.getMap().getBP().getInbattle().setMove(use);
                 Main.it.getBag().fadeOut();
                Thread thread = new Thread (Main.getMap().getBP());
                Main.w.Switch(Main.it.getBag(), Main.getMap().getBP());
                thread.start();
                
            }
        }
    }
    
    static String use = "Potion";
    public static void activate(){
        if(Items.itemtype.get(Items.allitems.indexOf(use)).equalsIgnoreCase("Medicine")){
            if (use.equalsIgnoreCase("Potion")){
                Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] += 20;
                if (Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0]>Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1]){
                    Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Potion"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Potion"))-1);
                    Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] = Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1];
                }
            }
            else if (use.equalsIgnoreCase("Super Potion")){
                Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] += 50;
                if (Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0]>Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1]){
                    Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Super Potion"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Super Potion"))-1);
                    Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] = Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1];
                }
            }
            else if (use.equalsIgnoreCase("Hyper Potion")){
                Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] += 200;
                if (Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0]>Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1]){
                    Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Hyper Potion"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Hyper Potion"))-1);
                    Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] = Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1];
                }
            }
            else if (use.equalsIgnoreCase("Max Potion") && Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0]!=Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1]){
                Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Max Potion"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Max Potion"))-1);
                Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] = Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1];
            }
            else if (use.equalsIgnoreCase("Full Restore") && Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0]!=Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1]){
                Main.it.getBag().numitems.set(Main.it.getBag().items.indexOf("Full Restore"), Main.it.getBag().numitems.get(Main.it.getBag().items.indexOf("Full Restore"))-1);
                Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][0] = Main.it.getParty().get(Main.it.getBag().selectedpokemon).getStats()[0][1];
            }
        }
    }
    
    public void toBeIncluded(){
        /*
        Items:
        "potion",
        "super potion",
        "hyper potion",
        "max potion",
        "full restore",
        "awakening",
        "paralysis heal",
        "burn heal",
        "antidote",
        "ice heal",
        "full heal",
        "ether",
        "max ether",
        "elixir",
        "max elixir",
        "revive",
        "max revive",
        "sacred ash",
        "rare candy",
        "escape rope",
        "repel",
        "super repel",
        "max repel",
        "superior repel",
        "nugget",
        
        Pokeballs
        "pokeball",
        "great ball",
        "ultra ball",
        "master ball",
        "premier ball",
        "timer ball",
        "quick ball",
        
        
        */
    }
    
}
