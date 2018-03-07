/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Pokemon;

import Battle.BattleMoves;
import Battle.Types;
import Main.p;
import Main.Main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 */
public class PokemonSummary extends JPanel implements Runnable, KeyListener{
    
    boolean running = false, fadeIn = false, fadeOut = false, showexperience = false;
    int opacity = 0;
    
    List<Pokemon> party;
    Pokemon pokemon;
    int index = 0;
    PokemonMenu pm;
    
    public PokemonSummary(List<Pokemon>pokemon, int index, PokemonMenu panel){
        this.pokemon = pokemon.get(index);
        party = pokemon;
        this.index = index;
        pm = panel;
        addKeyListener(this);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
//        System.out.println(ke.toString());
        if (keyCode == Main.it.getRight() && page<2){ //right
            page++;
        }
        else if (keyCode == Main.it.getLeft() && page>0){ //right
            page--;
        }
        else if(keyCode == Main.it.getUp() && page != 3 && index>0){ // up
            index--;
            pokemon = party.get(index);
        }
        else if(keyCode == Main.it.getDown() && page != 3 && index<party.size()-1){ // down
            index++;
            pokemon = party.get(index);
        }
        else if(keyCode == Main.it.getUp() && page == 3 && moveindex>0){ // up
            moveindex--;
        }
        else if(keyCode == Main.it.getDown() && page == 3 && moveindex<pokemon.moveset.size()-1){ // down
            moveindex++;
        }
        else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())){ // Enter
            if (page == 2){
                page = 3;
                moveindex = 0;
            }
            else if(page == 3 && !switching){
                switch1 = moveindex;
                switching = true;
            }
            else if (page == 3 && switching){
                switch2 = moveindex;
                if (switch1 != switch2){
                    String move = pokemon.moveset.get(switch1);
                    int[] pp = pokemon.pp.get(switch1);
                    pokemon.moveset.set(switch1, pokemon.moveset.get(switch2));
                    pokemon.moveset.set(switch2, move);
                    pokemon.pp.get(switch1)[0] = pokemon.pp.get(switch2)[0];
                    pokemon.pp.get(switch1)[1] = pokemon.pp.get(switch2)[1];
                    pokemon.pp.get(switch2)[0] = pp[0];
                    pokemon.pp.get(switch2)[1] = pp[1];
                    moveindex = switch1;
                    switch1 = 0;
                    switch2 = 0;
                }
                switching = false;
            }
        }
        else if(keyCode == 109 && pokemon.getStats()[0][0]>0){
            pokemon.getStats()[0][0]--;
        }
        else if(keyCode == 107 && pokemon.getStats()[0][0]<pokemon.getStats()[0][1]){
            pokemon.getStats()[0][0]++;
        }
        else if(keyCode == KeyEvent.VK_SPACE){
            if (showexperience){
                showexperience = false;
            }
            else{
                showexperience = true;
            }
        }
        else if (keyCode == KeyEvent.VK_ESCAPE){
            if (switching){
                switching = false;
                switch1 = 0;
                switch2 = 0;
            }
            else if (page == 3){
                page = 2;
            }
            else{
                fadeOut();
                Thread thread = new Thread(pm);
                Main.w.Switch(this, pm);
                thread.start();
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
//        int keyCode = ke.getKeyCode();
    }
    
    @Override
    public void run() {
        fadeIn();
        setFocusable(true);
        requestFocusInWindow(true);
    }
    
    BufferedImage[] summaryPages = {p.loadImage("./Graphics/Pokemon Summary/Pokemon Summary Page 1.png"), p.loadImage("./Graphics/Pokemon Summary/Pokemon Summary Page 2.png"), p.loadImage("./Graphics/Pokemon Summary/Pokemon Summary Page 3.png"), p.loadImage("./Graphics/Pokemon Summary/Pokemon Summary Page 4.png")};
    BufferedImage experience = p.loadImage("./Graphics/Pokemon Summary/Experience Bar.png");
    BufferedImage redbox = p.loadImage("./Graphics/Pokemon Summary/Pokemon Summary Page 4 Selection.png");
    BufferedImage bluebox = p.loadImage("./Graphics/Pokemon Summary/Pokemon Summary Page 4 Selection Blue.png");
    int page = 0, moveindex = 0;
    int switch1 = 0, switch2 = 0;
    boolean switching = false;
    @Override
    public void paintComponent(Graphics g){
        double scale = 3.225;
        p.drawImage(g, summaryPages[page], 0, 0, scale);
        g.setFont(p.font(35));
        p.drawString2(g, pokemon.getDisplayname(), 130, 95, Color.WHITE, Color.DARK_GRAY);
        p.drawImage(g, p.gender[pokemon.getGender()], 340, 65, scale);
        if (page != 3){
            p.drawString2(g, "Lv"+pokemon.getLevel(), 13, 95, Color.WHITE, Color.DARK_GRAY);
            p.drawImage(g, pokemon.getImage(), 100, 100, 1.2);
        }
        if (page == 0){
            drawPage1(g);
        }
        else if(page ==1){
            drawPage2(g);
        }
        else if(page ==2){
            drawPage3(g);
        }
        else if(page == 3){
            drawPage4(g);
        }
        if (fadeOut){
            opacity++;
            try{
                Thread.sleep(1);
            }catch (InterruptedException ex){}
            if (opacity > 255){
                fadeOut = false;
            }
            repaint();
        }
        Color color = p.fadeRect(g, opacity);
    }
    
    public void drawPage1 (Graphics g){
        int xAlign = 540;
        p.drawString2(g, "Pokemon Info", 20, 35, Color.WHITE, Color.GRAY);
        p.drawString2(g, pokemon.getIndex()+1+"", xAlign, 99, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, pokemon.getDisplayname()+"", xAlign, 146, Color.DARK_GRAY, Color.LIGHT_GRAY);
        for (int a = 0; a<pokemon.getTypes().size(); a++){
            p.drawImage(g, Types.getTypeIcon(pokemon.getTypes().get(a)), xAlign+105*a, 166, 2.99);
        }
        p.drawString2(g, Main.it.getName()+"", xAlign, 244, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, Main.it.getID()+"", xAlign, 294, Color.DARK_GRAY, Color.LIGHT_GRAY);
        if (pokemon.getItem().equals("")){
            p.drawString2(g, "None", xAlign, 340, Color.DARK_GRAY, Color.LIGHT_GRAY);
        }
        else{
            p.drawString2(g, pokemon.getItem()+"", xAlign, 340, Color.DARK_GRAY, Color.LIGHT_GRAY);
        }
    }
    public void drawPage2 (Graphics g){
        p.drawString2(g, "Pokemon Skills", 20, 35, Color.WHITE, Color.GRAY);
        p.drawString2(g, pokemon.getStats()[0][0]+"/"+pokemon.getStats()[0][1], 678+(5-(pokemon.getStats()[0][0]+"/"+pokemon.getStats()[0][1]).length())*17, 98, Color.DARK_GRAY, Color.LIGHT_GRAY);
        for (int a = 1; a<=5; a++){
            p.drawString2(g, pokemon.getStats()[a][1]+"", 678+(5-(pokemon.getStats()[a][1]+"").length())*17, 156+42*(a-1), Color.DARK_GRAY, Color.LIGHT_GRAY);
        }
        p.drawString2(g, pokemon.getAbility(), 235, 450, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, pokemon.getAbilityDescription(), 40, 493, Color.DARK_GRAY, Color.LIGHT_GRAY);
        double scale = 3.15, ratio = ((double)pokemon.getStats()[0][0]/pokemon.getStats()[0][1])*p.healthbar.getWidth();
        if ((int)ratio>0){
            BufferedImage ophealth = p.healthbar.getSubimage(0, 0, (int)ratio, p.healthbar.getHeight());
            int x = 586, y = 107;
            double xadjust = -5*pokemon.getStats()[0][0]/((double)pokemon.getStats()[0][1]);
//            p.drawImage(g, ophealth, x, y, scale);
            g.drawImage(ophealth, x+1, y, (int)(ophealth.getWidth()*scale-xadjust), (int)(ophealth.getHeight()*scale), null);
            if (((double)pokemon.getStats()[0][0]/pokemon.getStats()[0][1]>0.5)){
                g.drawImage(p.greenhealthbar,  x, y, (int)(p.greenhealthbar.getWidth()*scale)+4, (int)(p.greenhealthbar.getHeight()*scale), null);
            }
            else if (((double)pokemon.getStats()[0][0]/pokemon.getStats()[0][1]>0.15)){
                g.drawImage(p.yellowhealthbar,  x-1, y, (int)(p.yellowhealthbar.getWidth()*scale)+5, (int)(p.yellowhealthbar.getHeight()*scale), null);
            }
        }
        p.drawString2(g, "Exp. Points", 235, 365, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, pokemon.getExp()+"", 678+(5-(pokemon.getExp()+"").length())*17, 365, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, "Next Level", 235, 407, Color.DARK_GRAY, Color.LIGHT_GRAY);
        if (pokemon.getLevel() < 100){
            p.drawString2(g, pokemon.getExpToLevel()+"", 678+(5-(pokemon.getExpToLevel()+"").length())*17, 407, Color.DARK_GRAY, Color.LIGHT_GRAY); 
        }
        ratio = (pokemon.getExp()-Main.data.experienceTotal[pokemon.getLevel()-1][pokemon.getExpType()])/(double)Main.data.experienceToNext[pokemon.getLevel()-1][pokemon.getExpType()];
        if (ratio > 0 ){
            if (showexperience){
                ratio = 1;
            }
            scale = 3.4;
            BufferedImage exp = experience.getSubimage(0, 0, (int)Math.ceil(ratio*experience.getWidth()), experience.getHeight());
            g.drawImage(exp, 541, 419, (int)(exp.getWidth()*scale), (int)(exp.getHeight()*scale), null);
        }
    }
    public void drawPage3 (Graphics g){
        p.drawString2(g, "Pokemon Move Sets", 20, 35, Color.WHITE, Color.GRAY);
        for (int a = 0; a<pokemon.getMoveset().size(); a++){
            p.drawImage(g, Types.getTypeIcon(BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(pokemon.getMoveset().get(a)))[0]), 397, 68+90*a, 2.99);
            p.drawString2(g, pokemon.getMoveset().get(a), 520, 98+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "pp", 637, 130+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, pokemon.getPp().get(a)[0]+"", 675+(2-(pokemon.getPp().get(a)[0]+"").length())*17, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "/", 713, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, pokemon.getPp().get(a)[1]+"", 730+(2-(pokemon.getPp().get(a)[1]+"").length())*17, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
        }
        for (int a = pokemon.getMoveset().size(); a<4;  a++){
            p.drawString2(g, "--", 520, 105+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "pp", 637, 130+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "--", 675, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
        }
    }
    public void drawPage4(Graphics g){
        p.drawString2(g, "Pokemon Move Sets", 20, 35, Color.WHITE, Color.GRAY);
        p.drawImage(g, pokemon.animatedmenuimage, 40, 55, 1.2);
        for (int a = 0; a<pokemon.getTypes().size(); a++){
            p.drawImage(g, Types.getTypeIcon(pokemon.getTypes().get(a)), 140+105*a, 110, 2.99);
        }
        for (int a = 0; a<pokemon.getMoveset().size(); a++){
            p.drawImage(g, Types.getTypeIcon(BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(pokemon.getMoveset().get(a)))[0]), 397, 68+90*a, 2.99);
            p.drawString2(g, pokemon.getMoveset().get(a), 520, 98+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "pp", 637, 130+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, pokemon.getPp().get(a)[0]+"", 675+(2-(pokemon.getPp().get(a)[0]+"").length())*17, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "/", 713, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, pokemon.getPp().get(a)[1]+"", 730+(2-(pokemon.getPp().get(a)[1]+"").length())*17, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
        }
        for (int a = pokemon.getMoveset().size(); a<4;  a++){
            p.drawString2(g, "--", 520, 105+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "pp", 637, 130+90*a, Color.BLACK, Color.LIGHT_GRAY);
            p.drawString2(g, "--", 675, 135+90*a, Color.BLACK, Color.LIGHT_GRAY);
        }
        if (switching){
            p.drawImage(g, bluebox, 389, 58+90*switch1, 3.225);
        }
        p.drawImage(g, redbox, 384, 58+90*moveindex, 3.225);
        String power = BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(pokemon.moveset.get(moveindex)))[3];
        p.drawString1(g, power, 230+(2-power.length())*17, 217, Color.DARK_GRAY, Color.LIGHT_GRAY);
        String accuracy = BattleMoves.movelist.get(BattleMoves.moveindex.indexOf(pokemon.moveset.get(moveindex)))[4];
        p.drawString1(g, accuracy, 230+(2-accuracy.length())*17, 262, Color.DARK_GRAY, Color.LIGHT_GRAY);
        String description = BattleMoves.movedescriptions.get(BattleMoves.moveindex.indexOf(pokemon.moveset.get(moveindex)));
//        String description = BattleMoves.movedescriptions.get(BattleMoves.moveindex.indexOf("Foresight"));
        p.drawString1(g, description, 20, 350, 340, 45, 26, Color.BLACK, Color.LIGHT_GRAY);
    }
    
    public void fadeIn(){
        if (!fadeOut){
            fadeIn = true;
            opacity = 320;
            while(fadeIn){
                opacity--;
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
                if (opacity <= 0){
                    fadeIn = false;
                }
                repaint();
            }
        }
    }
    
    public void fadeOut(){
        if (!fadeIn){
            fadeOut = true;
            opacity = 0;
        }
        
    }
    
    
}
