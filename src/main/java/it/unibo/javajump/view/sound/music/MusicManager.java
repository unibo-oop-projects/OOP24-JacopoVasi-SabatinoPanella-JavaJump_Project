package it.unibo.javajump.view.sound.music;

public interface MusicManager {
	void loadBackgroundMusic(String filePath);

	void startMusic();

	void stopMusic();

	void pauseMusic();

	void resumeMusic();

	void fadeOut(float durationSeconds);

	void setVolume(float vol);
}
