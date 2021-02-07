package com.company.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import com.company.world.MyWorld;
import org.jbox2d.common.Vec2;

public class Choice extends StaticBody {

    private static final BoxShape box = new BoxShape(1, 1);
    private BodyImage choiceImage;
    private boolean rightChoice;

    public Choice(MyWorld world, BodyImage choiceImage, float x, float y, boolean rightChoice) {
        super(world, box);
        this.choiceImage = choiceImage;
        setPosition(new Vec2(x, y));
        addImage(choiceImage);
        this.rightChoice = rightChoice;
    }

    public boolean isRightChoice() {
        return rightChoice;
    }
}
