package it.unibo.javajump.view.sound.sfx;

/**
 * Interface that describes the manager of sound effects.
 */
public interface SoundEffectsManager {
    /**
     * Plays a requested sound.
     *
     * @param type the type of sound requested
     */
    void playSound(SFXType type);

    /**
     * Sets global volume.
     *
     * @param vol the desired global volume
     */
    void setGlobalVolume(float vol);
}
