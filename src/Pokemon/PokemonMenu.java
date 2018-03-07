/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Pokemon;

import Bag.Items;
import Battle.BattleMoves;
import Main.Main;
import Main.p;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 */
public class PokemonMenu extends JPanel implements Runnable, KeyListener{
    
    BufferedImage emptymenu = p.loadImage("./Graphics/Pokemon Menu/Empty Pokemon Menu.png");
    BufferedImage cancel = p.loadImage("./Graphics/Pokemon Menu/Cancel Button.png");
    BufferedImage selectedcancel = p.loadImage("./Graphics/Pokemon Menu/Cancel Button Selected.png");
    public boolean running = false, optionmenu = false, switching = false;
    int switch1= 0, switch2 = 0;
    boolean fadeIn = true, fadeOut = false;
    int cases;
    /*
    Case 0: Accessing normally through Map menu
    Case 1: Accessing through battle
    Case 2: Accessing through bag from battle
    Case 3: Accessing through bag from Map
    */
    int opacity;
    Color specialoptions = new Color(0, 0, 203);
    
    public PokemonMenu(int cases){
        this.cases = cases;
        addKeyListener(this);
    }
    
    public PokemonMenu(boolean option, int index){
        cases = 0;
        optionmenu = option;
        menuindex = index;
        addKeyListener(this);
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (optionmenu){
            if (keyCode == Main.it.getDown()){
                if (optionindex<options.size()-1){
                    optionindex++;
                }
                else{
                    optionindex = 0;
                }
            }
            else if (keyCode == Main.it.getUp()){
                if (optionindex>0){
                    optionindex--;
                }
                else{
                    optionindex = options.size()-1;
                }
            }
            else if (!fadeOut && optionmenu && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && ((optionindex == 0 && cases == 0) || (optionindex == 1 && cases == 1))){
                fadeOut();
                PokemonSummary summary = new PokemonSummary(Main.it.getParty(), menuindex, this);
                Thread thread = new Thread(summary);
                Main.w.Switch(this, summary);
                thread.start();
                repaint();
            }
            else if (!fadeOut && optionmenu && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && optionindex == 1 && cases == 0){
                switching = true;
                optionmenu = false;
                switch1 = menuindex;
                switch2 = menuindex;
            }
            else if (!fadeOut && optionmenu && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && optionindex == 0 && cases == 1 && Main.it.getParty().get(menuindex).stats[0][0] > 0){
                Main.map.getBP().setInbattle(Main.it.getParty().get(menuindex));
                fadeOut();
                Thread thread = new Thread(Main.map.getBP());
                Main.w.Switch(this, Main.map.getBP());
                thread.start();
            }
            else if (!fadeOut && optionmenu && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) ){
                System.out.println(options.get(optionindex));
                optionmenu = false;
                repaint();
            }
        }
        else{
            if(!fadeOut && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && switching){
                switch2 = menuindex;
                if (switch1 != switch2){
                    Pokemon pokemon = Main.it.getParty().get(switch1);
                    Main.it.getParty().set(switch1, Main.it.getParty().get(switch2));
                    Main.it.getParty().set(switch2, pokemon);
                }
                switch1= 0;
                switch2 = 0;
                switching = false;
            }
            else if (keyCode == Main.it.getDown()){
//                if (menuindex == Main.it.getParty().size()-1){
//                   menuindex = 0;
//                   //CANCEL BUTTON
//                }
                if (menuindex<Main.it.getParty().size()){
                    menuindex++;
                }
                else{
                    menuindex = 0;
                }
                previous = menuindex;
            }
            else if (keyCode == Main.it.getUp()){
                if (menuindex>0){
                    menuindex--;
                }
                else{
                    //CANCEL BUTTON
                    menuindex = Main.it.getParty().size();
                }
                previous = menuindex;
            }
            else if (keyCode == Main.it.getLeft()){
                if (menuindex != 0){
                    menuindex = 0;
                }
            }
            else if (keyCode == Main.it.getRight()){
                if (menuindex == 0){
                    menuindex = previous;
                }
                previous = menuindex;
            }
            else if(keyCode == KeyEvent.VK_SPACE && menuindex<Main.it.getParty().size()){
                if (Main.it.getParty().get(menuindex).stats[0][0]>0){
                    Main.it.getParty().get(menuindex).stats[0][0] = 0;
                }
                else{
                    Main.it.getParty().get(menuindex).stats[0][0] = Main.it.getParty().get(menuindex).stats[0][1];
                }
            }
            else if (!fadeOut && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && menuindex < Main.it.getParty().size() && cases == 3){
                Main.it.getBag().selectedpokemon = menuindex;
                Items.activate();
                System.out.println("activated");
                repaint();
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){}
                fadeOut();
                Thread thread = new Thread(Main.it.getBag());
                Main.w.Switch(this, Main.it.getBag());
                thread.start();
            }
            else if (!fadeOut && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && menuindex < Main.it.getParty().size() && cases == 2){
                Main.it.getBag().selectedpokemon = menuindex;
                Items.activate();
                System.out.println("activated");
                repaint();
                try{
                    Thread.sleep(500);
                }catch(InterruptedException e){}
                fadeOut();
                Thread thread = new Thread(Main.map.getBP());
                Main.map.getBP().battling = true;
                Main.w.Switch(this, Main.map.getBP());
                thread.start();
            }
            else if (!fadeOut && (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && menuindex < Main.it.getParty().size()){
                optionmenu = true;
                optionindex = 0;
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
//        if (ke.isAltDown() && keyCode == KeyEvent.VK_BACK_QUOTE){
//            specialoptions = JColorChooser.showDialog(null, "Pick A Color", specialoptions);
//            repaint();
//        }
//        else
        if (!fadeOut && (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()) && switching){
            switch1 = 0;
            switch2 = 0;
            switching = false;
            repaint();
        }
        else if (!fadeOut && optionmenu && (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack() || ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && optionindex == options.size()-1))){
            optionindex = 0;
            optionmenu = false;
            repaint();
        }
        else if (!fadeOut && cases == 0 && (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack() || ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && menuindex == Main.it.getParty().size()))){
            fadeOut();
            Thread thread = new Thread(Main.map);
            Main.w.Switch(Main.menu, Main.map);
            thread.start();
        }
        else if (!fadeOut && cases == 1 && (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack() || ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && menuindex == Main.it.getParty().size()))){
            fadeOut();
            Thread thread = new Thread(Main.map.getBP());
            Main.map.getBP().battling = true;
            Main.w.Switch(this, Main.map.getBP());
            thread.start();
        }
        else if (!fadeOut && cases == 2 && (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack() || ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())  && menuindex == Main.it.getParty().size()))){
            fadeOut();
            Thread thread = new Thread(Main.it.getBag());
            Main.w.Switch(this, Main.it.getBag());
            thread.start();
        }
    }
    
    long start = System.currentTimeMillis();
    public void run(){
        setFocusable(true);
        requestFocusInWindow();
        start = System.currentTimeMillis();
        fadeIn();
        running = true;
        menuindex = 0;
        for (int a = 0; running; a++){
            if (fadeIn){
                opacity--;
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
                if (opacity <= 0){
                    fadeIn = false;
                }
                repaint();
            }
            else if(fadeOut){
                opacity++;
                repaint();
                try{
                    Thread.sleep(1);
                }catch (InterruptedException ex){}
                if (opacity >255){
                    fadeOut = false;
                    running = false;
                }
            }
            else if (menuindex<Main.it.getParty().size() && Main.it.getParty().get(menuindex).stats[0][0]>0){
                int[][] array = {{0, 0, Main.it.getParty().get(menuindex).menuimage.getWidth()/2, Main.it.getParty().get(menuindex).menuimage.getHeight()},{Main.it.getParty().get(menuindex).menuimage.getWidth()/2, 0, Main.it.getParty().get(menuindex).menuimage.getWidth()/2, Main.it.getParty().get(menuindex).menuimage.getHeight()}};
                Main.it.getParty().get(menuindex).animatedmenuimage = Main.it.getParty().get(menuindex).menuimage.getSubimage(array[a%2][0], array[a%2][1], array[a%2][2], array[a%2][3]);
                repaint();
                try {
                    if ((double)Main.it.getParty().get(menuindex).stats[0][0]/Main.it.getParty().get(menuindex).stats[0][1]>0.5){
                        Thread.sleep(200);
                    }
                    else if ((double)Main.it.getParty().get(menuindex).stats[0][0]/Main.it.getParty().get(menuindex).stats[0][1]>0.15){
                        Thread.sleep(400);
                    }
                    else{
                        Thread.sleep(600);
                    }
                } catch (InterruptedException ex) { }
            }
            if (running){
                repaint();
            }
            Thread.yield();
        }
        System.out.println("Pokemon Menu Thread Complete");
    }
    
    int menuindex = 0;
    int previous = 1;
    BufferedImage index = p.loadImage("./Graphics/Pokemon Menu/Pokemon Index.png");
    BufferedImage selectedindex = p.loadImage("./Graphics/Pokemon Menu/Selected Pokemon Index.png");
    BufferedImage faint = p.loadImage("./Graphics/Pokemon Menu/Pokemon Faint.png");
    BufferedImage selectedfaint = p.loadImage("./Graphics/Pokemon Menu/Selected Pokemon Faint.png");
    BufferedImage healthbar = p.loadImage("./Graphics/Pokemon Menu/Health Bar.png");
    BufferedImage greenhealthbar = p.loadImage("./Graphics/Pokemon Menu/Green Health Bar.png");
    BufferedImage yellowhealthbar = p.loadImage("./Graphics/Pokemon Menu/Yellow Health Bar.png");
    BufferedImage greyhealthbar = p.loadImage("./Graphics/Pokemon Menu/Grey Health Bar.png");
    BufferedImage[][] indices = {{p.loadImage("./Graphics/Pokemon Menu/Pokemon Index 1.png"), p.loadImage("./Graphics/Pokemon Menu/Selected Pokemon Index 1.png")},{index, selectedindex},{index, selectedindex},{index, selectedindex},{index, selectedindex},{index, selectedindex}};
    BufferedImage[][] faints = {{p.loadImage("./Graphics/Pokemon Menu/Pokemon Faint 1.png"), p.loadImage("./Graphics/Pokemon Menu/Selected Pokemon Faint 1.png")},{faint, selectedfaint},{faint, selectedfaint},{faint, selectedfaint},{faint, selectedfaint},{faint, selectedfaint}};
    BufferedImage[] switchindex = {p.loadImage("./Graphics/Pokemon Menu/Pokemon Switch2.png"), p.loadImage("./Graphics/Pokemon Menu/Pokemon Switch1.png")};
    int[][][] coordinates = {{{37, 69},{40, 69}}, {{300, 40},{300, 34}}, {{300, 112},{300, 106}}, {{300, 184},{300, 178}}, {{300, 256},{300, 250}}, {{300, 328},{300, 322}}};
    int[][] pokecoordinates = {{37, 64}, {300, 30}, {300, 102}, {300, 174}, {300, 246}, {300, 318}};
    int[][] namecoordinates = {{127, 147}, {385, 77}, {385, 150}, {385, 223}, {385, 296}, {385, 369}};
    int[] fontsizes = {31, 30, 30, 30, 30, 30};
    int[][] levelcoordinates = {{143, 180}, {401, 107}, {401, 180}, {401, 252}, {401, 326}, {401, 399}};
    int[][] healthcoordinates = {{183, 227}, {646, 104}, {646, 177}, {646, 249}, {646, 323}, {646, 396}};
    int[][] healthbarcoordinates = {{130, 189}, {593, 66}, {593, 138}, {593, 210}, {593, 281}, {593, 353}};
    //katrina rocks
    int[][] gendercoordinates = {{245, 150}, {523, 80}, {523, 152}, {523, 224}, {523, 298}, {523, 371}};
    double[][]scales = {{3, 3}, {2.9, 2.9}, {2.9, 2.9}, {2.9, 2.9}, {2.9, 2.9}, {2.9, 2.9}};
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        double scale = 3;
        g2.drawImage(emptymenu, 0, 0, (int)(emptymenu.getWidth()*scale), (int)(emptymenu.getHeight()*scale), null);
        if (menuindex != Main.it.getParty().size()){
            g2.drawImage(cancel, 585, 411, (int)(cancel.getWidth()*scale), (int)(cancel.getHeight()*scale), null);
        }
        else{
            g2.drawImage(selectedcancel, 582, 405, (int)(selectedcancel.getWidth()*scale), (int)(selectedcancel.getHeight()*scale), null);
        }
        drawParty(g2);
        String display = "Choose a Pokémon or Cancel";
        if (optionmenu){
            drawOptions(g2);
            display = "Do what with this Pokémon?";
        }
        g2.setFont(p.font(30));
        g2.setColor(Color.GRAY);
        g2.drawString(display, 61, 461);
        g2.setColor(Color.BLACK);
        g2.drawString(display, 60, 460);
        Color color = p.fadeRect(g, opacity);
        g2.setColor(color);
    }
    
    public void drawParty(Graphics2D g2){
        double scale = 1.2;
        for (int a = 0; a<Main.it.getParty().size(); a++){
            scale = 1.2;
            if (switching && a == switch1){
                if (a == 0){
                    p.drawImage((Graphics)g2, switchindex[0], coordinates[switch1][1][0]+9, coordinates[switch1][1][1], 3);
                    g2.drawImage(Main.it.getParty().get(a).animatedmenuimage, pokecoordinates[switch1][0], pokecoordinates[switch1][1], (int)(Main.it.getParty().get(switch1).animatedmenuimage.getWidth()*scale), (int)(Main.it.getParty().get(switch1).animatedmenuimage.getHeight()*scale), null);
                }
                else{
                    p.drawImage((Graphics)g2, switchindex[1], coordinates[switch1][1][0]-9, coordinates[switch1][1][1]+6, 2.92);
                    g2.drawImage(Main.it.getParty().get(switch1).animatedmenuimage, pokecoordinates[switch1][0], pokecoordinates[switch1][1], (int)(Main.it.getParty().get(switch1).animatedmenuimage.getWidth()*scale), (int)(Main.it.getParty().get(switch1).animatedmenuimage.getHeight()*scale), null);
                }
            }
            else if (a!=menuindex){
                if (Main.it.getParty().get(a).stats[0][0] == 0){
                    p.drawImage((Graphics)g2, faints[a][0], coordinates[a][0][0], coordinates[a][0][1], scales[a][0]);
                }
                else{
                    g2.drawImage(indices[a][0], coordinates[a][0][0], coordinates[a][0][1], (int)(indices[a][0].getWidth()*scales[a][0]), (int)(indices[a][0].getHeight()*scales[a][0]), null);
                }
                g2.drawImage(Main.it.getParty().get(a).animatedmenuimage, pokecoordinates[a][0], pokecoordinates[a][1], (int)(Main.it.getParty().get(a).animatedmenuimage.getWidth()*scale), (int)(Main.it.getParty().get(a).animatedmenuimage.getHeight()*scale), null);
            }
            else if (a==menuindex){
                if (Main.it.getParty().get(a).stats[0][0] == 0){
                    p.drawImage((Graphics)g2, faints[a][1], coordinates[a][1][0], coordinates[a][1][1], scales[a][1]);
                }
                else{
                    g2.drawImage(indices[a][1], coordinates[a][1][0], coordinates[a][1][1], (int)(indices[a][1].getWidth()*scales[a][1]), (int)(indices[a][1].getHeight()*scales[a][0]), null);
                }
                g2.drawImage(Main.it.getParty().get(a).animatedmenuimage, pokecoordinates[a][0], pokecoordinates[a][1], (int)(Main.it.getParty().get(a).animatedmenuimage.getWidth()*scale), (int)(Main.it.getParty().get(a).animatedmenuimage.getHeight()*scale), null);
            }
//            else if (Main.it.getParty().get(a).stats[0][0] == 0){
//                p.drawImage((Graphics)g2, faints[a][0], coordinates[a][0][0], coordinates[a][0][1], scales[a][0]);
//                p.drawImage((Graphics)g2, Main.it.getParty().get(a).animatedmenuimage, pokecoordinates[a][0], pokecoordinates[a][1], scale);
//            }
//            else if (menuindex < Main.it.getParty().size()) {
//                if (Main.it.getParty().get(menuindex).stats[0][0] == 0){
//                    p.drawImage((Graphics)g2, faints[a][1], coordinates[menuindex][1][1], coordinates[menuindex][1][1], scales[a][1]);
//                }
//                else{
//                    p.drawImage((Graphics)g2, indices[menuindex][1], coordinates[menuindex][1][0], coordinates[menuindex][1][1], scales[menuindex][1]);
//                }
//                g2.drawImage(Main.it.getParty().get(menuindex).animatedmenuimage, pokecoordinates[a][0], pokecoordinates[a][1], (int)(Main.it.getParty().get(menuindex).animatedmenuimage.getWidth()*scale), (int)(Main.it.getParty().get(menuindex).animatedmenuimage.getHeight()*scale), null);
//            }
            g2.setFont(p.font2(fontsizes[a]));
            p.drawString2((Graphics)g2, Main.it.getParty().get(a).displayname, namecoordinates[a][0], namecoordinates[a][1], Color.WHITE, Color.DARK_GRAY);
            g2.setFont(p.font(28));
            p.drawString2((Graphics)g2, "Lv "+Main.it.getParty().get(a).level, levelcoordinates[a][0], levelcoordinates[a][1], Color.WHITE, Color.DARK_GRAY);
            g2.setFont(p.font2(fontsizes[a]-5));
            p.drawString2((Graphics)g2, Main.it.getParty().get(a).stats[0][0]+" / "+Main.it.getParty().get(a).stats[0][1], healthcoordinates[a][0]+(10*(3-String.valueOf(Main.it.getParty().get(a).stats[0][0]).length())), healthcoordinates[a][1], Color.WHITE, Color.DARK_GRAY);
            scale = 3;
            if (Main.it.getParty().get(a).stats[0][0]>0){
                BufferedImage health = healthbar.getSubimage(0, 0, 3, healthbar.getHeight());
                if (healthbar.getWidth()*(double)Main.it.getParty().get(a).stats[0][0]/Main.it.getParty().get(a).stats[0][1]>3){
                    health = healthbar.getSubimage(0, 0, (int)(healthbar.getWidth()*(double)Main.it.getParty().get(a).stats[0][0]/Main.it.getParty().get(a).stats[0][1]), healthbar.getHeight());
                }
                g2.drawImage(health, healthbarcoordinates[a][0],  healthbarcoordinates[a][1], (int)(health.getWidth()*scales[a][0])+1, (int)(health.getHeight()*scales[a][0])+1, null);
                if ((double)Main.it.getParty().get(a).stats[0][0]/Main.it.getParty().get(a).stats[0][1] > 0.5){
                    g2.drawImage(greenhealthbar, healthbarcoordinates[a][0],  healthbarcoordinates[a][1], (int)(greenhealthbar.getWidth()*scales[a][0])+1, (int)(greenhealthbar.getHeight()*scales[a][0])+1, null);
                }
                else if ((double)Main.it.getParty().get(a).stats[0][0]/Main.it.getParty().get(a).stats[0][1] > 0.15){
                    g2.drawImage(yellowhealthbar, healthbarcoordinates[a][0],  healthbarcoordinates[a][1], (int)(yellowhealthbar.getWidth()*scales[a][0])+1, (int)(yellowhealthbar.getHeight()*scales[a][0])+1, null);
                }
            }
            g2.drawImage(p.gender[Main.it.getParty().get(a).gender], gendercoordinates[a][0],  gendercoordinates[a][1], (int)(p.gender[Main.it.getParty().get(a).gender].getWidth()*scale), (int)(p.gender[Main.it.getParty().get(a).gender].getHeight()*scale), null);
        }    }
    
    List<String> options;
    int optionindex = 0;
    public void drawOptions(Graphics2D g){
        if (cases == 0){
            options = new ArrayList<>();
            options.add("Summary");
            for (int a = 0; a<Main.moves.hms.size(); a++){
                if (Main.it.getParty().get(menuindex).moveset.contains(Main.moves.hms.get(a))){
                    options.add(Main.moves.hms.get(a));
                }
            }
            if (Main.it.getParty().size()>1){
                options.add("Switch");
            }
            options.add("Item");
            options.add("Cancel");
        }
        else if(cases == 1){
            options = new ArrayList<>(Arrays.asList("Shift", "Summary", "Cancel"));
        }
        int size = 30;
        g.setFont(p.font(size));
        p.drawMenu(g, Main.w.getWidth()-213, Main.w.getHeight()-options.size()*(size+10)-70, 173, options.size()*(size+10)+20);
        int height = Main.w.getHeight()-options.size()*(size+10)-70;
        for (int a = 0; a<options.size(); a++){
            height+=size+10;
            g.setColor(Color.DARK_GRAY);
            g.drawString(options.get(a), Main.w.getWidth()-169, height+1);
            g.setColor(Color.BLACK);
            if (Main.moves.hms.contains(options.get(a))){
                g.setColor(specialoptions);
            }
            g.drawString(options.get(a), Main.w.getWidth()-170, height);
        }
        g.drawImage(p.menucursor, Main.w.getWidth()-205, Main.w.getHeight()-options.size()*(size+10)-54+optionindex*40, 30, 30, null);
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
            opacity = -1;
        }
    }
}
