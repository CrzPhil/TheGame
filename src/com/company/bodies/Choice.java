package com.company.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import com.company.world.MyWorld;
import org.jbox2d.common.Vec2;

public class Choice extends StaticBody {

    private static final BoxShape box = new BoxShape(1, 1);

    // Differentiate between right and wrong choices, so as to penalise wrong choice.
    private final boolean rightChoice;

    public Choice(MyWorld world, BodyImage choiceImage, float x, float y, boolean rightChoice) {
        super(world, box);
        // Different choices are displayed as images
        setPosition(new Vec2(x, y));
        addImage(choiceImage);
        this.rightChoice = rightChoice;
    }

    // Getter to check whether instance of this object is indeed the right choice
    public boolean isRightChoice() {
        return rightChoice;
    }
}
