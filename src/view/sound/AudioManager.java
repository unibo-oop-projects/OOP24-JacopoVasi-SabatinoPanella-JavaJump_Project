package view.sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioManager {

	private static Clip backgroundClip;
	private static FloatControl volumeControl;
	private static Thread fadeThread;


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
		if (backgroundClip == null) return;
		if (backgroundClip.isRunning()) {

			return;
		}
		backgroundClip.setFramePosition(0);
		backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);

		backgroundClip.start();
		setVolume(1.0f);

		System.out.println("Starting music called");
	}


	public static void stopMusic() {
		if (backgroundClip != null && backgroundClip.isRunning()) {
			backgroundClip.stop();
			System.out.println("Stopping music called");
		}
	}


	public static void pauseMusic() {
		if (backgroundClip != null && backgroundClip.isRunning()) {
			backgroundClip.stop();
			System.out.println("Pausing music called");
		}
	}


	public static void resumeMusic() {
		if (backgroundClip != null && !backgroundClip.isRunning()) {
			backgroundClip.start();
		}
	}


	public static void setVolume(float vol) {
		if (volumeControl == null) return;


		float min = volumeControl.getMinimum();
		float max = volumeControl.getMaximum();
		float dB = min + (max - min) * vol;
		volumeControl.setValue(dB);
	}


	public static void fadeOut(final float durationSeconds) {
		if (backgroundClip == null || volumeControl == null) return;


		if (fadeThread != null && fadeThread.isAlive()) {
			fadeThread.interrupt();
		}

		fadeThread = new Thread(() -> {
			try {

				float min = volumeControl.getMinimum();
				float max = volumeControl.getMaximum();
				float currentVol = volumeControl.getValue();

				int steps = 50;
				float stepTime = durationSeconds / steps;

				for (int i = 0; i < steps; i++) {

					float alpha = (float)i/(steps-1);
					float newVol = currentVol + alpha*(min - currentVol);
					volumeControl.setValue(newVol);
					Thread.sleep((long)(stepTime*1000));
				}

				stopMusic();

				setVolume(1.0f);
			} catch (InterruptedException e) {

			}
		});
		fadeThread.start();
	}
}
