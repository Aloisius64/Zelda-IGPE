package common;

import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;

import javax.swing.JLabel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantURL;
import staticConstant.StaticConstantURLMenuButton;

public class ImageProvider
{
	static Toolkit tk;
	static MediaTracker tracker;

	// Pausa
	public static Image gamePause;
	public static Image gamePauseContinue;
	public static Image gamePauseOption;
	public static Image gamePauseExit;

	// Navi
	public static Image[] navi = new Image[StaticConstantURL.NAVI.length];

	// Effetto Nebbia
	public static Image[] fog = new Image[StaticConstantURL.FOG.length];
	// Effetto Pioggia
	public static Image[] rain = new Image[StaticConstantURL.RAIN.length];
	// Effetto foschia del vulcano
	public static Image[] volcanoFog = new Image[StaticConstantURL.VOLCANO_FOG.length];
	// Effetto nuvole
	public static Image cloud;
	// Effetto buio
	public static Image[] darkness = new Image[StaticConstantURL.DARKNESS.length];

	// barra info eroe
	public static Image sword;
	public static Image scepter;
	public static Image energyBar;
	public static Image background;
	public static Image overground;
	public static Image printInfoArea;

	// Character
	public static Image hero_back;
	public static Image hero_front;
	public static Image hero_left;
	public static Image hero_right;
	public static Image[] heroWalkBack = new Image[StaticConstantURL.HERO_WALK_BACK.length];
	public static Image[] heroWalkFront = new Image[StaticConstantURL.HERO_WALK_BACK.length];
	public static Image[] heroWalkLeft = new Image[StaticConstantURL.HERO_WALK_BACK.length];
	public static Image[] heroWalkRight = new Image[StaticConstantURL.HERO_WALK_BACK.length];
	public static Image[] heroPhysicAttackBack = new Image[StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length];
	public static Image[] heroPhysicAttackFront = new Image[StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length];
	public static Image[] heroPhysicAttackLeft = new Image[StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length];
	public static Image[] heroPhysicAttackRight = new Image[StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length];

	public static Image[] skullWalkUp = new Image[StaticConstantURL.SKULL_WALK_UP.length];
	public static Image[] skullWalkDown = new Image[StaticConstantURL.SKULL_WALK_UP.length];
	public static Image[] skullWalkLeft = new Image[StaticConstantURL.SKULL_WALK_UP.length];
	public static Image[] skullWalkRight = new Image[StaticConstantURL.SKULL_WALK_UP.length];

	public static Image[] warriorWalkUp = new Image[StaticConstantURL.WARRIOR_WALK_UP.length];
	public static Image[] warriorWalkDown = new Image[StaticConstantURL.WARRIOR_WALK_UP.length];
	public static Image[] warriorWalkLeft = new Image[StaticConstantURL.WARRIOR_WALK_UP.length];
	public static Image[] warriorWalkRight = new Image[StaticConstantURL.WARRIOR_WALK_UP.length];
	public static Image[] warriorAttackUp = new Image[StaticConstantURL.WARRIOR_ATTACK_UP.length];
	public static Image[] warriorAttackDown = new Image[StaticConstantURL.WARRIOR_ATTACK_UP.length];
	public static Image[] warriorAttackLeft = new Image[StaticConstantURL.WARRIOR_ATTACK_UP.length];
	public static Image[] warriorAttackRight = new Image[StaticConstantURL.WARRIOR_ATTACK_UP.length];

	public static Image wizardUp;
	public static Image wizardDown;
	public static Image wizardLeft;
	public static Image wizardRight;

	public static Image[] ganondorfWalkUp = new Image[StaticConstantURL.GANONDORF_WALK_UP.length];
	public static Image[] ganondorfWalkDown = new Image[StaticConstantURL.GANONDORF_WALK_UP.length];
	public static Image[] ganondorfWalkLeft = new Image[StaticConstantURL.GANONDORF_WALK_UP.length];
	public static Image[] ganondorfWalkRight = new Image[StaticConstantURL.GANONDORF_WALK_UP.length];
	public static Image[] ganondorfAttackUp = new Image[StaticConstantURL.GANONDORF_ATTACK_UP.length];
	public static Image[] ganondorfAttackDown = new Image[StaticConstantURL.GANONDORF_ATTACK_UP.length];
	public static Image[] ganondorfAttackLeft = new Image[StaticConstantURL.GANONDORF_ATTACK_UP.length];
	public static Image[] ganondorfAttackRight = new Image[StaticConstantURL.GANONDORF_ATTACK_UP.length];

	public static Image[] attack_sphere = new Image[StaticConstantURL.ATTACK_SPHERE.length];

	public static Image empty_character;

	// Object
	public static Image start_point;
	public static Image final_point;
	public static Image platform;
	public static Image invisible_wall;
	public static Image heart;
	public static Image[] magic_sphere = new Image[StaticConstantURL.MAGIC_SPHERE.length];
	public static Image[] redCoin = new Image[StaticConstantURL.RED_COIN.length];
	public static Image[] blueCoin = new Image[StaticConstantURL.BLUE_COIN.length];
	public static Image[] greenCoin = new Image[StaticConstantURL.GREEN_COIN.length];
	public static Image[] button = new Image[StaticConstantURL.BUTTON.length];
	public static Image[] gate = new Image[StaticConstantURL.GATE.length];
	public static Image[] flower = new Image[StaticConstantURL.FLOWER.length];
	public static Image[] treasure = new Image[StaticConstantURL.TREASURE.length];
	public static Image[] flameTower = new Image[StaticConstantURL.FLAME_TOWER.length];
	public static Image block;
	public static Image[] skullHead = new Image[StaticConstantURL.SKULL_HEAD.length];
	public static Image[] magicGate = new Image[StaticConstantURL.MAGIC_GATE.length];
	public static Image[] fallPoint = new Image[StaticConstantURL.FALL_POINT.length];
	public static Image[] triforcePiece = new Image[StaticConstantURL.TRIFORCE_PIECE.length];

	public static Image empty_utility;

	// Scene
	public static Image wall;
	public static Image tree;
	public static Image treeGreen;
	public static Image treeViolet;
	public static Image trunk;
	public static Image xland;
	public static Image houseLarge;
	public static Image houseSmall;
	public static Image marbleScaleDown;
	public static Image marbleScaleUp;
	public static Image marbleScaleLeft;
	public static Image marbleScaleRight;
	public static Image statue;
	public static Image swordTrone;
	public static Image towerRoyal;
	public static Image land;
	public static Image landTerrain;
	public static Image discesaDown;
	public static Image discesaLeft;
	public static Image discesaUp;
	public static Image discesaRight;
	public static Image landTerrainCornerDownLeftInside;
	public static Image landTerrainCornerDownLeftOutside;
	public static Image landTerrainCornerDownRightInside;
	public static Image landTerrainCornerDownRightOutside;
	public static Image landTerrainCornerUpLeftInside;
	public static Image landTerrainCornerUpLeftOutside;
	public static Image landTerrainCornerUpRightInside;
	public static Image landTerrainCornerUpRightOutside;
	public static Image landTerrainDown;
	public static Image landTerrainLeft;
	public static Image landTerrainRight;
	public static Image landTerrainUp;
	public static Image mountainDown;
	public static Image mountainLeft;
	public static Image mountainRight;
	public static Image mountainUp;
	public static Image mountainCornerLeftDown;
	public static Image mountainCornerLeftUp;
	public static Image mountainCornerRightDown;
	public static Image mountainCornerRightUp;
	public static Image mountainCornerLeftDownInside;
	public static Image mountainCornerLeftUpInside;
	public static Image mountainCornerRightDownInside;
	public static Image mountainCornerRightUpInside;
	public static Image water;
	public static Image[] waterAngleDownLeft = new Image[StaticConstantURL.WATER_ANGLE_DOWN_LEFT.length];
	public static Image[] waterAngleDownRight = new Image[StaticConstantURL.WATER_ANGLE_DOWN_RIGHT.length];
	public static Image[] waterAngleUpLeft = new Image[StaticConstantURL.WATER_ANGLE_UP_LEFT.length];
	public static Image[] waterAngleUpRight = new Image[StaticConstantURL.WATER_ANGLE_UP_RIGHT.length];
	public static Image[] waterBorderDown = new Image[StaticConstantURL.WATER_BORDER_DOWN.length];
	public static Image[] waterBorderLeft = new Image[StaticConstantURL.WATER_BORDER_LEFT.length];
	public static Image[] waterBorderRight = new Image[StaticConstantURL.WATER_BORDER_RIGHT.length];
	public static Image[] waterBorderUp = new Image[StaticConstantURL.WATER_BORDER_UP.length];

	public static Image[] kakariko = new Image[7];
	public static Image[] desert = new Image[12];
	public static Image[] dungeon = new Image[32];
	public static Image[] dungeonAbove = new Image[3];
	public static Image[] forest = new Image[16];
	public static Image[][] lake = new Image[StaticConstantURL.LAKE.length][StaticConstantURL.LAKE[0].length];
	public static Image[] volcano = new Image[StaticConstantURL.VOLCANO.length];
	public static Image[] sky = new Image[StaticConstantURL.SKY.length];

	public ImageProvider()
	{

		tk = Toolkit.getDefaultToolkit();

		tracker = new MediaTracker(new JLabel());

		for (int i = 0; i < StaticConstantURL.NAVI.length; i++)
		{
			navi[i] = tk.getImage(StaticConstantURL.NAVI[i]);
			tracker.addImage(navi[i], 100);
		}

		sword = tk.getImage(StaticConstantURL.SWORD);
		scepter = tk.getImage(StaticConstantURL.SCEPTER);
		energyBar = tk.getImage(StaticConstantURL.ENERGY_BAR);
		background = tk.getImage(StaticConstantURL.BACKGROUND);
		overground = tk.getImage(StaticConstantURL.OVERGROUND);
		printInfoArea = tk.getImage(StaticConstantURL.AREA_PRINT_INFO);

		gamePause = tk.getImage(StaticConstantURLMenuButton.PAUSA);
		gamePauseContinue = tk.getImage(StaticConstantURLMenuButton.PAUSA_CONTINUA);
		gamePauseOption = tk.getImage(StaticConstantURLMenuButton.PAUSA_OPZIONI);
		gamePauseExit = tk.getImage(StaticConstantURLMenuButton.PAUSA_ESCI);

		tracker.addImage(sword, 0);
		tracker.addImage(scepter, 0);
		tracker.addImage(energyBar, 0);
		tracker.addImage(background, 0);
		tracker.addImage(overground, 0);
		tracker.addImage(printInfoArea, 0);

		loadScene();
		loadObject();
		loadCharacter();
		switch (StaticConstantGame.typeWorldChoose)
		{
			case 1:
				loadVillage();
				break;
			case 2:
				loadDesert();
				break;
			case 3:
				loadDungeon();
				break;
			case 4:
				loadForest();
				break;
			case 5:
				loadLake();
				break;
			case 6:
				loadVolcano();
				break;
			case 7:
				loadSky();
				break;
			default:
				loadVillage();
				loadDesert();
				loadDungeon();
				loadForest();
				loadLake();
				loadVolcano();
				loadSky();
				break;
		}

		loadEffect();

		try
		{
			tracker.waitForAll();
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException("images not loaded", e);
		}
	}

	private void loadCharacter()
	{
		// Hero
		hero_back = tk.getImage(StaticConstantURL.HERO_BACK);
		hero_front = tk.getImage(StaticConstantURL.HERO_FRONT);
		hero_left = tk.getImage(StaticConstantURL.HERO_LEFT);
		hero_right = tk.getImage(StaticConstantURL.HERO_RIGHT);

		for (int i = 0; i < StaticConstantURL.HERO_WALK_FRONT.length; i++)
		{
			heroWalkBack[i] = tk.getImage(StaticConstantURL.HERO_WALK_BACK[i]);
			heroWalkFront[i] = tk.getImage(StaticConstantURL.HERO_WALK_FRONT[i]);
			heroWalkLeft[i] = tk.getImage(StaticConstantURL.HERO_WALK_SX[i]);
			heroWalkRight[i] = tk.getImage(StaticConstantURL.HERO_WALK_DX[i]);
		}

		for (int i = 0; i < StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length; i++)
		{
			heroPhysicAttackBack[i] = tk.getImage(StaticConstantURL.HERO_PHYSIC_ATTACK_BACK[i]);
			heroPhysicAttackFront[i] = tk.getImage(StaticConstantURL.HERO_PHYSIC_ATTACK_FRONT[i]);
			heroPhysicAttackLeft[i] = tk.getImage(StaticConstantURL.HERO_PHYSIC_ATTACK_SX[i]);
			heroPhysicAttackRight[i] = tk.getImage(StaticConstantURL.HERO_PHYSIC_ATTACK_DX[i]);
		}

		// Skull
		for (int i = 0; i < StaticConstantURL.SKULL_WALK_UP.length; i++)
		{
			skullWalkUp[i] = tk.getImage(StaticConstantURL.SKULL_WALK_UP[i]);
			skullWalkDown[i] = tk.getImage(StaticConstantURL.SKULL_WALK_DOWN[i]);
			skullWalkLeft[i] = tk.getImage(StaticConstantURL.SKULL_WALK_LEFT[i]);
			skullWalkRight[i] = tk.getImage(StaticConstantURL.SKULL_WALK_RIGHT[i]);
		}

		// Warrior
		for (int i = 0; i < StaticConstantURL.WARRIOR_WALK_UP.length; i++)
		{
			warriorWalkUp[i] = tk.getImage(StaticConstantURL.WARRIOR_WALK_UP[i]);
			warriorWalkDown[i] = tk.getImage(StaticConstantURL.WARRIOR_WALK_DOWN[i]);
			warriorWalkLeft[i] = tk.getImage(StaticConstantURL.WARRIOR_WALK_LEFT[i]);
			warriorWalkRight[i] = tk.getImage(StaticConstantURL.WARRIOR_WALK_RIGHT[i]);
		}
		for (int i = 0; i < StaticConstantURL.WARRIOR_ATTACK_UP.length; i++)
		{
			warriorAttackUp[i] = tk.getImage(StaticConstantURL.WARRIOR_ATTACK_UP[i]);
			warriorAttackDown[i] = tk.getImage(StaticConstantURL.WARRIOR_ATTACK_DOWN[i]);
			warriorAttackLeft[i] = tk.getImage(StaticConstantURL.WARRIOR_ATTACK_LEFT[i]);
			warriorAttackRight[i] = tk.getImage(StaticConstantURL.WARRIOR_ATTACK_RIGHT[i]);
		}

		wizardUp = tk.getImage(StaticConstantURL.WIZARD_UP);
		wizardDown = tk.getImage(StaticConstantURL.WIZARD_DOWN);
		wizardLeft = tk.getImage(StaticConstantURL.WIZARD_LEFT);
		wizardRight = tk.getImage(StaticConstantURL.WIZARD_RIGHT);

		// Ganondorf
		for (int i = 0; i < StaticConstantURL.GANONDORF_WALK_UP.length; i++)
		{
			ganondorfWalkUp[i] = tk.getImage(StaticConstantURL.GANONDORF_WALK_UP[i]);
			ganondorfWalkDown[i] = tk.getImage(StaticConstantURL.GANONDORF_WALK_DOWN[i]);
			ganondorfWalkLeft[i] = tk.getImage(StaticConstantURL.GANONDORF_WALK_LEFT[i]);
			ganondorfWalkRight[i] = tk.getImage(StaticConstantURL.GANONDORF_WALK_RIGHT[i]);
		}
		for (int i = 0; i < StaticConstantURL.GANONDORF_ATTACK_UP.length; i++)
		{
			ganondorfAttackUp[i] = tk.getImage(StaticConstantURL.GANONDORF_ATTACK_UP[i]);
			ganondorfAttackDown[i] = tk.getImage(StaticConstantURL.GANONDORF_ATTACK_DOWN[i]);
			ganondorfAttackLeft[i] = tk.getImage(StaticConstantURL.GANONDORF_ATTACK_LEFT[i]);
			ganondorfAttackRight[i] = tk.getImage(StaticConstantURL.GANONDORF_ATTACK_RIGHT[i]);
		}

		// Attack Sphere
		for (int i = 0; i < StaticConstantURL.ATTACK_SPHERE.length; i++)
		{
			attack_sphere[i] = tk.getImage(StaticConstantURL.ATTACK_SPHERE[i]);
		}
		// empty_character = tk.getImage(StaticConstantURL.EMPTY_CHARACTER);

		tracker.addImage(hero_back, StaticConstantID.HERO);
		tracker.addImage(hero_front, StaticConstantID.HERO);
		tracker.addImage(hero_left, StaticConstantID.HERO);
		tracker.addImage(hero_right, StaticConstantID.HERO);
		for (int i = 0; i < StaticConstantURL.HERO_WALK_FRONT.length; i++)
		{
			tracker.addImage(heroWalkBack[i], StaticConstantID.HERO);
			tracker.addImage(heroWalkFront[i], StaticConstantID.HERO);
			tracker.addImage(heroWalkLeft[i], StaticConstantID.HERO);
			tracker.addImage(heroWalkRight[i], StaticConstantID.HERO);
		}
		for (int i = 0; i < StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length; i++)
		{
			tracker.addImage(heroPhysicAttackBack[i], StaticConstantID.HERO);
			tracker.addImage(heroPhysicAttackFront[i], StaticConstantID.HERO);
			tracker.addImage(heroPhysicAttackLeft[i], StaticConstantID.HERO);
			tracker.addImage(heroPhysicAttackRight[i], StaticConstantID.HERO);
		}

		for (int i = 0; i < StaticConstantURL.SKULL_WALK_UP.length; i++)
		{
			tracker.addImage(skullWalkUp[i], StaticConstantID.SKULL);
			tracker.addImage(skullWalkDown[i], StaticConstantID.SKULL);
			tracker.addImage(skullWalkLeft[i], StaticConstantID.SKULL);
			tracker.addImage(skullWalkRight[i], StaticConstantID.SKULL);
		}

		for (int i = 0; i < StaticConstantURL.WARRIOR_WALK_UP.length; i++)
		{
			tracker.addImage(warriorWalkUp[i], StaticConstantID.WARRIOR);
			tracker.addImage(warriorWalkDown[i], StaticConstantID.WARRIOR);
			tracker.addImage(warriorWalkLeft[i], StaticConstantID.WARRIOR);
			tracker.addImage(warriorWalkRight[i], StaticConstantID.WARRIOR);
		}
		for (int i = 0; i < StaticConstantURL.WARRIOR_ATTACK_UP.length; i++)
		{
			tracker.addImage(warriorAttackUp[i], StaticConstantID.WARRIOR);
			tracker.addImage(warriorAttackDown[i], StaticConstantID.WARRIOR);
			tracker.addImage(warriorAttackLeft[i], StaticConstantID.WARRIOR);
			tracker.addImage(warriorAttackRight[i], StaticConstantID.WARRIOR);
		}

		tracker.addImage(wizardUp, StaticConstantID.WIZARD);
		tracker.addImage(wizardDown, StaticConstantID.WIZARD);
		tracker.addImage(wizardLeft, StaticConstantID.WIZARD);
		tracker.addImage(wizardRight, StaticConstantID.WIZARD);

		for (int i = 0; i < StaticConstantURL.GANONDORF_WALK_UP.length; i++)
		{
			tracker.addImage(ganondorfWalkUp[i], StaticConstantID.GANONDORF);
			tracker.addImage(ganondorfWalkDown[i], StaticConstantID.GANONDORF);
			tracker.addImage(ganondorfWalkLeft[i], StaticConstantID.GANONDORF);
			tracker.addImage(ganondorfWalkRight[i], StaticConstantID.GANONDORF);
		}
		for (int i = 0; i < StaticConstantURL.GANONDORF_ATTACK_UP.length; i++)
		{
			tracker.addImage(ganondorfAttackUp[i], StaticConstantID.GANONDORF);
			tracker.addImage(ganondorfAttackDown[i], StaticConstantID.GANONDORF);
			tracker.addImage(ganondorfAttackLeft[i], StaticConstantID.GANONDORF);
			tracker.addImage(ganondorfAttackRight[i], StaticConstantID.GANONDORF);
		}

		for (int i = 0; i < StaticConstantURL.ATTACK_SPHERE.length; i++)
		{
			tracker.addImage(attack_sphere[i], StaticConstantID.ATTACK_SPHERE);
		}
	}

	private void loadObject()
	{
		block = tk.getImage(StaticConstantURL.BLOCK);

		start_point = tk.getImage(StaticConstantURL.START_POINT);
		final_point = tk.getImage(StaticConstantURL.FINAL_POINT);
		platform = tk.getImage(StaticConstantURL.PLATFORM);
		// invisible_wall = tk.getImage(StaticConstantURL.INVISIBLE_WALL);
		heart = tk.getImage(StaticConstantURL.HEART);

		for (int i = 0; i < StaticConstantURL.MAGIC_SPHERE.length; i++)
		{
			magic_sphere[i] = tk.getImage(StaticConstantURL.MAGIC_SPHERE[i]);
			tracker.addImage(magic_sphere[i], StaticConstantID.MAGIC_SPHERE);
		}

		for (int i = 0; i < StaticConstantURL.SKULL_HEAD.length; i++)
		{
			skullHead[i] = tk.getImage(StaticConstantURL.SKULL_HEAD[i]);
		}

		for (int i = 0; i < StaticConstantURL.BUTTON.length; i++)
		{
			button[i] = tk.getImage(StaticConstantURL.BUTTON[i]);
		}

		for (int i = 0; i < StaticConstantURL.RED_COIN.length; i++)
		{
			redCoin[i] = tk.getImage(StaticConstantURL.RED_COIN[i]);
			blueCoin[i] = tk.getImage(StaticConstantURL.BLUE_COIN[i]);
			greenCoin[i] = tk.getImage(StaticConstantURL.GREEN_COIN[i]);
		}

		for (int i = 0; i < StaticConstantURL.GATE.length; i++)
		{
			gate[i] = tk.getImage(StaticConstantURL.GATE[i]);
		}

		for (int i = 0; i < StaticConstantURL.FLOWER.length; i++)
		{
			flower[i] = tk.getImage(StaticConstantURL.FLOWER[i]);
		}

		for (int i = 0; i < StaticConstantURL.TREASURE.length; i++)
		{
			treasure[i] = tk.getImage(StaticConstantURL.TREASURE[i]);
		}

		for (int i = 0; i < StaticConstantURL.FLAME_TOWER.length; i++)
		{
			flameTower[i] = tk.getImage(StaticConstantURL.FLAME_TOWER[i]);
		}

		for (int i = 0; i < StaticConstantURL.MAGIC_GATE.length; i++)
		{
			magicGate[i] = tk.getImage(StaticConstantURL.MAGIC_GATE[i]);
			tracker.addImage(magicGate[i], i);
		}

		for (int i = 0; i < StaticConstantURL.FALL_POINT.length; i++)
		{
			fallPoint[i] = tk.getImage(StaticConstantURL.FALL_POINT[i]);
			tracker.addImage(fallPoint[i], i);
		}

		for (int i = 0; i < StaticConstantURL.TRIFORCE_PIECE.length; i++)
		{
			triforcePiece[i] = tk.getImage(StaticConstantURL.TRIFORCE_PIECE[i]);
			tracker.addImage(triforcePiece[i], i);
		}

		// empty_utility = tk.getImage(StaticConstantURL.EMPTY_UTILITY);

		for (int i = 0; i < StaticConstantURL.BUTTON.length; i++)
		{
			tracker.addImage(button[i], StaticConstantID.BUTTON);
		}

		for (int i = 0; i < StaticConstantURL.RED_COIN.length; i++)
		{
			tracker.addImage(redCoin[i], StaticConstantID.COIN);
			tracker.addImage(blueCoin[i], StaticConstantID.COIN);
			tracker.addImage(greenCoin[i], StaticConstantID.COIN);
		}

		for (int i = 0; i < StaticConstantURL.GATE.length; i++)
		{
			tracker.addImage(gate[i], StaticConstantID.GATE);
		}

		for (int i = 0; i < StaticConstantURL.FLOWER.length; i++)
		{
			tracker.addImage(flower[i], StaticConstantID.FLOWER);
		}

		for (int i = 0; i < StaticConstantURL.TREASURE.length; i++)
		{
			tracker.addImage(treasure[i], StaticConstantID.TREASURE);
		}

		for (int i = 0; i < StaticConstantURL.TREASURE.length; i++)
		{
			tracker.addImage(flameTower[i], StaticConstantID.FLAME_TOWER);
		}

		for (int i = 0; i < StaticConstantURL.SKULL_HEAD.length; i++)
		{
			tracker.addImage(skullHead[i], StaticConstantID.SKULL_HEAD);
		}

		tracker.addImage(block, StaticConstantID.BLOCK);

		tracker.addImage(start_point, StaticConstantID.START_POINT);
		tracker.addImage(final_point, StaticConstantID.FINAL_POINT);
		tracker.addImage(platform, StaticConstantID.PLATFORM);
		tracker.addImage(heart, StaticConstantID.HEART);

	}

	private void loadScene()
	{
		// Scene
		wall = tk.getImage(StaticConstantURL.WALL);
		tree = tk.getImage(StaticConstantURL.TREE);
		treeGreen = tk.getImage(StaticConstantURL.TREE_GREEN);
		treeViolet = tk.getImage(StaticConstantURL.TREE_VIOLET);
		trunk = tk.getImage(StaticConstantURL.TRUNK);
		marbleScaleDown = tk.getImage(StaticConstantURL.MARBLE_SCALE_DOWN);
		marbleScaleUp = tk.getImage(StaticConstantURL.MARBLE_SCALE_UP);
		marbleScaleLeft = tk.getImage(StaticConstantURL.MARBLE_SCALE_LEFT);
		marbleScaleRight = tk.getImage(StaticConstantURL.MARBLE_SCALE_RIGHT);
		statue = tk.getImage(StaticConstantURL.STATUE);
		swordTrone = tk.getImage(StaticConstantURL.SWORD_TRONE);
		towerRoyal = tk.getImage(StaticConstantURL.TOWER_ROYAL);
		houseLarge = tk.getImage(StaticConstantURL.HOUSE_LARGE);
		houseSmall = tk.getImage(StaticConstantURL.HOUSE_SMALL);

		// Foresta
		land = tk.getImage(StaticConstantURL.LAND);
		landTerrain = tk.getImage(StaticConstantURL.LAND_TERRAIN);
		discesaDown = tk.getImage(StaticConstantURL.DISCESA_DOWN);
		discesaLeft = tk.getImage(StaticConstantURL.DISCESA_LEFT);
		discesaUp = tk.getImage(StaticConstantURL.DISCESA_UP);
		discesaRight = tk.getImage(StaticConstantURL.DISCESA_RIGHT);
		landTerrainCornerDownLeftInside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_DOWN_LEFT_INSIDE);
		landTerrainCornerDownLeftOutside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_DOWN_LEFT_OUTSIDE);
		landTerrainCornerDownRightInside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_DOWN_RIGHT_INSIDE);
		landTerrainCornerDownRightOutside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_DOWN_RIGHT_OUTSIDE);
		landTerrainCornerUpLeftInside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_UP_LEFT_INSIDE);
		landTerrainCornerUpLeftOutside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_UP_LEFT_OUTSIDE);
		landTerrainCornerUpRightInside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_UP_RIGHT_INSIDE);
		landTerrainCornerUpRightOutside = tk
				.getImage(StaticConstantURL.LAND_TERRAIN_CORNER_UP_RIGHT_OUTSIDE);
		landTerrainDown = tk.getImage(StaticConstantURL.LAND_TERRAIN_DOWN);
		landTerrainUp = tk.getImage(StaticConstantURL.LAND_TERRAIN_UP);
		landTerrainLeft = tk.getImage(StaticConstantURL.LAND_TERRAIN_LEFT);
		landTerrainRight = tk.getImage(StaticConstantURL.LAND_TERRAIN_RIGHT);
		mountainDown = tk.getImage(StaticConstantURL.MOUNTAIN_DOWN);
		mountainLeft = tk.getImage(StaticConstantURL.MOUNTAIN_LEFT);
		mountainRight = tk.getImage(StaticConstantURL.MOUNTAIN_RIGHT);
		mountainUp = tk.getImage(StaticConstantURL.MOUNTAIN_UP);
		mountainCornerLeftDown = tk.getImage(StaticConstantURL.MOUNTAIN_CORNER_LEFT_DOWN);
		mountainCornerLeftUp = tk.getImage(StaticConstantURL.MOUNTAIN_CORNER_LEFT_UP);
		mountainCornerRightDown = tk.getImage(StaticConstantURL.MOUNTAIN_CORNER_RIGHT_DOWN);
		mountainCornerRightUp = tk.getImage(StaticConstantURL.MOUNTAIN_CORNER_RIGHT_UP);
		mountainCornerLeftDownInside = tk
				.getImage(StaticConstantURL.MOUNTAIN_CORNER_LEFT_DOWN_INSIDE);
		mountainCornerLeftUpInside = tk.getImage(StaticConstantURL.MOUNTAIN_CORNER_LEFT_UP_INSIDE);
		mountainCornerRightDownInside = tk
				.getImage(StaticConstantURL.MOUNTAIN_CORNER_RIGHT_DOWN_INSIDE);
		mountainCornerRightUpInside = tk
				.getImage(StaticConstantURL.MOUNTAIN_CORNER_RIGHT_UP_INSIDE);
		water = tk.getImage(StaticConstantURL.WATER);
		for (int i = 0; i < StaticConstantURL.WATER_ANGLE_DOWN_LEFT.length; i++)
		{
			waterAngleDownLeft[i] = tk.getImage(StaticConstantURL.WATER_ANGLE_DOWN_LEFT[i]);
			waterAngleDownRight[i] = tk.getImage(StaticConstantURL.WATER_ANGLE_DOWN_RIGHT[i]);
			waterAngleUpLeft[i] = tk.getImage(StaticConstantURL.WATER_ANGLE_UP_LEFT[i]);
			waterAngleUpRight[i] = tk.getImage(StaticConstantURL.WATER_ANGLE_UP_RIGHT[i]);
			waterBorderDown[i] = tk.getImage(StaticConstantURL.WATER_BORDER_DOWN[i]);
			waterBorderLeft[i] = tk.getImage(StaticConstantURL.WATER_BORDER_LEFT[i]);
			waterBorderRight[i] = tk.getImage(StaticConstantURL.WATER_BORDER_RIGHT[i]);
			waterBorderUp[i] = tk.getImage(StaticConstantURL.WATER_BORDER_UP[i]);
		}

		// xland = tk.getImage(StaticConstantURL.XLAND);

		tracker.addImage(wall, StaticConstantID.WALL);
		tracker.addImage(tree, StaticConstantID.TREE);
		tracker.addImage(treeGreen, StaticConstantID.TREE_GREEN);
		tracker.addImage(treeViolet, StaticConstantID.TREE_VIOLET);
		tracker.addImage(trunk, StaticConstantID.TRUNK);
		tracker.addImage(marbleScaleDown, StaticConstantID.MARBLE_SCALE_DOWN);
		tracker.addImage(marbleScaleUp, StaticConstantID.MARBLE_SCALE_UP);
		tracker.addImage(marbleScaleLeft, StaticConstantID.MARBLE_SCALE_LEFT);
		tracker.addImage(marbleScaleRight, StaticConstantID.MARBLE_SCALE_RIGHT);
		tracker.addImage(statue, StaticConstantID.STATUE);
		tracker.addImage(swordTrone, StaticConstantID.SWORD_TRONE);
		tracker.addImage(towerRoyal, StaticConstantID.TOWER_ROYAL);

		// Foresta
		tracker.addImage(land, StaticConstantID.LAND);
		tracker.addImage(discesaRight, StaticConstantID.DISCESA_RIGHT);
		tracker.addImage(discesaDown, StaticConstantID.DISCESA_DOWN);
		tracker.addImage(discesaLeft, StaticConstantID.DISCESA_LEFT);
		tracker.addImage(discesaUp, StaticConstantID.DISCESA_UP);
		tracker.addImage(landTerrain, StaticConstantID.LAND_TERRAIN);
		tracker.addImage(landTerrainCornerDownLeftInside,
				StaticConstantID.LAND_TERRAIN_CORNER_DOWN_LEFT_INSIDE);
		tracker.addImage(landTerrainCornerDownLeftOutside,
				StaticConstantID.LAND_TERRAIN_CORNER_DOWN_LEFT_OUTSIDE);
		tracker.addImage(landTerrainCornerDownRightInside,
				StaticConstantID.LAND_TERRAIN_CORNER_DOWN_RIGHT_INSIDE);
		tracker.addImage(landTerrainCornerDownRightOutside,
				StaticConstantID.LAND_TERRAIN_CORNER_DOWN_RIGHT_OUTSIDE);
		tracker.addImage(landTerrainCornerUpLeftInside,
				StaticConstantID.LAND_TERRAIN_CORNER_UP_LEFT_INSIDE);
		tracker.addImage(landTerrainCornerUpLeftOutside,
				StaticConstantID.LAND_TERRAIN_CORNER_UP_LEFT_OUTSIDE);
		tracker.addImage(landTerrainCornerUpRightInside,
				StaticConstantID.LAND_TERRAIN_CORNER_UP_RIGHT_INSIDE);
		tracker.addImage(landTerrainCornerUpRightOutside,
				StaticConstantID.LAND_TERRAIN_CORNER_UP_RIGHT_OUTSIDE);
		tracker.addImage(landTerrainDown, StaticConstantID.LAND_TERRAIN_DOWN);
		tracker.addImage(landTerrainUp, StaticConstantID.LAND_TERRAIN_UP);
		tracker.addImage(landTerrainLeft, StaticConstantID.LAND_TERRAIN_LEFT);
		tracker.addImage(landTerrainRight, StaticConstantID.LAND_TERRAIN_RIGHT);
		tracker.addImage(mountainDown, StaticConstantID.MOUNTAIN_DOWN);
		tracker.addImage(mountainUp, StaticConstantID.MOUNTAIN_UP);
		tracker.addImage(mountainLeft, StaticConstantID.MOUNTAIN_LEFT);
		tracker.addImage(mountainRight, StaticConstantID.MOUNTAIN_RIGHT);
		tracker.addImage(mountainCornerLeftDown, StaticConstantID.MOUNTAIN_CORNER_LEFT_DOWN);
		tracker.addImage(mountainCornerLeftUp, StaticConstantID.MOUNTAIN_CORNER_LEFT_UP);
		tracker.addImage(mountainCornerRightDown, StaticConstantID.MOUNTAIN_CORNER_RIGHT_DOWN);
		tracker.addImage(mountainCornerRightUp, StaticConstantID.MOUNTAIN_CORNER_RIGHT_UP);
		tracker.addImage(mountainCornerLeftDownInside,
				StaticConstantID.MOUNTAIN_CORNER_LEFT_DOWN_INSIDE);
		tracker.addImage(mountainCornerLeftUpInside,
				StaticConstantID.MOUNTAIN_CORNER_LEFT_UP_INSIDE);
		tracker.addImage(mountainCornerRightDownInside,
				StaticConstantID.MOUNTAIN_CORNER_RIGHT_DOWN_INSIDE);
		tracker.addImage(mountainCornerRightUpInside,
				StaticConstantID.MOUNTAIN_CORNER_RIGHT_UP_INSIDE);

		for (int i = 0; i < StaticConstantURL.WATER_ANGLE_DOWN_LEFT.length; i++)
		{
			tracker.addImage(waterAngleDownLeft[i], StaticConstantID.WATER_ANGLE_DOWN_LEFT);
			tracker.addImage(waterAngleDownRight[i], StaticConstantID.WATER_ANGLE_DOWN_RIGHT);
			tracker.addImage(waterAngleUpLeft[i], StaticConstantID.WATER_ANGLE_UP_LEFT);
			tracker.addImage(waterAngleUpRight[i], StaticConstantID.WATER_ANGLE_UP_RIGHT);
		}
	}

	public static void loadVillage()
	{
		kakariko[0] = tk.getImage(StaticConstantURL.KAKARIKO_TWO);
		kakariko[1] = tk.getImage(StaticConstantURL.KAKARIKO_FOUR);
		kakariko[2] = tk.getImage(StaticConstantURL.KAKARIKO_FIVE);
		kakariko[3] = tk.getImage(StaticConstantURL.KAKARIKO_SIX);
		kakariko[4] = tk.getImage(StaticConstantURL.KAKARIKO_SEVEN);
		kakariko[5] = tk.getImage(StaticConstantURL.KAKARIKO_EIGHT);
		kakariko[6] = tk.getImage(StaticConstantURL.KAKARIKO_NINE);

		for (int i = 0; i < 7; i++)
		{
			tracker.addImage(kakariko[i], i);
		}
	}

	public static void loadDesert()
	{
		desert[0] = tk.getImage(StaticConstantURL.DESERT_ONE);
		desert[1] = tk.getImage(StaticConstantURL.DESERT_TWO);
		desert[2] = tk.getImage(StaticConstantURL.DESERT_THREE);
		desert[3] = tk.getImage(StaticConstantURL.DESERT_FOUR);
		desert[4] = tk.getImage(StaticConstantURL.DESERT_FIVE);
		desert[5] = tk.getImage(StaticConstantURL.DESERT_SIX);
		desert[6] = tk.getImage(StaticConstantURL.DESERT_SEVEN);
		desert[7] = tk.getImage(StaticConstantURL.DESERT_EIGHT);
		desert[8] = tk.getImage(StaticConstantURL.DESERT_NINE);
		desert[9] = tk.getImage(StaticConstantURL.DESERT_TEN);
		desert[10] = tk.getImage(StaticConstantURL.DESERT_ELEVEN);
		desert[11] = tk.getImage(StaticConstantURL.DESERT_TWELVE);

		for (int i = 0; i < 12; i++)
		{
			tracker.addImage(desert[i], i);
		}
	}

	public static void loadDungeon()
	{
		dungeon[0] = tk.getImage(StaticConstantURL.DUNGEON_1);
		dungeon[1] = tk.getImage(StaticConstantURL.DUNGEON_2);
		dungeon[2] = tk.getImage(StaticConstantURL.DUNGEON_3);
		dungeon[3] = tk.getImage(StaticConstantURL.DUNGEON_4);
		dungeon[4] = tk.getImage(StaticConstantURL.DUNGEON_5);
		dungeon[5] = tk.getImage(StaticConstantURL.DUNGEON_6);
		dungeon[6] = tk.getImage(StaticConstantURL.DUNGEON_7);
		dungeon[7] = tk.getImage(StaticConstantURL.DUNGEON_8);
		dungeon[8] = tk.getImage(StaticConstantURL.DUNGEON_9);
		dungeon[9] = tk.getImage(StaticConstantURL.DUNGEON_10);
		dungeon[10] = tk.getImage(StaticConstantURL.DUNGEON_11);
		dungeon[11] = tk.getImage(StaticConstantURL.DUNGEON_12);
		dungeon[12] = tk.getImage(StaticConstantURL.DUNGEON_13);
		dungeon[13] = tk.getImage(StaticConstantURL.DUNGEON_14);
		dungeon[14] = tk.getImage(StaticConstantURL.DUNGEON_15);
		dungeon[15] = tk.getImage(StaticConstantURL.DUNGEON_16);
		dungeon[16] = tk.getImage(StaticConstantURL.DUNGEON_17);
		dungeon[17] = tk.getImage(StaticConstantURL.DUNGEON_18);
		dungeon[18] = tk.getImage(StaticConstantURL.DUNGEON_19);
		dungeon[19] = tk.getImage(StaticConstantURL.DUNGEON_20);
		dungeon[20] = tk.getImage(StaticConstantURL.DUNGEON_21);
		dungeon[21] = tk.getImage(StaticConstantURL.DUNGEON_22);
		dungeon[22] = tk.getImage(StaticConstantURL.DUNGEON_23);
		dungeon[23] = tk.getImage(StaticConstantURL.DUNGEON_24);
		dungeon[24] = tk.getImage(StaticConstantURL.DUNGEON_25);
		dungeon[25] = tk.getImage(StaticConstantURL.DUNGEON_26);
		dungeon[26] = tk.getImage(StaticConstantURL.DUNGEON_27);
		dungeon[27] = tk.getImage(StaticConstantURL.DUNGEON_28);
		dungeon[28] = tk.getImage(StaticConstantURL.DUNGEON_29);
		dungeon[29] = tk.getImage(StaticConstantURL.DUNGEON_30);
		dungeon[30] = tk.getImage(StaticConstantURL.DUNGEON_31);
		dungeon[31] = tk.getImage(StaticConstantURL.DUNGEON_32);

		dungeonAbove[0] = tk.getImage("Images/Scene/Dungeon/DungeonPart6Above.png");
		dungeonAbove[1] = tk.getImage("Images/Scene/Dungeon/DungeonPart14Above.png");
		dungeonAbove[2] = tk.getImage("Images/Scene/Dungeon/DungeonPart16Above.png");

		for (int i = 0; i < 32; i++)
		{
			tracker.addImage(dungeon[i], i);
		}

		tracker.addImage(dungeonAbove[0], 0);
		tracker.addImage(dungeonAbove[1], 1);
		tracker.addImage(dungeonAbove[2], 2);
	}

	public static void loadForest()
	{
		forest[0] = tk.getImage(StaticConstantURL.FOREST_1);
		forest[1] = tk.getImage(StaticConstantURL.FOREST_2);
		forest[2] = tk.getImage(StaticConstantURL.FOREST_3);
		forest[3] = tk.getImage(StaticConstantURL.FOREST_4);
		forest[4] = tk.getImage(StaticConstantURL.FOREST_5);
		forest[5] = tk.getImage(StaticConstantURL.FOREST_6);
		forest[6] = tk.getImage(StaticConstantURL.FOREST_7);
		forest[7] = tk.getImage(StaticConstantURL.FOREST_8);
		forest[8] = tk.getImage(StaticConstantURL.FOREST_9);
		forest[9] = tk.getImage(StaticConstantURL.FOREST_10);
		forest[10] = tk.getImage(StaticConstantURL.FOREST_11);
		forest[11] = tk.getImage(StaticConstantURL.FOREST_12);
		forest[12] = tk.getImage(StaticConstantURL.FOREST_13);
		forest[13] = tk.getImage(StaticConstantURL.FOREST_14);
		forest[14] = tk.getImage(StaticConstantURL.FOREST_15);
		forest[15] = tk.getImage(StaticConstantURL.FOREST_16);

		for (int i = 0; i < 16; i++)
		{
			tracker.addImage(forest[i], i);
		}
	}

	public static void loadLake()
	{
		for (int i = 0; i < StaticConstantURL.LAKE.length; i++)
		{
			for (int j = 0; j < StaticConstantURL.LAKE[0].length; j++)
			{
				lake[i][j] = tk.getImage(StaticConstantURL.LAKE[i][j]);
				tracker.addImage(lake[i][j], 0);
			}
		}
	}

	public static void loadVolcano()
	{
		for (int i = 0; i < StaticConstantURL.VOLCANO.length; i++)
		{
			volcano[i] = tk.getImage(StaticConstantURL.VOLCANO[i]);
			tracker.addImage(volcano[i], 0);
		}
	}

	public static void loadSky()
	{
		for (int i = 0; i < StaticConstantURL.SKY.length; i++)
		{
			sky[i] = tk.getImage(StaticConstantURL.SKY[i]);
			tracker.addImage(sky[i], 0);
		}
	}

	public static void loadEffect()
	{
		// Immagini per l'effeto nebbia
		for (int i = 0; i < fog.length; i++)
		{
			fog[i] = tk.getImage(StaticConstantURL.FOG[i]);
			tracker.addImage(fog[i], i);
		}

		// Immagini per l'effeto nebbia
		for (int i = 0; i < rain.length; i++)
		{
			rain[i] = tk.getImage(StaticConstantURL.RAIN[i]);
			tracker.addImage(rain[i], i);
		}

		// Immagini per l'effeto nebbia del vulcano
		for (int i = 0; i < volcanoFog.length; i++)
		{
			volcanoFog[i] = tk.getImage(StaticConstantURL.VOLCANO_FOG[i]);
			tracker.addImage(volcanoFog[i], i);
		}

		// Immagini per l'effetto nuvole del cielo
		cloud = tk.getImage(StaticConstantURL.CLOUD);
		tracker.addImage(cloud, 0);

		// Immagini per l'effetto buio
		for (int i = 0; i < darkness.length; i++)
		{
			darkness[i] = tk.getImage(StaticConstantURL.DARKNESS[i]);
			tracker.addImage(darkness[i], i);
		}

	}
}
