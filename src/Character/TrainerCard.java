/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Character;

import Main.Main;
import Main.p;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 */
public class TrainerCard extends JPanel implements KeyListener, Runnable, MouseListener{
    
    Trainer character;
    boolean fadeIn = false, fadeOut = false;
    int opacity = 0;
    
    public TrainerCard(Trainer character){
        this.character = character;
        characterimage = character.drawing;
        String[] genders = {"Boy", "Girl"};
        background = p.loadImage("./Graphics/Trainer Card/"+genders[character.getGender()]+" Background.png");
        addKeyListener(this);
        addMouseListener(this);
    }
    
    @Override
    public void run(){
        fadeIn();
        setFocusable(true);
        requestFocusInWindow(true);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
//        if ((keyCode == character.getEnter() || keyCode == character.getAltEnter()) && card == 0){
//            card = 1;
//        }
//        else if (keyCode == character.getBack() && card == 1){
//            card = 0;
//        }
//        else if (((keyCode == character.getEnter() || keyCode == character.getAltEnter()) && card == 1) || (keyCode == character.getBack() && card == 0)){
//            fadeOut();
//            Thread thread = new Thread(Main.map);
//            Main.w.Switch(this, Main.map);
//            thread.start();
//        }
        if ((keyCode == character.getEnter() || keyCode == character.getAltEnter()) || keyCode == character.getBack()){
            fadeOut();
            Thread thread = new Thread(Main.map);
            Main.w.Switch(this, Main.map);
            thread.start();
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    int card = 0;
    BufferedImage[] trainercard = {p.loadImage("./Graphics/Trainer Card/Trainer Card.png"), p.loadImage("./Graphics/Trainer Card/Trainer Card Back.png")};
    BufferedImage background;
    BufferedImage characterimage;
    
    @Override
    public void paintComponent(Graphics g){
//        double scale = 3;
        p.drawImage(g, background, 0, 0, 3.4);
        p.drawImage(g, trainercard[card], 34, 20, 3.225);
        if (card == 0){
            drawCard1(g);
        }
        else{
            drawCard2(g);
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
    
    String[] badges = {"Boulder Badge", "Cascade Badge", "Thunder Badge", "Rainbow Badge", "Soul Badge", "Marsh Badge", "Volcano Badge", "Earth Badge"};
    BufferedImage[] badgeImages = {p.loadImage("./Graphics/Trainer Card/Boulder Badge.png"), p.loadImage("./Graphics/Trainer Card/Cascade Badge.png"), p.loadImage("./Graphics/Trainer Card/Thunder Badge.png"),
        p.loadImage("./Graphics/Trainer Card/Rainbow Badge.png"), p.loadImage("./Graphics/Trainer Card/Soul Badge.png"), p.loadImage("./Graphics/Trainer Card/Marsh Badge.png"),
        p.loadImage("./Graphics/Trainer Card/Volcano Badge.png"), p.loadImage("./Graphics/Trainer Card/Earth Badge.png")};
    int[][] badgecoordinates = {{121, 417}, {204, 417}, {276, 417}, {353, 417}, {431, 417}, {509, 417}, {588, 417}, {664, 417}};
    double[] badgeScales = {3.225, 3.225, 2.85, 2.85, 3.225, 3.2, 3, 2.85};
    public void drawCard1(Graphics g){
        /*
        475, 70
        103, 133
        102, 224
        105, 275
        105, 328
        121, 417
        198, 416
        276, 417
        353, 417
        431, 416
        509, 417
        586, 417
        664, 417
        598, 195
        */
        g.setFont(p.font(35));
        p.drawString2(g, "IDNo. "+character.getID(), 515, 90, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, ("Name:   "+character.getName()).toUpperCase(), 103, 153, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, ("Money").toUpperCase(), 103, 245, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, "$ "+character.getMoney(), 460+(3-("$ "+character.getMoney()).length())*17, 245, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, "POKéDEX", 103, 295, Color.DARK_GRAY, Color.LIGHT_GRAY);
        String number = character.getNumPokedex()+"";
        if ((1-number.length()) != 0){
            System.out.println(number);
        }
        p.drawString2(g, ""+number, 445+(4-number.length())*14, 295, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, "TIME", 103, 347, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawString2(g, character.getTimePlayedString(), 445+(4-(character.getTimePlayedString()).length())*14, 347, Color.DARK_GRAY, Color.LIGHT_GRAY);
        p.drawImage(g, characterimage, 575, 185, 3.225);
        for (int a = 0; a<badges.length; a++){
            if (character.getBadges().contains(badges[a])){
                p.drawImage(g, badgeImages[a], badgecoordinates[a][0], badgecoordinates[a][1], badgeScales[a]);
            }
        }
    }
    
    public void drawCard2(Graphics g){
        
    }
    
    public void fadeIn(){
        if (!fadeOut){
            fadeIn = true;
            opacity = 255;
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
            repaint();
        }
        
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        System.out.println(me.getX() +", "+me.getY());
    }
    
    @Override
    public void mouseReleased(MouseEvent me) {    }
    
    @Override
    public void mouseEntered(MouseEvent me) {    }
    
    @Override
    public void mouseExited(MouseEvent me) {    }
    
}
