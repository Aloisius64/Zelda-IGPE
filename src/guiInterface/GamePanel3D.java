/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiInterface;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.ChaseCamera;
import com.jme3.input.KeyInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.post.filters.FogFilter;
import com.jme3.scene.Spatial;
import com.jme3.system.AppSettings;
import com.jme3.texture.Texture2D;
import com.jme3.ui.Picture;
import com.jme3.util.SkyFactory;
import common.Direction;
import eventState.WiiMoteListener3D;
import gameLogic.GameManager;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.CharacterState;
import gameLogic.world.ConcreteWorld;
import guiInterface.character3D.Hero3D;
import guiInterface.character3D.Startable;
import guiInterface.character3D.Warrior3D;
import guiInterface.character3D.Wizard3D;
import guiInterface.startScreen.MenuPanel;
import java.util.Random;
import staticConstant.StaticConstantGame;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

/**
 * 
 * @author Aloisius
 */
public class GamePanel3D extends SimpleApplication implements ActionListener, AnalogListener
{

	// World;
	ConcreteWorld concreteWorld;
	// Hero
	private Hero3D hero;
	private static boolean left = false, right = false, up = false, down = false,
			physicAttack = false, distanceAttack = false, twistAttack = false;
	private boolean released = false;
	// camera
	private static boolean changeCameraView = false;
	ChaseCamera chaseCam;
	int indexCurrentView = 0;
	private final float[] horizontalCamera = { -1.57f, 3.14f, 1.57f, 0.0f, };
	private final int[][] mapMovement = {
			{ Direction.DOWN, Direction.RIGHT, Direction.UP, Direction.LEFT },
			{ Direction.RIGHT, Direction.UP, Direction.LEFT, Direction.DOWN },
			{ Direction.UP, Direction.LEFT, Direction.DOWN, Direction.RIGHT },
			{ Direction.LEFT, Direction.DOWN, Direction.RIGHT, Direction.UP } };
	// Spatial del livello
	private Spatial forestLevel;
	// Effetti del livello
	private FilterPostProcessor fpp;
	private FogFilter fog;
	public static boolean move = false;
	// Effetto ultimo cuore
	public static boolean updateGuiNode = false;
	// Utility
	private final Random random = new Random();
	private int counterTwits = 0;
	private boolean ending = false;

	@Override
	public void simpleInitApp()
	{
		StaticConstantGame.rootNode = rootNode;
		StaticConstantGame.assetManager = getAssetManager();
		new GameManager().start();
		hero = ((Hero3D) StaticConstantGame.gameManager.getHero());
		concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		loadScene();
		initFog();
		addLight();
		setupKeys();
		if (StaticConstantGame.wiimoteActive)
		{
			try
			{
				createWiiMoteConnection();
			}
			catch (Exception ex)
			{
				// Logger.getLogger(GamePanel3D.class.getName()).log(Level.SEVERE,
				// null, ex);
			}
		}
		// inizializzo la camera
		chaseCam = new ChaseCamera(cam,
				((Hero3D) StaticConstantGame.gameManager.getHero()).getModel(), inputManager);
		chaseCam.setDefaultHorizontalRotation(horizontalCamera[indexCurrentView]);
		chaseCam.setDefaultVerticalRotation(0.6236328f);
		chaseCam.setDefaultDistance(8.0f);
		// HUD
		paintLife();
		paintMagic();
		paintNameAndScore();
		StaticConstantGame.application3D = this;
		for (int i = 0; i < concreteWorld.getWidth(); i++)
		{
			for (int j = 0; j < concreteWorld.getHeight(); j++)
			{
				AbstractCharacter cellCharacterWorld = concreteWorld.getCellCharacterWorld(i, j);
				if (cellCharacterWorld instanceof Startable)
				{
					((Startable) cellCharacterWorld).start();
				}
			}
		}

		rootNode.attachChild(SkyFactory.createSky(assetManager,
				"Textures/Sky/Bright/BrightSky.dds", false));
	}

	@Override
	public void simpleUpdate(float tpf)
	{
		super.simpleUpdate(tpf);

		if (StaticConstantGame.gameRunning)
		{

			StaticConstantGame.gameManager.update();

			if (updateGuiNode)
			{
				guiNode.detachAllChildren();
				paintLife();
				paintMagic();
				paintNameAndScore();
				updateGuiNode = false;
			}

			if (changeCameraView)
			{
				manageCamera(hero.getCurrentDirection());
				changeCameraView = false;
			}

			if (right)
			{
				hero.manageChangeDirection(mapMovement[indexCurrentView][3]);
				hero.setCurrentDirection(mapMovement[indexCurrentView][3]);
				hero.setState(CharacterState.WALK);
			}
			else if (left)
			{
				hero.manageChangeDirection(mapMovement[indexCurrentView][1]);
				hero.setCurrentDirection(mapMovement[indexCurrentView][1]);
				hero.setState(CharacterState.WALK);
			}
			else if (down)
			{
				hero.manageChangeDirection(mapMovement[indexCurrentView][2]);
				hero.setCurrentDirection(mapMovement[indexCurrentView][2]);
				hero.setState(CharacterState.WALK);
			}
			else if (up)
			{
				hero.manageChangeDirection(mapMovement[indexCurrentView][0]);
				hero.setCurrentDirection(mapMovement[indexCurrentView][0]);
				hero.setState(CharacterState.WALK);
			}
			else
			{
				hero.setState(CharacterState.STAND);
			}

			if (twistAttack)
			{
				hero.setState(CharacterState.SPECIAL_ATTACK);
				twistAttack = false;
				counterTwits = 0;
			}
			else if (physicAttack)
			{
				hero.setState(CharacterState.PHYSIC_ATTACK);
				hero.setIndexAnimation(random.nextInt(4) + 4);
				physicAttack = false;
				counterTwits = 0;
			}

			if (distanceAttack)
			{
				updateGuiNode = true;
				hero.setIndexAnimation(8);
				hero.setState(CharacterState.DISTANCE_ATTACK);
				distanceAttack = false;
			}
		}
		else
		{
			if (!ending)
			{
				paintEnding();
				ending = true;
			}
		}
	}

	public void configure()
	{
		setShowSettings(false);
		setDisplayStatView(false);
		setDisplayFps(false);
		setSettings(new AppSettings(true));
		settings.setHeight(720);
		settings.setWidth(1300);
	}

	private void loadScene()
	{
		// Importo il livello nella scena
		forestLevel = getAssetManager().loadModel("Scenes/Forest3D/FinalLevel.j3o");
		forestLevel.move(19.5f, 0.0f, 19.0f);
		rootNode.attachChild(forestLevel);
	}

	private void initFog()
	{
		// Effetto nebbia
		fpp = new FilterPostProcessor(assetManager);
		fog = new FogFilter();
		fog.setFogColor(new ColorRGBA(ColorRGBA.Gray));
		fog.setFogDistance(30);
		fog.setFogDensity(2.0f);
		fpp.addFilter(fog);
		viewPort.addProcessor(fpp);
	}

	private void addLight()
	{
		DirectionalLight sun = new DirectionalLight();
		sun.setDirection((new Vector3f(-0.5f, -0.5f, -0.5f)).normalizeLocal());
		sun.setColor(ColorRGBA.White);
		rootNode.addLight(sun);

		AmbientLight ambient = new AmbientLight();
		ambient.setColor(ColorRGBA.White);
		rootNode.addLight(ambient);
	}

	@Override
	public void onAction(String binding, boolean pressed, float tpf)
	{
		if (binding.equals("Close"))
		{
			System.exit(0);
		}

		if (binding.equals("CharLeft"))
		{
			if (pressed)
			{
				left = true;
			}
			else
			{
				left = false;
			}
		}
		else if (binding.equals("CharRight"))
		{
			if (pressed)
			{
				right = true;
			}
			else
			{
				right = false;
			}
		}
		else if (binding.equals("CharUp"))
		{
			if (pressed)
			{
				up = true;
			}
			else
			{
				up = false;
			}
		}
		else if (binding.equals("CharDown"))
		{
			if (pressed)
			{
				down = true;
			}
			else
			{
				down = false;
			}
		}

		if (binding.equals("ChangeView"))
		{
			if (pressed)
			{
				changeCameraView = true;
			}
		}

		if (binding.equals("PhysicAttack"))
		{
			if (pressed)
			{
				physicAttack = true;
				twistAttack = false;
				released = false;
			}
			else
			{
				physicAttack = false;
				released = true;
			}
		}

		if (binding.equals("DistanceAttack"))
		{
			if (pressed)
			{
				distanceAttack = true;
			}
		}
	}

	@Override
	public void onAnalog(String name, float value, float tpf)
	{
		if (name.equals("PhysicAttack"))
		{
			hero.setState(CharacterState.STAND);
			counterTwits++;
			if (counterTwits > 20 && released)
			{
				hero.setIndexAnimation(9);
				twistAttack = true;
				physicAttack = false;
				counterTwits = 0;
				released = false;
			}
		}
	}

	private void setupKeys()
	{
		inputManager.addMapping("Close", new KeyTrigger(KeyInput.KEY_U));
		inputManager.addMapping("ChangeView", new KeyTrigger(KeyInput.KEY_O));
		inputManager.addMapping("CharLeft", new KeyTrigger(KeyInput.KEY_A));
		inputManager.addMapping("CharRight", new KeyTrigger(KeyInput.KEY_D));
		inputManager.addMapping("CharUp", new KeyTrigger(KeyInput.KEY_W));
		inputManager.addMapping("CharDown", new KeyTrigger(KeyInput.KEY_S));
		inputManager.addMapping("PhysicAttack", new KeyTrigger(KeyInput.KEY_P));
		inputManager.addMapping("DistanceAttack", new KeyTrigger(KeyInput.KEY_L));

		inputManager.addListener(this, "Close");
		inputManager.addListener(this, "ChangeView");
		inputManager.addListener(this, "CharLeft");
		inputManager.addListener(this, "CharRight");
		inputManager.addListener(this, "CharUp");
		inputManager.addListener(this, "CharDown");
		inputManager.addListener(this, "CharSpace");
		inputManager.addListener(this, "CharShoot");
		inputManager.addListener(this, "PhysicAttack");
		inputManager.addListener(this, "DistanceAttack");
	}

	private void manageCamera(Direction currentDirection)
	{
		switch (currentDirection.getTypeDirection())
		{
			case Direction.UP:
				indexCurrentView = 2;
				break;
			case Direction.DOWN:
				indexCurrentView = 0;
				break;
			case Direction.RIGHT:
				indexCurrentView = 1;
				break;
			case Direction.LEFT:
				indexCurrentView = 3;
				break;
			default:
				break;
		}
		chaseCam.setDefaultHorizontalRotation(horizontalCamera[indexCurrentView]);
	}

	private void createWiiMoteConnection() throws Exception
	{
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
		if (wiimotes.length > 0)
		{
			Wiimote wiimote = wiimotes[0];
			wiimote.activateMotionSensing();
			wiimote.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListener3D();
			wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimote = wiimote;
		}
		else
		{
			throw new Exception("I cannot find any wiimote");
		}
	}

	private void paintLife()
	{
		int hearts = hero.getHearts();
		int initWidth = 5;
		for (int i = 0; i < hearts; i++)
		{
			Picture heart = new Picture("Hearts");
			heart.setWidth(50);
			heart.setHeight(50);
			heart.setTexture(assetManager,
					(Texture2D) assetManager.loadTexture("Textures/heart.png"), true);
			if (i == hearts - 1)
			{
				heart.setWidth(80);
				heart.setHeight(80);
				heart.setPosition(initWidth + (i * 55), 640);
			}
			else
			{
				heart.setPosition(initWidth + (i * 55), 650);
				heart.setWidth(50);
				heart.setHeight(50);
			}
			getGuiNode().attachChild(heart);
		}
	}

	private void paintMagic()
	{
		int energy = hero.getMagicEnergy();
		int maxEnergy = hero.getMaximumMagicEnergy();
		Picture energyContainer = new Picture("EnergyContainer");
		energyContainer.setWidth(200);
		energyContainer.setHeight(25);
		energyContainer.setTexture(assetManager,
				(Texture2D) assetManager.loadTexture("Textures/energyBar.png"), true);
		energyContainer.setPosition(10, 620);
		getGuiNode().attachChild(energyContainer);

		int widthSingleCell = 180 / maxEnergy;
		int heightSingleCell = 14;
		for (int i = 0; i < energy; i++)
		{
			Picture cellEnergy = new Picture("cellEnergy");
			cellEnergy.setWidth(widthSingleCell);
			cellEnergy.setHeight(heightSingleCell);
			cellEnergy.setTexture(assetManager,
					(Texture2D) assetManager.loadTexture("Textures/cellEnergy.png"), true);
			cellEnergy.setPosition(20 + (i * widthSingleCell), 625);
			getGuiNode().attachChild(cellEnergy);
		}
	}

	private void paintNameAndScore()
	{
		BitmapText hudText = new BitmapText(guiFont, false);
		hudText.setSize(guiFont.getCharSet().getRenderedSize());
		hudText.setColor(ColorRGBA.Yellow);
		hudText.setText(StaticConstantGame.nameHero + "\t Score: " + hero.getScore());
		hudText.setLocalTranslation(20, 60, 0);
		hudText.setSize(30);
		guiNode.attachChild(hudText);
	}

	private void paintEnding()
	{
		BitmapText hudText = new BitmapText(guiFont, false);
		hudText.setSize(guiFont.getCharSet().getRenderedSize());
		hudText.setColor(ColorRGBA.Yellow);
		hudText.setText("Game Over.. Press Esc");
		hudText.setLocalTranslation(100, 300, 0);
		hudText.setSize(30);
		guiNode.attachChild(hudText);
	}

	public static boolean isLeft()
	{
		return left;
	}

	public static void setLeft(boolean left)
	{
		GamePanel3D.left = left;
	}

	public static boolean isRight()
	{
		return right;
	}

	public static void setRight(boolean right)
	{
		GamePanel3D.right = right;
	}

	public static boolean isUp()
	{
		return up;
	}

	public static void setUp(boolean up)
	{
		GamePanel3D.up = up;
	}

	public static boolean isDown()
	{
		return down;
	}

	public static void setDown(boolean down)
	{
		GamePanel3D.down = down;
	}

	public static boolean isPhysicAttack()
	{
		return physicAttack;
	}

	public static void setPhysicAttack(boolean physicAttack)
	{
		GamePanel3D.physicAttack = physicAttack;
	}

	public static boolean isDistanceAttack()
	{
		return distanceAttack;
	}

	public static void setDistanceAttack(boolean distanceAttack)
	{
		GamePanel3D.distanceAttack = distanceAttack;
	}

	public static boolean isChangeCameraView()
	{
		return changeCameraView;
	}

	public static void setChangeCameraView(boolean changeCameraView)
	{
		GamePanel3D.changeCameraView = changeCameraView;
	}

	@Override
	public void destroy()
	{
		super.destroy();

		ConcreteWorld world = StaticConstantGame.gameManager.getConcreteWorld();

		for (int i = 0; i < world.getWidth(); i++)
		{
			for (int j = 0; j < world.getHeight(); j++)
			{
				AbstractCharacter character = world.getCellCharacterWorld(i, j);

				if (character instanceof Wizard3D)
				{
					((Wizard3D) character).setAlive(false);
				}
				if (character instanceof Warrior3D)
				{
					((Warrior3D) character).setAlive(false);
				}
			}

		}

		StaticConstantGame.enable3D = false;
		StaticConstantGame.gameRunning = false;
		MenuPanel menuPanel = new MenuPanel(StaticConstantGame.mainFrame);
		StaticConstantGame.mainFrame.switchPanel(menuPanel);
	}
}
