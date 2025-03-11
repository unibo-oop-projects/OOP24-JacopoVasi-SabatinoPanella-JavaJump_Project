package it.unibo.javajump.view.sound.sfx;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static it.unibo.javajump.utility.Constants.RESOURCES_PATH;
import static it.unibo.javajump.utility.Constants.RESOURCE_BOUNCE_SFX;
import static it.unibo.javajump.utility.Constants.RESOURCE_BREAK_SFX;
import static it.unibo.javajump.utility.Constants.RESOURCE_COIN_SFX;
import static it.unibo.javajump.utility.Constants.RESOURCE_DEFAULT_SFX;
import static it.unibo.javajump.utility.Constants.SOUNDS_POOL_SIZE_NUMBER;

/**
 * The type Sound effects manager.
 */
public class SoundEffectsManager {
    private final float defaultVolume;
    private final Map<SFXType, Queue<Clip>> clipPools = new HashMap<>();
    private static final int POOL_SIZE = SOUNDS_POOL_SIZE_NUMBER;

    /**
     * Instantiates a new Sound effects manager.
     *
     * @param defaultVolume the default volume
     */
    public SoundEffectsManager(final float defaultVolume) {
        this.defaultVolume = defaultVolume;

        for (final SFXType type : SFXType.values()) {
            Queue<Clip> pool = new ConcurrentLinkedQueue<>();
            for (int i = 0; i < POOL_SIZE; i++) {
                Clip clip = loadClip(getFilePathForType(type));
                if (clip != null) {
                    setVolumeForClip(clip, defaultVolume);
                    if (!pool.offer(clip)) {
                        throw new IllegalStateException("GameAction Queue is full, cannot add: " + clip);
                    }
                }
            }
            clipPools.put(type, pool);
        }
        setGlobalVolume(defaultVolume);
    }

    private String getFilePathForType(SFXType type) {
        return switch (type) {
            case COIN -> RESOURCES_PATH + RESOURCE_COIN_SFX;
            case BOUNCE -> RESOURCES_PATH + RESOURCE_BOUNCE_SFX;
            case BREAK -> RESOURCES_PATH + RESOURCE_BREAK_SFX;
            case DEFAULT -> RESOURCES_PATH + RESOURCE_DEFAULT_SFX;
        };
    }

    private Clip loadClip(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            return clip;
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Logger.getLogger(SoundEffectsManager.class.getName()).log(Level.SEVERE, "Error loading the audio file", e);
        }
        return null;
    }


    private void setVolumeForClip(Clip clip, float defaultVolume) {
        if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float min = control.getMinimum();
            float max = control.getMaximum();
            float dB = min + (max - min) * defaultVolume;
            control.setValue(dB);
        }
    }

    /**
     * Play sound.
     *
     * @param type the type
     */
    public void playSound(SFXType type) {
        Queue<Clip> pool = clipPools.get(type);
        if (pool == null) return;

        Clip clip = pool.poll();
        if (clip == null) {
            clip = loadClip(getFilePathForType(type));
            if (clip != null) {
                setVolumeForClip(clip, defaultVolume);
            }
        }

        if (clip != null) {
            clip.setFramePosition(0);
            Clip finalClip = clip;
            clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                    if (event.getType() == LineEvent.Type.STOP) {
                        finalClip.removeLineListener(this);

                        if (!pool.offer(finalClip)) {
                            throw new IllegalStateException("GameAction Queue is full, cannot add: " + finalClip);
                        }
                    }
                }
            });
            clip.start();
        }
    }

    /**
     * Sets global volume.
     *
     * @param vol the vol
     */
    public void setGlobalVolume(float vol) {
        for (Queue<Clip> pool : clipPools.values()) {
            for (Clip clip : pool) {
                setVolumeForClip(clip, vol);
            }
        }
    }
}
