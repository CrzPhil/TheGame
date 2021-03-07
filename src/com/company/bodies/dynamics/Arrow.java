package com.company.bodies.dynamics;

import city.cs.engine.*;
import com.company.levels.GameLevel;
import org.jbox2d.common.Vec2;

/*
    Arrow class is used in the first level, for three sequences of arrow-rain.
    They get destroyed on-collision with spears, or the floor.
    They cause damage to the Hero.
 */

public class Arrow extends DynamicBody {

    private static final Shape arrowShape = new PolygonShape(-0.18f, -2.04f,
            -0.44f, -1.17f,
            -0.34f, 2.02f,
            -0.02f, 2.03f,
            0.08f, -1.18f);

    private static final BodyImage arrowImage = new BodyImage("data/graphics/enemy_arrow.png", 5f);
    private final AttachedImage arrowAttached = new AttachedImage(this, arrowImage, 1f, 0, new Vec2(0,0));

    // Will track arrows fired per Sequence, so that the Sequence can end after 'x' arrows fired
    private static int arrowCount = 0;

    public Arrow(GameLevel world) {
        super(world, arrowShape);
        arrowCount++;
    }

    // Getter
    public static int getArrowCount() {
        return arrowCount;
    }

    // Reset arrow count after each sequence of Arrow-Rain
    public static void resetArrows() {
        arrowCount = 0;
    }
}