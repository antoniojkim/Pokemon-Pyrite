/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Character;

import Bag.Bag;
import Battle.BattleMoves;
import Main.Main;
import static Main.Main.music;
import Main.p;
import Pokemon.Pokemon;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio's Laptop
 */
public class Save{
    
    public static void main (String[] args){
        String test = encrypt("");
        System.out.println(test);
        System.out.println(decrypt(test));
        
        for (int x=0; x<BattleMoves.prioritylist.size(); x++){
            System.out.println(BattleMoves.prioritylist.get(x)[0] + ": " + BattleMoves.prioritylist.get(x)[1]);
        }
    }
    
    List<Pokemon> party = new ArrayList<>();
    Bag bag = new Bag();
    
    int worldX, worldY, pX, pY;
    public String name = "";
    int gender = 0, money = 0, trainerID = 0, repelSteps = 0;
    boolean surf = false;
    
    public boolean isSurf(){
        return surf;
    }
    
    public List<String> badges = new ArrayList<>();
    public List<String> pokemoncaught = new ArrayList<>();
    List<String> pokemonseen = new ArrayList<>();
    int[] keyMap = new int[12];
    static int encryptLength = 15;
    
    public Save(){
        
    }
    public Save(Trainer it){
        save(it);
    }
    
    public void save(Trainer it){
        
        PrintWriter pr = p.printwriter("./Save/"+it.name+".txt");
        pr.print(encrypt(it.name));
        pr.print(encrypt(it.gender+""));
        pr.print(encrypt(Main.map.getWorldX()+""));
        pr.print(encrypt(Main.map.getWorldY()+""));
        pr.print(encrypt(Main.map.getpX()+""));
        pr.print(encrypt(Main.map.getpY()+""));
        pr.print(encrypt(Main.map.getRepelSteps()+""));
        if (Main.map.isSurf()){
            pr.print(encrypt("0"));
        }
        else{
            pr.print(encrypt("1"));
        }
        pr.print(encrypt(it.money+""));
        pr.print(encrypt(it.trainerID+""));
        pr.print(encrypt(it.badges.size()+""));
        for (int a = 0; a<it.badges.size(); a++){
            pr.print(encrypt(it.badges.get(a)+""));
        }
        pr.print(encrypt(it.pokemoncaught.size()+""));
        for (int a = 0; a<it.pokemoncaught.size(); a++){
            pr.print(encrypt(it.pokemoncaught.get(a)+""));
        }
        pr.print(encrypt(it.pokemonseen.size()+""));
        for (int a = 0; a<it.pokemonseen.size(); a++){
            pr.print(encrypt(it.pokemonseen.get(a)+""));
        }
        for (int a = 0; a<it.keyMap.length; a++){
            pr.print(encrypt(it.keyMap[a]+""));
        }
        pr.print(encrypt(it.party.size()+""));
        for (int a = 0; a<it.party.size(); a++){
            pr.print(encrypt(""+it.party.get(a).getName()));
            pr.print(encrypt(""+it.party.get(a).getLevel()));
            pr.print(encrypt(""+it.party.get(a).getDisplayname()));
            pr.print(encrypt(""+it.party.get(a).getIndex()));
            pr.print(encrypt(""+it.party.get(a).getTypes().size()));
            for (int b = 0; b<it.party.get(a).getTypes().size(); b++){
                pr.print(encrypt(""+it.party.get(a).getTypes().get(b)));
            }
            pr.print(encrypt(""+it.party.get(a).getGender()));
            pr.print(encrypt(""+it.party.get(a).getWeight()));
            pr.print(encrypt(""+it.party.get(a).getHeight()));
            pr.print(encrypt(""+it.party.get(a).getAbility()));
            pr.print(encrypt(""+it.party.get(a).getAbilityDescription()));
            pr.print(encrypt(""+it.party.get(a).getItem()));
            pr.print(encrypt(""+it.party.get(a).getCry()));
            pr.print(encrypt(""+it.party.get(a).getEv()[0]));
            pr.print(encrypt(""+it.party.get(a).getEv()[1]));
            pr.print(encrypt(""+it.party.get(a).getEv()[2]));
            pr.print(encrypt(""+it.party.get(a).getEv()[3]));
            pr.print(encrypt(""+it.party.get(a).getEv()[4]));
            pr.print(encrypt(""+it.party.get(a).getEv()[5]));
            pr.print(encrypt(""+it.party.get(a).getIv()[0]));
            pr.print(encrypt(""+it.party.get(a).getIv()[1]));
            pr.print(encrypt(""+it.party.get(a).getIv()[2]));
            pr.print(encrypt(""+it.party.get(a).getIv()[3]));
            pr.print(encrypt(""+it.party.get(a).getIv()[4]));
            pr.print(encrypt(""+it.party.get(a).getIv()[5]));
            pr.print(encrypt(""+it.party.get(a).getExp()));
            pr.print(encrypt(""+it.party.get(a).getExpType()));
            pr.print(encrypt(""+it.party.get(a).getEvolvesat()));
            for (int b = 0; b<it.party.get(a).getStats().length; b++){
                for (int c = 0; c<it.party.get(a).getStats()[b].length; c++){
                    pr.print(encrypt(""+it.party.get(a).getStats()[b][c]));
                }
            }
            pr.print(encrypt(""+it.party.get(a).getLearnset().size()));
            for (int b = 0; b<it.party.get(a).getLearnset().size(); b++){
                pr.print(encrypt(""+it.party.get(a).getLearnset().get(b).size()));
                for (int c = 0; c<it.party.get(a).getLearnset().get(b).size(); c++){
                    pr.print(encrypt(""+it.party.get(a).getLearnset().get(b).get(c)));
                }
            }
            pr.print(encrypt(""+it.party.get(a).getMoveset().size()));
            for (int b = 0; b<it.party.get(a).getMoveset().size(); b++){
                pr.print(encrypt(""+it.party.get(a).getMoveset().get(b)));
                pr.print(encrypt(""+it.party.get(a).getPp().get(b)[0]));
                pr.print(encrypt(""+it.party.get(a).getPp().get(b)[1]));
            }
            pr.print(encrypt(""+it.party.get(a).getMultiturnCount()));
            pr.print(encrypt(""+it.party.get(a).getNonVolatileStatus()));
//            pr.print(encrypt(""+it.party.get(a).get));
//            pr.print(encrypt(""+it.party.get(a).get));
//            pr.print(encrypt(""+it.party.get(a).get));
        }
        pr.print(encrypt(""+it.bag.items.size()));
        for (int a = 0; a<it.bag.items.size(); a++){
            pr.print(encrypt(""+it.bag.items.get(a)));
            pr.print(encrypt(""+it.bag.numitems.get(a)));
        }
        pr.print(encrypt(""+it.bag.pokeballs.size()));
        for (int a = 0; a<it.bag.pokeballs.size(); a++){
            pr.print(encrypt(""+it.bag.pokeballs.get(a)));
            pr.print(encrypt(""+it.bag.numpokeballs.get(a)));
        }
        pr.print(encrypt(""+it.bag.tms.size()));
        for (int a = 0; a<it.bag.tms.size(); a++){
            pr.print(encrypt(""+it.bag.tms.get(a)));
        }
        pr.print(encrypt(""+it.bag.hms.size()));
        for (int a = 0; a<it.bag.hms.size(); a++){
            pr.print(encrypt(""+it.bag.hms.get(a)));
        }
        pr.print(encrypt(""+it.bag.keyitems.size()));
        for (int a = 0; a<it.bag.keyitems.size(); a++){
            pr.print(encrypt(""+it.bag.keyitems.get(a)));
        }
        pr.close();
//        saveRecent(it);
        Main.sfx = music("./Audio/SE/save.wav");
        Main.sfx.start();
    }
    
    public static Save getSave(String path){
        Save save = new Save();
        BufferedReader br = p.filereader(path);
        String line = "", data = "";
        int num = 0;
        try {
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                data+=line;
            }
            String[] datum = data.split("\\&");
            try{
                save.name = decrypt(datum[num]);
//                Debug(datum, ++num);
                save.gender = Integer.parseInt(decrypt(datum[++num]));
                save.worldX = Integer.parseInt(decrypt(datum[++num]));
                save.worldY = Integer.parseInt(decrypt(datum[++num]));
                save.pX = Integer.parseInt(decrypt(datum[++num]));
                save.pY = Integer.parseInt(decrypt(datum[++num]));
                save.repelSteps = Integer.parseInt(decrypt(datum[++num]));
                boolean[] isSurf = {true, false};
                save.surf = isSurf[Integer.parseInt(decrypt(datum[++num]))];
                save.money = Integer.parseInt(decrypt(datum[++num]));
                save.trainerID = Integer.parseInt(decrypt(datum[++num]));
                int numBadges = Integer.parseInt(decrypt(datum[++num]));
                save.badges.clear();
                for (int a = 0; a<numBadges; a++){
                    save.badges.add(decrypt(datum[++num]));
                }
                int numCaught = Integer.parseInt(decrypt(datum[++num]));
                save.pokemoncaught.clear();
                for (int a = 0; a<numCaught; a++){
                    save.pokemoncaught.add(decrypt(datum[++num]));
                }
                int numSeen= Integer.parseInt(decrypt(datum[++num]));
                save.pokemonseen.clear();
                for (int a = 0; a<numSeen; a++){
                    save.pokemonseen.add(decrypt(datum[++num]));
                }
                for (int a = 0; a<save.keyMap.length; a++){
                    save.keyMap[a] = Integer.parseInt(decrypt(datum[++num]));
                }
                int numParty = Integer.parseInt(decrypt(datum[++num]));
                save.party.clear();
                for (int a = 0; a<numParty; a++){
                    Pokemon pokemon = new Pokemon(decrypt(datum[++num]), Integer.parseInt(decrypt(datum[++num])));
                    pokemon.setDisplayname(decrypt(datum[++num]));
                    pokemon.setIndex(Integer.parseInt(decrypt(datum[++num])));
                    int numTypes = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getTypes().clear();
                    for (int b = 0; b<numTypes; b++){
                        pokemon.getTypes().add(decrypt(datum[++num]));
                    }
                    pokemon.setGender(Integer.parseInt(decrypt(datum[++num])));
                    pokemon.setWeight(Double.parseDouble(decrypt(datum[++num])));
                    pokemon.setHeight(Double.parseDouble(decrypt(datum[++num])));
                    pokemon.setAbility(decrypt(datum[++num]));
                    pokemon.setAbilitydescription(decrypt(datum[++num]));
                    pokemon.setItem(decrypt(datum[++num]));
                    pokemon.setCry(decrypt(datum[++num]));
                    pokemon.getEv()[0] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getEv()[1] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getEv()[2] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getEv()[3] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getEv()[4] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getEv()[5] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getIv()[0] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getIv()[1] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getIv()[2] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getIv()[3] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getIv()[4] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getIv()[5] = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.setExp(Integer.parseInt(decrypt(datum[++num])));
                    pokemon.setExptype(Integer.parseInt(decrypt(datum[++num])));
                    pokemon.setEvolvesat(Integer.parseInt(decrypt(datum[++num])));
                    for (int b = 0; b<pokemon.getStats().length; b++){
                        for (int c = 0; c<pokemon.getStats()[b].length; c++){
                            pokemon.getStats()[b][c] = Integer.parseInt(decrypt(datum[++num]));
                        }
                    }
                    int numLearnset = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getLearnset().clear();
                    for (int b = 0; b<numLearnset; b++){
                        int numLearnset2 = Integer.parseInt(decrypt(datum[++num]));
                        pokemon.getLearnset().add(new ArrayList<>());
                        for (int c = 0; c<numLearnset2; c++){
                            pokemon.getLearnset().get(b).add(decrypt(datum[++num]));
                        }
                    }
                    int numMoveset = Integer.parseInt(decrypt(datum[++num]));
                    pokemon.getMoveset().clear();
                    pokemon.getPp().clear();
                    for (int b = 0; b<numMoveset; b++){
                        pokemon.getMoveset().add(decrypt(datum[++num]));
                        int[] array = {Integer.parseInt(decrypt(datum[++num])), Integer.parseInt(decrypt(datum[++num]))};
                        pokemon.getPp().add(array);
                    }
                    pokemon.setMultiturnCount(Integer.parseInt(decrypt(datum[++num])));
                    pokemon.setNonVolatileStatus(Integer.parseInt(decrypt(datum[++num])));
                    save.party.add(pokemon);
                }
                save.bag.items.clear();
                save.bag.numitems.clear();
                int numItems = Integer.parseInt(decrypt(datum[++num]));
                for (int a = 0; a<numItems; a++){
                    save.bag.items.add(decrypt(datum[++num]));
                    save.bag.numitems.add(Integer.parseInt(decrypt(datum[++num])));
                }
                save.bag.pokeballs.clear();
                save.bag.numpokeballs.clear();
                int numPokeballs = Integer.parseInt(decrypt(datum[++num]));
                for (int a = 0; a<numPokeballs; a++){
                    save.bag.pokeballs.add(decrypt(datum[++num]));
                    save.bag.numpokeballs.add(Integer.parseInt(decrypt(datum[++num])));
                }
                save.bag.tms.clear();
                int numTms = Integer.parseInt(decrypt(datum[++num]));
                for (int a = 0; a<numTms; a++){
                    save.bag.tms.add(decrypt(datum[++num]));
                }
                save.bag.hms.clear();
                int numHms = Integer.parseInt(decrypt(datum[++num]));
                for (int a = 0; a<numHms; a++){
                    save.bag.hms.add(decrypt(datum[++num]));
                }
                save.bag.keyitems.clear();
                int numkeyitems = Integer.parseInt(decrypt(datum[++num]));
                for (int a = 0; a<numkeyitems; a++){
                    save.bag.keyitems.add(decrypt(datum[++num]));
                }
            } catch (NumberFormatException ex) {
                System.out.println("Number Format Exception");
                Debug(datum, num);
            }
        } catch (IOException ex) {
            System.out.println("Could not read file");
        }
        return save;
    }
    
    public static String encrypt(String str){
        String encrypted = "";
        for (int a = 0; a<str.length(); a++){
            for (int b = 0; b<encryptLength; b++){
                if (Math.random()<0.1){
                    encrypted += p.symbols[p.randomint(0, p.symbols.length-1)];
                }
                else if (Math.random()>0.65){
                    encrypted += ""+p.randomint(0, 9);
                }
                else{
                    if (Math.random()>0.5){
                        encrypted += ""+p.letters.get(p.randomint(0, p.letters.size()-1));
                    }
                    else{
                        encrypted += (""+p.letters.get(p.randomint(0, p.letters.size()-1))).toUpperCase();
                    }
                }
            }
            encrypted+= str.substring(a, a+1);
        }
        for (int b = 0; b<encryptLength; b++){
            if (Math.random()<0.1){
                encrypted += p.symbols[p.randomint(0, p.symbols.length-1)];
            }
            else if (Math.random()>0.65){
                encrypted += ""+p.randomint(0, 9);
            }
            else{
                if (Math.random()>0.5){
                    encrypted += ""+p.letters.get(p.randomint(0, p.letters.size()-1));
                }
                else{
                    encrypted += (""+p.letters.get(p.randomint(0, p.letters.size()-1))).toUpperCase();
                }
            }
        }
        encrypted+="&";
        return encrypted;
    }
    
    public static String decrypt(String str){
        str = str.replaceAll("&", "");
        String decrypted = "";
        for (int a = 1; encryptLength*a+(a-1) < str.length(); a++){
            decrypted += str.substring(encryptLength*a+(a-1), encryptLength*a+(a-1)+1);
        }
        decrypted = decrypted.trim();
        return decrypted;
    }
    
    public static void Debug(String[] datum, int num){
        System.out.println(datum[num]);
        System.out.println(decrypt(datum[num])+"\n");
    }
}
