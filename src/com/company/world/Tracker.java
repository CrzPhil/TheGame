package com.company.world;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import com.company.bodies.dynamics.Hero;
import com.company.bodies.dynamics.Spikeball;
import com.company.bodies.statics.Text;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;

public class Tracker implements StepListener {
    private static SoundClip yeah;
    private MyView view;
    private Game game;
    // Control-variable for preStep method
    private int temp=0;
    // We want to randomly generate balls in one of five locations (left-up;left;middle-up;right-up;right)
    Random r = new Random();
    // While sequence is true, balls will spawn
    boolean sequence = true;
    // Run will prevent the preStep() method of running indefinitely even after level 3 is beaten
    boolean run = true;

    static {
        try {
            yeah = new SoundClip("data/music/yeah.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    public Tracker(MyView view, Game game) {
        this.view = view;
        this.game = game;
    }

    public Tracker(MyView view) {
        this.view = view;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (run) {
            // If the Spartan manages to destroy 20 Balls, level 3 will be considered beaten.
            if (game.getWorld().getHero().getScore() >= 25) {
                sequence = false;
                new Text(game.getWorld(), new BodyImage("data/graphics/level3done.png")).setPosition(new Vec2(0, 0));
                // We stop this whole block from executing (i.e more Texts to spawn on top of eachother etc)
                run = false;
                yeah.play();
            }
            if (sequence) {
                // This blob of code executes roughly every two seconds, which will help us in spawning spikeBalls in level 3
                temp++;
                if (temp % 60 == 0) {
                    // We generate a number between 0(incl)-5(excl) and generate a spike in that location
                    int randLoc = r.nextInt(5);
                    new Spikeball(game.getWorld(), randLoc);
                    temp = 0;
                }
            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
