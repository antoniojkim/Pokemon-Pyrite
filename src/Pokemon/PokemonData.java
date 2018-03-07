/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Pokemon;

import Main.p;
import Battle.BattleMoves;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio's Laptop
 */
public final class PokemonData {
    
    public static List<String> PokemonList = new ArrayList<>();
    public static List<String[]> TypeList = new ArrayList<>();
    public static List<String> categories = new ArrayList<>();
    public static List<Double> height = new ArrayList<>();
    public static List<Double> weight = new ArrayList<>();
    public static List<String> Genderless= new ArrayList<>();
    public static List<String> Female = new ArrayList<>();
    public static List<String> Male = new ArrayList<>();
    public static List<String> abilitieslist = new ArrayList<>();
    public static List<String> abilitiesdescriptions = new ArrayList<>();
    public static String[][] abilities = new String[0][0];
    public static List<List<List<String>>>learnset = new ArrayList<>();
    public static List<List<String>> TMLearnables = new ArrayList<>();
    public static int[][] basestats = new int[0][0];
    public static int[][] EVYield = new int[0][0];
    public static int[][] experienceToNext = new int [98][0];
    public static int[][] experienceTotal = new int [98][0];
    
    public static void main (String[] args) throws IOException{
        Pokemon pokemon = new Pokemon("Snorlax", 60);
        p.print(pokemon.getTmset().toArray());
//        System.out.println("\n"+BattleMoves.tms.contains("Surf"));
//        System.out.println(pokemon.getTmset().contains("Surf"));
    }
    
    public PokemonData(){
        long start = System.currentTimeMillis();
        PokemonList.clear();
        TypeList.clear();
        Genderless.clear();
        Male.clear();
        Female.clear();
        getPokemonNameandType();
        abilitieslist.clear();
        getAbilitiesList();
        getPokemonAbilities();
        learnset.clear();
        getLearnSet();
        getTMLearnables();
        getBaseStats();
        getEVYield();
        getExperienceTable();
        getGenderList();
        getSizes();
        getCategories();
    }
    
    public void getPokemonNameandType(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Pokemon List.txt")));
            String line = "";
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                PokemonList.add(array[0]);
                String[] type = new String[array.length-1];
                for (int a = 0; a<type.length; a++){
                    if (!array[a+1].equals("Fairy")){
                        type[a] = array[a+1];
                    }
                }
                TypeList.add(type);
            }
        }catch(IOException e){}
    }
    
    public void getGenderList(){
        try{
            BufferedReader brG = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Genderless.txt")));
            String name = "";
            while(true){
                name = brG.readLine();
                if (name == null){
                    break;
                }
                name = name.trim();
                if (PokemonList.contains(name)){
                    Genderless.add(name);
                }
            }
            BufferedReader brF = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Female.txt")));
            name = "";
            while(true){
                name = brF.readLine();
                if (name == null){
                    break;
                }
                name = name.trim();
                if (PokemonList.contains(name)){
                    Female.add(name);
                }
                
            }
            BufferedReader brM = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Male.txt")));
            name = "";
            while(true){
                name = brM.readLine();
                if (name == null){
                    break;
                }
                name = name.trim();
                if (PokemonList.contains(name)){
                    Male.add(name);
                }
            }
            
        }catch(IOException e){}
    }
    
    public void getAbilitiesList(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("List of Abilities.txt")));
            String line = "";
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                abilitieslist.add(array[0]);
                abilitiesdescriptions.add(array[1]);
            }
        }catch(IOException e){}
    }
    
    public void getPokemonAbilities(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Pokemon Abilities.txt")));
            List<String[]> list = new ArrayList<>();
            List<String> names = new ArrayList<>();
            String line = "";
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                names.add(array[0]);
                String[] ability = new String [array.length-1];
                for (int a = 0; a<ability.length; a++){
                    ability[a] = array[a+1];
                }
                list.add(ability);
            }
            abilities = new String[PokemonList.size()][0];
            for (int a = 0; a<PokemonList.size(); a++){
                int index = names.indexOf(PokemonList.get(a));
                if (index == -1){
                    //System.out.println("Could not find "+PokemonList.get(a)+"'s Abilities");
                    break;
                }
                abilities[a] = list.get(index);
            }
        }catch(IOException e){}
    }
    
    public void getLearnSet(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Pokemon Move Set.txt")));
            List<List<List<String>>>list3 = new ArrayList<>();
            List<String>moves = new ArrayList<>();
            List<Integer>level = new ArrayList<>();
            List<String>names = new ArrayList<>();
            String line = "";
            String name = "";
            while(true){
                line = br.readLine();
                if (line == null){
                    if (PokemonList.contains(name)){
                        names.add(name);
                        List<List<String>>list2 = new ArrayList<>();
                        for (int a = 1; a<=100; a++){
                            List<String>list = new ArrayList<>();
                            for (int b = 0; b<level.size(); b++){
                                if (level.get(b)>a){
                                    break;
                                }
                                else if(level.get(b)==a){
                                    list.add(moves.get(b));
                                }
                            }
                            list2.add(list);
                        }
                        list3.add(list2);
                    }
                    break;
                }
                String[] newarray = line.split("	");
                try{
                    if(!newarray[0].equals("")){
                        if (PokemonList.contains(name)){
                            names.add(name);
                            List<List<String>>list2 = new ArrayList<>();
                            for (int a = 0; a<=100; a++){
                                List<String>list = new ArrayList<>();
                                for (int b = 0; b<level.size(); b++){
                                    if (level.get(b)>a){
                                        break;
                                    }
                                    else if(level.get(b)==a){
                                        list.add(moves.get(b));
                                    }
                                }
                                list2.add(list);
//                        p.print(list.toArray());
                            }
                            list3.add(list2);
//                        System.out.println("");
                        }
                        moves.clear();
                        level.clear();
                        if (PokemonList.contains(newarray[0])){
                            name = newarray[0];
                        }
                        else{
                            name = p.toProperCase(newarray[0]);
                        }
                    }
                    if (BattleMoves.moveindex.contains(p.toProperCase(newarray[2]))){
                        moves.add(p.toProperCase(newarray[2]));
                        level.add(Integer.parseInt(newarray[1]));
                    }
                }catch(ArrayIndexOutOfBoundsException e){}
            }
            for (int a = 0; a<PokemonList.size(); a++){
                int index = names.indexOf(PokemonList.get(a));
                if (index == -1){
                    break;
                }
                learnset.add(list3.get(index));
            }
        }catch(IOException e){}
    }
    
    public void getTMLearnables(){
        BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Learnable TMs List.txt")));
        String line = "";
        List<String> names = new ArrayList<>();
        List<List<String>> set = new ArrayList<>();
        List<String> list = new ArrayList<>();
        boolean first = true;
        try {
            list.clear();
            while(true){
                line = br.readLine();
                if (line == null){
                    set.add(list);
                    break;
                }
                if (!line.trim().equals("")){
                    String[] array = line.split("	");
                    if (!array[0].equals("")){
                        if (!first){
                            List<String> newlist = new ArrayList();
                            newlist.addAll(list);
                            set.add(newlist);
//                        p.print(newlist.toArray());
//                        System.out.println("");
                            list.clear();
                        }
                        else{
                            first = false;
                        }
                        if (!PokemonData.PokemonList.contains(array[0])){
                            array[0] = p.toProperCase(array[0]);
                        }
//                        System.out.print(array[0]+ "     ");
                        names.add(array[0]);
                    }
                    if (array.length>1){
                        if (!BattleMoves.tms.contains(array[2])){
                            array[2] = p.toProperCase(array[2]);
                        }
                        int index = BattleMoves.tms.indexOf(array[2]);
                        if(BattleMoves.tms.contains(array[2])){
                            list.add(array[2]);
                        }
                    }
                }
            }
            TMLearnables.clear();
            //System.out.println("");
            for (int a = 0; a<PokemonData.PokemonList.size(); a++){
                int index = names.indexOf(PokemonData.PokemonList.get(a));
                if (index == -1){
                    //System.out.println(PokemonData.PokemonList.get(a)+"'s TM Learnset could not be found");
//                    p.print(names.toArray());
                    break;
                }
//                System.out.print(index+"    "+PokemonData.PokemonList.get(a)+"    ");
//                p.print(set.get(index).toArray());
//                System.out.println("");
                TMLearnables.add(set.get(index));
            }
//            p.print(TMLearnables.toArray());
        } catch (IOException ex) {            }
        
    }
    
    public void getBaseStats(){
        BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Pokemon Base Stats.txt")));
        String line = "";
        try{
            List<int[]> list = new ArrayList<>();
            List<String> name = new ArrayList<>();
            while(true){
                line = br.readLine();
                if (line == null || line.replaceAll("	", "").equals("")){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                name.add(array[0]);
                int[] array2 = {Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6])};
                list.add(array2);
            }
            basestats = new int [PokemonList.size()][0];
            for (int a = 0; a<PokemonList.size(); a++){
                int index = name.indexOf(PokemonList.get(a));
                if (index == -1){
                    System.out.println(PokemonList.get(a)+" could not be found from the base stats list");
                    break;
                }
                basestats[a] = list.get(index);
            }
        }catch(IOException | ArrayIndexOutOfBoundsException e){        }
    }
    
    public void getEVYield(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("EV Yield.txt")));
            String line = "";
            List<int[]> list = new ArrayList<>();
            List<String> name = new ArrayList<>();
            while(true){
                line = br.readLine();
                if (line == null || line.replaceAll("	", "").equals("")){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                name.add(array[0]);
                int[] array2 = {Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5]), Integer.parseInt(array[6]), Integer.parseInt(array[7])};
                list.add(array2);
            }
            EVYield = new int [PokemonList.size()][0];
            for (int a = 0; a<PokemonList.size(); a++){
                int index = name.indexOf(PokemonList.get(a));
                if (index == -1){
                    System.out.println("Could not find EV Yield for:   "+PokemonList.get(a));
//                    break;
                }
                EVYield[a] = list.get(index);
            }
        }catch(IOException e){}
    }
    
    public void getExperienceTable(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Experience Table.txt")));
            String line = "";
            for (int a = 0; a<experienceToNext.length; a++){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                int[] array2 = {Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])};
                experienceToNext[a] = array2;
            }
            br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Total Experience Table.txt")));
            for (int a = 0; a<experienceTotal.length; a++){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\*", "");
                String[] array = line.split("	");
                int[] array2 = {Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4]), Integer.parseInt(array[5])};
                experienceTotal[a] = array2;
            }
        }catch(IOException e){}
    }
    
    public void getSizes(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Pokemon Sizes.txt")));
            String line = "";
            List<String> names = new ArrayList<>();
            List<Double> heights = new ArrayList<>();
            List<Double> weights = new ArrayList<>();
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
//                line = line.replaceAll("*", "");
                String[] array = line.split("	");
                if (array.length == 3){
                    names.add(array[0]);
                    heights.add(Double.parseDouble(array[1].substring(array[1].indexOf("(")+1, array[1].indexOf("m"))));
                    weights.add(Double.parseDouble(array[2].substring(array[2].indexOf("(")+1, array[2].indexOf(" kg"))));
                }
//                else{
//                    System.out.println(line);
//                }
            }
            for (int a = 0; a<PokemonList.size(); a++){
                int index = names.indexOf(PokemonList.get(a));
                if (index == -1){
                    System.out.println("Could not find "+PokemonList.get(a)+"'s height and weight");
                }
                else{
                    this.height.add(heights.get(index));
                    this.weight.add(weights.get(index));
                }
            }
        }
        catch(IOException e){}
    }
    
    public void getCategories(){
        try{
            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream("Pokemon Categories.txt")));
            String line = "";
            List<String>names = new ArrayList<>();
            List<String>category = new ArrayList<>();
            while(true){
                line = br.readLine();
                if (line == null){
                    break;
                }
                line = line.replaceAll("\\?", "");
                String[] array = line.split("	");
                names.add(array[0]);
                category.add(array[1]);
//                System.out.println(array[1]);
            }
            for (int a = 0; a<PokemonList.size(); a++){
                int index = names.indexOf(PokemonList.get(a));
                if (index == -1){
                    System.out.println("Could not find the Category for "+PokemonList.get(a));
                    break;
                }
                categories.add(category.get(index));
            }
        }catch(IOException e){}
    }
    
}
