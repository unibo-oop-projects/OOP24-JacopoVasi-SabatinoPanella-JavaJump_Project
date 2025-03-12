package it.unibo.javajump.utility;

public final class TestConstants {

    public static final int KEY_EVENT_MODIFIER = 0;
    public static final float DELTA_TIME = 0.01f;
    public static final int DIV_TO_CENTER = 2;
    public static final int SCREEN_WIDTH = 800;
    public static final int SCREEN_HEIGHT = 600;
    public static final int COUNTER_START = 0;
    public static final int STARTING_SCORE = 0;
    public static final int SCORE_POINTS = 50;
    public static final int MAX_COUNT_PHYSICS = 6;
    public static final int MAX_COUNT_PACMAN = 100;
    public static final int MAX_COUNT_JUMPING = 200;
    public static final int MAX_COUNT_PLATFORM = 200;
    public static final int X_LEFT_SIDE_SCREEN = 0;
    public static final int HEIGHT_OFF_MULTIPLIER = 2;
    public static final int PLATFORM_OFFSET = 100;
    public static final int CAMERA_INCREASING_OFFSET = 150;
    public static final int CAMERA_DECREASING_OFFSET = 100;

    private TestConstants() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }

}
