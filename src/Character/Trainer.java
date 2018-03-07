package Character;


import Bag.Bag;
import Main.Main;
import Main.p;
import Pokemon.Pokemon;
import com.sun.glass.events.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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
public class Trainer{
    
    public BufferedImage map;
    public BufferedImage drawnmap;
    public BufferedImage drawing;
    public BufferedImage[][] characters = new BufferedImage[4][4];
    public BufferedImage character;
    public BufferedImage[][] charactersurf = new BufferedImage[4][2];
    
    protected List<Pokemon> party = new ArrayList<>();
    protected Bag bag = new Bag();
    
    protected int gender = 0, money = 0;
    protected int worldX, worldY, pX, pY;
    protected String name = "Name";
    protected int trainerID = p.randomint(10000, 65535), timePlayed = 0;
//    protected List<String> badges = new ArrayList<>(Arrays.asList("Boulder Badge", "Cascade Badge", "Thunder Badge", "Rainbow Badge", "Soul Badge", "Marsh Badge", "Volcano Badge", "Earth Badge"));
    protected List<String> badges = new ArrayList<>();
    protected List<String> pokemoncaught = new ArrayList<>();
    protected List<String> pokemonseen = new ArrayList<>();
    
    public Trainer(){
        String[] names = {"Antonio", "Julian", "Katrina"};
        String[] gender = {"Male", "Male", "Female"};
        int[] genderint = {0, 0, 1};
        int r = p.randomint(0, 2);
        setName(names[r]);
        setGender(genderint[r]);
        readCharacter(this.gender);
        character = characters[0][0];
        generateParty();
        money = p.randomint(0, 999999);
        bag.addItem("Hyper potion", p.randomint(1, 999));
        bag.addItem("Max potion", p.randomint(1, 999));
        bag.addItem("revive", p.randomint(1, 999));
        bag.addItem("Max repel", p.randomint(1, 999));
        bag.addItem("bicycle");
    }
    public Trainer(String name){
        if (name.equalsIgnoreCase("Antonio")){
            setName("Antonio");
            setGender(0);
            readCharacter(gender);
            character = characters[0][0];
            party.add(new Pokemon("Charizard", 80));
            party.add(new Pokemon("Gyarados", 70));
            party.add(new Pokemon("Snorlax", 60));
            generateParty();
            party.get(2).overwriteMove(2, "Surf");
            money = 999888;
            bag.addItem("Hyper potion", 799);
            bag.addItem("Max potion", 799);
            bag.addItem("revive", 799);
            bag.addItem("Max repel", 799);
            bag.addItem("Ultimate repel", 799);
            bag.addItem("Poke Ball", 799);
            bag.addItem("Great Ball", 799);
            bag.addItem("Ultra Ball", 799);
            bag.addItem("Master Ball", 799);
        }
        else if (name.equalsIgnoreCase("Katrina")){
            setName("Katrina");
            setGender(1);
            readCharacter(gender);
            character = characters[0][0];
            party.add(new Pokemon("Mew", 80));
            party.add(new Pokemon("Leafeon", 70));
            party.add(new Pokemon("Venusaur", 60));
            generateParty();
            party.get(5).overwriteMove(0, "Sleep Powder");
            party.get(5).overwriteMove(1, "Will-O-Wisp");
            party.get(5).overwriteMove(0, "Surf");
            money = p.randomint(0, 999999);
            bag.addItem("Hyper potion", 999);
            bag.addItem("Max potion", 999);
            bag.addItem("revive", 999);
            bag.addItem("Max repel", 999);
//            bag.addItem("bicycle");
            bag.addItem("super rod");
        }
    }
    public Trainer (String name, int gender){
        setName(name);
        setGender(gender);
        readCharacter(gender);
        character = characters[0][0];
        money = 2000;
        bag.addItem("Potion", 5);
        bag.addItem("Poke Ball", 5);
    }
    public Trainer(Save it){
        worldX = it.worldX;
        worldY = it.worldY;
        pX = it.pX;
        pY = it.pY;
        Main.map.setMap(worldX, worldY, pX, pY);
        Main.map.setRepel(it.repelSteps);
        Main.map.setSurf(it.isSurf());
        setName(it.name);
        setGender(it.gender);
//        party.clear();
        party = (it.party);
        bag = it.bag;
        bag.updateAllItems();
        money = it.money;
        trainerID = it.trainerID;
        badges.clear();
        badges.addAll(it.badges);
        pokemoncaught.clear();
        pokemoncaught.addAll(it.pokemoncaught);
        pokemonseen.clear();
        pokemonseen.addAll(it.pokemonseen);
        keyMap = it.keyMap;
        readCharacter(this.gender);
        System.out.println("Read Complete");
    }
    
    
    public void readCharacter(int gender){
        String[] gendertext = {"Male", "Female"};
        BufferedImage character = p.loadImage("./Characters/"+gendertext[gender]+" Character Pixel.png");
        int width = character.getWidth();
        int height = character.getHeight();
        for (int a = 0; a<4; a++){
            for (int b = 0; b<4; b++){
                this.characters[a][b] = character.getSubimage(b*(width/4), a*(height/4), width/4, height/4);
            }
        }
        character = characters[0][0];
        if (character == null){
            System.out.println("character is null");
        }
        BufferedImage surf = p.loadImage("./Characters/Surf Sprites/"+gendertext[gender]+" Surf Sprites.png");
        width = surf.getWidth();
        height = surf.getHeight();
        int[] indices = {0, 3, 1, 2};
        for (int a = 0; a<charactersurf.length; a++){
            for (int b = 0; b<charactersurf[a].length; b++){
                charactersurf[indices[a]][b] = surf.getSubimage(b*(width/2), a*(height/4), width/2, height/4);
            }
        }
    }
    
    public void generateParty(){
        //       addToParty(new Pokemon("Bulbasaur", 15));
//        addToParty(new Pokemon("Genesect", 50));
        while(party.size()<6){
            try{
                int r = p.randomint(0, Main.data.PokemonList.indexOf("Genesect"));
                if (r<=500){
                    addToParty(new Pokemon(Main.data.PokemonList.get(r), p.randomint(5,95)));
                }
            }catch(NullPointerException e){                            }
        }
//        for (int a = Main.data.PokemonList.indexOf("Shaymin (Land Forme)"); party.size()<6; a++){
//            party.add(new BattlePokemon(Main.data.PokemonList.get(a), p.randomint(5, 95)));
//        }
    }
    
    public void save(){
        Save save = new Save(Main.it);
    }
    
    protected int[] keyMap = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, KeyEvent.VK_D, KeyEvent.VK_ENTER, KeyEvent.VK_W, KeyEvent.VK_ESCAPE, KeyEvent.VK_A, KeyEvent.VK_M,
        KeyEvent.VK_S, KeyEvent.VK_Q};
//    public int[] getKeyMap(){
//        return keyMap;
//    }
    public int getUp(){
        return keyMap[0];
    }
    public int getDown(){
        return keyMap[1];
    }
    public int getRight(){
        return keyMap[2];
    }
    public int getLeft(){
        return keyMap[3];
    }
    public int getEnter(){
        return keyMap[4];
    }
    public int getAltEnter(){
        return keyMap[5];
    }
    public int getBack(){
        return keyMap[6];
    }
    public int getAltBack(){
        return keyMap[7];
    }
    public int getStart(){
        return keyMap[8];
    }
    public int getAltStart(){
        return keyMap[9];
    }
    public int getSave(){
        return keyMap[10];
    }
    public int getRitem(){
        return keyMap[11];
    }
    
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getGender(){
        return gender;
    }
    public void setGender(int gender){
        this.gender = gender;
        String[] genders = {"Male", "Female"};
        drawing = p.loadImage("./Characters/"+genders[gender]+" Character Image.png");
    }
    public int getMoney(){
        return money;
    }
    public void adjustMoney(int amount){
        this.money += amount;
    }
    public int getNumPokedex(){
        return pokemoncaught.size();
    }
    public List<String> getPokemonCaught(){
        return pokemoncaught;
    }
    public void addPokemonCaught(Pokemon pokemon){
        if (!pokemoncaught.contains(pokemon.getName())){
            pokemoncaught.add(pokemon.getName());
            addPokemonSeen(pokemon);
        }
    }
    public List<String> getPokemonSeen(){
        return pokemonseen;
    }
    public void addPokemonSeen(Pokemon pokemon){
        if (!pokemonseen.contains(pokemon.getName())){
            pokemonseen.add(pokemon.getName());
        }
    }
    public String getTimePlayedString(){
        String time =  timePlayed/60 +":";
        String string= +timePlayed%60+"";
        if (string.length() < 2){
            string = "0"+string;
        }
        return time + string;
    }
    public List<String> getBadges(){
        return badges;
    }
    public int getID(){
        return trainerID;
    }
    public List<Pokemon> getParty(){
        return party;
    }
    public void addToParty(Pokemon pokemon){
        party.add(pokemon);
        addPokemonCaught(pokemon);
    }
    public Bag getBag(){
        return bag;
    }
    
    public BufferedImage getMap() {
        return map;
    }
    
    public BufferedImage getDrawnmap() {
        return drawnmap;
    }
    
    public BufferedImage getDrawing() {
        return drawing;
    }
    
    public BufferedImage[][] getCharacters() {
        return characters;
    }
    
    public BufferedImage getCharacter() {
        return character;
    }
    
    
    public void setCharacter(BufferedImage character) {
        this.character = character;
    }
    
    
}
