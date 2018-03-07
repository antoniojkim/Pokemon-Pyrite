package Main;

import Character.Trainer;
import Bag.Items;
import Battle.BattleMoves;
import Battle.BattlePanel;
import Events.DumbNPC;
import Maps.MapManager;
import Pokemon.PokemonData;
import Pokemon.PokemonMenu;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main
{
    
    public static Window w;
    public static PokemonMenu menu = new PokemonMenu(0);
    static Thread thread;
    public static boolean gMode = true;
    public static BattleMoves moves = new BattleMoves();
    public static PokemonData data = new PokemonData();
    public static Items items = new Items();
    public static MapManager map;
    public static Trainer it;
    public static Intro intro = new Intro();
    public static Clip music = music("./Audio/Pokemon Origins Title Screen.wav");
    public static Clip sfx = music("./Audio/SE/save.wav");
    public static boolean introduce = true;
    
    public static void main(String[] args) throws IOException, FontFormatException
    {
  //      introduce = false;
        it = new Trainer();
        MapManager.fillMap();
        map = new MapManager();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./Graphics/Font.ttf")));
        ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./Graphics/Font2.ttf")));
        w = new Window();
        w.add(intro, BorderLayout.CENTER);
        w.setVisible(true);
        DumbNPC.loadSprites();
        music.start();
        if (introduce){
            thread = new Thread(intro);
            thread.start();
//            intro.run();
        }
        else{
            thread = new Thread(map);
            w.Switch(intro, map);
            thread.start();
        }
    }
    
    public static Clip music(String path){
        try{
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(file));
            return clip;
        }catch(LineUnavailableException | UnsupportedAudioFileException | IOException e){
            System.out.println("Could not play music");
        }
        return null;
    }
    
    public static MapManager getMap ()
    {
        return map;
    }
    
}
