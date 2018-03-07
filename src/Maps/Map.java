/*
Map class which the blueprint for a map class. All maps extend this class

 */
package Maps;

import Events.Event;
import Main.p;
import Pokemon.Pokemon;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julian bardin
 */
public class Map
{

    private String name;
    private int[][] colArray;
    protected Event[][] eventArray;
    private BufferedImage image, top;
    private int x, y;
    protected int[] shifts;
    protected List<String> grassPokemon, waterPokemon, fishPokemon;
    protected boolean[] show;
    protected int bLevel, hLevel;
    protected List<String> items = new ArrayList<>();

    /*
    Constructor which instantiates the Map class. Map is never instnatiated
    directly, it is called in all classes which inherit Map. 
    pre: The files for the map image, map top image, and collision arreay must
    exist.
    post: Class is instantiated and can be accessed in MapManager (where it is loaded)
    */
    public Map(String name)
    {
        readFile(name);
        shifts = new int[4];
        show = new boolean[]
        {
            true, true, true, true
        };
//        System.out.println(x + ", " + y);
        eventArray = new Event[y][x];
    }

    /*
    This is responsible for loading in all the file associated with the Map. the
    the name pased during instantiation. This gets the map image, the mostly-
    transparent top layer (used for depth) and the collision array.
    pre: All needed files are present and an according name is passed
    post: Images and collision array are loaded in.
    */
    
    public void readFile(String name)
    {
        image = p.loadImage("./Maps/" + name + ".png");
        top = p.loadImage("./Maps/" + name + " Top.png");
        File file = new File("./Maps/" + name + ".txt");
        int[] row = null;
        String[] rowS;
        String temp;

        try
        {
            FileReader reader = new FileReader(file);
            BufferedReader br = new BufferedReader(reader);
            x = Integer.parseInt(br.readLine());
            y = Integer.parseInt(br.readLine());
            colArray = new int[y][x];
            row = new int[x];

            for (int i = 0; i < y; i++)
            {
                temp = br.readLine();
                rowS = temp.split(" ");
                for (int j = 0; j < x; j++)
                {
                    row[j] = Integer.parseInt(rowS[j]);
                }
//                System.out.println(row [1]);
                colArray[i] = row.clone();
//                System.out.println(colArray [i] [1]);
//                System.out.println("");
            }

            br.close();
        } catch (IOException ex)
        {

        }
    }

    /*
    A getter for accessing the map's image.
    pre: Image is loaded
    post: Image is returned
    */    
    public BufferedImage getImage()
    {
        return image;
    }

    /*
    A getter for getting the collision value of a tile in the collision array
    pre:collision array is loaded and that x and y are within the bounds of the
    array.
    post: Integer of tile value returned
    */
    public int getTile(int x, int y)
    {
        return colArray[y][x];
    }

    /*
    Returns the top image, which is mostly transparent but has the part of the
    map rendered on top of the play i.e. tree tops
    pre: The top image was loaded in
    post: BufferedImage of top returned
    */
    public BufferedImage getTop()
    {
        return top;
    }

    /*
    This returns the shift of the map relative to bordering maps. This is because
    in mapArray in MapManager, adjacent maps are just adjacent. This creates the
    illusion that maps are not all lined up in a perfect grid. It is always a 
    multiple of 32 as a tile is 32 x 32 pixels.
    pre: none
    post: int of shift returned.
    */
    public int getShift(int n)
    {
        return shifts[n];
    }

    /*
    Returns the list of pokemon that can be found in tall grass
    pre: none
    post: List of names returned
    */    
    public List<String> getGrassPokemon()
    {
        return grassPokemon;
    }

    /*
    Returns the list of pokemon that can be found while surfing
    pre: none
    post: List of names returned
    */
    public List<String> getWaterPokemon()
    {
        return waterPokemon;
    }

    /*
    Each map has a range of levels that wild Pokemon can spawn as. This returns
    the bottom number of that range.
    pre: none
    post: Int for bottom of level range returned
    */
    public int getbLevel()
    {
        return bLevel;
    }

    /*
    This returns the value for the upper number of the level range.
    pre: none
    post: Int for bottom of level range returned
    */
    public int gethLevel()
    {
        return hLevel;
    }

    /*
    This returns the list of pokemon that can be caught while fishing.
    pre: none
    post: List of names returned
    */
    public List<String> getFishPokemon()
    {
        return fishPokemon;
    }
    
    /*
    This returns the event at the given tile. Events include teleports, signs,
    and NPCs. This allows the player to interact. 
    pre: none
    post: Event returned
    */
    public Event getEvent (int x, int y)
    {
        try {
        return eventArray[y][x];
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            return null;
        }
    }

}
