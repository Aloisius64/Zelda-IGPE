package staticConstant;

import gameLogic.GameManager;
import guiInterface.GamePanel2D;
import guiInterface.Navi;
import guiInterface.startScreen.MainFrame;

import java.awt.event.KeyEvent;

import profile.Profile;
import profile.ProfileManager;
import threadState.MessageBoardManager;
import threadState.ObjectManager;
import threadState.SphereManager;
import threadState.WorldUpdater;
import wiiusej.Wiimote;
import wiiusej.wiiusejevents.utils.WiimoteListener;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;

public class StaticConstantGame
{
	// Main Frame
	public static MainFrame mainFrame = null;
	public static int typePanelActive = -1;
	// Navi
	public static boolean activeNavi = true;
	public static Navi navi = null;
	// Profilo scelto
	public static ProfileManager profileManager = null;
	public static Profile currentProfile = null;
	//
	public static String nameHero = "Link";
	public static String currentMessage = " ";
	public static boolean timerActive = false;
	public static int time = 0;
	//
	public static GameManager gameManager;
	public static SphereManager sphereManager;
	public static ObjectManager objectManager;
	public static WorldUpdater worldUpdater;
	public static MessageBoardManager messageBoardManager;
	public static boolean paused = false;
	public static boolean gameRunning = false;
	public static GamePanel2D gamePanel2D = null;
	public static boolean loading = false;
	// Effects
	public static boolean fog = false;
	public static boolean rain = false;
	public static boolean cloud = false;
	public static boolean volcanoFog = false;
	public static boolean darkness = false;
	public static int typeDarkness = 0;
	// Parametri del gioco
	public static int typeAI = 1;
	public static int typeGameLogic = 0;
	public static int typeWorldChoose = 1;
	public static int typeWorldLogic = 0;
	// Pulsanti controller
	public static int moveUp = KeyEvent.VK_UP;
	public static int moveDown = KeyEvent.VK_DOWN;
	public static int moveLeft = KeyEvent.VK_LEFT;
	public static int moveRight = KeyEvent.VK_RIGHT;
	public static int attack1 = KeyEvent.VK_Z;
	public static int attack2 = KeyEvent.VK_X;
	// Costanti per la grafica
	public static int xResolution = 720;
	public static int yResolution = 720;
	public static int timeAttackSphere = 5;
	public static int timeGanondorf = 900;
	public static int timeSkull = 500;
	public static int timeWarrior = 800;
	public static int timeWizard = 800;
	// Controller
	public static boolean wiimoteActive = false;
	public static Wiimote wiimote = null;
	public static WiimoteListener wiimoteListener = null;
	// 3D //
	public static boolean enable3D = false;
	public static SimpleApplication application3D;
	public static Node rootNode;
	public static AssetManager assetManager;
	// Network
	public static boolean isPlayerOne = false;
	public static boolean networkGame = false;
}
