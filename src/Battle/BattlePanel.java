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
import Pokemon.Pokemon;
import Pokemon.PokemonData;
import Pokemon.PokemonMenu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 * AND KATRINA !!!!!!!!!!!!!!!!!!!!!!!!
 * :D
 */
public class BattlePanel extends JPanel implements Runnable, KeyListener{
    
    boolean fadeIn = false, fadeOut = false, terrainanimation = false, running = false, readyToMove = true, trainerBattle = false;
    public boolean optionmenu = true, fightmenu = false, battling = false, levelup = false;
    private int opacity = 0, damageCounter = 0, pay, faintCounter=0, shakes=0;
    static String type = "Grass";
    static String lastText = "";
    private List<BattlePokemon> battlers = new ArrayList<>();
    private List<BattlePokemon> playerParty = new ArrayList<>();
    private List<BattlePokemon> opponentParty = new ArrayList<>();
    private static BattlePokemon inbattle = new BattlePokemon(Main.it.getParty().get(0));
    private static BattlePokemon opponent;
    List<List<String>> animations = new ArrayList<>();
    
    public static BattlePokemon getInbattle(){
        return inbattle;
    }
    public void setInbattle(Pokemon pokemon){
        inbattle = new BattlePokemon(pokemon);
        inbattle.setMove("");
        if (!battlers.contains(pokemon)){
            battlers.add(inbattle);
        }
        
    }
    public static BattlePokemon getOpponent(){
        return opponent;
    }
    
    public int getPay() {
        return pay;
    }
    
    public void setOpponentParty(){
        opponentParty.clear();
        for (int x=0; x<1; x++){
            //opponentParty.add (new BattlePokemon (PokemonData.PokemonList.get(p.randomint(0,500)), 3));
            opponentParty.add(new BattlePokemon (PokemonData.PokemonList.get(p.randomint(0,500)), p.randomint(Main.it.getParty().get(0).getLevel()-5,Main.it.getParty().get(0).getLevel()+5)));
        }
        opponent = opponentParty.get(0);
        trainerBattle = true;
    }
    
    public boolean isTrainerBattle() {
        return trainerBattle;
    }
    
    
    public List<BattlePokemon> getOpponentParty() {
        return opponentParty;
    }
    
    public List<BattlePokemon> getPlayerParty() {
        return playerParty;
    }
    
    public void setPlayerParty(){
        for (int x=0; x<Main.it.getParty().size(); x++){
            if (!Main.it.getParty().get(x).isFainted()){
            playerParty.add(new BattlePokemon(Main.it.getParty().get(x)));
            }
        }
    }
    
    public static void setOpponent(BattlePokemon opponent) {
        BattlePanel.opponent = opponent;
    }
    
    public BattlePanel(String type){
        trainerBattle=true;
        setPlayerParty();
        inbattle = playerParty.get(0);
        battlers.clear();
        battlers.add(inbattle);
        setOpponentParty();
        pay = 50*opponentParty.get(opponentParty.size()-1).getLevel();
        Main.it.addPokemonSeen(opponent.getPokemon());
        this.type = type;
        terrain = p.loadImage("./Graphics/Battle Panel/"+type+" Terrain.png");
        terrainimage = p.loadImage("./Graphics/Battle Panel/"+type+" Animation.png");
    }
    public BattlePanel(List<String> wild, int low, int high, String type){
        setPlayerParty();
        inbattle = playerParty.get(0);
        battlers.clear();
        battlers.add(inbattle);
        String pokemon = wild.get(p.randomint(0, wild.size()-1));
        int level = p.randomint(low, high);
        opponent= new BattlePokemon(pokemon, level);
        opponentParty.add(opponent);
        Main.it.addPokemonSeen(opponent.getPokemon());
        this.type = type;
        terrain = p.loadImage("./Graphics/Battle Panel/"+type+" Terrain.png");
        terrainimage = p.loadImage("./Graphics/Battle Panel/"+type+" Animation.png");
    }
    public BattlePanel (List<Pokemon> party, String type){
        setPlayerParty();
        inbattle = playerParty.get(0);
        battlers.clear();
        battlers.add(inbattle);
        opponentParty.clear();
        for (int x=0; x<party.size(); x++){
            opponentParty.add(new BattlePokemon (party.get(x)));
            //System.out.println(party.get(x).getName());
        }
        opponent = opponentParty.get(0);
        Main.it.addPokemonSeen(opponent.getPokemon());
        pay = 50*opponentParty.get(opponentParty.size()-1).getLevel();
        trainerBattle = true;
        this.type = type;
        terrain = p.loadImage("./Graphics/Battle Panel/"+type+" Terrain.png");
        terrainimage = p.loadImage("./Graphics/Battle Panel/"+type+" Animation.png");
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (readyToMove){
            if (optionmenu){
                if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && optionindex == 0){
                    int ppTotal =0;
                    for (int [] move : inbattle.getPp()){
                        ppTotal+= move[0];
                    }
                    if (ppTotal>0){
                        optionmenu = false;
                        fightmenu = true;
                    }
                    else{
                        inbattle.setMove("Struggle");
                        Turn.takeTurn(inbattle, opponent, animations);
                    }
                }
                else if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && optionindex == 1){
                    fadeOut();
                    Thread thread = new Thread(Main.it.getBag());
                    Main.w.Switch(Main.map.getBP(), Main.it.getBag());
                    Main.it.getBag().cases = 1;
                    thread.start();
                }
                else if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && optionindex == 2){
                    fadeOut();
                    PokemonMenu pm = new PokemonMenu(1);
                    Thread thread = new Thread(pm);
                    Main.w.Switch(Main.map.getBP(), pm);
                    thread.start();
                }
                else if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && optionindex == 3){
                    fadeOut();
                    Main.map.playMusic();
                    Thread thread = new Thread(Main.map);
                    Main.w.Switch(Main.map.getBP(), Main.map);
                    Main.map.clear();
                    thread.start();
                }
                else if (keyCode == Main.it.getUp()){
                    if (optionindex == 2){
                        optionindex = 0;
                    }
                    else if (optionindex == 3){
                        optionindex = 1;
                    }
                }
                else if (keyCode == Main.it.getDown()){
                    if (optionindex == 0){
                        optionindex = 2;
                    }
                    else if (optionindex == 1){
                        optionindex = 3;
                    }
                }
                else if (keyCode == Main.it.getLeft()){
                    if (optionindex == 1){
                        optionindex = 0;
                    }
                    else if (optionindex == 3){
                        optionindex = 2;
                    }
                }
                else if (keyCode == Main.it.getRight()){
                    if (optionindex == 0){
                        optionindex = 1;
                    }
                    else if (optionindex == 2){
                        optionindex = 3;
                    }
                }
            }
            else if (fightmenu){
                if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())){
                    if (inbattle.getPp().get(fightindex)[0]>0){
                        inbattle.setMove(inbattle.getMoveset().get(fightindex));
                        fightmenu=false;
                        optionmenu=true;
                        Turn.takeTurn(inbattle, opponent, animations);
                        readyToMove = false;
                        p.delay(200);
                        repaint();
                    }
                }
                else if (keyCode == Main.it.getUp()){
                    if (fightindex == 2){
                        fightindex = 0;
                    }
                    else if (fightindex == 3 && inbattle.getMoveset().size()>1){
                        fightindex = 1;
                    }
                }
                else if (keyCode == Main.it.getDown()){
                    if (fightindex == 0 && inbattle.getMoveset().size()>2){
                        fightindex = 2;
                    }
                    else if (fightindex == 1 && inbattle.getMoveset().size()>3){
                        fightindex = 3;
                    }
                }
                else if (keyCode == Main.it.getLeft()){
                    if (fightindex == 1){
                        fightindex = 0;
                    }
                    else if (fightindex == 3 && inbattle.getMoveset().size()>2){
                        fightindex = 2;
                    }
                }
                else if (keyCode == Main.it.getRight()){
                    if (fightindex == 0 && inbattle.getMoveset().size()>1){
                        fightindex = 1;
                    }
                    else if (fightindex == 2 && inbattle.getMoveset().size()>3){
                        fightindex = 3;
                    }
                }
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (battling && (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack() && fightmenu)){
            fightmenu = false;
            optionmenu = true;
            repaint();
        }
//        else if(keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack() ){
//            fadeOut();
//            Thread thread = new Thread(Main.map);
//            Main.w.Switch(this, Main.map);
//            battling = false;
//            Main.map.clear();
//            thread.start();
//        }
    }
    
    @Override
    public void run(){
        fadeIn();
        
        if (!battling){
            terrainanimation = true;
            battling = true;
        }
        else{
            terrainanimation = false;
        }
        running = true;
        setFocusable(true);
        requestFocusInWindow(true);
        for (int a = 0; running; a++){
            if (fadeIn){
                opacity--;
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
                if (opacity <= 0){
                    fadeIn = false;
                    a = 0;
                }
                repaint();
            }
            else if(fadeOut){
                opacity++;
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
                if (opacity >255){
                    fadeOut = false;
                    running = false;
                }
                repaint();
            }
            if (!fadeIn && Main.items.allpokeballs.contains(inbattle.getMove())){
                Turn.takeTurn(inbattle, opponent, animations);
            }
            if (terrainanimation && -terraincoordinate[0]<(terrainimage.getWidth()/2)){
                terraincoordinate[0]-=3;
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
            }
            else if (terrainanimation){
                terraincoordinate[0]-=3;
                if (a%2 == 0){
                    terraincoordinate[1]++;
                }
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
            }
            if (running){
                
                if (inbattle.getMultiTurnCount()>0 && !opponent.isFainted()){
                    Turn.takeTurn(inbattle, opponent, animations);
                }
                if (damageCounter>0){
                    repaint();
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e){}
                }
                else if (!animations.isEmpty() && animations.get(0).get(0).equals("3")){
                    int increment= (int)Math.ceil((double)getTarget(animations.get(0).get(1)).getStats()[0][1]/50.0);
                    repaint();
                    try {
                        Thread.sleep(120/increment);
                    } catch (InterruptedException e){}
                }
                else if (!animations.isEmpty() && animations.get(0).get(0).equals("5")){
                    int increment = (int)Math.ceil(inbattle.getExp()/120.0);
                    repaint();
                    try {
                        Thread.sleep(80/increment);
                    } catch (InterruptedException e){}
                }
                else if (!animations.isEmpty()){
                    repaint();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){}
                }
                else if (!fadeIn && !fadeOut && !terrainanimation){
                    try{
                        Thread.sleep(8);
                    }catch(InterruptedException e){}
                }
                else{
//                    p.delay(400);
                    Thread.yield();
                }
                repaint();
            }
        }
    }
    
    static BufferedImage terrain = p.loadImage("./Graphics/Battle Panel/"+type+" Terrain.png");
    static BufferedImage base = p.loadImage("./Graphics/Battle Panel/Base Menu Bar.png");
    BufferedImage options = p.loadImage("./Graphics/Battle Panel/Options Menu.png");
    BufferedImage terrainimage = p.loadImage("./Graphics/Battle Panel/"+type+" Animation.png");
    BufferedImage pokemoninfo = p.loadImage("./Graphics/Battle Panel/Pokemon Info.png");
    BufferedImage opponentinfo = p.loadImage("./Graphics/Battle Panel/Opponent Info.png");
    BufferedImage[] gender = {p.loadImage("./Graphics/Pokemon Menu/Gender Male.png"), p.loadImage("./Graphics/Pokemon Menu/Gender Female.png"), p.loadImage("./Graphics/Pokemon Menu/Empty.png")};
    BufferedImage[] nvStatuses = {p.loadImage("./Graphics/Type Icons/Burn Status.png"),p.loadImage("./Graphics/Type Icons/Frozen Status.png"), p.loadImage("./Graphics/Type Icons/Paralysis Status.png"),p.loadImage("./Graphics/Type Icons/Poison Status.png"), p.loadImage("./Graphics/Type Icons/Sleep Status.png")};
    BufferedImage expbar = p.loadImage("./Graphics/Battle Panel/Experience Bar.png");
    BufferedImage pokeballSprite = p.loadImage("./Graphics/Pokeballs/Poke Ball.png");
    
    int optionindex = 0;
    int fightindex = 0;
    int[][] optioncursorcoordinates = {{416, 393}, {600, 393}, {416, 448}, {600, 448}};
    int[][] movecoordinates = {{25, 393}, {250, 393}, {25, 448}, {250, 448}};
    
//Drawing the battlepanel
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        double scale = 3.3225;
        g.drawImage(terrain, 0, -15, (int)(terrain.getWidth()*scale), (int)(terrain.getHeight()*scale), null);
        
        if (damageCounter==0 && faintCounter==0 ){
            pokemonSprites (g);
        }
        
        if (terrainanimation || fadeIn){
            if (terrainanimation){
                terrainAnimation(g);
            }
            g.drawImage(base, 0, -15+(int)(terrain.getHeight()*scale), (int)(base.getWidth()*scale), (int)(base.getHeight()*scale), null);
        }
        
        else{
            if (!animations.isEmpty()){
                animationManager(g);
            }
            else if (optionmenu){
                optionmenu(g);
            }
            else if(fightmenu){
                fightmenu(g);
            }
            pokemonInfo(g);
        }
        Color color = p.fadeRect(g, opacity);
        if (animations.isEmpty()){
            readyToMove = true;
        }
    }
    
    public void pokemonSprites (Graphics g){
        double scale = 1.4;
        if (inbattle.isDrawable()){
            g.drawImage(inbattle.getBackimage(), 42, 130, (int)(inbattle.getBackimage().getWidth()*scale), (int)(inbattle.getBackimage().getHeight()*scale), null);
        }
        scale = 1.2;
        if (shakes>0){
            g.drawImage(pokeballSprite, 553, 158, (int)(pokeballSprite.getWidth()*scale), (int)(pokeballSprite.getHeight()*scale), null);
        }
        else if (opponent.isDrawable()){
            g.drawImage(opponent.getImage(), 490, 25, (int)(opponent.getImage().getWidth()*scale), (int)(opponent.getImage().getHeight()*scale), null);
        }
        battletext(g, lastText);
    }
    
    public void pokemonSprites (Graphics g, BattlePokemon transparent){
        
        double scale = 1.4;
        if (!transparent.equals(inbattle)){
            g.drawImage(inbattle.getBackimage(), 42, 130, (int)(inbattle.getBackimage().getWidth()*scale), (int)(inbattle.getBackimage().getHeight()*scale), null);
        }
        scale = 1.2;
        if (!transparent.equals(opponent)){
            g.drawImage(opponent.getImage(), 490, 25, (int)(opponent.getImage().getWidth()*scale), (int)(opponent.getImage().getHeight()*scale), null);
        }
        battletext(g, lastText);
    }
    
    public void pokemonInfo(Graphics g){
        double scale = 3.4;
        g.setFont(p.font(33));
        g.drawImage(pokemoninfo, 420, 232, (int)(pokemoninfo.getWidth()*scale), (int)(pokemoninfo.getHeight()*scale), null);
        p.drawString1(g, inbattle.getDisplayname(), 472, 270, Color.BLACK, Color.GRAY);
        p.drawString1(g, "Lv"+inbattle.getLevel(), 680, 270, Color.BLACK, Color.GRAY);
        String statdisplay = inbattle.getStats()[0][0] + "/" + inbattle.getStats()[0][1];
        p.drawString1(g, statdisplay, 748 - p.stringWidth(statdisplay, p.font(33)), 332, Color.BLACK, Color.GRAY);
        g.drawImage(gender[inbattle.getGender()], 465 + p.stringWidth(inbattle.getDisplayname(), p.font(33)), 240, (int)(gender[inbattle.getGender()].getWidth()*scale), (int)(gender[inbattle.getGender()].getHeight()*scale), null);
        g.drawImage(p.greyhealthbar, 584, 289, (int)(p.greyhealthbar.getWidth()*scale)-1, (int)(p.greyhealthbar.getHeight()*scale), null);
        if ((int)(Math.ceil(((double)inbattle.getStats()[0][0]/inbattle.getStats()[0][1])*p.healthbar.getWidth()))>0){
            scale = 3.35;
            BufferedImage health = p.healthbar.getSubimage(0, 0, (int)(Math.ceil(((double)inbattle.getStats()[0][0]/inbattle.getStats()[0][1])*p.healthbar.getWidth())), p.healthbar.getHeight());
            g.drawImage(health, 577, 286, (int)(health.getWidth()*scale), (int)(health.getHeight()*scale), null);
            if (((double)inbattle.getStats()[0][0]/inbattle.getStats()[0][1]>0.5)){
                p.drawImage(g, p.greenhealthbar, 577, 286, scale);
            }
            else if (((double)inbattle.getStats()[0][0]/inbattle.getStats()[0][1]>0.15)){
                p.drawImage(g, p.yellowhealthbar, 577, 286, scale);
            }
        }
        if (inbattle.getLevel()<100){
            double ratio = (inbattle.getPokemon().getExp()-Main.data.experienceTotal[inbattle.getLevel()-1][inbattle.getPokemon().getExpType()])/(double)Main.data.experienceToNext[inbattle.getLevel()-1][inbattle.getPokemon().getExpType()];
            scale = 2.5;
            if (ratio>1){
                ratio=1;
            }
            g.drawImage (expbar, 528, 343, (int)(expbar.getWidth()*ratio*scale), (int)(expbar.getHeight()*scale), null);
        }
        scale = 3.4;
        
        g.drawImage(opponentinfo, 10, 32, (int)(opponentinfo.getWidth()*scale), (int)(opponentinfo.getHeight()*scale), null);
        p.drawString1(g, opponent.getDisplayname(),30, 70, Color.BLACK, Color.GRAY);
        p.drawString1(g, "Lv"+opponent.getLevel(), 238, 70, Color.BLACK, Color.GRAY);
        g.drawImage(gender[opponent.getGender()], 18 + p.stringWidth(opponent.getDisplayname(), p.font(33)), 40, (int)(gender[opponent.getGender()].getWidth()*scale), (int)(gender[opponent.getGender()].getHeight()*scale), null);
        g.drawImage(p.greyhealthbar, 144, 89, (int)(p.greyhealthbar.getWidth()*scale)-1, (int)(p.greyhealthbar.getHeight()*scale), null);
        if ((int)(Math.ceil(((double)opponent.getStats()[0][0]/opponent.getStats()[0][1])*p.healthbar.getWidth()))>0){
            BufferedImage ophealth = p.healthbar.getSubimage(0, 0, (int)(Math.ceil(((double)opponent.getStats()[0][0]/opponent.getStats()[0][1])*p.healthbar.getWidth())), p.healthbar.getHeight());
            p.drawImage(g, ophealth, 137, 86, scale);
            if (((double)opponent.getStats()[0][0]/opponent.getStats()[0][1]>0.5)){
                p.drawImage(g, p.greenhealthbar, 137, 86, scale);
            }
            else if (((double)opponent.getStats()[0][0]/opponent.getStats()[0][1]>0.15)){
                p.drawImage(g, p.yellowhealthbar, 137, 86, scale);
            }
        }
        if (inbattle.isStatusIcon()){
            statusDraw(g, inbattle);
        }
        if (opponent.isStatusIcon()){
            statusDraw(g, opponent);
        }
    }
    
    public void statusDraw(Graphics g, BattlePokemon poke){
        double scale = 3;
        if (poke.equals(inbattle)&&inbattle.getNonVolatileStatus()!=-1){
            p.drawImage(g, nvStatuses[inbattle.getNonVolatileStatus()], 464, 283, scale);
        }
        else if (poke.equals(opponent) && opponent.getNonVolatileStatus()!=-1){
            p.drawImage(g, nvStatuses[opponent.getNonVolatileStatus()], 22, 82, scale);
        }
    }
    
    public void optionmenu(Graphics g){
        double scale = 3.3225;
        g.drawImage(base, 0, -15+(int)(terrain.getHeight()*scale), (int)(base.getWidth()*scale), (int)(base.getHeight()*scale), null);
        g.drawImage(options, Main.w.getWidth()-(int)(options.getWidth()*scale), -15+(int)(terrain.getHeight()*scale), (int)(options.getWidth()*scale), (int)(options.getHeight()*scale), null);
        scale = 2.8;
        g.drawImage(p.menucursor, optioncursorcoordinates[optionindex][0], optioncursorcoordinates[optionindex][1], (int)(p.menucursor.getWidth()*scale), (int)(p.menucursor.getHeight()*scale), null);
        scale = 3.4;
        g.setFont(p.font(39));
        p.drawString2(g, "What will", 40, 40+(int)(terrain.getHeight()*scale), Color.WHITE, Color.DARK_GRAY);
        p.drawString2(g, inbattle.getDisplayname()+" do?", 40, 90+(int)(terrain.getHeight()*scale), Color.WHITE, Color.DARK_GRAY);
        p.drawString1(g, "Fight", optioncursorcoordinates[0][0]+45, optioncursorcoordinates[0][1]+32, Color.BLACK, Color.GRAY);
        p.drawString1(g, "Bag", optioncursorcoordinates[1][0]+45, optioncursorcoordinates[1][1]+32, Color.BLACK, Color.GRAY);
        p.drawString1(g, "Pokemon", optioncursorcoordinates[2][0]+45, optioncursorcoordinates[2][1]+32, Color.BLACK, Color.GRAY);
        p.drawString1(g, "Run", optioncursorcoordinates[3][0]+45, optioncursorcoordinates[3][1]+32, Color.BLACK, Color.GRAY);
    }
    
    public static void battletext (Graphics g, String text){
        double scale = 3.3225;
        g.drawImage(base, 0, -15+(int)(terrain.getHeight()*scale), (int)(Main.w.getWidth()), (int)(base.getHeight()*scale), null);
        p.drawString1(g, text, 40, 40+(int)(terrain.getHeight()*scale), Main.w.getWidth()-80, 2, 35, Color.WHITE, Color.GRAY);
    }
    
    public void fightmenu(Graphics g){
        double scale = 3.3225;
        int ratio = 135;
        g.drawImage(options, 0, -15+(int)(terrain.getHeight()*scale), (int)(options.getWidth()*scale)+ratio, (int)(options.getHeight()*scale), null);
        g.drawImage(options, (int)(options.getWidth()*scale)+ratio, -15+(int)(terrain.getHeight()*scale), (int)(options.getWidth()*scale)-ratio, (int)(options.getHeight()*scale), null);
        g.setFont(p.font(37));
        for (int a = 0; a<inbattle.getMoveset().size(); a++){
            p.drawString1(g, inbattle.getMoveset().get(a), movecoordinates[a][0]+45, movecoordinates[a][1]+32, Color.BLACK, Color.GRAY);
        }
        for (int a = inbattle.getMoveset().size(); a<4; a++){
            p.drawString1(g, "--", movecoordinates[a][0]+45, movecoordinates[a][1]+32, Color.BLACK, Color.GRAY);
        }
        scale = 2.8;
        g.drawImage(p.menucursor, movecoordinates[fightindex][0], movecoordinates[fightindex][1], (int)(p.menucursor.getWidth()*scale), (int)(p.menucursor.getHeight()*scale), null);
        p.drawString1(g, "PP", 555, 420, Color.BLACK, Color.GRAY);
        p.drawString1(g, inbattle.getPp().get(fightindex)[0]+"/"+inbattle.getPp().get(fightindex)[1], 675, 420, Color.BLACK, Color.GRAY);
        p.drawString1(g, "Type/   "+BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(inbattle.getMoveset().get(fightindex)))[0], 555, 470, Color.BLACK, Color.GRAY);
    }
    
    public void distribute(int[] EVYield){
        int experience = EVYield[0];
        int[] EV = {EVYield[1], EVYield[2], EVYield[3], EVYield[4], EVYield[5], EVYield[6]};
        for (int a = 0; a<battlers.size(); a++){
            battlers.get(a).adjustEndExp((int)Math.ceil(experience/battlers.size()));
            for (int b = 1; b<EVYield.length; b++){
                battlers.get(a).getPokemon().incEv(EV);
            }
        }
    }
    
    public void adjustHealth(){
        for (int a = 0; a<battlers.size(); a++){
            int[][] stats = battlers.get(a).getStats();
            stats[0][0] = battlers.get(a).getStats()[0][0];
            battlers.get(a).getPokemon().setStats(stats);
        }
    }
    
//Animations
    
    int[] terraincoordinate = {0, -15+(int)(terrain.getHeight()*3.3225)-(int)(terrainimage.getHeight()*3)};
    public void terrainAnimation(Graphics g){
        double scale = 3;
        g.drawImage(terrainimage, terraincoordinate[0], terraincoordinate[1], (int)(terrainimage.getWidth()*scale), (int)(terrainimage.getHeight()*scale), null);
        if (terraincoordinate[1] > (int)(terrain.getHeight()*scale)){
            terrainanimation = false;
            addKeyListener(this);
        }
    }
    
    public void fadeIn(){
        if (!fadeOut){
            fadeIn = true;
            opacity = 320;
        }
    }
    
    public void fadeOut(){
        if (!fadeIn){
            fadeOut = true;
            opacity = 0;
        }
    }
    
    public void animationManager(Graphics g){
        BattlePokemon target;
        int animationType = Integer.parseInt(animations.get(0).get(0));
        //text display
        if (animationType==0){
            lastText = animations.get(0).get(1);
            battletext(g, lastText);
            p.delay(50);
            animations.remove(0);
            
        }
        //damage animations - flashing sprite
        else if (animationType==1){
            if (damageCounter==0){
                damageCounter = 8;
                damageAnimation(g, getTarget(animations.get(0).get(1)));
            }
            else if (damageCounter>0){
                damageAnimation (g, getTarget(animations.get(0).get(1)));
            }
            if (damageCounter==0){
                animations.remove(0);
            }
        }
        //faint animation
        else if (animationType==2){
            if (faintCounter==0){
                faintAnimation(g, getTarget(animations.get(0).get(1)));
                faintCounter=2;
            }
            else if (faintCounter>0){
                pokemonSprites(g, getTarget(animations.get(0).get(1)));
                faintCounter--;
                if (faintCounter==0){
                    animations.remove(0);
                }
            }
            
        }
        //health bar animation
        else if (animationType == 3){ // need to add new animation type
            target = getTarget(animations.get(0).get(1));
            if (target.getEndHealth() < target.getStats()[0][0]){
                target.adjustHealth(-1);
            }
            else if (target.getEndHealth() > target.getStats()[0][0]){
                target.adjustHealth(1);
            }
            else if (target.getEndHealth()==target.getStats()[0][0]){
                animations.remove(0);
            }
        }
        //status icons
        else if (animationType==4){
            getTarget(animations.get(0).get(1)).setStatusIcon(true);
            animations.remove(0);
        }
        //increment exp bar
        else if (animationType == 5){
            if (inbattle.getEndExp() > inbattle.getExp() && inbattle.getLevel()<100){
                inbattle.adjustExp(1); //ups exp by 1, recalculates exp needed to next level
                pokemonSprites(g, opponent);
                if (inbattle.getExpToLevel()==0 ){
                    Main.sfx = Main.music("./Audio/SE/expfull.wav");
                    Main.sfx.start();
                    inbattle.levelUp();
                    levelup=true;
                }
            }
            
            else{
                animations.remove(0);
            }
        }
        //checks to end off battle
        else if (animationType == 6){
            checkWin();   
            animations.remove(0);
        }
        //pokemon sprite -> pokeball
        else if (animationType==7){
            shakes = Integer.parseInt(animations.get(0).get(2));
            pokeballSprite = p.loadImage("./Graphics/Pokeballs/" + animations.get(0).get(1) + ".png" );
            animations.remove(0);
        }
        //sound fx
        else if (animationType==8){
            String fxpath = animations.get(0).get(1);
            Main.sfx = Main.music(animations.get(0).get(1));
            Main.sfx.start();
            p.delay(500);
            if (fxpath.equals("./Audio/SE/ballshake.wav")){
                shakes--;
            }
            animations.remove(0);
        }
        
    }
    
    public void damageAnimation(Graphics g, BattlePokemon pokemon){
        
        if (damageCounter%2==0){
            pokemonSprites(g);
        }
        else{
            pokemonSprites(g, pokemon);
        }
        if (damageCounter==7){
            Main.sfx = Main.music("./Audio/SE/Battle.WAV");
            Main.sfx.start();
        }
        damageCounter--;
    }
    
    public void faintAnimation(Graphics g, BattlePokemon pokemon){
        Main.sfx = Main.music(pokemon.getCry());
        Main.sfx.start();
        p.delay(1000);
        Main.sfx = Main.music("./Audio/SE/faint.wav");
        Main.sfx.start();
        pokemon.setDrawable(false);
    }
    
    public void checkWin(){
        if (Main.map.getBP().getPlayerParty().isEmpty()){
            loseBattle();
        }
        else if (Main.map.getBP().getOpponentParty().isEmpty()){
            winBattle();
        }
        else {
            Main.map.getBP().setOpponent(Main.map.getBP().getOpponentParty().get(0));
        }
    }
    
    public BattlePokemon getTarget (String name){
        if (name.equals("opponent")){
            return opponent;
        }
        else{
            return inbattle;
        }
    }
    
    public void forceSwitch(){
        Main.map.getBP().fadeOut();
        PokemonMenu pm = new PokemonMenu(1);
        Thread thread = new Thread(pm);
        Main.w.Switch(Main.map.getBP(), pm);
        thread.start();
    }
    
    public void loseBattle(){
        running = false;
        for (int x=0; x<Main.it.getParty().size(); x++){
            Main.it.getParty().get(x).pokeCentreHeal();
        }
        Main.getMap().setMap(11,5,18,15);
        
        p.delay(1000);
        Main.map.getBP().fadeOut();
        Main.map.playMusic();
        Thread thread = new Thread (Main.map);
        Main.map.clear();
        Main.w.Switch(Main.map.getBP(), Main.map);
        thread.start();
    }
    
    public void winBattle(){
        running = false;
        p.delay(1000);
        Main.it.adjustMoney(pay);
        Main.map.getBP().fadeOut();
        Main.map.playMusic();
        Thread thread = new Thread (Main.map);
        Main.map.clear();
        Main.w.Switch(Main.map.getBP(), Main.map);
        thread.start();
    }
    
    
}