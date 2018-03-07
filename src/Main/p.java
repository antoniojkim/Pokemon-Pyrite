
package Main;


import Battle.BattleMoves;
import Pokemon.Pokemon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class p {
    
    public static void main (String[] args) throws IOException{
        Pokemon pokemon = new Pokemon("Snorlax", 60);
        print(pokemon.getTmset().toArray());
        System.out.println("\n"+BattleMoves.tms.contains("Surf"));
//        System.out.println(pokemon.getTmset().contains("Surf"));
    }
    
    public static  Font Heading1 = new Font ("Calibri", Font.PLAIN, 40),
            Heading2 = new Font ("Calibri", Font.PLAIN, 30),
            Heading3 = new Font ("Calibri", Font.PLAIN, 25),
            Normal = new Font ("Calibri", Font.PLAIN, 20),
            Normal2 = new Font ("Calibri", Font.PLAIN, 18),
            Bold = new Font ("Calibri", Font.BOLD, 16);
    public static  int buttonW = 110, buttonL = 30;
    public static  Color defaultcolor = UIManager.getColor ( "Panel.background" );
    public static  Border border = new LineBorder(Color.BLACK, 1);
    public final static List<String> letters = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
    public final static String[] symbols = {"", "!", "@", "#", "$", "%", "^", "*"};
    public final static BufferedImage menuimage = p.loadImage("./Graphics/Menu.png");
    public final static BufferedImage menuimage2 = p.loadImage("./Graphics/Menu 2.png");
    public final static BufferedImage menucursor = p.loadImage("./Graphics/Menu Cursor.png");
    public final static BufferedImage[] gender = {p.loadImage("./Graphics/Pokemon Menu/Gender Male.png"), p.loadImage("./Graphics/Pokemon Menu/Gender Female.png"), p.loadImage("./Graphics/Pokemon Menu/Empty.png")};
    
    public final static BufferedImage healthbar = p.loadImage("./Graphics/Pokemon Menu/Health Bar.png");
    public final static BufferedImage greenhealthbar = p.loadImage("./Graphics/Pokemon Menu/Green Health Bar.png");
    public final static BufferedImage yellowhealthbar = p.loadImage("./Graphics/Pokemon Menu/Yellow Health Bar.png");
    public final static BufferedImage greyhealthbar = p.loadImage("./Graphics/Pokemon Menu/Grey Health Bar.png");
    
    //Math methods
    public static double round(double num, int place){
        double rounded = Math.round(num*Math.pow(10, place))/Math.pow(10, place);
        return rounded;
    }
    public static double random(double low, double high){
        return (high-low+1)*Math.random()+low;
    }
    public static int randomint(int low, int high){
        return (int)((high-low+1)*Math.random()+low);
    }
    
    
    //JComponents
    public static Border border(int thickness){
        Border border = new LineBorder(Color.BLACK, thickness);
        return border;
    }
    public static Font font(int size){
        Font font = new Font("Pokemon FireLeaf", Font.PLAIN, size);
        return font;
    }
    public static Font font(int type, int size){
        Font font = new Font("Pokemon FireLeaf", type, size);
        return font;
    }
    public static Font font2(int size){
        Font font = new Font("Power Red and Green", Font.PLAIN, size);
        return font;
    }
    public static Font font2(int type, int size){
        Font font = new Font("Power Red and Green", type, size);
        return font;
    }
    public static void drawString1(Graphics g, String str, int x, int y, Color Foreground, Color Background){
        g.setColor(Background);
        g.drawString(str, x+1, y+1);
        g.setColor(Foreground);
        g.drawString(str, x, y);
    }
    public static void drawString1(Graphics g, String str, int x, int y, int widthlimit, int spacing, int fontsize, Color Foreground, Color Background){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = p.font(fontsize);
        g.setFont(font);
        String[] words = str.split(" ");
        String line = "";
        int num = 0;
        for (int a = 0; a<words.length; a++){
            int width = (int)(font.getStringBounds(line+words[a], frc).getWidth());
            if (width>widthlimit){
                g.setColor(Background);
                g.drawString(line, x+1, y+1+num*spacing);
                g.setColor(Foreground);
                g.drawString(line, x, y+num*spacing);
                num++;
                line = words[a]+" ";
            }
            else{
                line+=words[a]+" ";
            }
        }
//        num++;
        if (!line.equals("")){
            g.setColor(Background);
            g.drawString(line, x+1, y+1+num*spacing);
            g.setColor(Foreground);
            g.drawString(line, x, y+num*spacing);
        }
    }
    public static void drawString2(Graphics g, String str, int x, int y, Color Foreground, Color Background){
        g.setColor(Background);
        g.drawString(str, x+2, y+2);
        g.setColor(Foreground);
        g.drawString(str, x, y);
    }
    public static void drawString2(Graphics g, String str, int x, int y, int widthlimit, int spacing, int fontsize, Color Foreground, Color Background){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = p.font(fontsize);
        g.setFont(font);
        String[] words = str.split(" ");
        String line = "";
        int num = 0;
        for (int a = 0; a<words.length; a++){
            int width = (int)(font.getStringBounds(line+words[a], frc).getWidth());
            if (width>widthlimit){
                g.setColor(Background);
                g.drawString(line, x+2, y+2+num*spacing);
                g.setColor(Foreground);
                g.drawString(line, x, y+num*spacing);
                num++;
                line = words[a]+" ";
            }
            else{
                line+=words[a]+" ";
            }
        }
//        num++;
        if (!line.equals("")){
            g.setColor(Background);
            g.drawString(line, x+2, y+2+num*spacing);
            g.setColor(Foreground);
            g.drawString(line, x, y+num*spacing);
        }
    }
    public static JLabel label (int xcoordinate, int ycoordinate, String text, int fontsize){
        JLabel label = new JLabel();
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("Calibri", Font.PLAIN, fontsize);
        int width = (int)(font.getStringBounds(text, frc).getWidth());
        int height = (int)(font.getStringBounds(text, frc).getHeight());
        
//        System.out.println(text+":   "+width+", "+height);
        label.setLocation(xcoordinate, ycoordinate);
        label.setSize(width+5, height+5);
        label.setFont(font);
        label.setText(text);
        return label;
    }
    public static void JMessagePane(String message, String header, int type){
        JOptionPane.showMessageDialog (null, message, header, type);
    }
    public static int showConfirmDialog(String message, String header, int mode, int type){
        return JOptionPane.showConfirmDialog(null, message, header, mode, type);
    }
    public static JTextArea JTextArea (int xcoordinate, int ycoordinate, int width, int height, int fontsize, boolean edittable, boolean visible){
        JTextArea area = new JTextArea();
        area.setLocation(xcoordinate, ycoordinate);
        area.setSize(width, height);
        if (!visible){
            area.setBackground(defaultcolor);
        }
        else{
            area.setBorder(new LineBorder(Color.BLACK, 1));
        }
        area.setFont(p.font(fontsize));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(edittable);
        area.setText("");
        return area;
    }
    public static JTextField JTextField (int xcoordinate, int ycoordinate, int width, int height, int fontsize, boolean visible){
        JTextField area = new JTextField();
        area.setLocation(xcoordinate, ycoordinate);
        area.setSize(width, height);
        if (!visible){
            area.setOpaque(false);
            area.setBorder(new LineBorder(Color.BLACK, 0));
        }
        else{
            area.setBorder(new LineBorder(Color.BLACK, 1));
        }
        area.setFont(p.font(fontsize));
        area.setText("");
        return area;
    }
    public static JScrollPane JScrollPane (int xcoordinate, int ycoordinate, int width, int height){
        JScrollPane pane = new JScrollPane();
        pane.setLocation(xcoordinate, ycoordinate);
        pane.setSize(width, height);
        return pane;
    }
    public static Color fadeRect(Graphics g, int opacity){
        Color color;
        if(opacity>=0 && opacity<=255){
            color = new Color(0,0,0, opacity);
        }
        else{
            color = Color.BLACK;
        }
        g.setColor(color);
        g.fillRect(0, 0, Main.w.getWidth(), Main.w.getHeight());
        return color;
    }
    public static void drawMenu(Graphics g, int x, int y, int width, int height){
        height+=5;
        g.drawImage(menuimage.getSubimage(0, 0, menuimage.getWidth(), 20), x, y, width, 15, null);
        g.drawImage(menuimage.getSubimage(0, 20, menuimage.getWidth(), 30), x, y+15, width, height-30, null);
        g.drawImage(menuimage.getSubimage(0, menuimage.getHeight()-20, menuimage.getWidth(), 20), x, y+height-20, width, 15, null);
    }
    public static void drawMenu2(Graphics g, int x, int y, int width, int height){
        height+=5;
        g.drawImage(menuimage2.getSubimage(0, 0, menuimage2.getWidth(), 20), x, y, width, 15, null);
        g.drawImage(menuimage2.getSubimage(0, 20, menuimage2.getWidth(), 30), x, y+15, width, height-30, null);
        g.drawImage(menuimage2.getSubimage(0, menuimage2.getHeight()-20, menuimage2.getWidth(), 20), x, y+height-20, width, 15, null);
    }
    public static void drawImage(Graphics g, BufferedImage image, int x, int y, double scale){
        g.drawImage(image, x, y, (int)(image.getWidth()*scale), (int)(image.getHeight()*scale), null);
    }
    public static int stringWidth(String string, Font f){
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = f;
        int width = (int)(font.getStringBounds(string, frc).getWidth());
        return width;
    }
    public static String extendNumber(String string){
        while(string.length()<3){
            string = "0"+string;
        }
        return string;
    }
    public static String extendNumber(String string, int num){
        if (num > 0){
            while(string.length()<num){
                string = "0"+string;
            }
        }
        return string;
    }
    public static String formatName(String name){
        String displayname = "";
        if (name.equalsIgnoreCase("Mr. Mime")){
            displayname = "MR. MIME";
        }
        else if (name.equalsIgnoreCase("Mime Jr.")){
            displayname = "MIME JR.";
        }
        else if (name.split(" ").length > 1){
            displayname = name.split(" ")[0].toUpperCase();
        }
        else{
            displayname = name.toUpperCase();
        }
        return displayname;
    }
    public static String formatIndex(int index){
        String number = "";
        if (index<=349){
            number = (index+1)+"";
        }
        else if (index>349 && index<=384){
            number = (index+2)+"";
        }
        else if (index>384 && index<=387){
            number = "386_"+(index-384);
        }
        else if(index>387 && index <= 493){
            number = (index-1)+"";
        }
        else if(index == 494){
            number = "492_1";
        }
        else if(index >494 && index <= 557){
            number = (index-2)+"";
        }
        else if(index == 558){
            number = "555_1";
        }
        else if(index >558 && index<=651){
            number = (index-3)+"";
        }
        else if(index == 652){
            number = "648_1";
        }
        else if(index >652){
            number = (index-4)+"";
        }
        return number;
    }
    
    //Delay
    public static void delay (double time){
        try{
            Thread.sleep((long)Math.floor(time), (int)((time-Math.floor(time))*1000000));
//            System.out.println("Delayed "+(long)Math.floor(time)+"Milliseconds and "+(int)((time-Math.floor(time))*100000)+" Nanoseconds");
        }catch(InterruptedException e){}
    }
    
    //Buffered Input
    public static BufferedImage loadImage(String path){
        try {
//            image = ImageIO.read(getClass().getResource(path));
            return(ImageIO.read(new FileInputStream(path)));
        } catch (IOException e) {
            System.out.println("Could not load:   "+path);
        }
        return null;
    }
    public static String getString(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try {
            return( br.readLine());
        } catch (IOException ex) {        }
        return "";
    }
    public static int getInt(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try{
            return Integer.parseInt(br.readLine());
        }catch (NumberFormatException | IOException e){        }
        return -1;
    }
    public static double getDouble(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try{
            return Double.parseDouble(br.readLine());
        }catch (NumberFormatException | IOException e){        }
        return -1;
    }
    public static long getLong(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try{
            return Long.parseLong(br.readLine());
        }catch (NumberFormatException | IOException e){        }
        return -1L;
    }
    public static float getFloat(){
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        try{
            return Float.parseFloat(br.readLine());
        }catch (NumberFormatException | IOException e){        }
        return -1f;
    }
    public static BufferedReader filereader(String path){
        
        //example path:
        
        try {
            return new BufferedReader (new FileReader (path));
        } catch (FileNotFoundException ex) {        }
        return null;
    }
    public static PrintWriter printwriter(String path){
        try {
            return new PrintWriter (new FileWriter (path));
        } catch (IOException ex) {        }
        return null;
    }
    public static List<String> readFile(String path){
        List<String>list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader (new FileReader (path));
            String line = "";
            while(true){
                line = br.readLine();
                if (line==null){
                    break;
                }
                list.add(line);
            }
        } catch (FileNotFoundException ex) {}   catch(IOException e){}
        return list;
    }
//    public static List<String> readResource(String path){
//        List<String>list = new ArrayList<>();
//        try {
//            BufferedReader br = new BufferedReader (new InputStreamReader(getClass().getResourceAsStream(path)));
//            String line = "";
//            while(true){
//                line = br.readLine();
//                if (line==null){
//                    break;
//                }
//                list.add(line);
//            }
//        } catch (FileNotFoundException ex) {}   catch(IOException e){}
//        return list;
//    }
    
    //Print Arrays
    public static void print(String[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
    public static void print(int[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
    public static void print(double[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
    public static void print(long[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
    public static void print(float[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
    public static void print(Object[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.print("}");
    }
    public static void println(Object[] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print(array[a]);
            if ((a+1)<array.length){
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }
    public static void print(String[][] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print("{");
            for (int b = 0; b<array[a].length; b++){
                System.out.print(array[a][b]);
                if ((b+1)<array[a].length){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
    public static void print(int[][] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print("{");
            for (int b = 0; b<array[a].length; b++){
                System.out.print(array[a][b]);
                if ((b+1)<array[a].length){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
    public static void print(double[][] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print("{");
            for (int b = 0; b<array[a].length; b++){
                System.out.print(array[a][b]);
                if ((b+1)<array[a].length){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
    public static void print(long[][] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print("{");
            for (int b = 0; b<array[a].length; b++){
                System.out.print(array[a][b]);
                if ((b+1)<array[a].length){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
    public static void print(float[][] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print("{");
            for (int b = 0; b<array[a].length; b++){
                System.out.print(array[a][b]);
                if ((b+1)<array[a].length){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
    public static void print(Object[][] array){
        System.out.print("{");
        for (int a = 0; a<array.length; a++){
            System.out.print("{");
            for (int b = 0; b<array[a].length; b++){
                System.out.print(array[a][b]);
                if ((b+1)<array[a].length){
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
        System.out.println("}");
    }
    
    //Array index
    public static int indexOf(int[] array, int num){
        for (int a = 0; a<array.length; a++){
            if (array[a] == num){
                return a;
            }
        }
        return -1;
    }
    public static int indexOf(double[] array, double num){
        for (int a = 0; a<array.length; a++){
            if (array[a] == num){
                return a;
            }
        }
        return -1;
    }
    public static int indexOf(long[] array, long num){
        for (int a = 0; a<array.length; a++){
            if (array[a] == num){
                return a;
            }
        }
        return -1;
    }
    public static int indexOf(float[] array, float num){
        for (int a = 0; a<array.length; a++){
            if (array[a] == num){
                return a;
            }
        }
        return -1;
    }
    public static int indexOf(String[] array, String str){
        for (int a = 0; a<array.length; a++){
            if (array[a].equals(str)){
                return a;
            }
        }
        return -1;
    }
    public static int indexOf(Object[] array, Object str){
        for (int a = 0; a<array.length; a++){
            if (array[a].equals(str)){
                return a;
            }
        }
        return -1;
    }
    
    //String cases
    public static String toProperCase(String str){
        str = str.toLowerCase();
        str = str.trim();
        int index = -1;
        str = str.replaceFirst(str.substring(0, 1), str.substring(0,1).toUpperCase());
        if (str.contains("-")){
            try{
                index = -1;
                while(true){
                    index = str.indexOf("-", index+1);
                    if (index == -1){
                        break;
                    }
                    try{
                        str = str.replaceAll(str.substring(index, index+2), "-"+str.substring(index+1, index+2).toUpperCase());
                    }catch(PatternSyntaxException e){}
                }
            }catch(StringIndexOutOfBoundsException e){}
        }
        if (str.contains("\\(")){
            try{
                index = -1;
                while(true){
                    index = str.indexOf("\\(", index+1);
                    if (index == -1){
                        break;
                    }
                    try{
                        str = str.replaceAll(str.substring(index, index+2), "\\("+str.substring(index+1, index+2).toUpperCase());
                    }catch(PatternSyntaxException e){}
                }
            }catch(StringIndexOutOfBoundsException e){}
        }
        if (str.contains(" ")){
            try{
                index = -1;
                while(true){
                    index = str.indexOf(" ", index+1);
                    if (index == -1){
                        break;
                    }
                    try{
                        str = str.replaceAll(str.substring(index, index+2), " "+str.substring(index+1, index+2).toUpperCase());
                    }catch(PatternSyntaxException e){}
                }
            }catch(StringIndexOutOfBoundsException e){}
        }
        return str;
    }
    
}
