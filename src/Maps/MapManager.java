/*
This Class manages virtually everything being seen while navigating maps.
*/
package Maps;

import Bag.Store;
import Battle.BattlePanel;
import Character.TrainerCard;
import Events.Event;
import Main.Main;
import Main.p;
import Pokemon.Pokedex;
import Pokemon.Pokemon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author julian bardin
 */
public class MapManager extends JPanel implements Runnable, KeyListener {
    
    private static Map[][] mapArray = new Map[32][32];
    private static Map currentMap;
    protected static BattlePanel bp;
    
    /*
    Returns the Map object
    pre:Map has been instantiated
    post: returns the map
    */
    public static Map getTest()
    {
        return currentMap;
    }
    
    /*
    Returns the BattlePanel object
    pre: bp has been instantiated
    post: returns the BattlePanel
    */
    public BattlePanel getBP()
    {
        return bp;
    }
    
    public List<Pokemon> party = new ArrayList<>();
    
    BufferedImage map;
    BufferedImage drawnmap;
    ImageObserver io;
    
    int menuindex = 0;
    int sizeX, sizeY, mapX = 0, mapY = 0, a = 32, opacity = 300;
    private int worldX = 1, worldY = 3;
    private int repelSteps = 0, surfindex = 0;
    
    /*
    Returns the steps until repel wears off
    pre: none
    post: returns the int
    */
    public int getRepelSteps()
    {
        return repelSteps;
    }
    
    /*
    Sets the steps until repel wears off
    pre: steps > 0
    post: repelSteps set
    */
    public void setRepel(int steps)
    {
        repelSteps = steps;
    }
    
    /*
    Adjusts the steps until Repel wears off
    pre: none
    post: repelSteps adjusted
    */
    public void adjustRepel(int steps)
    {
        repelSteps += steps;
    }
    
    private Boolean right = false, left = false, up = false, down = false, fadeIn = false, fadeOut = false, menu = false, grid = false, dialogue = false, tallGrass = false, water = false, chooseSurf = false, textCleared = false;
    public Boolean running = false, toPokemon = false;
    protected List<String> dialoguetext = new ArrayList<>();
    protected List<String> optiontext = new ArrayList<>();
    
    /*
    Adds dialogue to be displayed for the player
    pre: non
    post: dialogue displayed
    */
    public void addDialoguetext(String dialoguetext)
    {
        textCleared = false;
        clear();
        this.dialoguetext.add(dialoguetext);
    }
    
    LinkedList<String> pressed = new LinkedList<>();
    
    /*
    Used to deal with player pressing two keys at once or more.
    pre: none
    post: key valyue returned
    */
    public LinkedList<String> getPressed()
    {
        return pressed;
    }
    
    /*
    Returns the orientation of the player, which is an int.
    pre: none
    post: int returned
    */
    public int getOrientation()
    {
        return orientation;
    }
    
    /*
    Returns whether or not the player is moving.
    pre: none
    post: boolean returned
    */
    public boolean getMoving()
    {
        return moving;
    }
    
    /*
    Allows the variable for moving to be set
    pre: none
    post: moving has been set
    */
    public void setMoving(boolean set)
    {
        moving = set;
    }
    
    private boolean surf = false;
    
    /*
    Returns if the player is surfing
    pre: none
    post: boolean reaturned
    */
    public boolean isSurf()
    {
        return surf;
    }
    
    /*
    Allows the boolean for surfing to be set.
    pre: none
    post: surf is set
    */
    public void setSurf(boolean isSurf)
    {
        surf = isSurf;
    }
    
    private boolean store = false, storeSelected = false, itemSelected = false, storeconfirm = false;
    private int storeindex = 0, itemindex = 0, optionsindex = 0, begin = 0, numselected = 0, confirmindex = 0;
    private Store Store = new Store();
    BufferedImage[] options = {p.loadImage("./Graphics/Store/Buy Option.png"), p.loadImage("./Graphics/Store/Sell Option.png"), p.loadImage("./Graphics/Store/See Ya Option.png")};
    
    /*
    Returns if the player is viewing the store or not
    pre: none
    post: boolean returned
    */
    public boolean isStore()
    {
        return store;
    }
    
    /*
    Sets the store boolean and loads the store if true
    pre: none
    post: boolean set and store may be loaded
    */
    public void setStore(boolean set)
    {
        store = set;
        if (store) {
            storeindex = 0;
            itemindex = 0;
        }
    }
    
    /*
    Gets what product the player is viewing
    pre: none
    post: int returned
    */
    public int getStoreIndex()
    {
        return storeindex;
    }
    
    /*
    Gets if store is in selection menu
    pre: none
    post: returns boolean
    */
    public boolean getStoreSelected()
    {
        return storeSelected;
    }
    
    /*
    Gets the item's index
    pre: none
    post: return int
    */
    public int getItemIndex()
    {
        return itemindex;
    }
    
    /*
    Gets if an item is currently selected
    pre: none
    post: boolean returned
    */
    public boolean isItemSelected()
    {
        return itemSelected;
    }
    
    /*
    Returns the int for the options index being veiwed
    pre: none
    post: int returned
    */
    public int getOptionsIndex()
    {
        return optionsindex;
    }
    
    /*
    Returns store varaible which allows player to scroll to store
    pre: none
    post: int returned
    */
    public int getBegin()
    {
        return begin;
    }
    
    /*
    Gets the selected number of items
    pre: none
    post: int returned
    */
    public int getNumSelected()
    {
        return numselected;
    }
    
    /*
    Gets the boolean which shows if player has confirmed their selection
    pre: none
    post: boolean returned
    */
    public boolean getConfirm()
    {
        return storeconfirm;
    }
    
    /*
    Gets the confirmed index of the item the player selected
    pre: none
    post: int returned
    */
    public int getConfirmindex()
    {
        return confirmindex;
    }
    
    double speed = 6;
    int orientation = 0;
    boolean moving = false;
    boolean disable = true;
    boolean rundialogue = false;
    boolean forceMove = false;
    long start;
    
    
    public String[] menuitems = {
        "Pokédex", "Pokémon", "Bag", "Name", "Save", "Options", "Exit"
    };
    
    /*
    Instantiates Mapmanager setting default location, loading in map, player
    location, and images
    pre: none
    post: Mapmanager instantiated
    */
    public MapManager()
    {
//        long begin = System.currentTimeMillis();
//        mapY=-32*185+characters[0][0].getHeight()-32;
//        mapY=-32*24+14;
//        System.out.println("Took "+(System.currentTimeMillis()-begin)+" Milliseconds to load the map");
//        map = p.loadImage(mappath);
//        fillMap();
        currentMap = mapArray[worldX][worldY];
        map = this.currentMap.getImage();
        sizeX = map.getWidth(io);
        sizeY = map.getHeight(io);
        mapX = 0;
        mapY = -128; //Adjust
        addKeyListener(this);
    }
    
    /*
    Infinite for loop for everything managed by MapManager. Movement, orientation,
    collision, and rendering.
    pre: none
    post: MapManager is running
    */
    @Override
    public void run()
    {
        position = 0;
        Main.it.setCharacter(Main.it.characters[orientation][0]);
        fadeIn(1);
        running = true;
        menuitems[3] = Main.it.getName();
        setFocusable(true);
        requestFocusInWindow();
        for (int a = 0; running; a++) {
            if (dialoguetext.isEmpty()) {
                if (forceMove) {
                    movement(orientation);
                    forceMove = false;
                } else if ((System.currentTimeMillis() - start) >= 100 && !menu && !store) {
                    position = 0;
                    Main.it.setCharacter(Main.it.characters[orientation][0]);
                    if (pressed.size() > 0 && pressed.peekLast().equals(KeyEvent.VK_RIGHT + "")) {
                        orientation = 2;
                        if (collision(pX + 1, pY)) {
                            movement(orientation);
                        }
                    } else if (pressed.size() > 0 && pressed.peekLast().equals(KeyEvent.VK_LEFT + "")) {
                        orientation = 1;
                        if (collision(pX - 1, pY)) {
                            movement(orientation);
                        }
                    } else if (pressed.size() > 0 && pressed.peekLast().equals(KeyEvent.VK_DOWN + "")) {
                        orientation = 0;
                        if (collision(pX, pY + 1)) {
                            movement(orientation);
                        }
                    } else if (pressed.size() > 0 && pressed.peekLast().equals(KeyEvent.VK_UP + "")) {
                        orientation = 3;
                        if (collision(pX, pY - 1)) {
                            movement(orientation);
                        }
                    }
                    p.delay(speed);
                } else if (!pressed.isEmpty() && (System.currentTimeMillis() - start) == 99) {
                    position = 1;
                    Main.it.setCharacter(Main.it.characters[direction[Integer.parseInt(pressed.peekLast()) - 37]][1]);
                } else if (pressed.isEmpty()) {
                    position = 0;
                    Main.it.setCharacter(Main.it.characters[orientation][0]);
                }
            }
            if (running) {
//                System.out.println("repainting");
                repaint();
                if (!fadeIn && !fadeOut && menu) {
                    p.delay(100);
                } else if (!fadeIn && !fadeOut) {
                    p.delay(3 * speed + 1);
                } else {
                    Thread.yield();
                }
            }
            pX = 12 - mapX / 32;
            pY = 8 - mapY / 32;
        }
        System.out.println("Map Thread Complete");
    }
    
    /*
    If the player is moving, it changes the player's location.
    pre: orientatin is between 0 and 3
    post: player location changed
    */
    public void movement(int orientation)
    {
//        System.out.println((12 - mapX / 32) + " " + (8 - mapY / 32));
        if (orientation == 0) {//down
            for (int b = 0; b < 32; b++) {
                if (b % 16 == 0) {
                    lastPressed = 0;
                    change();
                }
                moving = true;
                mapY--;
                repaint();
                p.delay(speed);
            }
        } else if (orientation == 1) {//Left
            for (int b = 0; b < 32; b++) {
                if (b % 16 == 0) {
                    lastPressed = 1;
                    change();
                }
                moving = true;
                mapX++;
                repaint();
                p.delay(speed);
            }
            
        } else if (orientation == 2) {//Right
            for (int b = 0; b < 32; b++) {
                if (b % 16 == 0) {
                    lastPressed = 2;
                    change();
                }
                moving = true;
                mapX--;
                repaint();
                p.delay(speed);
            }
        } else if (orientation == 3) {//Up
            for (int b = 0; b < 32; b++) {
                if (b % 16 == 0) {
                    lastPressed = 3;
                    change();
                }
                moving = true;
                mapY++;
                repaint();
                p.delay(speed);
            }
        }
        change();
        textCleared = false;
    }
    
    /*
    Gets the tile on CurrentMap Map
    pre: fX and fY are within bounds
    post: int returned
    */
    public int getTemp(int fX, int fY)
    {
        return this.currentMap.getTile(fX, fY);
    }
    
    /*
    Allows the player's location to be set
    pre: variables are all within bounds
    post: location set
    */
    public void setMap(int wX, int wY, int mX, int mY)
    {
        worldX = wX;
        worldY = wY;
        mapX = (12 - mX) * 32;
        mapY = (8 - mY) * 32;
        currentMap = mapArray[worldX][worldY];
        map = this.currentMap.getImage();
        repaint();
    }
    
    /*
    A method which returns the player's x-location in the world (where  map is)
    pre: none
    post: int returned
    */
    public int getWorldX()
    {
        return worldX;
    }
    
    /*
    A method which returns the player's y-location in the world (where map is)
    pre: none
    post: int returned
    */
    public int getWorldY()
    {
        return worldY;
    }
    
    /*
    A method which returns the player's "tile location" on the map" This is the
    x-coordinate.
    pre: none
    post: int returned
    */
    public int getpX()
    {
        return pX;
    }
    
    /*
    A method which returns the player's "tile location" on the map" This is the
    y-coordinate.
    pre: none
    post: int returned
    */
    public int getpY()
    {
        return pY;
    }
    
    /*
    Checks what tile the player is walking on, and deals with it accordingly
    pre: none
    post: boolean returned
    */
    public boolean collision(int fX, int fY)
    {
        int temp;
        try {
            temp = getTemp(fX, fY);
        } catch (ArrayIndexOutOfBoundsException ex) {
            try {
                if (fX >= currentMap.getImage().getWidth() / 32 && mapArray[worldX + 1][worldY] != null) {
                    worldX++;
                    currentMap = mapArray[worldX][worldY];
                    map = this.currentMap.getImage();
                    mapX = 13 * 32;
                    mapY += mapArray[worldX][worldY].getShift(1);
                } else if (fX <= -1 && mapArray[worldX - 1][worldY] != null) {
                    worldX--;
                    currentMap = mapArray[worldX][worldY];
                    map = this.currentMap.getImage();
                    mapX = (12 - map.getWidth() / 32) * 32;
                    mapY += mapArray[worldX][worldY].getShift(3);
                } else if (fY < 0 && mapArray[worldX][worldY + 1] != null) {
                    worldY++;
                    currentMap = mapArray[worldX][worldY];
                    map = this.currentMap.getImage();
                    mapX += mapArray[worldX][worldY].getShift(2);
                    mapY = (8 - map.getHeight() / 32) * 32;
                } else if (fY >= currentMap.getImage().getHeight() / 32 && mapArray[worldX][worldY - 1] != null) {
                    worldY--;
                    currentMap = mapArray[worldX][worldY];
                    map = this.currentMap.getImage();
                    mapX += mapArray[worldX][worldY].getShift(0);
                    mapY = 9 * 32;
                }
//                BufferedImage map = Main.it.character.
//                System.out.println(pX + ", " + pY + " | " + worldX);
            } catch (NullPointerException e) {
            }
            temp = 0;
        }
        if (temp == 1)//No go
        {
            if (!textCleared) {
                eventCol(fX, fY);
            }
            return false;
        } else if (temp == 2 && repelSteps <= 0) //Tall Grass
        {
            double r = Math.random();
//            System.out.println(r);
            if (r < 0.05) {
                tallGrass = true;
                pressed.clear();
                change();
                return true;
            }
        } else if (temp == 3) //Jump animation clear
        {
            return true;
        } else if (temp == 4) //Ledge
        {
            if (currentMap.getTile(pX, pY) == 3) {
                return false;
            }
            return true;
        } else if (temp == 9) //Water
        {
            if (Math.random() < 0.05 && surf && repelSteps <= 0) {
                water = true;
                pressed.clear();
                change();
            }
            return surf;
        }
        if (repelSteps > 0) {
            repelSteps--;
            if (repelSteps <= 0) {
                addDialoguetext("Repel's Effect has worn off");
            }
        }
        if (surf) {
            surf = false;
        }
        repaint();
        if (!textCleared) {
            return eventCol(fX, fY);
        } else if (currentMap.getEvent(fX, fY) != null) {
            return false;
        }
        return true;
    }
    
    /*
    Runs the events if the character is tryin to walk into it
    pre: fX and fY are within bounds
    post: returns boolean and activates event
    */
    public boolean eventCol(int fX, int fY)
    {
        try {
            Event event = currentMap.getEvent(fX, fY);
            if (event != null) {
                repaint();
                event.activate();
                return false;
            }
            
        } catch (ArrayIndexOutOfBoundsException e) {
        }
        return true;
    }
    
    /*
    Changes the player sprite to allign with the orentation entered
    */
    public void change()
    {
        if (moving) {
//            character = characters[direction[Integer.parseInt(pressed.peekLast())-37]][0];
//            delay(152);
            position++;
            if (position > 3) {
                position = 0;
            }
            Main.it.setCharacter(Main.it.characters[lastPressed][position]);
            moving = false;
        } else if (!pressed.isEmpty()) {
            position = 0;
            Main.it.setCharacter(Main.it.characters[direction[Integer.parseInt(pressed.peekLast()) - 37]][0]);
        } else if (pressed.isEmpty()) {
            position = 0;
            Main.it.setCharacter(Main.it.characters[orientation][0]);
        }
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke)
    {
        
    }
    
    /*
    Action-based method which deals with key-input. Most user input comes through
    this method.
    pre: none
    post: User has interacted with game. Player may have moved, menu appeared,
    or item selected
    */
    @Override
    public void keyPressed(KeyEvent ke)
    {
        int keyCode = ke.getKeyCode();
        if (chooseSurf) {
            if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && chooseSurf) {
                chooseSurf = false;
                dialoguetext.remove(0);
                if (surfindex == 0) {
                    for (int a = 0; a < Main.it.getParty().size(); a++) {
                        if (Main.it.getParty().get(a).getMoveset().contains("Surf")) {
                            addDialoguetext(Main.it.getParty().get(a).getDisplayname() + " used SURF!");
                            repaint();
                            dialoguetext.remove(0);
                            forceMove = true;
                            repaint();
                            surf = true;
                            break;
                        }
                    }
                }
            } else if (keyCode == Main.it.getUp() && surfindex < 1) {
                surfindex++;
            } else if (keyCode == Main.it.getDown() && surfindex > 0) {
                surfindex--;
            }
        }
        if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && dialoguetext.size() > 0 && (!store || storeconfirm)) {
            dialoguetext.remove(0);
            textCleared = true;
            storeconfirm = false;
        } else if (store) {
            if (storeconfirm) {
                if (keyCode == Main.it.getDown() && confirmindex < 1) {
                    confirmindex++;
                } else if (keyCode == Main.it.getUp() && confirmindex > 0) {
                    confirmindex--;
                } else if (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) {
                    if (confirmindex == 0) {
                        itemSelected = false;
                        if (storeindex == 0) {
                            Main.it.adjustMoney(-numselected * Store.price(Store.getItemsList().get(begin + itemindex)));
                            Main.it.getBag().addItem(Store.getItemsList().get(begin + itemindex), numselected);
                            addDialoguetext("Here you are!  Thank You!");
                        } else if (storeindex == 1) {
                            int price = (numselected * Store.price(Store.getItemsList().get(Main.it.getBag().allitems.indexOf(Main.getMap().getItemIndex() + Main.getMap().getBegin()))) / 3);
                            Main.it.adjustMoney(price);
                            Main.it.getBag().addItem(Store.getItemsList().get(begin + itemindex), -numselected);
                            addDialoguetext("Turned over the " + Store.getItemsList().get(begin + itemindex) + "(s) worth $" + price + ".");
                        }
                    }
                }
            } else if (itemSelected) {
                if (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) {
                    storeconfirm = true;
                } else if (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()) {
                    itemSelected = false;
                } else if (storeindex == 0) {
                    if (keyCode == Main.it.getDown() && numselected > 1) {
                        numselected--;
                    } else if (keyCode == Main.it.getDown()) {
                        numselected = Math.min(999, Main.it.getMoney() / Store.price(Store.getItemsList().get(begin + itemindex)));
                    } else if (keyCode == Main.it.getUp() && numselected < Math.min(999, Main.it.getMoney() / Store.price(Store.getItemsList().get(begin + itemindex)))) {
                        numselected++;
                    } else if (keyCode == Main.it.getUp()) {
                        numselected = 1;
                    }
                } else if (storeindex == 1) {
                    if (keyCode == Main.it.getDown() && numselected > 1) {
                        numselected--;
                    } else if (keyCode == Main.it.getDown()) {
                        numselected = Main.it.getBag().allitemcount.get(begin + itemindex);
                    } else if (keyCode == Main.it.getUp() && numselected < Main.it.getBag().allitemcount.get(begin + itemindex)) {
                        numselected++;
                    } else if (keyCode == Main.it.getUp()) {
                        numselected = 1;
                    }
                }
            } else if (!storeSelected) {
                if (keyCode == Main.it.getDown() && storeindex < 2) {
                    storeindex++;
                } else if (keyCode == Main.it.getUp() && storeindex > 0) {
                    storeindex--;
                } else if (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) {
                    if (storeindex != 2) {
                        dialoguetext.clear();
                        storeSelected = true;
                        begin = 0;
                        itemindex = 0;
                    } else {
                        dialoguetext.clear();
                        store = false;
                    }
                } else if (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()) {
                    dialoguetext.clear();
                    store = false;
                }
            } else {
                int limit = Store.getItemsList().size();
                if (storeindex == 1) {
                    limit = Main.it.getBag().allitems.size();
                }
                if (keyCode == Main.it.getDown() && (itemindex + begin) < limit) {
                    if ((itemindex + begin) < 3 || (itemindex + begin) >= limit - 2) {
                        itemindex++;
                    } else if ((itemindex + begin) >= 3 && (itemindex + begin) < limit - 2) {
                        begin++;
                    }
                } else if (keyCode == Main.it.getUp() && (itemindex + begin) > 0) {
                    if (begin > 0 && (itemindex + begin) < limit - 2) {
                        begin--;
                    } else {
                        itemindex--;
                    }
                } else if (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) {
                    if ((itemindex + begin) == limit) {
                        dialoguetext.clear();
                        storeSelected = false;
                    } else {
                        itemSelected = true;
                        numselected = 1;
                    }
                } else if (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()) {
                    itemindex = 0;
                    storeSelected = false;
                    addDialoguetext("Is there anything else I can do to help?");
                }
            }
        } else {
            if (tallGrass) {
                tallGrass = false;
                pressed.clear();
                Main.music.stop();
                Main.music = Main.music("./Audio/Pokemon Origins Wild Pokemon Music.wav");
                Main.music.start();
                fadeOut(1, false);
                bp = new BattlePanel(currentMap.getGrassPokemon(), currentMap.getbLevel(), currentMap.gethLevel(), "Grass");
//            bp.battling = true;
                Thread thread = new Thread(bp);
                Main.w.Switch(Main.map, bp);
                thread.start();
            } else if (water) {
                water = false;
                pressed.clear();
                Main.music.stop();
                Main.music = Main.music("./Audio/Pokemon Origins Wild Pokemon Music.wav");
                Main.music.start();
                fadeOut(1, false);
                bp = new BattlePanel(currentMap.getWaterPokemon(), currentMap.getbLevel(), currentMap.gethLevel(), "Water");
//            bp.battling = true;
                Thread thread = new Thread(bp);
                Main.w.Switch(Main.map, bp);
                thread.start();
            } else if (keyCode == KeyEvent.VK_G && ke.isAltDown()) {
                Main.it.generateParty();
            } else if ((keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) && !surf && !menu) {
                int[][] collision = {
                    {
                        pX, pY + 1
                    },
                    {
                        pX - 1, pY
                    },
                    {
                        pX + 1, pY
                    },
                    {
                        pX, pY - 1
                    }
                };
                if (getTemp(collision[orientation][0], collision[orientation][1]) == 9) {
                    for (int a = 0; a < Main.it.getParty().size(); a++) {
                        if (Main.it.getParty().get(a).getMoveset().contains("Surf")) {
                            addDialoguetext("The water is dyed a deep blue... Would you like to SURF?");
                            surfindex = 0;
                            chooseSurf = true;
                            break;
                        }
                    }
                }
            } else if (keyCode == Main.it.getSave() && !menu) {
                menu = false;
                Main.it.save();
                addDialoguetext("Game has been saved successfully!");
            } else if (keyCode == Main.it.getStart() || keyCode == Main.it.getAltStart()) {
                if (menu) {
                    menu = false;
                } else {
                    menu = true;
                    menuindex = 0;
                    pressed.clear();
                }
            } else if (keyCode == Main.it.getBack() || keyCode == Main.it.getAltBack()) {
                menu = false;
            } else if (menu) {
                if (keyCode == Main.it.getUp()) {
                    if (menuindex > 0) {
                        menuindex--;
                    } else {
                        menuindex = menuitems.length - 1;
                    }
                } else if (keyCode == Main.it.getDown()) {
                    if (menuindex < menuitems.length - 1) {
                        menuindex++;
                    } else {
                        menuindex = 0;
                    }
                } else if (keyCode == Main.it.getEnter() || keyCode == Main.it.getAltEnter()) {
                    if (menuindex == menuitems.length - 1) {
                        menu = false;
                    } else if (menuindex == 0) {
                        fadeOut(1, false);
                        Pokedex pokedex = new Pokedex();
                        Thread thread = new Thread(pokedex);
                        Main.w.Switch(Main.map, pokedex);
                        thread.start();
                    } else if (menuindex == 1) {
                        fadeOut(1, false);
                        Thread thread = new Thread(Main.menu);
                        Main.w.Switch(Main.map, Main.menu);
                        thread.start();
                    } else if (menuindex == 2) {
                        fadeOut(1, false);
                        Thread thread = new Thread(Main.it.getBag());
                        Main.w.Switch(Main.map, Main.it.getBag());
                        thread.start();
                    } else if (menuindex == 3) {
                        fadeOut(1, false);
                        TrainerCard tc = new TrainerCard(Main.it);
                        Thread thread = new Thread(tc);
                        Main.w.Switch(Main.map, tc);
                        thread.start();
                    } else if (menuindex == 4) {
                        menu = false;
                        Main.it.save();
                        addDialoguetext("Game has been saved successfully!");
                    } else {
                        System.out.println(menuitems[menuindex]);
                    }
                }
            } else if (!menu && dialoguetext.isEmpty() && !store) {
                if ((keyCode == Main.it.getUp()) && (mapY <= 0 || disable) && !pressed.contains(KeyEvent.VK_UP + "")) {
                    if (pressed.isEmpty() && !moving) {
                        Main.it.setCharacter(Main.it.characters[3][0]);
                    }
                    orientation = 3;
                    if (!pressed.contains(KeyEvent.VK_DOWN + "")) {
                        start = System.currentTimeMillis();
                    }
                    pressed.remove(KeyEvent.VK_DOWN + "");
                    pressed.add(KeyEvent.VK_UP + "");
//                    if (orientation != 3){
//                        character = characters[3][1];
//                    }
//                    orientation = 3;
//                    position = 0;
                }
                if ((keyCode == Main.it.getDown()) && ((mapY + sizeY) >= Main.w.getHeight() || disable) && !pressed.contains(KeyEvent.VK_DOWN + "")) {
                    if (pressed.isEmpty() && !moving) {
                        Main.it.setCharacter(Main.it.characters[0][0]);
                    }
                    orientation = 0;
                    if (!pressed.contains(KeyEvent.VK_UP + "")) {
                        start = System.currentTimeMillis();
                    }
                    pressed.remove(KeyEvent.VK_UP + "");
                    pressed.add(KeyEvent.VK_DOWN + "");
//                    if (orientation != 0){
//                        character = characters[0][1];
//                    }
//                    orientation = 0;
//                    position = 0;
                }
                if ((keyCode == Main.it.getLeft()) && (mapX <= 0 || disable) && !pressed.contains(KeyEvent.VK_LEFT + "")) {
                    if (pressed.isEmpty() && !moving) {
                        Main.it.setCharacter(Main.it.characters[1][0]);
                    }
                    orientation = 1;
                    if (!pressed.contains(KeyEvent.VK_LEFT + "")) {
                        start = System.currentTimeMillis();
                    }
                    pressed.remove(KeyEvent.VK_RIGHT + "");
                    pressed.add(KeyEvent.VK_LEFT + "");
//                    if (orientation != 1){
//                        System.out.println(orientation);
//                        character = characters[1][1];
//                    }
//                    orientation = 1;
//                    position = 0;
                }
                if ((keyCode == Main.it.getRight()) && ((mapX - 32 + sizeX) > Main.w.getWidth() || disable) && !pressed.contains(KeyEvent.VK_RIGHT + "")) {
                    if (pressed.isEmpty() && !moving) {
                        Main.it.setCharacter(Main.it.characters[2][0]);
                    }
                    orientation = 2;
                    if (!pressed.contains(KeyEvent.VK_RIGHT + "")) {
                        start = System.currentTimeMillis();
                    }
                    pressed.remove(KeyEvent.VK_LEFT + "");
                    pressed.add(KeyEvent.VK_RIGHT + "");
//                    if (orientation != 2){
//                        character = characters[2][1];
//                    }
//                    orientation = 2;
//                    position = 0;
                }
            }
//                System.out.println(pressed.peekLast());
            if (ke.isShiftDown()) {
                speed = 1.5;
//                speedinterval = 16.0;
            } else if (Main.gMode && ke.isControlDown()) {
                speed = 0.001;
//                speedinterval = 12.0;
            } else {
                speed = 6;
//                speedinterval = 20.0;
            }
        }
        repaint();
    }
    
    /*
    Deals with special cases of keys being released in regards to movement
    pre: none
    post: orientation changed
    */
    @Override
    public void keyReleased(KeyEvent ke)
    {
        int keyCode = ke.getKeyCode();
        if (keyCode == KeyEvent.VK_BACK_QUOTE && ke.isAltDown()) {
            if (grid) {
                grid = false;
            } else {
                grid = true;
            }
            repaint();
        } //        else if (keyCode == KeyEvent.VK_Q && ke.isAltDown()) {
        //            if (disable) {
        //                disable = false;
        //            } else {
        //                disable = true;
        //            }
        //            System.out.println(disable);
        //            repaint();
        //        }
        //        else if (keyCode == KeyEvent.VK_I && ke.isAltDown()) {
        //            if (dialogue) {
        //                dialogue = false;
        //            } else {
        //                dialogue = true;
        //                rundialogue = true;
        //            }
        //            repaint();
        //        }
        else if (keyCode == KeyEvent.VK_B && ke.isAltDown()) {
            fadeOut(1, false);
            bp = new BattlePanel("Grass");
//            bp.battling = true;
            Thread thread = new Thread(bp);
            Main.w.Switch(Main.map, bp);
            thread.start();
        } else if (keyCode == KeyEvent.VK_L && ke.isAltDown()) {
            System.out.println(pX + ", " + pY);
        } else if (!menu && dialoguetext.isEmpty() && !store) {
            if (keyCode == Main.it.getUp()) {
                pressed.remove(KeyEvent.VK_UP + "");
                if (pressed.isEmpty() && !fadeIn && !fadeOut && !moving) {
                    Main.it.setCharacter(Main.it.characters[3][0]);
                }
                orientation = 3;
//                    up = false;
            }
            if (keyCode == Main.it.getDown()) {
                pressed.remove(KeyEvent.VK_DOWN + "");
                if (pressed.isEmpty() && !fadeIn && !fadeOut && !moving) {
                    Main.it.setCharacter(Main.it.characters[0][0]);
                }
                orientation = 0;
//                    down = false;
            }
            if (keyCode == Main.it.getLeft()) {
                pressed.remove(KeyEvent.VK_LEFT + "");
                if (pressed.isEmpty() && !fadeIn && !fadeOut && !moving) {
                    Main.it.setCharacter(Main.it.characters[1][0]);
                }
                orientation = 1;
//                    left = false;
            }
            if (keyCode == Main.it.getRight()) {
                pressed.remove(KeyEvent.VK_RIGHT + "");
                if (pressed.isEmpty() && !fadeIn && !fadeOut && !moving) {
                    Main.it.setCharacter(Main.it.characters[2][0]);
                }
                orientation = 2;
//                    right = false;
            }
//            if (keyCode == KeyEvent.VK_F) {
//                fadeOut();
//                fadeIn();
//            }
            if (ke.isShiftDown()) {
                speed = 1.5;
//                speedinterval = 16.0;
            } else if (Main.gMode && ke.isControlDown()) {
                speed = 0.001;
//                speedinterval = 12.0;
            } else {
                speed = 6;
//                speedinterval = 20.0;
            }
        }
        repaint();
    }
    
    int position = 0;
    int lastPressed = 0;
    int[] direction
            = {
                1, 3, 2, 0
            };
    private int pX, pY;
    
    /*
    Fills the world with all the maps and gives them coordinates for referencing
    pre: none
    post: All maps instantiated
    */
    public static void fillMap()
    {
        mapArray[1][3] = new AmuletTown();
        mapArray[2][3] = new Route1();
        mapArray[1][4] = new Route11();
        mapArray[3][3] = new Town2();
        mapArray[3][2] = new Mystery();
        mapArray[4][3] = new Route2();
        mapArray[3][4] = new Route5();
        mapArray[3][5] = new TrumpCity();
        mapArray[4][5] = new Route6();
        mapArray[5][5] = new Route7();
        mapArray[6][5] = new Route8();
        mapArray[7][5] = new Route9();
        mapArray[5][4] = new Route12();
        mapArray[5][3] = new Route4();
        mapArray[5][2] = new Amishville();
        mapArray[4][2] = new Route3();
        mapArray[8][5] = new Easmos();
        mapArray[9][9] = new AmuletH1();
        mapArray[10][10] = new AmuletH2();
        mapArray[10][8] = new AmuletH3();
        mapArray[10][6] = new AmuletLab();
        mapArray[10][4] = new Town2H1();
        mapArray[9][7] = new T2PokeC();
        mapArray[11][1] = new Town2H2();
        mapArray[9][3] = new T2PokeMart();
        mapArray[9][1] = new TrumpPokeMart();
        mapArray[10][2] = new TrumpPokeC();
        mapArray[11][9] = new TrumpH1();
        mapArray[11][7] = new AmishMart();
        mapArray[11][5] = new AmishPokeC();
        mapArray[11][3] = new AmishH1();
        mapArray[11][1] = new EasmosPokeC();
        
    }
    
    /*
    Clears backlog of pressed keys
    pre: none
    post: pressed List cleared
    */
    public void clear()
    {
        pressed.clear();
    }
    
    /*
    Paints everything on screen
    pre: none
    post: screen updated
    */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int cx = mapX, cy = mapY, adjustX = 0, adjustY = 0;
        if (storeSelected) {
            adjustX = 32 * 8;
            adjustY = 32 * 1;
            cx -= adjustX;
            cy -= adjustY;
        }
        g.drawImage(map, cx, cy, null);
        paintOutMaps(g, cx, cy);
        paintNPCs(g, cx, cy);
        if (!surf) {
            BufferedImage character = Main.it.getCharacter();
            if (character != null) {
                g.drawImage(character, 32 * 12 - adjustX, 32 * 9 - (character.getHeight()) - adjustY, null);
            }
        } else {
            p.drawImage(g, Main.it.charactersurf[orientation][position % 2], 32 * 12 - 15, 32 * 9 - (Main.it.charactersurf[orientation][position % 2].getHeight()) - 25, 2);
        }
        g.drawImage(currentMap.getTop(), cx, cy, null);
        if (fadeIn || fadeOut) {
            Color color = p.fadeRect(g, opacity);
        }
        if (store && !storeSelected) {
            p.drawImage(g, options[Main.getMap().getStoreIndex()], 0, 0, 3.225);
        } else if (storeSelected) {
            Store.drawStore(g);
        } else {
            if (menu) {
                drawMenu(g);
            }
            if (grid) {
                drawGrid(g);
            }
        }
        if (dialoguetext.size() > 0) {
            drawDialogue(g);
            if (chooseSurf) {
                drawOptions(g, "Yes", " No", surfindex);
            }
        }
    }
    
    /*
    Paints the maps surrounding the map that the player is currently on. The map
    the player is one is interactible, while the surrounding one's give the
    illusion of a continued world.
    pre: none
    post: Maps surrounding currently interactible map painted
    */
    public void paintOutMaps(Graphics g, int x, int y)
    {
        try {
            g.drawImage(mapArray[worldX + 1][worldY].getImage(), x + currentMap.getImage().getWidth(), y + mapArray[worldX + 1][worldY].getShift(1), null);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
        }
        try {
            g.drawImage(mapArray[worldX - 1][worldY].getImage(), -mapArray[worldX - 1][worldY].getImage().getWidth() + x, y + mapArray[worldX - 1][worldY].getShift(3), null);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
        }
        try {
            g.drawImage(mapArray[worldX][worldY + 1].getImage(), x + mapArray[worldX][worldY + 1].getShift(2), y - mapArray[worldX][worldY + 1].getImage().getHeight(), null);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
        }
        try {
            g.drawImage(mapArray[worldX][worldY - 1].getImage(), x + mapArray[worldX][worldY - 1].getShift(0), y + mapArray[worldX][worldY].getImage().getHeight(), null);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
        }
        
    }
    
    /*
    Paints NPCs on map that the player is currently in
    pre: none
    post: none (cX and cY should be related to map's location on screen)
    */
    public void paintNPCs(Graphics g, int cX, int cY)
    {
        for (int i = 0; i < (currentMap.getImage().getHeight() / 32); i++) {
            for (int j = 0; j < (currentMap.getImage().getWidth() / 32); j++) {
                try {
//(8-y +Main.Main.getMap().getpY())*32 (12-x +Main.Main.getMap().getpX())*32 12+j - pX
                    g.drawImage(currentMap.getEvent(j, i).getSprite(), cX + j * 32, cY + i * 32 - 16, null);
                } catch (NullPointerException e) {
                    
                }
            }
        }
    }
    
    /*
    Method which draws the menu when the player chooses to open it.
    pre: none
    post: menu drawn
    */
    public void drawMenu(Graphics g)
    {
        int width = 130;
        p.drawMenu(g, Main.w.getWidth() - 9 - width, 0, width, 25 + menuitems.length * 30);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Pokemon FireLeaf", Font.PLAIN, 24));
        int textY = 5, adjust = 18;
        for (int a = 0; a < menuitems.length; a++) {
            g.drawString(menuitems[a], Main.w.getWidth() + adjust - width, textY += 30);
        }
        g.drawImage(p.menucursor, Main.w.getWidth() - 7 - width, 15 + menuindex * 30, 25, 25, null);
//        g.setColor(color);
    }
    
//    public void drawGrid(Graphics g, Color color)
    public void drawGrid(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.drawLine(Main.w.getWidth() / 2, 0, Main.w.getWidth() / 2, Main.w.getHeight());
        g.drawLine(0, Main.w.getHeight() / 2, Main.w.getWidth(), Main.w.getHeight() / 2);
        g.setColor(Color.GRAY);
        for (int a = 1; 32 * a < Main.w.getWidth(); a++) {
            if (32 * a < Main.w.getHeight()) {
                g.drawLine(0, 32 * a, Main.w.getWidth(), 32 * a);
            }
            g.drawLine(32 * a, 0, 32 * a, Main.w.getHeight());
        }
//        g.setColor(color);
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
        for (int a = 0; a < words.length; a++) {
            int width = (int) (font.getStringBounds(line + words[a], frc).getWidth());
            if (width > 700) {
                p.drawString1(g, line, 50, 450 + num * 40, Color.BLACK, Color.GRAY);
                num++;
                line = "";
                if (num > 1) {
                    for (int b = a; b < words.length; b++) {
                        line += words[b] + " ";
                    }
                    if (dialoguetext.size() > 1 && !dialoguetext.get(1).startsWith(line)) {
                        List<String> modified = new ArrayList<>();
                        modified.add(dialoguetext.get(0));
                        modified.add(line);
                        modified.addAll(dialoguetext.subList(1, dialoguetext.size() - 1));
                        dialoguetext.clear();
                        dialoguetext.addAll(modified);
                    } else if (!dialoguetext.get(dialoguetext.size() - 1).equalsIgnoreCase(line)) {
                        dialoguetext.add(line);
                    }
                    return true;
                }
                line = words[a] + " ";
            } else {
                line += words[a] + " ";
            }
        }
        if (!line.equals("")) {
            p.drawString1(g, line, 50, 450 + num * 40, Color.BLACK, Color.GRAY);
        }
        return true;
    }
    
    public void drawOptions(Graphics g, String option1, String option2, int index)
    {
        p.drawMenu(g, 650, 270, 130, 130);
        p.drawString1(g, option1, 710, 320, Color.BLACK, Color.GRAY);
        p.drawString1(g, option2, 710, 370, Color.BLACK, Color.GRAY);
        p.drawImage(g, p.menucursor, 650, 287 + 50 * index, 3.225);
    }
    
    public void trainerBattle(List<Pokemon> pokemon)
    {
        Main.music.stop();
        Main.music = Main.music("./Audio/Pokemon Origins Wild Pokemon Music.wav");
        Main.music.start();
        fadeOut(1, false);
        bp = new BattlePanel(pokemon, "Grass");
        Thread thread = new Thread(bp);
        Main.w.Switch(Main.map, bp);
        bp.revalidate();
        thread.start();
    }
    
    public void playMusic(){
        Main.music.stop();
        Main.music = Main.music("./Audio/Overworld Music.wav");
        Main.music.loop(-1);
    }
    
    public void fadeIn(double speed)
    {
        if (!fadeOut) {
            fadeIn = true;
            opacity = 320;
            while (fadeIn) {
                opacity--;
                repaint();
                p.delay(speed);
                if (opacity <= 0) {
                    fadeIn = false;
                    start = 0;
                    a = 0;
                }
                Thread.yield();
            }
        }
    }
    
    public void fadeOut(double speed, boolean running)
    {
        if (!fadeIn) {
            clear();
            fadeOut = true;
            opacity = 0;
            while (fadeOut) {
                opacity++;
                repaint();
                p.delay(speed);
                if (opacity > 255) {
                    fadeOut = false;
                    this.running = running;
                }
                Thread.yield();
            }
        }
        repaint();
    }
}
