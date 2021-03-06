package com.dungeonsecrets.sound;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class MusicManager {

    public static MusicThread music;

    public static Clip clip;

    public MusicManager(String musicPath) {

        try {

            URL url = this.getClass().getClassLoader().getResource(musicPath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

            clip = AudioSystem.getClip();
            clip.open(audioIn);

            // Reduce volume by 15 decibels.
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f);
            clip.loop(Clip.LOOP_CONTINUOUSLY);

            clip.start();

            //No idea how this works, but I'm convinced its 50% black magic
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

    }

    public static void stop(){

        clip.stop();

    }

}

