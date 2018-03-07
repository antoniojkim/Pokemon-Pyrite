/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Character.Save;
import Character.Trainer;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Antonio's Laptop
 */
public class Login extends JPanel implements Runnable, KeyListener{
    
    int index = 0, opacity = 300;
    boolean fadeIn = false, fadeOut = false, continueExists = true, running = false, newgame = false;
    Save save, it;
    
    @Override
    public void run() {
        Main.intro.fadeOut();
        Main.w.Switch(Main.intro, this);
        revalidate();
        try{
            File file = new File("./Save/Recent.txt");
            if (!file.canRead()){
                continueExists = false;
            }
            else{
                it = Save.getSave("./Save/Recent.txt");
                if (it == null){
                    continueExists = false;
                }
            }
        }catch(Exception e){}
        fadeIn();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);
        running = true;
        for (int a = 0; running; a++){
            p.delay(300);
            repaint();
        }
        fadeOut();
        if (newgame){
            NewGame game = new NewGame();
            Thread thread = new Thread(game);
            Main.w.Switch(this, game);
            thread.start();
            game.revalidate();
        }
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
//        if (keyCode == KeyEvent.VK_ESCAPE){
//            running = false;
//            Thread thread = new Thread(Main.map);
//            Main.w.Switch(this, Main.map);
//            thread.start();
//            Main.map.repaint();
//            Main.map.revalidate();
//            Main.music.stop();
//        }
//        else
        if (continueExists){
            if (keyCode == Main.it.getUp() && index < 2){
                index++;
            }
            else if (keyCode == Main.it.getDown() && index >0){
                index--;
            }
            else if (keyCode == Main.it.getEnter()){
                if (index == 0){
                    if (it != null){
                        Main.it = new Trainer(it);
                    }
                }
            }
        }
        else{
            if (keyCode == Main.it.getDown() && index < 1){
                index++;
            }
            else if (keyCode == Main.it.getUp() && index > 0){
                index--;
            }
            else if (keyCode == KeyEvent.VK_BACK_QUOTE && ke.isAltDown()){
                Main.it = new Trainer("Antonio");
                running = false;
                Main.map.playMusic();
                Thread thread = new Thread(Main.map);
                Main.w.Switch(this, Main.map);
                Main.map.repaint();
                Main.map.revalidate();
                Main.map.setFocusable(true);
                Main.map.requestFocusInWindow();
                thread.start();
            }
            else if (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()){
                if (index == 0){
                    newgame = true;
                    running = false;
                }
                else{
                    JButton open = new JButton();
                    JFileChooser directory = new JFileChooser();
                    directory.setCurrentDirectory(new java.io.File("./Save"));
                    directory.setDialogTitle("While Save File would you like to open?");
                    directory.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Save Files", "txt");
                    directory.setFileFilter(filter);
                    if (directory.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
                        String path = directory.getSelectedFile().getAbsolutePath();
//                        System.out.println(path);
                        Save save = Save.getSave(path);
                        Main.it = new Trainer(save);
                        running = false;
                        Main.map.playMusic();
                        Thread thread = new Thread(Main.map);
                        Main.w.Switch(this, Main.map);
                        Main.map.repaint();
                        Main.map.revalidate();
                        thread.start();
                    }
                }
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    BufferedImage[] options = {p.loadImage("./Graphics/Login/Continue Option.png"), p.loadImage("./Graphics/Login/New Game Option.png"), p.loadImage("./Graphics/Login/Load Game Option.png")};
    BufferedImage[] NEoptions = {p.loadImage("./Graphics/Login/New Game Option 2.png"), p.loadImage("./Graphics/Login/Load Game Option 2.png")};
    @Override
    public void paintComponent(Graphics g){
        g.setFont(p.font(40));
        if (continueExists){
            p.drawImage(g, options[index], 0, 0, 3.225);
            if (index == 0){
                Color color = new Color(51, 153, 255);
                p.drawString1(g, "Continue", 100, 60, color, Color.GRAY);
                p.drawString1(g, "Player", 100, 110, color, Color.GRAY);
                p.drawString1(g, this.it.name, 300, 110, color, Color.GRAY);
                p.drawString1(g, "Time", 100, 160, color, Color.GRAY);
                p.drawString1(g, "0:00", 300, 160, color, Color.GRAY);
                p.drawString1(g, "Pokédex", 100, 210, color, Color.GRAY);
                p.drawString1(g, this.it.pokemoncaught.size()+"", 300, 210, color, Color.GRAY);
                p.drawString1(g, "Badges", 100, 260, color, Color.GRAY);
                p.drawString1(g, this.it.badges.size()+"", 300, 260, color, Color.GRAY);
            }
            else if (index == 1){
                
            }
            else if (index == 2){
                
            }
        }
        else{
            p.drawImage(g, NEoptions[index], 0, 0, 3.225);
            if (index == 0){
                p.drawString1(g, "New Game", 100, 65, Color.DARK_GRAY, Color.LIGHT_GRAY);
                p.drawString1(g, "Load Game", 100, 170, Color.DARK_GRAY, Color.GRAY);
            }
            else if (index == 1){
                p.drawString1(g, "New Game", 100, 65, Color.DARK_GRAY, Color.GRAY);
                p.drawString1(g, "Load Game", 100, 170, Color.DARK_GRAY, Color.LIGHT_GRAY);
            }
        }
        Color color = p.fadeRect(g, opacity);
    }
    
    public void fadeIn()
    {
        if (!fadeOut)
        {
            fadeIn = true;
            opacity = 320;
            while(fadeIn){
                opacity--;
                p.delay(1);
                if (opacity <= 0){
                    fadeIn = false;
                }
                repaint();
            }
        }
    }
    
    public void fadeOut()
    {
        if (!fadeIn)
        {
            fadeOut = true;
            opacity = 0;
            while(fadeOut){
                opacity++;
                p.delay(1);
                if (opacity > 255){
                    fadeOut = false;
                }
                repaint();
            }
        }
        repaint();
    }
}
