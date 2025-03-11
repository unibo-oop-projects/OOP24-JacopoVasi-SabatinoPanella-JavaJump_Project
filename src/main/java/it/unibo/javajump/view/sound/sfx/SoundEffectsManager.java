package it.unibo.javajump.view.sound.sfx;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static it.unibo.javajump.utility.Constants.*;

public class SoundEffectsManager {
	private FloatControl volumeControl;
	private final float defaultVolume;
	private final Map<SFXType, Clip> clips = new HashMap<>();

	public SoundEffectsManager(float defaultVolume) {
		this.defaultVolume = defaultVolume;
		clips.put(SFXType.BOUNCE, loadClip(RESOURCESWINDOWSPATH + RESOURCE_BOUNCE_SFX));
		clips.put(SFXType.BREAK, loadClip(RESOURCESWINDOWSPATH + RESOURCE_BREAK_SFX));
		clips.put(SFXType.DEFAULT, loadClip(RESOURCESWINDOWSPATH + RESOURCE_DEFAULT_SFX));
		clips.put(SFXType.COIN, loadClip(RESOURCESWINDOWSPATH + RESOURCE_COIN_SFX));
		setGlobalVolume(defaultVolume);
	}

	private Clip loadClip(String filePath) {
		try {
			File audioFile = new File(filePath);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			return clip;
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void playSound(SFXType type) {
		Clip clip = clips.get(type);
		if (clip != null) {
			clip.setFramePosition(0);
			clip.start();
		}
	}

	public void setGlobalVolume(float vol) {
		for (Clip clip : clips.values()) {
			if (clip != null && clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				float min = control.getMinimum();
				float max = control.getMaximum();
				float dB = min + (max - min) * vol;
				control.setValue(dB);
			}
		}
	}
}
