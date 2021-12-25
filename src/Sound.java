package com.group17;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {
    File file;
    Clip clip;

    public Sound() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        file = new File("teeth.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip = AudioSystem.getClip();
        clip.open(audioStream);
    }

    //start the music
    public void playMusic(){
        clip.start();
    }
}
