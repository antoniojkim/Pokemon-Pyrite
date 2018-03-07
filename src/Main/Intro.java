/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import static Main.Main.thread;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Antonio's Laptop
 */
public class Intro extends JPanel implements Runnable, KeyListener, MouseListener{
    
    boolean intro = false, fadeIn = false, fadeOut = false, silhouette = false;
    int sequence = 0, opacity = 320;
    boolean showMaple = true;
    
    @Override
    public void run(){
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        showMaple = true;
        if (showMaple){
            intro = true;
            p.delay(1000);
            fadeIn();
            opacity = 0;
            repaint();
            p.delay(2000);
            fadeOut();
            p.delay(500);
        }
        opacity = 0;
        intro = false;
        silhouette = true;
        repaint();
        p.delay(100);
        silhouette = false;
        setFocusable(true);
        requestFocusInWindow();
        fadeIn();
//        opacity = 300;
        repaint();
    }
    
    public void map(){
        Main.music.start();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        if (!intro && ke.getKeyCode() == KeyEvent.VK_ENTER){
//            Thread thread = new Thread(Main.map);
//            Main.w.Switch(Main.intro, Main.map);
//            thread.start();
//            Main.map.repaint();
//            Main.map.revalidate();
//            Main.music.stop();
            Login login = new Login();
            Thread thread = new Thread(login);
            thread.start();
        }
        else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE){
            Main.thread.interrupt();
            Login login = new Login();
            Thread thread = new Thread(login);
            thread.start();
        }
//        else{
//            System.out.println(ke.getKeyCode());
//        }
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
//    BufferedImage[] grassSprites = {p.loadImage("./Graphics/Intro/Grass Sprite 1.png"), p.loadImage("./Graphics/Intro/Grass Sprite 2.png"), p.loadImage("./Graphics/Intro/Grass Sprite 3.png")};
//    int numgs = 0;
    BufferedImage title = p.loadImage("./Graphics/Intro/Pokemon Title.png");
    BufferedImage start = p.loadImage("./Graphics/Intro/Press Enter.png");
    BufferedImage version = p.loadImage("./Graphics/Intro/Title.png");
    BufferedImage mew1 = p.loadImage("./Graphics/Intro/Mew Silhouette.jpg");
    BufferedImage mew2 = p.loadImage("./Graphics/Intro/Mew Silhouette Opposite.png");
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (intro){
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Main.w.getWidth(), Main.w.getHeight());
            g.setFont(p.font(40));
            g.setColor(Color.WHITE);
            g.drawString("A True Maple Production", 215, 200);
        }
        else if(silhouette){
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Main.w.getWidth(), Main.w.getHeight());
            p.drawImage(g, mew1, 200, 50, 0.5);
        }
        else{
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, Main.w.getWidth(), Main.w.getHeight());
            p.drawImage(g, mew2, 270, 190, 0.8);
            p.drawImage(g, version, 265, 125, 0.6);
            p.drawImage(g, title, 180, 30, 1.2);
            p.drawImage(g, start, 100, 450, 1.2);
        }
        Color color = p.fadeRect(g, opacity);
    }
    
    public void fadeIn()
    {
        if (!fadeOut)
        {
            fadeIn = true;
            opacity = 320;
            Runnable run = () -> {
                while(fadeIn){
                    opacity--;
                    p.delay(1);
                    if (opacity <= 0){
                        fadeIn = false;
                    }
                    repaint();
                }   };
            Thread thread = new Thread(run);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException ex) {            }
        }
    }
    
    public void fadeOut()
    {
        if (!fadeIn)
        {
            fadeOut = true;
            opacity = 0;
            Runnable run = () -> {
                while(fadeOut){
                    opacity++;
                    p.delay(1);
                    if (opacity > 255){
                        fadeOut = false;
                    }
                    repaint();
                }
            };
            Thread thread = new Thread(run);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException ex) {            }
        }
        repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent me) {
        
    }
    
    @Override
    public void mousePressed(MouseEvent me) {
        setFocusable(true);
        requestFocusInWindow(true);
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
