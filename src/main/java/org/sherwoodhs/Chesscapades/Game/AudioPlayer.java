package org.sherwoodhs.Chesscapades.Game;

import java.io.*;
import javax.sound.sampled.*;


//Audioplayer class

public class AudioPlayer {
    //PLAY METHOD
    //Used to start specified .wav file
    public static void play(String filename) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
