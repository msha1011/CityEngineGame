import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {

    private static Clip backgroundClip;
    private static boolean muted = false;
    private static String currentBackgroundPath;

    public static void playSound(String filePath) {
        if (muted) return;

        try {
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Sound error: " + filePath);
        }
    }

    public static void playBackgroundMusic(String filePath) {
        currentBackgroundPath = filePath;

        if (muted) return;

        try {
            if (backgroundClip != null) {
                backgroundClip.stop();
                backgroundClip.close();
            }

            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File(filePath));
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioInput);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Background music error: " + filePath);
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundClip != null) {
            backgroundClip.stop();
            backgroundClip.close();
        }
    }

    public static void toggleMute() {
        muted = !muted;

        if (muted) {
            stopBackgroundMusic();
        } else if (currentBackgroundPath != null) {
            playBackgroundMusic(currentBackgroundPath);
        }
    }

    public static boolean isMuted() {
        return muted;
    }
}