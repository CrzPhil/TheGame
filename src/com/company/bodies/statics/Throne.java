package com.company.bodies.statics;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class Throne extends StaticBody {
    private static final BoxShape throneShape = new BoxShape(5, 5);
    private static final BodyImage throne = new BodyImage("data/graphics/throne.png", 7);

    public Throne(World w) {
        super(w, throneShape);
        addImage(throne);
    }
}
