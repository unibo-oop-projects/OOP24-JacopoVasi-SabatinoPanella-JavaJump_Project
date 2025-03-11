package it.unibo.javajump.utility;


public final class Constants {

    //MAIN
    public static final int SCREEN_WIDTH = 600;

    public static final int SCREEN_HEIGHT = 800;

    public static final int MIN_SPACING = 50;

    public static final int MAX_SPACING = 150;

    public static final float COIN_CHANCE = 0.3f;

    public static final float SCORE_FACTOR = 0.5f;

    public static final float GRAVITY = 1350;

    public static final float ACCELERATION = 3000;

    public static final float MAX_SPEED = 400;

    public static final float DECELERATION = 15000;

    public static final String GAME_TITLE = "JAVA JUMP";

    //Controller
    public static final double FPS = 60;

    public static final double NANOSECONDS_PER_SECOND = 1000000000;

    public static final int NULL_DIRECTION = 0;

    public static final int LEFT_DIRECTION = -1;

    public static final int RIGHT_DIRECTION = 1;

    public static final int SLEEP_THREAD = 1;

    //Camera
    public static final int OFFSET_INIT = 0;

    public static final float HEIGHT_DIV = 2;

    public static final float WIDTH_DIV = 0.05f;

    //Collision Manager
    public static final int COIN_SCORE_VALUE = 50;

    //CharacterImpl
    public static final int VELOCITY_INIT = 0;


    //Physics
    public static final int NULL_VELOCITY = 0;

    //Moving Platform
    public static final int NULL_PLATFORM_VELOCITY = 0;

    //Platform
    public static final int TOP_PLATFORM_Y_INIT = 0;

    //Strategy
    public static final int SPAWN_Y_INIT = 0;

    //Generic Numbers
    public static final int FLIP_MAX = 1;
    public static final int FLIP_MIN = -1;
    public static final int SCREEN_LEFT_MARGIN = 0;
    public static final int BG_HORIZONTAL_OFFSET_INIT = 0;
    public static final int BG_TRANSITION_TIMER_INIT = 0;
    public static final int BG_HORIZONTAL_NULL_SPEED = 0;
    public static final int RENDER_COIN_IDLE_ROW = 0;
    public static final int RENDER_COIN_COLLECT_ROW = 1;
    public static final int RENDER_PLAYER_ANIM_TIMER_INIT = 0;
    public static final int RENDER_PLAYER_FRAME_GET_IMG_Y = 0;
    public static final int RENDER_PLAYER_FRAME_Y = 0;
    public static final int RENDER_PLAYER_FRAME_X = 0;
    public static final int RENDER_UI_SCORE_CONTAINER_X = 0;
    public static final int RENDER_UI_SCORE_CONTAINER_Y = 0;

    //Object Factory
    public static final float CHARACTER_WIDTH = 48;
    public static final float CHARACTER_HEIGHT = 50;
    public static final float CHARACTER_JUMP_FORCE = 700;
    public static final float STANDARD_PLATFORM_WIDTH = 100;
    public static final float PLATFORM_HEIGHT = 10;
    public static final float RANDOM_PLATFORM_WIDTH = 80;
    public static final int RANDOM_PLATFORM_RNG_FACTOR = 41;
    public static final float MOVING_PLATFORM_WIDTH = 70;
    public static final float MOVING_PLATFORM_RANGE = 50;
    public static final float MOVING_PLATFORM_SPEED = 30;
    public static final int MOVING_PLATFORM_WIDTH_RNG_FACTOR = 45;
    public static final float MOVING_PLATFORM_RANGE_RNG_FACTOR = 100;
    public static final int MOVING_PLATFORM_SPEED_RNG_FACTOR = 50;
    public static final float BREAKABLE_PLATFORM_WIDTH = 80;
    public static final int BREAKABLE_PLATFORM_RNG_FACTOR = 25;
    public static final float BOUNCE_PLATFORM_WIDTH = 80;
    public static final int BOUNCE_PLATFORM_RNG_FACTOR = 25;
    public static final float COIN_WIDTH = 44;
    public static final float COIN_HEIGHT = 52;

    // Collectibles Spawner
    public static final float COIN_X_DIV = 2;
    public static final float COIN_X_MUL = 0.2f;
    public static final float COIN_OFFSET = 50;

    //Difficulty
    public static final int SCORE_INIT = 0;
    public static final int HELL_MIN = 16000;
    public static final int HELL_MAX = 25000;
    public static final int VERY_HARD_MIN = 8000;
    public static final int VERY_HARD_MAX = 10000;
    public static final int HARD_MIN = 4000;
    public static final int HARD_MAX = 6000;
    public static final int MEDIUM_MIN = 1500;
    public static final int MEDIUM_MAX = 3000;

    //Spawn Utils
    public static final int SPAWN_X_OFFSET = 40;
    public static final int SPAWN_Y_OFFSET = 60;

    // Random Spawn Strategy
    public static final int MAX_PLATFORM_WIDTH = 120;
    public static final int GAP_EASY_ADDENDUM = 10;
    public static final int GAP_HARD_ADDENDUM = 30;
    public static final int GAP_INIT = 0;

    // CLean up manager
    public static final float CLEAN_UP_MARGIN_OFFSET = 50;

    // Spawn Manager
    public static final int INITIAL_Y_SPAWN_OFFSET = 150;
    public static final int INITIAL_PLATFORMS_NUMBER = 10;
    public static final int PROCEDURAL_PLATFORMS_NUMBER = 10;
    public static final float SPAWN_THRESHOLD = 400f;

    // Game Model
    public static final float CHARACTER_CREATION_WIDTH_DIV = 2;
    public static final float CHARACTER_CREATION_HEIGHT_MUL = 0.8f;

    // Background
    public static final int BG_EXTRA_TILES_NUMBER = 2;

    // CoinImpl Render
    public static final float COIN_ANIM_TIMER_START = 0;
    public static final int COIN_ANIMATION_CYCLE_DURATION = 6;
    public static final int COIN_ANIMATION_INDEX_MAX = 7;

    // Player Render
    public static final int PLAYER_ANIMATION_CYCLE_DURATION = 2;
    public static final int PLAYER_LANDING_START_FRAME = 0;
    public static final int PLAYER_LANDING_END_FRAME = 1;
    public static final int PLAYER_JUMP_START_FRAME = 2;
    public static final int PLAYER_JUMP_END_FRAME = 3;

    // Score Render
    public static final int SCORE_RENDER_X_POSITION = 10;
    public static final int SCORE_RENDER_Y_POSITION = 20;
    public static final int HIGH_SCORE_RENDER_Y_POSITION = 45;
    public static final int HIGH_SCORE_RENDER_X_POSITION = 10;
    public static final String SCORE_RENDER_TEXT = "Score:   ";
    public static final String HIGH_SCORE_RENDER_TEXT = "New High Score !!";

    // Render Manager
    public static final float RENDER_MANAGER_PLATFORM_OUTLINE_THICKNESS = 2;
    public static final int RENDER_MANAGER_PLATFORM_ROUND_CORNER_WIDTH = 10;
    public static final int RENDER_MANAGER_PLATFORM_ROUND_CORNER_HEIGHT = 10;
    public static final int RENDER_MANAGER_COIN_WIDTH = 44;
    public static final int RENDER_MANAGER_COIN_HEIGHT = 52;
    public static final float RENDER_MANAGER_COIN_FRAME_DURATION = 0.05f;
    public static final int RENDER_MANAGER_PLAYER_WIDTH = 48;
    public static final int RENDER_MANAGER_PLAYER_HEIGHT = 50;
    public static final float RENDER_MANAGER_PLAYER_FRAME_DURATION = 0.2f;
    public static final float RENDER_MANAGER_BACKGROUND_ONE_PARALLAX_MODIFIER = 0.2f;
    public static final float RENDER_MANAGER_BACKGROUND_TWO_PARALLAX_MODIFIER = 0.4f;
    public static final float RENDER_MANAGER_BACKGROUND_ONE_X_SPEED = 0;
    public static final float RENDER_MANAGER_BACKGROUND_TWO_X_SPEED = 20;
    public static final float TRANSITION_DURATION_1 = 0.8f;
    public static final float TRANSITION_DURATION_2 = 1.2f;


    // Audio Manager
    public static final float MUSIC_VOLUME = 0.7f;
    public static final float SOUND_EFFECTS_VOLUME = 0.75f;

    public static final int MUSIC_LOOP_START = 0;
    public static final float MUSIC_LOOP_END = 0.885f;

    public static final int AUDIO_FRAME_INIT = 0;
    public static final int AUDIO_STEPS = 50;
    public static final int AUDIO_SLEEP = 1000;

    public static final int SOUNDS_POOL_SIZE_NUMBER = 5;

    // In game view
    public static final int GAMEPLAY_MESSAGE_TIME_TOGGLE = 1700;

    // menu view
    public static final int MENUVIEWCENTERDIV = 2;
    public static final int MENUVIEWRECTX = 0;
    public static final int MENUVIEWRECTY = 0;
    public static final float MENUVIEWIMGWIDTHSCALAR = 0.03f;
    public static final int MENUVIEWIMGHEIGHTSCALAR = 5;
    public static final int MENUVIEWTILEWSCALAR = 1;
    public static final int MENUVIEWTILEHSCALAR = 1;
    public static final String MENUVIEWSTARTTEXT = "- Start Game (ENTER)";
    public static final String MENUVIEWQUITTEXT = "- Quit (ESC)";
    public static final int MENUVIEWXCENTER = 4;
    public static final int MENUVIEWSTARTYOFFSET = 30;
    public static final int MENUVIEWQUITYOFFSET = 70;
    public static final float MENUVIEWSTOKEWIDTH = 2f;
    public static final int MENUVIEWSTROKEMITERLIMIT = 0;
    public static final int MENUVIEWSTROKEARRAY = 9;
    public static final int MENUVIEWSTROKEDASH = 0;

    public static final int MENUVIEWROUNDRECTXDIV = 3;
    public static final int MENUVIEWROUNDRECTXOFF = 20;
    public static final int MENUVIEWROUNDRECTYOFF = 120;
    public static final int MENUVIEWROUNDRECTWIDTH = 400;
    public static final int MENUVIEWROUNDRECTHEIGHT = 40;
    public static final int MENUVIEWROUNDRECTARCW = 10;
    public static final int MENUVIEWROUNDRECTARCH = 10;

    public static final String MENUVIEWBESTSCORETEXT = "Best Score:   ";
    public static final int MENUVIEWBESTSCOREXDIV = 3;
    public static final int MENUVIEWBESTSCOREYOFF = 95;

    //GAMEOVERVIEW
    public static final float GAMEOVERALPHAINIT = 0;
    public static final float GAMEOVERDURATIONINIT = 1;
    public static final float GAMEOVERTIMEINIT = 0;
    public static final float GAMEOVERALPHA = 1;
    public static final int GAMEOVERCENTERDIV = 2;
    public static final int GAMEOVERRECTX = 0;
    public static final int GAMEOVERRECTY = 0;
    public static final float GAMEOVERIMGWOFF = 1.72f;
    public static final float GAMEOVERIMGHOFF = 0.15f;
    public static final float GAMEOVERIMGSCALEOFF = 1.1f;
    public static final String GAMEOVERNEWTEXT = "New Best Score:   ";
    public static final String GAMEOVERSCORETEXT = "Your Score:   ";
    public static final String GAMEOVERBESTTEXT = "Best Score:   ";
    public static final String GAMEOVERCONTINUETEXT = "Press ENTER to continue...";
    public static final float GAMEOVERTEXTXOFF = 0.65f;
    public static final int GAMEOVERTEXTNEWYOFF = 50;
    public static final int GAMEOVERTEXTSCOREYOFF = 50;
    public static final int GAMEOVERTEXTBESTYOFF = 80;
    public static final int GAMEOVERTEXTCONTINUEYOFF = 150;
    public static final String GAMEOVERNEWTEXTESC = " !!";

    // PAUSEVIEW
    public static final int PAUSECONTINUEY = 70;
    public static final int PAUSEMAINMENUY = 100;
    public static final int PAUSEQUITY = 130;
    public static final int PAUSESELECTIONX = 20;
    public static final String PAUSETEXT = "PAUSE";
    public static final String PAUSECONTINUETEXT = "Continue";
    public static final String PAUSEMAINMENUTEXT = "Main Menu";
    public static final String PAUSEQUITTEXT = "Quit";
    public static final String PAUSESELECTIONTEXT = "- ";
    public static final int PAUSECENTERDIV = 2;
    public static final int PAUSEWIDTHOFF = 50;

    //MAIN VIEW
    public static final int MAINVIEWCENTERDIV = 2;
    public static final float MAINVIEWAUDIOFADE = 2f;
    public static final int MAINVIEWDRAWXINIT = 0;
    public static final int MAINVIEWDRAWYINIT = 0;
    public static final int MAINVIEWRECTX = 0;
    public static final int MAINVIEWRECTY = 0;

    //COLOR CODING
    // 1- General colours
    public static final String BACKGROUND_DEFAULT_COLOR = "#05051C";
    public static final String GOLD_TEXT_COLOR = "#EAC10C";
    public static final String RED_TEXT_COLOR = "#F84534";

    // 2- GameObject colours
    public static final String PLATFORM_HIGHLIGHT_COLOR = "#D4C340";
    public static final String STANDARD_PLATFORM_COLOR = "#4D9F50";
    public static final String MOVING_PLATFORM_COLOR = "#276B91";
    public static final String BREAKABLE_PLATFORM_COLOR = "#EA4B1E";
    public static final String BOUNCE_PLATFORM_COLOR = "#D15484";

    public static final String OUTLINE_COLOR = "#0E081E";


    // Resources Path
    public static final String RESOURCES_PATH = "src/main/java/it/unibo/javajump/view/resources/";

    public static final String RESOURCES_TITLE = "JJ_Title.png";
    public static final String RESOURCES_SCORE_CONTAINER = "Score_Underlay_small.png";
    public static final String RESOURCES_GAMEOVER = "GameOver.png";
    public static final String RESOURCES_PLAYER = "Coffee-SheetBIG.png";
    public static final String RESOURCES_COIN = "Coin-Sheet.png";

    public static final String RESOURCES_BACKGROUND_EASY = "Background_Easy.png";
    public static final String RESOURCES_BACKGROUND_MEDIUM = "Background_Med.png";
    public static final String RESOURCES_BACKGROUND_HARD = "Background_Diff.png";

    public static final String RESOURCES_CLOUDS_EASY = "Clouds_Easy.png";
    public static final String RESOURCES_CLOUDS_MEDIUM = "Clouds_Med.png";
    public static final String RESOURCES_CLOUDS_HARD = "Clouds_Diff.png";

    public static final String RESOURCES_FONT_1 = "Daydream.ttf";
    public static final String RESOURCES_FONT_2 = "Daydream.ttf";
    public static final String RESOURCES_FONT_3 = "Daydream.ttf";

    public static final int SIZE_FONT_1 = 20;
    public static final int SIZE_FONT_2 = 15;
    public static final int SIZE_FONT_3 = 10;

    public static final String RESOURCES_MUSIC_1 = "GameMusic.wav";

    public static final String RESOURCE_BOUNCE_SFX = "Bounce.wav";
    public static final String RESOURCE_BREAK_SFX = "Break.wav";
    public static final String RESOURCE_DEFAULT_SFX = "Jump.wav";
    public static final String RESOURCE_COIN_SFX = "Coin.wav";


    private Constants() {
    }
}
