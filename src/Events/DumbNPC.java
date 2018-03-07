/*
The DumbNPC is the simplest NPC, it just talk.
 */
package Events;

import Main.p;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class DumbNPC extends Sign
{

    private static BufferedImage[][] sprites;
    private BufferedImage character;
    private int type, orientation;

    /*
    Instantiates the DumbNPC. It inherits Sign because it is effectively a sign
    with a sprite. The type is what NPC sprite itused, and the orientation is
    the direction the NPC is looking.
    pre: Type is between 0 and 28, and orientation is between 0 and 3
    post: NPC is initialized
    */
    public DumbNPC(int type, int orientation)
    {
        this.type = type;
        this.orientation = orientation;
    }

    /*
    This static method loads the sprites in for NPCs to use when being renderd.
    The first dimension is the type, the second is the orientation
    pre: The files exist
    post: Images loaded 
    */
    public static void loadSprites()
    {
        sprites = new BufferedImage[29][4];
        BufferedImage temp;
        for (int i = 1; i <= 29; i++)
        {
            temp = p.loadImage("./Characters/NPC " + i + ".png");
            for (int j = 0; j < 4; j++)
            {
                sprites[i-1][j] = temp.getSubimage(0, j * 48, 32, 48);
                
            }
        }
    }

    /*
    This returns the appropriate sprite for the specific NPC.
    pre: none
    post: BufferedImage returned
    */
    @Override
    public BufferedImage getSprite()
    {
        return sprites[type][orientation];
    }
}
