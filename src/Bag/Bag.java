/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Bag;

import Battle.BattleMoves;
import Main.Main;
import Main.p;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 */
public class Bag extends JPanel implements Runnable, KeyListener, MouseListener, Serializable{
    
    public String[] ImagePaths = {"./Graphics/Bag Menu/Bag1.png", "./Graphics/Bag Menu/Bag2.png", "./Graphics/Bag Menu/Bag3.png", "./Graphics/Bag Menu/Bag2.png"};
    
    public List<List<String>> itemslists = new ArrayList<>();
    public List<String> allitems = new ArrayList<>();
    public List<Integer> allitemcount = new ArrayList<>();
    
    public List<String>items = new ArrayList<>();
    public List<Integer>numitems = new ArrayList<>();
    
    public List<String>keyitems = new ArrayList<>();
    
    public  List<String>pokeballs = new ArrayList<>();
    public  List<Integer>numpokeballs = new ArrayList<>();
    
    public  List<String>tms = new ArrayList<>();
    
    public   List<String>hms = new ArrayList<>();
    
    public boolean fadeOut = false, fadeIn = false, running = false, bagjump = false, selecting = false, selected = false;
    public int opacity = 0;
    int bagindex = 0;
    public int selectedpokemon = 0;
    public int cases;
    
    public Bag(){
        addKeyListener(this);
        itemslists.add(items);
        itemslists.add(tms);
        itemslists.add(pokeballs);
        itemslists.add(keyitems);
        updateAllItems();
        setLayout(null);
        at = new AffineTransform();
        at.translate(bagX, bagposition);
        at.scale(3.22, 3.22);
    }
    
    public void updateAllItems(){
        allitems.clear();
        allitemcount.clear();
        allitems.addAll(items);
        allitems.addAll(pokeballs);
        allitemcount.addAll(numitems);
        allitemcount.addAll(numpokeballs);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (selected){
            if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && options.get(selectedindex).equalsIgnoreCase("Cancel")){
                selected = false;
            }
            else if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && cases == 0 && options.get(selectedindex).equalsIgnoreCase("Use")){
                selected = false;
                Items.use(itemslists.get(bagindex).get(itemsindex[bagindex]));
            }
            else if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && cases == 1 && options.get(selectedindex).equalsIgnoreCase("Use")){
                selected = false;
                Items.use(itemslists.get(bagindex).get(itemsindex[bagindex]));
            }
            else if(keyCode == Main.it.getUp()){
                if (selectedindex>0){
                    selectedindex--;
                }
            }
            else if(keyCode == Main.it.getDown()){
                if (!options.get(selectedindex).equals("Cancel")){
                    selectedindex++;
                }
            }
        }
        else{
            if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter())){
                selected = true;
                selectedindex = 0;
            }
            else if (keyCode == Main.it.getLeft()){
                bagJump();
                if (bagindex>0){
                    newbagindex = bagindex-1;
                }
                else{
                    newbagindex = 3;
                }
            }
            else if(keyCode == Main.it.getRight()){
                bagJump();
                if (bagindex<3){
                    newbagindex = bagindex+1;
                }
                else{
                    newbagindex = 0;
                }
            }
            else if(keyCode == Main.it.getUp()){
                if (itemsindex[bagindex]>0){
                    itemsindex[bagindex]--;
                    selecting = true;
                }
            }
            else if(keyCode == Main.it.getDown()){
                if (itemsindex[bagindex]<=itemslists.get(bagindex).size()-1){
                    itemsindex[bagindex]++;
                    selecting = true;
                }
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (cases == 0){
            if(keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()){
                if (selected){
                    selected = false;
                    repaint();
                }
                else{
                    fadeOut();
                    Thread thread = new Thread(Main.map);
                    Main.w.Switch(Main.it.getBag(), Main.map);
                    thread.start();
                }
            }
            else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && itemsindex[bagindex] >= itemslists.get(bagindex).size()){
                fadeOut();
                Thread thread = new Thread(Main.map);
                Main.w.Switch(Main.it.getBag(), Main.map);
                thread.start();
            }
        }
        else if(cases == 1){
            if(keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()){
                if (selected){
                    selected = false;
                    repaint();
                }
                else{
                    fadeOut();
                    Thread thread = new Thread(Main.map.getBP());
                    Main.map.getBP().battling = true;
                    Main.w.Switch(Main.it.getBag(), Main.map.getBP());
                    cases = 0;
                    thread.start();
                }
            }
            else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && itemsindex[bagindex] >= itemslists.get(bagindex).size()){
                fadeOut();
                Thread thread = new Thread(Main.map.getBP());
                Main.map.getBP().battling = true;
                Main.w.Switch(Main.it.getBag(), Main.map.getBP());
                cases = 0;
                thread.start();
            }
        }
    }
    
    public void addItem(String item, int num){
        item = p.toProperCase(item);
        if (Items.allitems.contains(item)){
            if (items.contains(item)){
                int index = items.indexOf(item);
                if (numitems.get(index)+num >999){
                    numitems.set(index, 999);
                }
                else{
                    numitems.set(index, numitems.get(index)+num);
                }
            }
            else{
                items.add(item);
                numitems.add(num);
            }
            updateAllItems();
//            System.out.println("Added "+item);
        }
        else if(Items.allpokeballs.contains(p.toProperCase(item))){
            if (pokeballs.contains(item.toLowerCase())){
                int index = pokeballs.indexOf(item);
                if (numpokeballs.get(index)+num>999){
                    numpokeballs.set(index, 999);
                }
                else{
                    numpokeballs.set(index, numpokeballs.get(index)+num);
                }
            }
            else{
                pokeballs.add(item);
                numpokeballs.add(num);
            }
            updateAllItems();
//            System.out.println("Added "+item);
        }
        else if(Items.allkeyitems.contains(item)){
            if (!keyitems.contains(item.toLowerCase())){
                allitems.add(item);
                keyitems.add(item);
            }
        }
        else if(BattleMoves.tms.contains(item)){
            if (!tms.contains(item.toLowerCase())){
                allitems.add(item);
                tms.add(item);
            }
        }
        else if(BattleMoves.hms.contains(item)){
            if (!hms.contains(item.toLowerCase())){
                allitems.add(item);
                hms.add(item);
            }
        }
//        else{
//            System.out.println(item+" not added");
//            System.out.println(Items.allitems.contains(item));
//        }
    }
    public void addItem(String item){
        if(Items.allkeyitems.contains(item.toLowerCase())){
            if (!keyitems.contains(item.toLowerCase())){
                allitems.add(item);
                keyitems.add(item);
            }
        }
        else if(BattleMoves.tms.contains(item.toLowerCase())){
            if (!tms.contains(item.toLowerCase())){
                allitems.add(item);
                tms.add(item);
            }
        }
        else if(BattleMoves.hms.contains(item.toLowerCase())){
            if (!hms.contains(item.toLowerCase())){
                allitems.add(item);
                hms.add(item);
            }
        }
    }
    
    public void discarditem(String item, int n){
        if (items.contains(item.toLowerCase())){
            int index = items.indexOf(item);
            if (numitems.get(index)==n){
                items.remove(index);
                numitems.remove(index);
            }
            else{
                numitems.set(index, numitems.get(index)-n);
            }
        }
        else if (pokeballs.contains(item.toLowerCase())){
            int index = pokeballs.indexOf(item);
            if (numpokeballs.get(index)==n){
                pokeballs.remove(index);
                numpokeballs.remove(index);
            }
            else{
                numpokeballs.set(index, numpokeballs.get(index)-n);
            }
        }
    }
    
    public void run(){
        fadeIn();
        running = true;
        System.out.println("Bag Thread Initiated");
        setFocusable(true);
        requestFocusInWindow(true);
        BufferedImage[] bags = {p.loadImage("./Graphics/Bag Menu/Bag1.png"), p.loadImage("./Graphics/Bag Menu/Bag2.png"), p.loadImage("./Graphics/Bag Menu/Bag3.png"), p.loadImage("./Graphics/Bag Menu/Bag2.png")};
        
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
            else if(bagjump){
                int time = 10, distance = 1;
                for (int b = 0; b<=10; b++){
                    bagposition-=distance;
                    at = new AffineTransform();
                    at.translate(bagX, bagposition);
                    at.scale(3.22, 3.22);
                    repaint();
                    try{
                        Thread.sleep(time);
                    }catch (InterruptedException ex){}
                }
                bagindex = newbagindex;
                for (int b = 0; b<=10; b++){
                    bagposition+=distance;
                    at = new AffineTransform();
                    at.translate(bagX, bagposition);
                    at.scale(3.22, 3.22);
                    repaint();
                    try{
                        Thread.sleep(time);
                    }catch (InterruptedException ex){}
                }
                bagjump = false;
                repaint();
            }
            else if(selecting){
                bagX = 153;
                bagposition = 215;
                for (int b = 0; b<rotate.length; b++){
                    at = new AffineTransform();
                    at.translate(bagX, bagposition);
                    at.rotate(rotate[b]);
                    at.scale(3.22, 3.22);
                    at.translate(-bags[bagindex].getWidth()/2, -bags[bagindex].getHeight()/2);
                    repaint();
                    try{
                        Thread.sleep(20);
                    }catch (InterruptedException ex){}
                }
                selecting = false;
                bagX = 50;
                bagposition = 100;
                repaint();
            }
            Thread.yield();
        }
        System.out.println("Bag Thread Complete");
    }
    
    int bagX = 50;
    int bagposition = 100;
    int newbagindex = 0;
    String[]titles = {"Items", "TMs and HMs", "Poké Balls", "Key Items"};
    int[][] titlecoordinates = {{110, 65}, {60, 65}, {75, 65}, {75, 65}};
    int rotatescale = 15;
    double[] rotate = {Math.PI/(rotatescale*2), Math.PI/rotatescale, Math.PI/(rotatescale*2), 0, -Math.PI/(rotatescale*2), -Math.PI/rotatescale, -Math.PI/(rotatescale*2), 0};
    AffineTransform at = new AffineTransform();
    @Override
    public void paintComponent(Graphics g){
        double scale = 3.22;
        Graphics2D g2 = (Graphics2D)g;
        g.drawImage(p.loadImage("./Graphics/Bag Menu/Bag.png"), 0, 0, (int)(p.loadImage("./Graphics/Bag Menu/Bag.png").getWidth()*scale), (int)(p.loadImage("./Graphics/Bag Menu/Bag.png").getHeight()*scale),  null);
        g.drawImage(p.loadImage("./Graphics/Bag Menu/Bag Shadow.png"), 50, 100, (int)(p.loadImage("./Graphics/Bag Menu/Bag Shadow.png").getWidth()*scale), (int)(p.loadImage("./Graphics/Bag Menu/Bag Shadow.png").getHeight()*scale), null);
        g2.drawImage(p.loadImage(ImagePaths[bagindex]), at, null);
        g.setFont(p.font(40));
        g.setColor(Color.DARK_GRAY);
        g.drawString(titles[bagindex], titlecoordinates[bagindex][0]+2, titlecoordinates[bagindex][1]+2);
        g.setColor(Color.WHITE);
        g.drawString(titles[bagindex], titlecoordinates[bagindex][0], titlecoordinates[bagindex][1]);
        drawItemString(g);
        if (selected){
            drawSelected(g);
        }
        else if (itemsindex[bagindex] < itemslists.get(bagindex).size() && ((bagindex == 0 && items.size()>0) || (bagindex == 2 && pokeballs.size()>0))){
            drawDescription(g);
        }
        else if(itemsindex[bagindex] >= itemslists.get(bagindex).size()){
            p.drawString2(g, "Close Bag", 150, 420, Color.WHITE, Color.GRAY);
        }
        Color color = p.fadeRect(g, opacity);
    }
    
    int[] numitemscoordinates = {0, 715, 700, 685};
    int drawnumspacing = 51;
    int[] itemsindex = {0, 0, 0, 0};
    public void drawItemString(Graphics g){
        g.setFont(p.font(32));
        int start = 0;
        if (itemslists.get(bagindex).size()<6){
            start = 0;
        }
        else if (itemsindex[bagindex]>=itemslists.get(bagindex).size()-3){
            start = itemslists.get(bagindex).size()-5;
        }
        else if (itemsindex[bagindex]>2){
            start = itemsindex[bagindex]-2;
        }
        g.drawImage(p.menucursor, 288, 37+(itemsindex[bagindex]-start)*51, (int)(p.menucursor.getWidth()*2.5), (int)(p.menucursor.getHeight()*2.5), null);
        for (int a = start; a<=Math.min(itemslists.get(bagindex).size(), start+6); a++){
            if (a == Math.min(itemslists.get(bagindex).size(), itemsindex[bagindex]+6) && itemslists.get(bagindex).size() < start+6){
                g.setColor(new Color(204, 204, 204));
                g.drawString("Cancel", 330+2, 65+drawnumspacing*(a-start)+2);
                g.setColor(Color.DARK_GRAY);
                g.drawString("Cancel", 330, 65+drawnumspacing*(a-start));
            }
            else if (a<Math.min(itemslists.get(bagindex).size(), start+6)){
                try{
                    g.setColor(new Color(204, 204, 204));
                    g.drawString(p.toProperCase(itemslists.get(bagindex).get(a)), 330+2, 65+drawnumspacing*(a-start)+2);
                    g.setColor(Color.DARK_GRAY);
                    g.drawString(p.toProperCase(itemslists.get(bagindex).get(a)), 330, 65+drawnumspacing*(a-start));
                    if (bagindex == 0 && numitems.get(a)>0){
                        g.setColor(new Color(204, 204, 204));
                        g.drawString("x", 660+2, 65+drawnumspacing*(a-start)+2);
                        g.drawString(numitems.get(a)+"", numitemscoordinates[String.valueOf(numitems.get(a)).length()]+2, 65+drawnumspacing*(a-start)+2);
                        g.setColor(Color.DARK_GRAY);
                        g.drawString("x", 660, 65+drawnumspacing*(a-start));
                        g.drawString(numitems.get(a)+"", numitemscoordinates[String.valueOf(numitems.get(a)).length()], 65+drawnumspacing*(a-start));
                    }
                    else if (bagindex == 2 && numpokeballs.get(a)>0){
                        g.setColor(new Color(204, 204, 204));
                        g.drawString("x", 660+2, 65+drawnumspacing*(a-start)+2);
                        g.drawString(numpokeballs.get(a)+"", numitemscoordinates[String.valueOf(numpokeballs.get(a)).length()]+2, 65+drawnumspacing*(a-start)+2);
                        g.setColor(Color.DARK_GRAY);
                        g.drawString("x", 660, 65+drawnumspacing*(a-start));
                        g.drawString(numpokeballs.get(a)+"", numitemscoordinates[String.valueOf(numpokeballs.get(a)).length()], 65+drawnumspacing*(a-start));
                    }
                }catch(ArrayIndexOutOfBoundsException e){}
            }
        }
    }
    
    public void drawDescription(Graphics g){
        String description = "";
        if (bagindex == 0){
            description = Items.itemdescriptions.get(Items.allitems.indexOf(itemslists.get(0).get(itemsindex[0])));
        }
        else if (bagindex == 2){
            description = Items.pokeballdescriptions.get(Items.allpokeballs.indexOf(itemslists.get(2).get(itemsindex[2])));
        }
        p.drawString1(g, description, 150, 400, 600, 40, 35, Color.WHITE, Color.GRAY);
    }
    
    int selectedindex = 0;
    List<String> options = new ArrayList(Arrays.asList("Use", "Give", "Toss", "Cancel"));
    int[][][] cursorcoordinates = {{}, {{590, 455}}, {{590, 404}, {590, 455}}, {{590, 353}, {590, 404}, {590, 455}}, {{590, 302}, {590, 353}, {590, 404}, {590, 455}}};
    public void drawSelected(Graphics g){
        options.clear();
        options.addAll(Arrays.asList("Use", "Give", "Toss", "Cancel"));
        try{
            if (cases == 0 && bagindex == 3){
                options.clear();
                options.addAll(Arrays.asList("Use", "Register", "Cancel"));
            }
            else if(cases == 1){
                if (Items.allitems.contains(itemslists.get(bagindex).get(itemsindex[bagindex])) && Arrays.asList("Medicine", "Battle items").contains((Items.itemtype.get(Items.allitems.indexOf(itemslists.get(bagindex).get(itemsindex[bagindex])))))){
                    options.clear();
                    options.addAll(Arrays.asList("Use", "Cancel"));
                }
                else if (Items.allpokeballs.contains(itemslists.get(bagindex).get(itemsindex[bagindex]))){
                    options.clear();
                    options.addAll(Arrays.asList("Use", "Cancel"));
                }
                else{
                    options.clear();
                    options.addAll(Arrays.asList("Cancel"));
                }
            }
            p.drawMenu(g, 585, 290+(4-options.size())*50, 200, 55*options.size()+(4-options.size())*4);
            p.drawMenu(g, 130, 370, 450, 140);
            p.drawString1(g, p.toProperCase(itemslists.get(bagindex).get(itemsindex[bagindex]))+" has been selected", 160, 420, 400, 40, 35, Color.BLACK, Color.GRAY);
            p.drawImage(g, p.menucursor, cursorcoordinates[options.size()][selectedindex][0], cursorcoordinates[options.size()][selectedindex][1], 2.9);
            for (int a = 0; a<options.size(); a++){
                p.drawString1(g, options.get(a), 635, 335+(4-options.size())*50+50*a, Color.BLACK, Color.GRAY);
            }
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }
    
    public void bagJump(){
        bagjump = true;
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
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println(me.getX()+",  "+me.getY());
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {
        
    }
    
    @Override
    public void mouseEntered(MouseEvent me) {
        
    }
    
    @Override
    public void mouseExited(MouseEvent me) {
        
    }
    
}
