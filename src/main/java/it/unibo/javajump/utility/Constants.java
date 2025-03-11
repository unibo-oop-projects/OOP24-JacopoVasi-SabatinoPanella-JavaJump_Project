package it.unibo.javajump.utility;


public final class Constants {

	//MAIN
	public static final int SCREENWIDTH = 600;

	public static final int SCREENHEIGHT = 800;

	public static final int MINSPACING = 50;

	public static final int MAXSPACING = 150;

	public static final float COINCHANCE = 0.3f;

	public static final float SCOREFACTOR = 0.5f;

	public static final float GRAVITY = 1350;

	public static final float ACCELERATION = 3000;

	public static final float MAXSPEED = 400;

	public static final float DECELERATION = 15000;

	public static final String GAMETITLE = "JAVA JUMP";

	//Controller
	public static final double FPS = 60;

	public static final double NANOSECONDS_PER_SECOND = 1000000000;

	public static final int NULLDIRECTION = 0;

	public static final int LEFTDIRECTION = -1;

	public static final int RIGHTDIRECTION = 1;

	public static final int SLEEPTHREAD = 1;

	//Camera
	public static final int OFFSETINIT = 0;

	public static final float HEIGHTDIV = 2;

	public static final float WIDTHDIV = 0.05f;

	//Collision Manager
	public static final int COINSCOREVALUE = 50;

	//CharacterImpl
	public static final int VELOCITYINIT = 0;


	//Physics
	public static final int NULLVELOCITY = 0;

	//Moving Platform
	public static final int NULLPLATFORMVELOCITY = 0;

	//Platform
	public static final int TOPPLATFORMY_INIT = 0;

	//Strategy
	public static final int SPAWNY_INIT = 0;

	//Generic Numbers
	public static final int FLIPMAX = 1;
	public static final int FLIPMIN = -1;
	public static final int SCREENLEFTMARGIN = 0;
	public static final int BGHORIZONTALOFFSETINIT = 0;
	public static final int BGTRANSITIONTIMERINIT = 0;
	public static final int BGHORIZONTALNULLSPEED = 0;
	public static final int RENDERCOINROWINIT = 0;
	public static final int RENDERPLAYERANIMTIMERINIT = 0;
	public static final int RENDERPLAYERFRAMEGETIMGY = 0;
	public static final int RENDERPLAYERFRAMEY = 0;
	public static final int RENDERPLAYERFRAMEX = 0;
	public static final int RENDERUISCORECONTAINERX = 0;
	public static final int RENDERUISCORECONTAINERY = 0;
	//Object Factory

	public static final float CHARACTERWIDTH = 48;
	public static final float CHARACTERHEIGHT = 50;
	public static final float CHARACTERJUMPFORCE = 700;
	public static final float STANDARDPLATFORMWIDTH = 100;
	public static final float PLATFORMHEIGHT = 10;
	public static final float RANDOMPLATFORMWIDTH = 80;
	public static final int RANDOMPLATFORMRNGFACTOR = 41;
	public static final float MOVINGPLATFORMWIDTH = 70;
	public static final float MOVINGPLATFORMRANGE = 50;
	public static final float MOVINGPLATFORMSPEED = 30;
	public static final int MOVINGPLATFORMWIDTHRNGFACTOR = 45;
	public static final float MOVINGPLATFORMRANGERNGFACTOR = 100;
	public static final int MOVINGPLATFORMSPEEDRNGFACTOR = 50;
	public static final float BREAKABLEPLATFORMWIDTH = 80;
	public static final int BREAKABLEPLATFORMRNGFACTOR = 25;
	public static final float BOUNCEPLATFORMWIDTH = 80;
	public static final int BOUNCEPLATFORMRNGFACTOR = 25;
	public static final float COINWIDTH = 44;
	public static final float COINHEIGHT = 52;

	// Collectibles Spawner
	public static final float COINXDIV = 2;
	public static final float COINXMUL = 0.2f;
	public static final float COINOFFSET = 50;

	//Difficulty
	public static final int SCOREINIT = 0;
	public static final String SCORETEXT = "Score: ";
	public static final int HELLMIN = 16000;
	public static final int HELLMAX = 25000;
	public static final int VERYHARDMIN = 8000;
	public static final int VERYHARDMAX = 10000;
	public static final int HARDMIN = 4000;
	public static final int HARDMAX = 6000;
	public static final int MEDIUMMIN = 1500;
	public static final int MEDIUMMAX = 3000;

	//Spawn Utils
	public static final int XOFFSET = 40;
	public static final int YOFFSET = 60;

	// Random Spaen Strategy
	public static final int MAXPLATFORMWIDTH = 120;
	public static final int GAPOFFSETTEN = 10;
	public static final int GAPOFFSETTHIRTY = 30;
	public static final int GAPINIT = 0;
	public static final String DIFFICULTYTEXT = "Difficulty: ";

	// CLean up manager
	public static final float MARGIN = 50;

	// Spawn Manager
	public static final int INITIALYOFFSET = 150;
	public static final int INITIAL_PLATFORMS_NUMBER = 10;
	public static final int PROCEDURAL_PLATFORMS_NUMBER = 10;
	public static final float SPAWN_THRESHOLD = 400f;

	// Game Model
	public static final float CHARACTERCREATIONWIDTHDIV = 2;
	public static final float CHARACTERCREATIONHEIGHTMUL = 0.8f;

	// Background
	public static final int EXTRATILES = 2;

	// CoinImpl Render
	public static final float COINANIMTIMERS = 0;
	public static final int COINCYCLEDURATION = 6;
	public static final int COINIDXMAX = 7;

	// Player Render
	public static final int PLAYERCYCLEDURATION = 2;
	public static final int PLAYERFIRSTFRAME = 0;
	public static final int PLAYERSECONDFRAME = 1;
	public static final int PLAYERTHIRDFRAME = 2;
	public static final int PLAYERFOURTHFRAME = 3;

	// Score Render
	public static final int SCORERENDERX = 10;
	public static final int SCORERENDERY = 20;
	public static final int HIGHSCORERENDERY = 45;
	public static final int HIGHSCORERENDERX = 10;
	public static final String SCORERENDERTEXT = "Score:   ";
	public static final String HIGHSCORERENDERTEXT = "New High Score !!";

	// Render Manager
	public static final float RENDERMANAGERPLATFORMOUTLINE = 2;
	public static final int RENDERMANAGERPLATFORMARCW = 10;
	public static final int RENDERMANAGERPLATFORMARCH = 10;
	public static final int RENDERMANAGERCOINWIDTH = 44;
	public static final int RENDERMANAGERCOINHEIGHT = 52;
	public static final float RENDERMANAGERCOINFRAMEDURATION = 0.05f;
	public static final int RENDERMANAGERPLAYERWIDTH = 48;
	public static final int RENDERMANAGERPLAYERHEIGHT = 50;
	public static final float RENDERMANAGERPLAYERFRAMEDURATION = 0.2f;
	public static final float RENDERMANAGERBACKGROUNDPARALLAXONE = 0.2f;
	public static final float RENDERMANAGERBACKGROUNDPARALLAXTWO = 0.4f;
	public static final float RENDERMANAGERBACKGROUNDSPEEDXONE = 0;
	public static final float RENDERMANAGERBACKGROUNDSPEEDTWO = 20;
	public static final float TRANSITION_DURATION_1 = 0.8f;
	public static final float TRANSITION_DURATION_2 = 1.2f;


	// Audio Manager
	public static final float MUSIC_VOLUME = 0.7f;
	public static final float SOUND_EFFECTS_VOLUME = 0.75f;

	public static final int MUSIC_LOOP_START = 0;
	public static final float MUSIC_LOOP_END = 0.885f;

	public static final int AUDIOFRAMEINIT = 0;
	public static final int AUDIOSTEPS = 50;
	public static final int AUDIOSLEEP = 1000;

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
