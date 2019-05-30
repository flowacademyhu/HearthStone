package Music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class InnkeeperSpeech implements Runnable {

    MenuMusic menuMusic = new MenuMusic();

    Thread innkeeper;
    Clip clip;

    @Override
    public void run() {

        innkeeper = new Thread();
        try {
            clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("./src/Music/Innkeeper.wav"));
            clip.open(inputStream);
            clip.start();
            innkeeper.sleep(5000);
            stop();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void stop() {
        menuMusic.run();
        clip.stop();
    }

    public void stopMenuMusic() {
        menuMusic.stop();
    }

}
