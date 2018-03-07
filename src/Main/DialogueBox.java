/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio's Laptop
 */
public class DialogueBox {
    
    public static void drawBox(Graphics g, String str){
        int y =400;
        p.drawMenu(g, 0, y, Main.w.getWidth()-15, Main.w.getHeight()-y-30);
        p.drawString1(g, str, 50, 440, 600, 50, 30, Color.BLACK, Color.GRAY);
    }
    
}
