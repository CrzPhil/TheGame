package com.company.bodies.statics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Checkpoint extends StaticBody {

    /*
        This class is for the Checkpoint flag found on some levels.
        On collision at the end of the level, the Player will have a new reset point
        in order for him not to have to restart from level 1.
     */

    private static final BoxShape flagShape = new BoxShape(1, 4);
    private static BodyImage redCheck = new BodyImage("data/graphics/checkpoint.png", 4);
    private static BodyImage greenCheck = new BodyImage("data/graphics/checkpointcompleted.png", 4);

    public Checkpoint(World world) {
        super(world, flagShape);
        this.addImage(redCheck);
        /*new AttachedImage(this, redCheck, 4, 0, new Vec2(0, 0));*/
        setPosition(new Vec2(-15.5f, 5f));
    }

    public Checkpoint(World world, BodyImage img) {
        super(world);
        this.addImage(greenCheck);
        this.addImage(img);
    }

    // Setter for updating Checkpoint image
    public void setImg(BodyImage img) {
        this.removeAllImages();
        redCheck = img;
        this.addImage(redCheck);
    }
}
