package com.company.bodies.dynamics;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Hero extends Walker  {
    private static final Shape heroShape = new PolygonShape(-0.76f,2.77f,
            0.43f,2.76f,
            1.09f,1.95f,
            1.67f,-0.69f,
            1.69f,-2.25f,
            1.09f,-3.46f,
            -1.57f,-3.46f,
            -1.99f,1.19f);

    private static BodyImage heroImage = new BodyImage("data/graphics/spartan_idle.png", 7f);

    private int health;

    private static SoundClip damage;

    public int tt = 0;

    static {
        try {
            damage = new SoundClip("data/music/playerdamage.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Hero(World world) {
        super(world, heroShape);
        addImage(heroImage);
        this.health = 3;
        this.setPosition(new Vec2(8, -10));
    }

    // Setter, in order to update image according to the direction Hero is facing.
    public void setHeroImage(BodyImage direction) {
        removeAllImages();
        Hero.heroImage = direction;
        addImage(heroImage);
    }

    // Damage sound plays when Hero takes damage, and health is decremented
    public void takeDamage() {
        health--;
        damage.play();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
