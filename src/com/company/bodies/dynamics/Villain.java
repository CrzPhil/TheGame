package com.company.bodies.dynamics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Villain extends DynamicBody {
    private static final PolygonShape villainShape = new PolygonShape(-4.31f,-4.58f,
            4.8f,-4.46f,
            4.8f,4.4f,
            -4.06f,4.44f);

    private static BodyImage sphinxImage = new BodyImage("data/graphics/sphinx.png", 10f);

    private int health;

    private static SoundClip villainDamage;

    static {
        try {
            villainDamage = new SoundClip("data/music/enemydamage.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println(exc);
        }
    }

    public Villain(World world) {
        super(world, villainShape);
        addImage(sphinxImage);
        this.setPosition(new Vec2(-20.5f, 5f));
        this.health = 3;
    }

    // Health gets decremented and damage sound gets played
    public void takeDamage() {
        health--;
        villainDamage.play();
    }

    public int getHealth() {
        return health;
    }

    public void setSphinxImage(BodyImage sphinxImage) {
        removeAllImages();
        Villain.sphinxImage = sphinxImage;
        addImage(sphinxImage);
    }
}
