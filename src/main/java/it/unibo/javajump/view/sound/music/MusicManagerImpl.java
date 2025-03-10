package it.unibo.javajump.view.sound.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

import static it.unibo.javajump.utility.Constants.*;

public class MusicManagerImpl implements MusicManager {

	private static Clip backgroundClip;
	private static FloatControl volumeControl;
	private static Thread fadeThread;
	private static final float VOLUME = AUDIOVOLUME;


	public static void loadBackgroundMusic(String filePath) {
		try {
			File audioFile = new File(filePath);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(audioFile);
			backgroundClip = AudioSystem.getClip();
			backgroundClip.open(audioIn);

			if (backgroundClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
				volumeControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
			} else {
				volumeControl = null;
			}
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
	}


	public static void startMusic() {
		if (backgroundClip == null) {
			return;
		}
		if (backgroundClip.isRunning()) {
			return;
		}

		int totalFrames = backgroundClip.getFrameLength();
		int loopEnd = (int) (totalFrames * AUDIOLOOPEND);
		backgroundClip.setLoopPoints(AUDIOLOOPSTART, loopEnd);
		backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
		backgroundClip.start();
		setVolume(VOLUME);
	}


	public static void stopMusic() {
		if (backgroundClip != null && backgroundClip.isRunning()) {
			backgroundClip.stop();
			backgroundClip.setFramePosition(AUDIOFRAMEINIT);
		}
	}


	public static void pauseMusic() {
		if (backgroundClip != null && backgroundClip.isRunning()) {
			backgroundClip.stop();
		}
	}


	public static void setVolume(float vol) {
		if (volumeControl == null) {
			return;
		}

		float min = volumeControl.getMinimum();
		float max = volumeControl.getMaximum();
		float dB = min + (max - min) * vol;
		volumeControl.setValue(dB);
	}


	public static void fadeOut(final float durationSeconds) {
		if (backgroundClip == null || volumeControl == null) {
			return;
		}


		if (fadeThread != null && fadeThread.isAlive()) {
			fadeThread.interrupt();
		}

		fadeThread = new Thread(() -> {
			try {

				float min = volumeControl.getMinimum();

				float currentVol = volumeControl.getValue();

				int steps = AUDIOSTEPS;
				float stepTime = durationSeconds / steps;

				for (int i = 0; i < steps; i++) {

					float alpha = (float) i / (steps - AUDIOSTEPSDECREASE);
					float newVol = currentVol + alpha * (min - currentVol);
					volumeControl.setValue(newVol);
					Thread.sleep((long) (stepTime * AUDIOSLEEP));
				}

				stopMusic();

				setVolume(VOLUME);
			} catch (InterruptedException e) {

			}
		});
		fadeThread.start();
	}
}
