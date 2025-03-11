package it.unibo.javajump.model.entities.platforms;

public interface BreakablePlatform extends Platform {

	boolean isBroken();

	void breakPlatform();

	void setFinished();

	boolean isFinished();
}
