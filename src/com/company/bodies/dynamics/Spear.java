package com.company.bodies.dynamics;

import city.cs.engine.*;
import com.company.levels.GameLevel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/*
    Spear class.
    Spears spawn on-click and will shoot from the Hero to wherever the mouse is pointing.
    (To see that implementation, see MouseHandler class)
    On-spawn, a *whoosh* sound will play.
 */

public class Spear extends DynamicBody {

    private static final PolygonShape spearShape = new PolygonShape(-0.13f,-2.33f,
            -0.35f,-2.14f,
            -0.37f,2.32f,
            0.34f,2.33f,
            0.35f,-1.44f,
            0.08f,-2.12f);

    private static final BodyImage spearImage = new BodyImage("data/graphics/spear.png", 5f);
    private static SoundClip whip;

    // Static function to load sound
    static {
        try {
            whip = new SoundClip("data/music/whip.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println(ex);
        }
    }

    // Constructor
    public Spear(GameLevel world) {
        super(world, spearShape);
        addImage(spearImage);
        whip.play();
    }
}

