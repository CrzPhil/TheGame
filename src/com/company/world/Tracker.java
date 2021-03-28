package com.company.world;

import city.cs.engine.BodyImage;
import city.cs.engine.SoundClip;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import com.company.bodies.dynamics.Spikeball;
import com.company.bodies.statics.Text;
import com.company.main.Game;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;

/**
 *     Tracker mainly used in level 3 to spawn balls randomly until the Spartan has a score of >25.
 *     A Randomly generated value is used here which is either 0,1,2,3, or 4.
 *     Accordingly, a ball will spawn in one of those five positions, as specified in the {@link Spikeball} class.
 */

public class Tracker implements StepListener {
    private static SoundClip yeah;

    private final Game game;
    /** Control variable to simulate a timer.& Balls will spawn roughly every second.
     */
    // Control-variable for preStep method
    private int temp=0;
    // We want to randomly generate balls in one of five locations (left-up;left;middle-up;right-up;right)
    /**
     * Used to generate a number between one and five to then later spawn the balls in a random location.
     */
    Random r = new Random();
    /** Used to control the sequence manage when the balls will spawn or not.
     */
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

    /**
     * Points local game field to the one in the constructor
     * @param game parent Game field
     */
    public Tracker( Game game) {
        this.game = game;
    }

    /**
     * Used to randomly spawn Spikeballs in one of five locations.
     * Creates a random value between 0 and 5, then, using the constructor of the {@link Spikeball} class,
     * spawns the balls in one of those locations.
     * When the Hero destroys 15 or more balls, the level is considered beaten and he moves on.
     * @param stepEvent
     */
    @Override
    public void preStep(StepEvent stepEvent) {
        if (run) {
            // If the Spartan manages to destroy 15 Balls, level 3 will be considered beaten.
            if (game.getWorld().getHero().getScore() >= 15) {
                sequence = false;
                new Text(game.getWorld(), new BodyImage("data/graphics/level3done.png")).setPosition(new Vec2(0, 0));
                // We stop this whole block from executing (i.e stop more Texts from spawning on top of each other etc)
                run = false;
                yeah.play();
                game.goToNextLevel();
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

    /**
     * Not used in this instance.
     * @param stepEvent
     */
    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
