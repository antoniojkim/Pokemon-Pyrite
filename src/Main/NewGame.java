/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import Character.Trainer;
import Pokemon.Pokemon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Antonio's Laptop
 */
public class NewGame extends JPanel implements Runnable, KeyListener{
    
    private boolean fadeIn = false, fadeOut = false, drawing = false, instructionoption = false, showcontrols = false, genderSelect = false, inputname = false, confirmname = false, disable = false, choosepokemon = false, selectpokemon = false;
    private int opacity = 300;
    protected List<String> dialoguetext = new ArrayList<>();
    private int index = 0, gender = 0;
    private String name = "";
    JTextField namefield = p.JTextField(200, 60, 450, 40, 35, false);
    
    private String[] starters = {"Bulbasaur", "Charmander", "Squirtle"};
    BufferedImage[] starterimages = {p.loadImage("./Sprites/Battle Sprites/"+p.extendNumber(p.formatIndex(Main.data.PokemonList.indexOf(starters[0])))+".png"),
        p.loadImage("./Sprites/Battle Sprites/"+p.extendNumber(p.formatIndex(Main.data.PokemonList.indexOf(starters[1])))+".png"),
        p.loadImage("./Sprites/Battle Sprites/"+p.extendNumber(p.formatIndex(Main.data.PokemonList.indexOf(starters[2])))+".png")};
    int starterindex = 0;
    
    public void addDialogueText(String dialoguetext)
    {
        this.dialoguetext.add(dialoguetext);
        repaint();
    }
    
    @Override
    public void run() {
        Main.music.stop();
        Main.music = Main.music("./Audio/New Game Music (Kalos).wav");
        Main.music.loop(-1);
        setLayout(null);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);
        p.delay(1000);
        fadeIn(2);
        p.delay(300);
        addDialogueText("Hi there!");
        addDialogueText("My name is Professor Maple.");
        addDialogueText("I am the Pokémon professor for the Hydon Region.");
        while(dialoguetext.size()>0){
            p.delay(400);
        }
        addDialogueText("Do you wish to view the controls?");
        instructionoption = true;
        index = 0;
        while (instructionoption){
            p.delay(500);
        }
        if (index == 0){
            showcontrols = true;
            fadeOut(2);
        }
        while (showcontrols){
            p.delay(500);
            if (!showcontrols){
                fadeIn(2);
            }
        }
        addDialogueText("I've told you about myself. Tell me a little bit about you.");
        while(dialoguetext.size()>0){
            p.delay(400);
        }
        addDialogueText("Are you a boy or a girl?");
        genderSelect = true;
        index = 0;
        while (genderSelect){
            p.delay(500);
        }
        gender = index;
        namefield.setAlignmentY(TOP_ALIGNMENT);
        namefield.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent ke) {
                
            }
            
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER){
                    dialoguetext.clear();
                }
                repaint();
            }
            
            @Override
            public void keyReleased(KeyEvent ke) {
                
            }
        });
        addDialogueText("What is your name?");
        p.delay(500);
        dialoguetext.clear();
        fadeOut(1);
        inputname = true;
        namefield.setText("");
        add(namefield);
        fadeIn(1);
        while(true){
            namefield.setText("");
            namefield.requestFocusInWindow();
            addDialogueText("Enter your name using the keyboard. Press Enter to Confirm.");
            while (dialoguetext.size()>0){
                p.delay(400);
            }
            if (!namefield.getText().trim().equals("")){
                fadeOut(1);
                inputname = false;
                remove(namefield);
                confirmname = true;
                index = 0;
                fadeIn(1);
                addDialogueText("So your name is "+namefield.getText()+"?");
                while (confirmname){
                    p.delay(400);
                }
                if (index == 0){
                    name = namefield.getText();
                    break;
                }
            }
        }
        addDialogueText("As you may already know, this region is inhabited by many different Pokémon.");
        addDialogueText("You will have the opportunity to explore this region.");
        while(dialoguetext.size()>0){
            p.delay(400);
        }
        addDialogueText("To begin your journey, you need a starter Pokémon.");
        addDialogueText("You will be responsible for caring for and making this Pokémon stronger.");
        while(dialoguetext.size()>0){
            p.delay(400);
        }
        disable = true;
        addDialogueText("Which Pokémon would you like to choose?");
        p.delay(500);
        fadeOut(1);
        dialoguetext.clear();
        disable = false;
        choosepokemon = true;
        int selectedpokemon = 0;
        fadeIn(1);
        for (int a = 0; true; a++){
            starterindex = a%3;
            addDialogueText("Would you like to choose "+starters[starterindex] + "?");
            repaint();
            selectpokemon = true;
            index = 0;
            while (selectpokemon){
                p.delay(400);
            }
            if (index == 0){
                addDialogueText("So you want to choose "+starters[starterindex]+"?");
                selectpokemon = true;
                index = 0;
                while (selectpokemon){
                    p.delay(400);
                }
                if (index == 0){
                    selectedpokemon = starterindex;
                    fadeOut(1);
                    choosepokemon = false;
                    break;
                }
            }
        }
        fadeIn(1);
        addDialogueText("Now that you have selected your starting pokemon...");
        disable = true;
        addDialogueText("Go out. Catch Pokémon, fight trainers, and have fun!!");
        p.delay(3000);
        fadeOut(3);
        if (name.equalsIgnoreCase("Antonio")){
            Main.it = new Trainer("Antonio");
        }
        else if (name.equalsIgnoreCase("Katrina")){
            Main.it = new Trainer("Katrina");
        }
        else{
            Main.it = new Trainer(name, gender);
            Main.it.addToParty(new Pokemon(starters[selectedpokemon], 5));
        }
        Main.map.playMusic();
        Thread thread = new Thread(Main.map);
        Main.w.Switch(this, Main.map);
        Main.map.revalidate();
        thread.start();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
        
    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
//        System.out.println(dialoguetext.size());
        if (instructionoption || genderSelect || confirmname || selectpokemon){
            if (keyCode == KeyEvent.VK_DOWN && index < 1){
                index++;
            }
            else if (keyCode == KeyEvent.VK_UP && index > 0){
                index--;
            }
        }
        if (keyCode == KeyEvent.VK_ENTER && drawing == false){
            if (instructionoption){
                dialoguetext.remove(0);
                instructionoption = false;
            }
            else if(showcontrols && !fadeIn && !fadeOut){
                showcontrols = false;
            }
            else if (genderSelect){
                dialoguetext.remove(0);
                genderSelect = false;
            }
            else if (confirmname){
                if (dialoguetext.size()>0){
                    dialoguetext.remove(0);
                }
                confirmname = false;
            }
            else if(selectpokemon){
                dialoguetext.remove(0);
                selectpokemon = false;
            }
            else if (dialoguetext.size() > 0){
                dialoguetext.remove(0);
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    BufferedImage oak = p.loadImage("./Graphics/New Game/Professor Oak Intro.png");
    BufferedImage backdrop = p.loadImage("./Graphics/New Game/Backdrop.png");
    
    @Override
    public void paintComponent(Graphics g){
        g.setFont(p.font(35));
        if (inputname){
            p.drawImage(g, backdrop, 0, 0, 3.225);
            int y = 5;
            p.drawMenu(g, 0, y, Main.w.getWidth() - 15, 114);
            p.drawString1(g, "Your name?", 200, 50, Color.BLACK, Color.GRAY);
            if (dialoguetext.size() > 0){
                drawing = true;
                drawDialogue(g);
                drawing = false;
            }
        }
        else if (choosepokemon){
            p.drawImage(g, backdrop, 0, 0, 3.225);
            p.drawMenu(g, 125, 50, 150, 200);
            p.drawImage(g, starterimages[starterindex], 110, 50, 1.2);
            drawOptions(g, "Yes", "No");
            if (dialoguetext.size() > 0){
                drawing = true;
                drawDialogue(g);
                drawing = false;
            }
        }
        else if (!showcontrols){
            p.drawImage(g, oak, 0, 0, 3.225);
            if (instructionoption || confirmname){
                drawOptions(g, "Yes", "No");
            }
            else if (genderSelect){
                drawOptions(g, "Boy", "Girl");
            }
            if (dialoguetext.size() > 0){
                drawing = true;
                drawDialogue(g);
                drawing = false;
            }
            Color color = p.fadeRect(g, opacity);
        }
        else if (showcontrols){
            Color color = p.fadeRect(g, opacity);
            p.drawString1(g, "Arrow Keys: Move", 40, 70, Color.WHITE, Color.GRAY);
            p.drawString1(g, "D: (\"A\" button); Talk/Select        (Alt key: Enter)", 40, 120, Color.WHITE, Color.GRAY);
            p.drawString1(g, "W: (\"B\" button); Back                   (Alt key: Escape)", 40, 170, Color.WHITE, Color.GRAY);
            p.drawString1(g, "A: (\"Start\" button); Open Menu    (Alt key:  M)   ", 40, 220, Color.WHITE, Color.GRAY);
            p.drawString1(g, "S: Quick Save", 40, 270, Color.WHITE, Color.GRAY);
            //p.drawString1(g, "Q: Registered Item", 40, 320, Color.WHITE, Color.GRAY);
            p.drawString1(g, "Shift: Run", 40, 320, Color.WHITE, Color.GRAY);
            p.drawString1(g, "Ctrl: Sprint", 40, 370, Color.WHITE, Color.GRAY);
        }
    }
    
    public void drawOptions(Graphics g, String option1, String option2){
        p.drawMenu(g, 650, 270, 130, 130);
        p.drawString1(g, option1, 710, 320, Color.BLACK, Color.GRAY);
        p.drawString1(g, option2, 710, 370, Color.BLACK, Color.GRAY);
        p.drawImage(g, p.menucursor, 650, 287+50*index, 3.225);
    }
    
    public boolean drawDialogue(Graphics g)
    {
        int y = 400;
        p.drawMenu(g, 0, y, Main.w.getWidth() - 15, Main.w.getHeight() - y - 30);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = p.font(35);
        g.setFont(font);
        String[] words = dialoguetext.get(0).split(" ");
        String line = "";
        int num = 0;
        for (int a = 0; a < words.length; a++)
        {
            int width = (int) (font.getStringBounds(line + words[a], frc).getWidth());
            if (width > 700)
            {
                p.drawString1(g, line, 50, 450 + num * 40, Color.BLACK, Color.GRAY);
                num++;
                line = "";
                if (num > 1)
                {
                    for (int b = a; b < words.length; b++)
                    {
                        line += words[b] + " ";
                    }
                    if (dialoguetext.size() > 1 && !dialoguetext.get(1).startsWith(line))
                    {
                        List<String> modified = new ArrayList<>();
                        modified.add(dialoguetext.get(0));
                        modified.add(line);
                        modified.addAll(dialoguetext.subList(1, dialoguetext.size() - 1));
                        dialoguetext.clear();
                        dialoguetext.addAll(modified);
                    }
                    else if (!dialoguetext.get(dialoguetext.size() - 1).equalsIgnoreCase(line))
                    {
                        dialoguetext.add(line);
                    }
                    return true;
                }
                line = words[a] + " ";
            }
            else
            {
                line += words[a] + " ";
            }
        }
        if (!line.equals(""))
        {
            p.drawString1(g, line, 50, 450 + num * 40, Color.BLACK, Color.GRAY);
        }
        return true;
    }
    
    public void fadeIn(int speed)
    {
        if (!fadeOut)
        {
            fadeIn = true;
            opacity = 320;
            while(fadeIn){
                opacity--;
                p.delay(speed);
                if (opacity <= 0){
                    fadeIn = false;
                }
                repaint();
            }
        }
    }
    
    public void fadeOut(int speed)
    {
        if (!fadeIn)
        {
            fadeOut = true;
            opacity = 0;
            while(fadeOut){
                opacity++;
                p.delay(speed);
                if (opacity > 255){
                    fadeOut = false;
                }
                repaint();
            }
        }
        repaint();
    }
    
}
