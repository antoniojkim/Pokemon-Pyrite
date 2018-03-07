
package Pokemon;

import Battle.BattleMoves;
import Main.p;
import Main.Main;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class Pokemon{
    
    protected String name = "", displayname = "";
    protected double height = 0, weight = 0;
    protected int index, gender = 2;
    protected BufferedImage image, backimage;
    protected BufferedImage menuimage, animatedmenuimage;
    protected int level = 0, exp = 0, exptype, expToNext, endExp, evolvesat = 101, endHealth, expToLevel;
    protected int multiturnCount = 0;
    protected boolean fainted = false;
    protected List<String> types = new ArrayList<>();
    protected String ability = "", abilitydescription = "";
    protected String item = "";
    protected String cry = "";
    protected List<List<String>> learnset = new ArrayList<>();
    protected List<String> tmset =  new ArrayList<>();
    protected List<String> moveset =  new ArrayList<>();
    protected List<int[]>pp = new ArrayList<>();
    protected int nonVolatileStatus = -1; //0:burn, 1:frozen, 2:paralysis, 3:poison, 4:sleep
    protected int[][]stats = {
        // {current stat, max stat, stage}
        /* 0.Health (HP)*/ {0, 0, 0},
        /* 1.Attack*/ {0, 0, 0},
        /* 2.Defense*/ {0, 0, 0},
        /* 3.Special Attack*/  {0, 0, 0},
        /* 4.Special Defense*/  {0, 0, 0},
        /* 5.Speed*/  {0, 0, 0},
        //These two are in-battle only and start at 100 at the beginning of each battle
        /* 6.Accuracy*/ {100 ,100, 0},
        /* 7.Evasion*/ {100, 100, 0}
    };
    protected int[] iv = {
        /*Health (HP)*/ (int)(32*Math.random()),
        /*Attack*/ (int)(32*Math.random()),
        /*Defense*/ (int)(32*Math.random()),
        /*Special Attack*/  (int)(32*Math.random()),
        /*Special Defense*/  (int)(32*Math.random()),
        /*Speed*/  (int)(32*Math.random())
    };
    protected int[] ev = {
        /*Health (HP)*/ 0,
        /*Attack*/ 0,
        /*Defense*/ 0,
        /*Special Attack*/  0,
        /*Special Defense*/  0,
        /*Speed*/  0
    };
    
    public Pokemon(String name, int level){
        if (Main.data.PokemonList.contains(name) || Main.data.PokemonList.contains(p.toProperCase(name))){
//            name = p.toProperCase(name);
this.name = name;
gender = setGender();
exptype = p.randomint(0, 5);
exp = Main.data.experienceTotal[level-1][exptype];
if (level<100){
    expToNext = Main.data.experienceToNext[level-1][exptype]-(exp-Main.data.experienceTotal[level-1][exptype]);
}
if (name.equalsIgnoreCase("Mr. Mime")){
    displayname = "MR. MIME";
}
else if (name.equalsIgnoreCase("Mime Jr.")){
    displayname = "MIME JR.";
}
else if (name.split(" ").length > 1){
    displayname = name.split(" ")[0].toUpperCase();
}
else{
    displayname = name.toUpperCase();
}
index = Main.data.PokemonList.indexOf(name);
this.height = Main.data.height.get(index);
this.weight = Main.data.weight.get(index);
String number = "";
if (index<=349){
    number = (index+1)+"";
}
else if (index>349 && index<=384){
    number = (index+2)+"";
}
else if (index>384 && index<=387){
    number = "386_"+(index-384);
}
else if(index>387 && index <= 493){
    number = (index-1)+"";
}
else if(index == 494){
    number = "492_1";
}
else if(index >494 && index <= 557){
    number = (index-2)+"";
}
else if(index == 558){
    number = "555_1";
}
else if(index >558){
    number = (index-3)+"";
}
while(number.length()<3){
    number = "0"+number;
}
if (("./Sprites/icon"+number+".png").equals("./Sprites/icon000.png")){
    System.out.println(name);
    System.out.println(index);
}
cry = "./Audio/SE/"+number+"Cry.wav";
this.menuimage = p.loadImage("./Sprites/icon"+number+".png");
this.animatedmenuimage = menuimage.getSubimage(0, 0, menuimage.getWidth()/2, menuimage.getHeight());
this.image = p.loadImage("./Sprites/Battle Sprites/"+number+".png");
this.backimage = p.loadImage("./Sprites/Battle Sprites/"+number+"b.png");

this.level = level;
types.clear();
for (int a = 0; a<Main.data.TypeList.get(index).length; a++){
    types.add(Main.data.TypeList.get(index)[a]);
}
stats[0][0] = getHealthStat(level, ev[0], iv[0], Main.data.basestats[index][0]);
stats[0][1] = stats[0][0];
for (int a = 1; a<6; a++){
    stats[a][0] = getStat(level, ev[a], iv[a], Main.data.basestats[index][a]);
    stats[a][1] = stats[a][0];
}
ability = "";
if (Main.data.abilities[index].length<2 || (!Main.data.abilities[index][0].equals("") && Main.data.abilities[index][1].equals(""))){
    ability = Main.data.abilities[index][0];
}
else if (!Main.data.abilities[index][0].equals("") && !Main.data.abilities[index][1].equals("")){
    ability = Main.data.abilities[index][p.randomint(0, 1)];
}
try{
    abilitydescription = Main.data.abilitiesdescriptions.get(Main.data.abilitieslist.indexOf(ability));
}catch(IndexOutOfBoundsException e){
    System.out.println(ability);
}
learnset.clear();
learnset.addAll(Main.data.learnset.get(index));
//            if (index>349){
//                index++;
//            }
tmset.clear();
try{
    tmset.addAll(Main.data.TMLearnables.get(index));
}catch(NullPointerException | IndexOutOfBoundsException e){
//                System.out.println(name+"'s Tm set could not be loaded");
}
fillMoveSet();
        }
    }
    public Pokemon (Pokemon pokemon){
        this.name = pokemon.name;
        this.gender = pokemon.gender;
        this.displayname = pokemon.displayname;
        this.index = pokemon.index;
        this.height = pokemon.height;
        this.weight = pokemon.weight;
        this.image = pokemon.image;
        this.backimage = pokemon.backimage;
        this.menuimage = pokemon.menuimage;
        this.animatedmenuimage = pokemon.animatedmenuimage;
        this.level = pokemon.level;
        this.cry = pokemon.cry;
        this.types.clear();
        this.types.addAll(pokemon.types);
        this.exp = pokemon.exp;
        this.exptype = pokemon.exptype;
        this.evolvesat = pokemon.evolvesat;
        this.item = pokemon.item;
        for (int a = 0; a<6; a++){
            this.stats[a][0] = pokemon.stats[a][0];
            this.stats[a][1] = stats[a][0];
            this.iv[a] = pokemon.iv[a];
            this.ev[a] = pokemon.ev[a];
        }
        this.ability = pokemon.ability;
        this.abilitydescription = pokemon.abilitydescription;
        this.nonVolatileStatus = pokemon.nonVolatileStatus;
        this.multiturnCount = pokemon.multiturnCount;
        this.learnset.clear();
        this.learnset.addAll(pokemon.learnset);
        this.moveset.clear();
        this.moveset.addAll(pokemon.moveset);
        this.tmset.clear();
        this.tmset.addAll(pokemon.tmset);
        this.pp.clear();
        this.pp.addAll(pokemon.pp);
        
    }
    
    
    public static int getHealthStat(int level, int ev, int iv, int base){
        return (int) (Math.floor(((base+iv)*2+Math.sqrt(ev)/4.0)*level/100.0)+level+10);
    }
    public static int getStat(int level, int ev, int iv, int base){
        return (int) (Math.floor(((base+iv)*2+Math.sqrt(ev)/4.0)*level/100.0)+5);
    }
    
    public int setGender(){
        if (Main.data.Genderless.contains(name)){
            return 2;
        }
        else if (Main.data.Female.contains(name)){
            return 1;
        }
        else if (Main.data.Male.contains(name)){
            return 0;
        }
        if (Math.random()<0.5){
            return 1; //female
        }
        return 0; //male
    }
    
    public static double getExperienceGain(String defeatedPokemon, boolean wild, int levelFainted, boolean lucky){
        double a = 1.5;
        if (wild){
            a = 1;
        }
        double b = Main.data.EVYield[Main.data.PokemonList.indexOf(defeatedPokemon)][0];
        double e = 1;
        if (lucky){
            e = 1.5;
        }
        return (int)Math.ceil((a*b*e*levelFainted)/(7.0));
    }
    
    //fills the pokemon's moveset
    public boolean fillMoveSet(){
        for (int a = level-1; a>=0; a--){
            if (learnset.get(a).size()>0){
                for (int b = learnset.get(a).size()-1; b>=0; b--){
                    if (moveset.size()<4 && !moveset.contains(learnset.get(a).get(b))){
                        String move = p.toProperCase(learnset.get(a).get(b).trim());
                        if (BattleMoves.moveindex.contains(move)){
                            moveset.add(move);
                            int[] array = {Integer.parseInt(BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[2]), Integer.parseInt(BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(move))[2])};
                            pp.add(array);
                        }
                    }
                    else{
                        return true;
                    }
                }
            }
        }
        return true;
    }
    
    public void overwriteMove(int index, String move){
        move = p.toProperCase(move);
        if (BattleMoves.moveindex.contains(move)){ //(learnset.contains(move) || tmset.contains(move))){
            moveset.set(index, move);
        }
    }
    
    public String getAbility() {
        return ability;
    }
    
    public String getAbilityDescription(){
        return abilitydescription;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDisplayname() {
        return displayname;
    }
    
    public int getIndex() {
        return index;
    }
    
    public int getGender() {
        return gender;
    }
    
    public double getHeight(){
        return height;
    }
    
    public double getWeight(){
        return weight;
    }
    
    public BufferedImage getImage() {
        return image;
    }
    
    public BufferedImage getBackimage() {
        return backimage;
    }
    
    public BufferedImage getMenuimage() {
        return menuimage;
    }
    
    public BufferedImage getAnimatedmenuimage() {
        return animatedmenuimage;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getExp() {
        return exp;
    }
    
    
    public int getExpToNext() {
        return expToNext;
    }
    
    public int getExpType(){
        return exptype;
    }
    
    public int getEvolvesat() {
        return evolvesat;
    }
    
    public int getMultiturnCount() {
        return multiturnCount;
    }
    
    public List<String> getTypes() {
        return types;
    }
    
    public List<List<String>> getLearnset() {
        return learnset;
    }
    
    public List<String> getMoveset() {
        return moveset;
    }
    
    public List<String> getTmset() {
        return tmset;
    }
    
    public List<int[]> getPp() {
        return pp;
    }
    
    public int getNonVolatileStatus() {
        return nonVolatileStatus;
    }
    
    public int[][] getStats() {
        return stats;
    }
    
    public int[] getIv() {
        return iv;
    }
    
    public int[] getEv() {
        return ev;
    }
    
    public String getItem(){
        return item;
    }
    
    public void setItem(String item){
        this.item = item;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }
    
    public void setIndex(int index) {
        this.index = index;
    }
    
    public void setGender(int gender) {
        this.gender = gender;
    }
    
    public void setImage(BufferedImage image) {
        this.image = image;
    }
    
    public void setBackimage(BufferedImage backimage) {
        this.backimage = backimage;
    }
    
    public void setMenuimage(BufferedImage menuimage) {
        this.menuimage = menuimage;
    }
    
    public void setAnimatedmenuimage(BufferedImage animatedmenuimage) {
        this.animatedmenuimage = animatedmenuimage;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }
    
    public void levelUp(){
        level++;
        stats[0][1] = getHealthStat(level, ev[0], iv[0], Main.data.basestats[index][0]);
        int healthDifference = stats[0][1] - stats[0][0];
        stats[0][0]+= healthDifference;
        for (int a = 1; a<6; a++){
            stats[a][0] = getStat(level, ev[a], iv[a], Main.data.basestats[index][a]);
            stats[a][1] = stats[a][0];
        }
        if (level<100){
            expToNext = Main.data.experienceToNext[level-1][exptype]-(exp-Main.data.experienceTotal[level-1][exptype]);
        }
        adjustExpToLevel();
        //new moves?
        for (int x=0; x<learnset.get(level-1).size(); x++){
            
        }
        
    }
    
    public void incExp(int adjust) {
        if (level<100){
            this.exp += adjust;
            adjustExpToLevel();
        }
    }
    
    public void setExpToNext(int expToNext) {
        this.expToNext = expToNext;
    }
    
    
    public void setEvolvesat(int evolvesat) {
        this.evolvesat = evolvesat;
    }
    
    public void setMultiturnCount(int multiturnCount) {
        this.multiturnCount = multiturnCount;
    }
    
    public void setTypes(List<String> types) {
        this.types = types;
    }
    
    public void setAbility(String ability) {
        this.ability = ability;
    }
    
    public void setLearnset(List<List<String>> learnset) {
        this.learnset = learnset;
    }
    
    public void setMoveset(List<String> moveset) {
        this.moveset = moveset;
    }
    
    public void setPp(List<int[]> pp) {
        this.pp = pp;
    }
    
    public void setNonVolatileStatus(int nonVolatileStatus) {
        this.nonVolatileStatus = nonVolatileStatus;
    }
    
    public void setStats(int[][] stats) {
        this.stats = stats;
    }
    
    public void adjustHealth (int adjust){
        stats[0][0] += adjust;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void setExp(int exp) {
        this.exp = exp;
    }
    
    public void setExptype(int exptype) {
        this.exptype = exptype;
    }
    
    public void setAbilitydescription(String abilitydescription) {
        this.abilitydescription = abilitydescription;
    }
    
    public void setCry() {
        String number = "";
        if (index<=349){
            number = (index+1)+"";
        }
        else if (index>349 && index<=384){
            number = (index+2)+"";
        }
        else if (index>384 && index<=387){
            number = "386_"+(index-384);
        }
        else if(index>387 && index <= 493){
            number = (index-1)+"";
        }
        else if(index == 494){
            number = "492_1";
        }
        else if(index >494 && index <= 557){
            number = (index-2)+"";
        }
        else if(index == 558){
            number = "555_1";
        }
        else if(index >558){
            number = (index-3)+"";
        }
        while(number.length()<3){
            number = "0"+number;
        }
        cry = "./Audio/SE/"+number+"Cry.wav";
    }

    public void setCry(String cry) {
        this.cry = cry;
    }
    
    
//    public void setStats(int whichStat, int num, int setTo){
//        this.stats[whichStat][num]=setTo;
//    }
    
    public void setIv(int[] iv) {
        this.iv = iv;
    }
    
    public void incEv(int[] ev) {
        this.ev[0]+=ev[0];
        this.ev[1]+=ev[1];
        this.ev[2]+=ev[2];
        this.ev[3]+=ev[3];
        this.ev[4]+=ev[4];
        this.ev[5]+=ev[5];
    }
    
    public String getCry() {
        return cry;
    }
    
    public int getEndExp() {
        return endExp;
    }
    
    public void setEndExp(int endExp) {
        this.endExp = endExp;
    }
    
    public void adjustEndExp(int adjust){
        endExp+=adjust;
    }
    
    public int getEndHealth() {
        return endHealth;
    }
    
    public void setEndHealth(int endHealth) {
        this.endHealth = endHealth;
    }
    
    public void adjustEndHealth(int adjust){
        this.endHealth += adjust;
    }
    
    public int getExpToLevel() {
        adjustExpToLevel();
        return expToLevel;
    }
    
    public void adjustExpToLevel(){
        if (level<100){
            expToLevel =  Main.data.experienceTotal[level][exptype] - exp;
        }
        else{
            expToLevel = 0;
        }
    }
    
    public void setExpToLevel(int expToLevel) {
        this.expToLevel = expToLevel;
    }
    
    public void pokeCentreHeal(){
        
        for (int x = 0; x<pp.size(); x++){
            pp.get(x)[0] = pp.get(x)[1];
        }
        for (int x=0; x<8; x++){
            stats[x][0] = stats[x][1];
            stats[x][2]=0;
        }
        nonVolatileStatus=-1;
        fainted=false;
    }

    public boolean isFainted() {
        return fainted;
    }

    public void setFainted(boolean fainted) {
        this.fainted = fainted;
    }
    
}
