/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Bag;

import Main.Main;
import Main.p;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Antonio's Laptop
 */
public class Store{
    
    
    
    private List<String> items = new ArrayList<>(Arrays.asList(
            "Potion",
            "Super Potion",
            "Hyper Potion",
            "Max Potion",
            "Repel",
            "Super Repel",
            "Max Repel",
            "Ultimate Repel",
            "Poke Ball",
            "Great Ball",
            "Ultra Ball",
            "Master Ball"
    ));
    private List<Integer> prices = new ArrayList<>(Arrays.asList(
            50,
            100,
            200,
            500,
            50,
            200,
            1000,
            5000,
            50,
            200,
            1000,
            10000
    ));
    
    public List<String> getItemsList(){
        return items;
    }
    public int price (String item){
        return prices.get(items.indexOf(item));
    }
    
    BufferedImage empty = p.loadImage("./Graphics/Store/Store.png");
    BufferedImage prompt = p.loadImage("./Graphics/Store/Prompt.png");
    BufferedImage confirm = p.loadImage("./Graphics/Store/Confirm.png");
    
    public void drawStore(Graphics g){
        p.drawImage(g, empty, 0, 0, 3.225);
        if (Main.getMap().getStoreIndex() == 0){
            drawItemString(g);
            drawDescription(g);
            p.drawImage(g, p.menucursor, 300, 40+50*Main.getMap().getItemIndex(), 2.9);
        }
        else{
            drawBagItemsString(g);
            p.drawImage(g, p.menucursor, 300, 40+50*Main.getMap().getItemIndex(), 2.9);
        }
        g.setFont(p.font(35));
        String price = p.extendNumber(Main.getMap().getNumSelected()*prices.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())+"", 2);
        if (Main.getMap().getStoreIndex() == 1){
            price = p.extendNumber((int)(Main.getMap().getNumSelected()*prices.get(items.indexOf(Main.it.getBag().allitems.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())))/3)+"", 2);
        }
        if (Main.getMap().getConfirm()){
            p.drawImage(g, confirm, 0, 0, 3.225);
            if (Main.getMap().getStoreIndex() == 0){
                p.drawString1(g, items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())+", and you want "+Main.getMap().getNumSelected()+".  That will be $"+price+". Okay?", 75, 425, 450, 50, 35, Color.DARK_GRAY, Color.GRAY);
            }
            else if (Main.getMap().getStoreIndex() == 1){
                p.drawString1(g, "I can pay $"+price+". Would that be okay?", 75, 425, 450, 50, 35, Color.DARK_GRAY, Color.GRAY);
            }
            p.drawString1(g, "Yes", 587, 265, Color.DARK_GRAY, Color.GRAY);
            p.drawString1(g, "No", 587, 315, Color.DARK_GRAY, Color.GRAY);
            p.drawImage(g, p.menucursor, 540, 233+50*Main.getMap().getConfirmindex(), 2.9);
        }
        else if (Main.getMap().isItemSelected()){
            p.drawImage(g, prompt, 0, 0, 3.225);
            try{
                if (Main.it.getBag().allitems.contains(items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin()))){
                    p.drawString1(g, "In Bag:     "+Main.it.getBag().allitemcount.get(Main.it.getBag().allitems.indexOf(items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin()))), 30, 320, Color.DARK_GRAY, Color.GRAY);
                }
                else{
                    p.drawString1(g, "In Bag:     0", 30, 320, Color.DARK_GRAY, Color.GRAY);
                }
            }catch(IndexOutOfBoundsException e){
                p.drawString1(g, "In Bag:     0", 30, 320, Color.DARK_GRAY, Color.GRAY);
            }
            p.drawString1(g, "x"+p.extendNumber(Main.getMap().getNumSelected()+"", 2), 490, 295, Color.DARK_GRAY, Color.GRAY);
            if (Main.getMap().getStoreIndex() == 0){
                p.drawString1(g, "$"+price, 700+(2-price.length())*17, 295, Color.DARK_GRAY, Color.GRAY);
                p.drawString1(g, items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())+"? Certainly. How many would you like?", 75, 425, 400, 50, 35, Color.DARK_GRAY, Color.GRAY);
            }
            else if (Main.getMap().getStoreIndex() == 1){
                p.drawString1(g, "$"+price, 700+(2-price.length())*17, 295, Color.DARK_GRAY, Color.GRAY);
                p.drawString1(g, Main.it.getBag().allitems.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())+"? How many would you like to sell?", 75, 425, 400, 50, 35, Color.DARK_GRAY, Color.GRAY);
            }
        }
        p.drawString1(g, "Money", 30, 50, Color.DARK_GRAY, Color.GRAY);
        p.drawString1(g, "$"+Main.it.getMoney(), 100, 100, Color.DARK_GRAY, Color.GRAY);
        
    }
    
    public void drawItemString(Graphics g){
        g.setFont(p.font(32));
        for (int a = Main.getMap().getBegin(); (a)<items.size() && a<(Main.getMap().getBegin()+6); a++){
            p.drawString1(g, items.get(a), 340, 70+50*(a-Main.getMap().getBegin()), Color.DARK_GRAY, Color.GRAY);
            p.drawString1(g, "$"+prices.get(a), 695+(2-(""+prices.get(a)).length())*17, 70+50*(a-Main.getMap().getBegin()), Color.DARK_GRAY, Color.GRAY);
        }
        if ((Main.getMap().getBegin()+5) >= items.size()){
            p.drawString1(g, "Close", 340, 70+50*5, Color.DARK_GRAY, Color.GRAY);
        }
    }
    
    public void drawDescription(Graphics g){
        String description = "Quit Shopping";
        try{
            if (Items.allitems.contains(items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin()))){
                description = Items.itemdescriptions.get(Items.allitems.indexOf(items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())));
            }
            else if (Items.allpokeballs.contains(items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin()))){
                description = Items.pokeballdescriptions.get(Items.allpokeballs.indexOf(items.get(Main.getMap().getItemIndex()+Main.getMap().getBegin())));
            }
            
        }catch(IndexOutOfBoundsException e){}
        p.drawString1(g, description, 150, 400, 600, 40, 35, Color.WHITE, Color.GRAY);
    }
    
    public void drawBagItemsString(Graphics g){
        g.setFont(p.font(32));
        for (int a = Main.getMap().getBegin(); (a)<Main.it.getBag().allitems.size() && a<(Main.getMap().getBegin()+6); a++){
            p.drawString1(g, Main.it.getBag().allitems.get(a), 340, 70+50*(a-Main.getMap().getBegin()), Color.DARK_GRAY, Color.GRAY);
            p.drawString1(g, "x"+Main.it.getBag().allitemcount.get(a), 705+(2-("x"+Main.it.getBag().allitemcount.get(a)).length())*17, 70+50*(a-Main.getMap().getBegin()), Color.DARK_GRAY, Color.GRAY);
        }
        if ((Main.getMap().getBegin()+5) >= Main.it.getBag().allitems.size()){
            p.drawString1(g, "Close", 340, 70+50*5, Color.DARK_GRAY, Color.GRAY);
        }
    }
}
