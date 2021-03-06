package Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class MenuMusic implements Runnable {

    Thread menuMusic;
    Clip clip;

    @Override
    public void run() {

        menuMusic = new Thread();
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./src/Music/Main_Title.wav"));
            clip.open(inputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void stop() {
        clip.stop();
    }

}
