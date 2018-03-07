/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Pokemon;

import Battle.Types;
import Main.p;
import Main.Main;
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
 */
public class Pokedex extends JPanel implements Runnable, KeyListener{
    
    boolean fadeIn = false, fadeOut = false;
    int opacity = 0;
    int page = 0;
    int[] pagecursor = {0, 0, 0, 0};
    int[] start = {0, 0, 0, 0};
    int previous = 0;
    List<String> Alphabetical;
    List<String> TypeSorted;
    /*
    page 0: Table of Contents
    page 1: Numerical Mode (Pokemon List)
    page 2: Alphabetical Mode
    page 3: Type Mode
    page 4: Pokemon Data
    */
    
    public Pokedex(){
        
    }
    
    @Override
    public void run(){
        fadeIn();
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow(true);
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {    }
    
    @Override
    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        if (page == 0){
            if (keyCode == Main.it.getDown() && pagecursor[page] < 3){
                pagecursor[page]++;
            }
            else if(keyCode == Main.it.getUp() && pagecursor[page] > 0){
                pagecursor[page]--;
            }
            else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && pagecursor[page]==0){
                page = 1;
            }
            else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && pagecursor[page]==1){
                Alphabetical = sortAlphbetical(Main.it.getPokemonSeen());
//                p.print(Alphabetical.toArray());
                page = 2;
            }
            else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && pagecursor[page]==2){
                TypeSorted = sortTypeList(sortAlphbetical(Main.it.getPokemonSeen()));
                page = 3;
            }
            else if(((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && pagecursor[page]==3) || keyCode == Main.it.getBack()){
                fadeOut();
                Thread thread = new Thread(Main.map);
                Main.w.Switch(this, Main.map);
                thread.start();
            }
        }
        else if (page == 1){
            if (keyCode == Main.it.getDown() && pagecursor[page] == 5 && start[page] < Main.data.PokemonList.indexOf("Genesect")-8){
                start[page]++;
            }
            else if (keyCode == Main.it.getUp() && pagecursor[page] == 5 && start[page] > 0){
                start[page]--;
            }
            else if (keyCode == Main.it.getDown() && (start[page] == 0 || start[page] >= Main.data.PokemonList.indexOf("Genesect")-8) && (start[page]+pagecursor[page]) < Main.data.PokemonList.indexOf("Genesect")){
                pagecursor[page]++;
            }
            else if(keyCode == Main.it.getUp() && (start[page] == 0 || start[page] >= Main.data.PokemonList.indexOf("Genesect")-8) && (start[page]+pagecursor[page])  > 0){
                pagecursor[page]--;
            }
            else if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && Main.it.getPokemonSeen().contains(Main.data.PokemonList.get((start[page]+pagecursor[page])))){
                previous = page;
                page = 4;
            }
            else if (keyCode == Main.it.getBack()){
                page = 0;
            }
        }
        else if (page == 2){
            if (keyCode == Main.it.getDown() && pagecursor[page] == 5 && start[page] < Alphabetical.size()-9){
                start[page]++;
            }
            else if (keyCode == Main.it.getUp() && pagecursor[page] == 5 && start[page] > 0){
                start[page]--;
            }
            else if (keyCode == Main.it.getDown() && (start[page] == 0 || start[page] >= Alphabetical.size()-9) && (start[page]+pagecursor[page]) < Alphabetical.size()-1){
                pagecursor[page]++;
            }
            else if(keyCode == Main.it.getUp() && (start[page] == 0 || start[page] >= Alphabetical.size()-9) && (start[page]+pagecursor[page])  > 0){
                pagecursor[page]--;
            }
            else if (keyCode == Main.it.getBack()){
                page = 0;
            }
        }
        else if (page == 3){
            if (keyCode == Main.it.getDown() && pagecursor[page] == 5 && start[page] < TypeSorted.size()-9){
                start[page]++;
            }
            else if (keyCode == Main.it.getUp() && pagecursor[page] == 5 && start[page] > 0){
                start[page]--;
            }
            else if (keyCode == Main.it.getDown() && (start[page] == 0 || start[page] >= TypeSorted.size()-9) && (start[page]+pagecursor[page]) < TypeSorted.size()-1){
                pagecursor[page]++;
            }
            else if(keyCode == Main.it.getUp() && (start[page] == 0 || start[page] >= TypeSorted.size()-9) && (start[page]+pagecursor[page])  > 0){
                pagecursor[page]--;
            }
            else if (keyCode == Main.it.getBack()){
                page = 0;
            }
        }
        else if(page == 4){
            if((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) || keyCode == Main.it.getBack()){
                page = previous;
                previous = page;
            }
        }
        repaint();
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        
    }
    
    BufferedImage background = p.loadImage("./Graphics/Pokedex/Background.png");
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if (page!=4){
            p.drawImage(g, background, 0, 0, 3.225);
        }
        g.setFont(p.font(35));
        if (page == 0){
            drawTableOfContents(g);
        }
        else if(page == 1){
            drawPokemonList(g);
        }
        else if(page == 2){
            drawAlphabeticalList(g);
        }
        else if(page == 3){
            drawTypeList(g);
        }
        else if(page == 4){
            drawPokemonInfo(g);
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
    
    int[][][] pagecursorcoordinates = {{{35, 110}, {35, 190}, {35, 230}, {35, 310}}};
    BufferedImage[] contenticons = {p.loadImage("./Graphics/Pokedex/Numerical Icon.png"), p.loadImage("./Graphics/Pokedex/Alphabetical Icon.png"), p.loadImage("./Graphics/Pokedex/Type Icon.png"), p.loadImage("./Graphics/Pokedex/Close Icon.png")};
    public void drawTableOfContents(Graphics g){
        p.drawString2(g, "POKéDEX       TABLE OF CONTENTS", 190, 40, Color.WHITE, Color.GRAY);
        p.drawString2(g, "POKéMON LIST", 40, 100, new Color(255, 50, 0), Color.DARK_GRAY);
        p.drawString2(g, "NUMERICAL MODE", 80, 140, Color.BLACK, Color.GRAY);
        p.drawString2(g, "SEARCH", 40, 180, new Color(255, 50, 0), Color.DARK_GRAY);
        p.drawString2(g, "ALPHABETICAL MODE", 80, 220, Color.BLACK, Color.GRAY);
        p.drawString2(g, "TYPE MODE", 80, 260, Color.BLACK, Color.GRAY);
        p.drawString2(g, "CLOSE POKéDEX", 80, 340, Color.BLACK, Color.GRAY);
        p.drawString2(g, "SEEN:", 590, 120, Color.BLACK, Color.GRAY);
        p.drawString2(g, Main.it.getPokemonSeen().size()+"", 630, 160, new Color(255, 50, 0), Color.DARK_GRAY);
        p.drawString2(g, "CAUGHT:", 590, 210, Color.BLACK, Color.GRAY);
        p.drawString2(g, Main.it.getPokemonCaught().size()+"", 630, 250, new Color(255, 50, 0), Color.DARK_GRAY);
        p.drawImage(g, p.menucursor, pagecursorcoordinates[page][pagecursor[page]][0], pagecursorcoordinates[page][pagecursor[page]][1], 2.9);
        p.drawImage(g, contenticons[pagecursor[0]], 500, 300, 3);
    }
    
    BufferedImage caughtIcon = p.loadImage("./Graphics/Pokedex/Caught Icon.png");
    public void drawPokemonList(Graphics g){
        p.drawString2(g, "POKéMON LIST", 305, 40, Color.WHITE, Color.GRAY);
        for (int a = start[page]; a<(start[page]+9); a++){
            String number = (a+1)+"";
            while(number.length()<3){
                number = "0"+number;
            }
            p.drawString2(g, "No"+number, 100, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            if (Main.it.getPokemonSeen().contains(Main.data.PokemonList.get(a))){
                p.drawString2(g, p.formatName(Main.data.PokemonList.get(a)), 260, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            }
            else{
                p.drawString2(g, "-----", 260, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            }
            if (Main.it.getPokemonCaught().contains(Main.data.PokemonList.get(a))){
                p.drawImage(g, caughtIcon, 205, 60+45*(a-start[page]), 3.225);
                for (int b = 0; b<Main.data.TypeList.get(a).length; b++){
                    p.drawImage(g, Types.getTypeIcon(Main.data.TypeList.get(a)[b]), 430+100*b, 60+45*(a-start[page]), 2.99);
                }
            }
        }
        p.drawImage(g, p.menucursor, 50, 58+45*pagecursor[page], 2.9);
    }
    
    public void drawAlphabeticalList(Graphics g){
        p.drawString2(g, "ALPHABETICAL LIST", 250, 40, Color.WHITE, Color.GRAY);
        for (int a = start[page]; a<Alphabetical.size() && a<(start[page]+9); a++){
            int index = Main.data.PokemonList.indexOf(Alphabetical.get(a));
            String number = p.extendNumber((index+1)+"");
            p.drawString2(g, "No"+number, 100, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            p.drawString2(g, p.formatName(Alphabetical.get(a)), 260, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            if (Main.it.getPokemonCaught().contains(Main.data.PokemonList.get(index))){
                p.drawImage(g, caughtIcon, 205, 60+45*(a-start[page]), 3.225);
            }
            for (int b = 0; b<Main.data.TypeList.get(index).length; b++){
                p.drawImage(g, Types.getTypeIcon(Main.data.TypeList.get(index)[b]), 430+100*b, 60+45*(a-start[page]), 2.99);
            }
        }
        p.drawImage(g, p.menucursor, 50, 58+45*pagecursor[page], 2.9);
    }
    
    public List<String> sortAlphbetical(List<String>caught){
        List<String>unsorted = new ArrayList<>();
        unsorted.addAll(caught);
        for (int a = 0; a<unsorted.size(); a++){
            for (int b = a; b<unsorted.size(); b++){
                if (unsorted.get(a).compareTo(unsorted.get(b))>0){
                    String switch1 = unsorted.get(a);
                    unsorted.set(a, unsorted.get(b));
                    unsorted.set(b, switch1);
                    return sortAlphbetical(unsorted);
                }
            }
        }
        return unsorted;
    }
    
    public void drawTypeList(Graphics g){
        p.drawString2(g, "TYPE SORTED LIST", 250, 40, Color.WHITE, Color.GRAY);
        for (int a = start[page]; a<TypeSorted.size() && a<(start[page]+9); a++){
            int index = Main.data.PokemonList.indexOf(TypeSorted.get(a));
            String number = p.extendNumber((index+1)+"");
            p.drawString2(g, "No"+number, 100, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            p.drawString2(g, p.formatName(TypeSorted.get(a)), 260, 90+45*(a-start[page]), Color.BLACK, Color.GRAY);
            if (Main.it.getPokemonCaught().contains(Main.data.PokemonList.get(index))){
                p.drawImage(g, caughtIcon, 205, 60+45*(a-start[page]), 3.225);
            }
            for (int b = 0; b<Main.data.TypeList.get(index).length; b++){
                p.drawImage(g, Types.getTypeIcon(Main.data.TypeList.get(index)[b]), 430+100*b, 60+45*(a-start[page]), 2.99);
            }
        }
        p.drawImage(g, p.menucursor, 50, 58+45*pagecursor[page], 2.9);
    }
    
    public List<String> sortTypeList(List<String> caught){
        List<String>unsorted = new ArrayList<>();
        unsorted.addAll(caught);
        List<String> sorted = new ArrayList<>();
        for (int a = 0; a<Types.getMove().size(); a++){
            for (int b = 0; b<unsorted.size(); b++){
                int index = Main.data.PokemonList.indexOf(unsorted.get(b));
                if (Main.data.TypeList.get(index).length == 1 && Main.data.TypeList.get(index)[0].equalsIgnoreCase(Types.getMove().get(a))){
                    sorted.add(unsorted.get(b));
                    unsorted.remove(b);
                    b = 0;
                }
                else if (Main.data.TypeList.get(index).length == 2 && (Main.data.TypeList.get(index)[0].equalsIgnoreCase(Types.getMove().get(a)) || Main.data.TypeList.get(index)[1].equalsIgnoreCase(Types.getMove().get(a)))){
                    sorted.add(unsorted.get(b));
                    unsorted.remove(b);
                    b = 0;
                }
            }
        }
//        p.print(sorted.toArray());
        return sorted;
    }
    
    BufferedImage pokemonInfoPage = p.loadImage("./Graphics/Pokedex/Pokemon Info Page.png");
    public void drawPokemonInfo(Graphics g){
        p.drawImage(g, pokemonInfoPage, 0, 0, 3.225);
        p.drawString2(g, "POKéMON LIST", 305, 40, Color.WHITE, Color.GRAY);
        int index = Main.data.PokemonList.indexOf(Main.data.PokemonList.get(pagecursor[previous]+start[previous]));
        String number = p.extendNumber((index+1)+"");
        p.drawString2(g, "No"+number, 75, 130, Color.BLACK, Color.GRAY);
        p.drawString2(g, p.formatName(Main.data.PokemonList.get(index)), 200, 130, Color.BLACK, Color.GRAY);
        p.drawString2(g, Main.data.categories.get(index).toUpperCase(), 75, 190, Color.BLACK, Color.GRAY);
        p.drawString2(g, ("Height:      "+Main.data.height.get(index)+"m").toUpperCase(), 75, 230, Color.BLACK, Color.GRAY);
        p.drawString2(g, ("Weight:      "+Main.data.weight.get(index)+" kg").toUpperCase(), 75, 270, Color.BLACK, Color.GRAY);
        p.drawImage(g, p.loadImage("./Sprites/Battle Sprites/"+p.extendNumber(p.formatIndex(index))+".png"), 480, 70, 1.2);
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
    
    
    
}
