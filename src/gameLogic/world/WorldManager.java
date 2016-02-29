package gameLogic.world;

import gameLogic.ObjectScene;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AttackSphere;
import gameLogic.character.EmptyCharacter;
import gameLogic.character.Ganondorf;
import gameLogic.character.Skull;
import gameLogic.logicState.LogicLevelFive;
import gameLogic.logicState.LogicLevelFour;
import gameLogic.logicState.LogicLevelOne;
import gameLogic.logicState.LogicLevelSeven;
import gameLogic.logicState.LogicLevelSix;
import gameLogic.logicState.LogicLevelThree;
import gameLogic.logicState.LogicLevelTwo;
import gameLogic.logicState.SurvivalLogic;
import gameLogic.logicState.WorldLogic;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import gameLogic.object.EmptyUtility;
import gameLogic.object.FallPoint;
import gameLogic.object.FlameTower;
import gameLogic.object.Flower;
import gameLogic.object.Gate;
import gameLogic.object.MagicGate;
import gameLogic.object.Platform;
import gameLogic.object.SkullHead;
import gameLogic.object.StaticPlatform;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.HouseLarge;
import gameLogic.scene.HouseSmall;
import gameLogic.scene.MarbleScaleDown;
import gameLogic.scene.MarbleScaleLeft;
import gameLogic.scene.MarbleScaleRight;
import gameLogic.scene.MarbleScaleUp;
import gameLogic.scene.Statue;
import gameLogic.scene.SwordTrone;
import gameLogic.scene.TowerRoyal;
import gameLogic.scene.Tree;
import gameLogic.scene.TreeGreen;
import gameLogic.scene.TreeViolet;
import gameLogic.scene.Trunk;
import gameLogic.scene.Wall;
import gameLogic.scene.XLand;
import gameLogic.scene.desert.DesertEight;
import gameLogic.scene.desert.DesertEleven;
import gameLogic.scene.desert.DesertFive;
import gameLogic.scene.desert.DesertFour;
import gameLogic.scene.desert.DesertNine;
import gameLogic.scene.desert.DesertOne;
import gameLogic.scene.desert.DesertSeven;
import gameLogic.scene.desert.DesertSix;
import gameLogic.scene.desert.DesertTen;
import gameLogic.scene.desert.DesertThree;
import gameLogic.scene.desert.DesertTwelve;
import gameLogic.scene.desert.DesertTwo;
import gameLogic.scene.desert.QuickSand;
import gameLogic.scene.dungeon.Dungeon1;
import gameLogic.scene.dungeon.Dungeon10;
import gameLogic.scene.dungeon.Dungeon11;
import gameLogic.scene.dungeon.Dungeon12;
import gameLogic.scene.dungeon.Dungeon13;
import gameLogic.scene.dungeon.Dungeon14;
import gameLogic.scene.dungeon.Dungeon15;
import gameLogic.scene.dungeon.Dungeon16;
import gameLogic.scene.dungeon.Dungeon17;
import gameLogic.scene.dungeon.Dungeon18;
import gameLogic.scene.dungeon.Dungeon19;
import gameLogic.scene.dungeon.Dungeon2;
import gameLogic.scene.dungeon.Dungeon20;
import gameLogic.scene.dungeon.Dungeon21;
import gameLogic.scene.dungeon.Dungeon22;
import gameLogic.scene.dungeon.Dungeon23;
import gameLogic.scene.dungeon.Dungeon24;
import gameLogic.scene.dungeon.Dungeon25;
import gameLogic.scene.dungeon.Dungeon26;
import gameLogic.scene.dungeon.Dungeon27;
import gameLogic.scene.dungeon.Dungeon28;
import gameLogic.scene.dungeon.Dungeon29;
import gameLogic.scene.dungeon.Dungeon3;
import gameLogic.scene.dungeon.Dungeon30;
import gameLogic.scene.dungeon.Dungeon31;
import gameLogic.scene.dungeon.Dungeon32;
import gameLogic.scene.dungeon.Dungeon4;
import gameLogic.scene.dungeon.Dungeon5;
import gameLogic.scene.dungeon.Dungeon6;
import gameLogic.scene.dungeon.Dungeon7;
import gameLogic.scene.dungeon.Dungeon8;
import gameLogic.scene.dungeon.Dungeon9;
import gameLogic.scene.forest.DiscesaDown;
import gameLogic.scene.forest.DiscesaLeft;
import gameLogic.scene.forest.DiscesaRight;
import gameLogic.scene.forest.DiscesaUp;
import gameLogic.scene.forest.Forest1;
import gameLogic.scene.forest.Forest10;
import gameLogic.scene.forest.Forest11;
import gameLogic.scene.forest.Forest12;
import gameLogic.scene.forest.Forest13;
import gameLogic.scene.forest.Forest14;
import gameLogic.scene.forest.Forest15;
import gameLogic.scene.forest.Forest16;
import gameLogic.scene.forest.Forest2;
import gameLogic.scene.forest.Forest3;
import gameLogic.scene.forest.Forest4;
import gameLogic.scene.forest.Forest5;
import gameLogic.scene.forest.Forest6;
import gameLogic.scene.forest.Forest7;
import gameLogic.scene.forest.Forest8;
import gameLogic.scene.forest.Forest9;
import gameLogic.scene.forest.Land;
import gameLogic.scene.forest.LandTerrain;
import gameLogic.scene.forest.LandTerrainCornerDownLeftInside;
import gameLogic.scene.forest.LandTerrainCornerDownLeftOutside;
import gameLogic.scene.forest.LandTerrainCornerDownRightInside;
import gameLogic.scene.forest.LandTerrainCornerDownRightOutside;
import gameLogic.scene.forest.LandTerrainCornerUpLeftInside;
import gameLogic.scene.forest.LandTerrainCornerUpLeftOutside;
import gameLogic.scene.forest.LandTerrainCornerUpRightInside;
import gameLogic.scene.forest.LandTerrainCornerUpRightOutside;
import gameLogic.scene.forest.LandTerrainDown;
import gameLogic.scene.forest.LandTerrainLeft;
import gameLogic.scene.forest.LandTerrainRight;
import gameLogic.scene.forest.LandTerrainUp;
import gameLogic.scene.forest.MountainCornerLeftDown;
import gameLogic.scene.forest.MountainCornerLeftDownInside;
import gameLogic.scene.forest.MountainCornerLeftUp;
import gameLogic.scene.forest.MountainCornerLeftUpInside;
import gameLogic.scene.forest.MountainCornerRightDown;
import gameLogic.scene.forest.MountainCornerRightDownInside;
import gameLogic.scene.forest.MountainCornerRightUp;
import gameLogic.scene.forest.MountainCornerRightUpInside;
import gameLogic.scene.forest.MountainDown;
import gameLogic.scene.forest.MountainLeft;
import gameLogic.scene.forest.MountainRight;
import gameLogic.scene.forest.MountainUp;
import gameLogic.scene.forest.Water;
import gameLogic.scene.forest.WaterAngleDownLeft;
import gameLogic.scene.forest.WaterAngleDownRight;
import gameLogic.scene.forest.WaterAngleUpLeft;
import gameLogic.scene.forest.WaterAngleUpRight;
import gameLogic.scene.forest.WaterBorderDown;
import gameLogic.scene.forest.WaterBorderLeft;
import gameLogic.scene.forest.WaterBorderRight;
import gameLogic.scene.forest.WaterBorderUp;
import gameLogic.scene.kakariko.KakarikoEight;
import gameLogic.scene.kakariko.KakarikoFive;
import gameLogic.scene.kakariko.KakarikoFour;
import gameLogic.scene.kakariko.KakarikoNine;
import gameLogic.scene.kakariko.KakarikoSeven;
import gameLogic.scene.kakariko.KakarikoSix;
import gameLogic.scene.kakariko.KakarikoTwo;
import gameLogic.scene.lake.Lake1;
import gameLogic.scene.lake.Lake10;
import gameLogic.scene.lake.Lake11;
import gameLogic.scene.lake.Lake12;
import gameLogic.scene.lake.Lake13;
import gameLogic.scene.lake.Lake14;
import gameLogic.scene.lake.Lake15;
import gameLogic.scene.lake.Lake16;
import gameLogic.scene.lake.Lake2;
import gameLogic.scene.lake.Lake3;
import gameLogic.scene.lake.Lake4;
import gameLogic.scene.lake.Lake5;
import gameLogic.scene.lake.Lake6;
import gameLogic.scene.lake.Lake7;
import gameLogic.scene.lake.Lake8;
import gameLogic.scene.lake.Lake9;
import gameLogic.scene.sky.Sky1;
import gameLogic.scene.sky.Sky10;
import gameLogic.scene.sky.Sky11;
import gameLogic.scene.sky.Sky12;
import gameLogic.scene.sky.Sky13;
import gameLogic.scene.sky.Sky14;
import gameLogic.scene.sky.Sky15;
import gameLogic.scene.sky.Sky16;
import gameLogic.scene.sky.Sky2;
import gameLogic.scene.sky.Sky3;
import gameLogic.scene.sky.Sky4;
import gameLogic.scene.sky.Sky5;
import gameLogic.scene.sky.Sky6;
import gameLogic.scene.sky.Sky7;
import gameLogic.scene.sky.Sky8;
import gameLogic.scene.sky.Sky9;
import gameLogic.scene.volcano.Volcano1;
import gameLogic.scene.volcano.Volcano10;
import gameLogic.scene.volcano.Volcano11;
import gameLogic.scene.volcano.Volcano12;
import gameLogic.scene.volcano.Volcano13;
import gameLogic.scene.volcano.Volcano14;
import gameLogic.scene.volcano.Volcano15;
import gameLogic.scene.volcano.Volcano16;
import gameLogic.scene.volcano.Volcano2;
import gameLogic.scene.volcano.Volcano3;
import gameLogic.scene.volcano.Volcano4;
import gameLogic.scene.volcano.Volcano5;
import gameLogic.scene.volcano.Volcano6;
import gameLogic.scene.volcano.Volcano7;
import gameLogic.scene.volcano.Volcano8;
import gameLogic.scene.volcano.Volcano9;
import guiInterface.character2D.AttackSphere2D;
import guiInterface.character2D.EmptyCharacter2D;
import guiInterface.character2D.Ganondorf2D;
import guiInterface.character2D.Hero2D;
import guiInterface.character2D.Skull2D;
import guiInterface.character2D.Warrior2D;
import guiInterface.character2D.Wizard2D;
import guiInterface.character3D.Hero3D;
import guiInterface.character3D.Warrior3D;
import guiInterface.character3D.Wizard3D;
import guiInterface.object2D.Block2D;
import guiInterface.object2D.Button2D;
import guiInterface.object2D.Coin2D;
import guiInterface.object2D.EmptyUtility2D;
import guiInterface.object2D.FallPoint2D;
import guiInterface.object2D.FinalPoint2D;
import guiInterface.object2D.FlameTower2D;
import guiInterface.object2D.Flower2D;
import guiInterface.object2D.Gate2D;
import guiInterface.object2D.Heart2D;
import guiInterface.object2D.InvisibleWall2D;
import guiInterface.object2D.MagicGate2D;
import guiInterface.object2D.MagicSphere2D;
import guiInterface.object2D.Platform2D;
import guiInterface.object2D.SkullHead2D;
import guiInterface.object2D.StartPoint2D;
import guiInterface.object2D.StaticPlatform2D;
import guiInterface.object2D.Treasure2D;
import guiInterface.object2D.TriforcePiece2D;
import guiInterface.object3D.Block3D;
import guiInterface.object3D.Coin3D;
import guiInterface.object3D.FinalPoint3D;
import guiInterface.object3D.Gate3D;
import guiInterface.object3D.Heart3D;
import guiInterface.object3D.InvisibleWall3D;
import guiInterface.object3D.MagicSphere3D;
import guiInterface.object3D.StartPoint3D;
import guiInterface.object3D.Treasure3D;
import guiInterface.scene2D.Empty2D;
import guiInterface.scene2D.HouseLarge2D;
import guiInterface.scene2D.HouseSmall2D;
import guiInterface.scene2D.MarbleScaleDown2D;
import guiInterface.scene2D.MarbleScaleLeft2D;
import guiInterface.scene2D.MarbleScaleRight2D;
import guiInterface.scene2D.MarbleScaleUp2D;
import guiInterface.scene2D.Statue2D;
import guiInterface.scene2D.SwordTrone2D;
import guiInterface.scene2D.TowerRoyal2D;
import guiInterface.scene2D.Tree2D;
import guiInterface.scene2D.TreeGreen2D;
import guiInterface.scene2D.TreeViolet2D;
import guiInterface.scene2D.Trunk2D;
import guiInterface.scene2D.Wall2D;
import guiInterface.scene2D.XLand2D;
import guiInterface.scene2D.desert.DesertEight2D;
import guiInterface.scene2D.desert.DesertEleven2D;
import guiInterface.scene2D.desert.DesertFive2D;
import guiInterface.scene2D.desert.DesertFour2D;
import guiInterface.scene2D.desert.DesertNine2D;
import guiInterface.scene2D.desert.DesertOne2D;
import guiInterface.scene2D.desert.DesertSeven2D;
import guiInterface.scene2D.desert.DesertSix2D;
import guiInterface.scene2D.desert.DesertTen2D;
import guiInterface.scene2D.desert.DesertThree2D;
import guiInterface.scene2D.desert.DesertTwelve2D;
import guiInterface.scene2D.desert.DesertTwo2D;
import guiInterface.scene2D.desert.QuickSand2D;
import guiInterface.scene2D.dungeon.Dungeon10_2D;
import guiInterface.scene2D.dungeon.Dungeon11_2D;
import guiInterface.scene2D.dungeon.Dungeon12_2D;
import guiInterface.scene2D.dungeon.Dungeon13_2D;
import guiInterface.scene2D.dungeon.Dungeon14_2D;
import guiInterface.scene2D.dungeon.Dungeon15_2D;
import guiInterface.scene2D.dungeon.Dungeon16_2D;
import guiInterface.scene2D.dungeon.Dungeon17_2D;
import guiInterface.scene2D.dungeon.Dungeon18_2D;
import guiInterface.scene2D.dungeon.Dungeon19_2D;
import guiInterface.scene2D.dungeon.Dungeon1_2D;
import guiInterface.scene2D.dungeon.Dungeon20_2D;
import guiInterface.scene2D.dungeon.Dungeon21_2D;
import guiInterface.scene2D.dungeon.Dungeon22_2D;
import guiInterface.scene2D.dungeon.Dungeon23_2D;
import guiInterface.scene2D.dungeon.Dungeon24_2D;
import guiInterface.scene2D.dungeon.Dungeon25_2D;
import guiInterface.scene2D.dungeon.Dungeon26_2D;
import guiInterface.scene2D.dungeon.Dungeon27_2D;
import guiInterface.scene2D.dungeon.Dungeon28_2D;
import guiInterface.scene2D.dungeon.Dungeon29_2D;
import guiInterface.scene2D.dungeon.Dungeon2_2D;
import guiInterface.scene2D.dungeon.Dungeon30_2D;
import guiInterface.scene2D.dungeon.Dungeon31_2D;
import guiInterface.scene2D.dungeon.Dungeon32_2D;
import guiInterface.scene2D.dungeon.Dungeon3_2D;
import guiInterface.scene2D.dungeon.Dungeon4_2D;
import guiInterface.scene2D.dungeon.Dungeon5_2D;
import guiInterface.scene2D.dungeon.Dungeon6_2D;
import guiInterface.scene2D.dungeon.Dungeon7_2D;
import guiInterface.scene2D.dungeon.Dungeon8_2D;
import guiInterface.scene2D.dungeon.Dungeon9_2D;
import guiInterface.scene2D.forest.DiscesaDown2D;
import guiInterface.scene2D.forest.DiscesaLeft2D;
import guiInterface.scene2D.forest.DiscesaRight2D;
import guiInterface.scene2D.forest.DiscesaUp2D;
import guiInterface.scene2D.forest.Forest10_2D;
import guiInterface.scene2D.forest.Forest11_2D;
import guiInterface.scene2D.forest.Forest12_2D;
import guiInterface.scene2D.forest.Forest13_2D;
import guiInterface.scene2D.forest.Forest14_2D;
import guiInterface.scene2D.forest.Forest15_2D;
import guiInterface.scene2D.forest.Forest16_2D;
import guiInterface.scene2D.forest.Forest1_2D;
import guiInterface.scene2D.forest.Forest2_2D;
import guiInterface.scene2D.forest.Forest3_2D;
import guiInterface.scene2D.forest.Forest4_2D;
import guiInterface.scene2D.forest.Forest5_2D;
import guiInterface.scene2D.forest.Forest6_2D;
import guiInterface.scene2D.forest.Forest7_2D;
import guiInterface.scene2D.forest.Forest8_2D;
import guiInterface.scene2D.forest.Forest9_2D;
import guiInterface.scene2D.forest.Land2D;
import guiInterface.scene2D.forest.LandTerrain2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownLeftInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownLeftOutside2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownRightInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownRightOutside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpLeftInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpLeftOutside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpRightInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpRightOutside2D;
import guiInterface.scene2D.forest.LandTerrainDown2D;
import guiInterface.scene2D.forest.LandTerrainLeft2D;
import guiInterface.scene2D.forest.LandTerrainRight2D;
import guiInterface.scene2D.forest.LandTerrainUp2D;
import guiInterface.scene2D.forest.MountainCornerLeftDown2D;
import guiInterface.scene2D.forest.MountainCornerLeftDownInside2D;
import guiInterface.scene2D.forest.MountainCornerLeftUp2D;
import guiInterface.scene2D.forest.MountainCornerLeftUpInside2D;
import guiInterface.scene2D.forest.MountainCornerRightDown2D;
import guiInterface.scene2D.forest.MountainCornerRightDownInside2D;
import guiInterface.scene2D.forest.MountainCornerRightUp2D;
import guiInterface.scene2D.forest.MountainCornerRightUpInside2D;
import guiInterface.scene2D.forest.MountainDown2D;
import guiInterface.scene2D.forest.MountainLeft2D;
import guiInterface.scene2D.forest.MountainRight2D;
import guiInterface.scene2D.forest.MountainUp2D;
import guiInterface.scene2D.forest.Water2D;
import guiInterface.scene2D.forest.WaterAngleDownLeft2D;
import guiInterface.scene2D.forest.WaterAngleDownRight2D;
import guiInterface.scene2D.forest.WaterAngleUpLeft2D;
import guiInterface.scene2D.forest.WaterAngleUpRight2D;
import guiInterface.scene2D.forest.WaterBorderDown2D;
import guiInterface.scene2D.forest.WaterBorderLeft2D;
import guiInterface.scene2D.forest.WaterBorderRight2D;
import guiInterface.scene2D.forest.WaterBorderUp2D;
import guiInterface.scene2D.kakariko.KakarikoEight2D;
import guiInterface.scene2D.kakariko.KakarikoFive2D;
import guiInterface.scene2D.kakariko.KakarikoFour2D;
import guiInterface.scene2D.kakariko.KakarikoNine2D;
import guiInterface.scene2D.kakariko.KakarikoSeven2D;
import guiInterface.scene2D.kakariko.KakarikoSix2D;
import guiInterface.scene2D.kakariko.KakarikoTwo2D;
import guiInterface.scene2D.lake.Lake10_2D;
import guiInterface.scene2D.lake.Lake11_2D;
import guiInterface.scene2D.lake.Lake12_2D;
import guiInterface.scene2D.lake.Lake13_2D;
import guiInterface.scene2D.lake.Lake14_2D;
import guiInterface.scene2D.lake.Lake15_2D;
import guiInterface.scene2D.lake.Lake16_2D;
import guiInterface.scene2D.lake.Lake1_2D;
import guiInterface.scene2D.lake.Lake2_2D;
import guiInterface.scene2D.lake.Lake3_2D;
import guiInterface.scene2D.lake.Lake4_2D;
import guiInterface.scene2D.lake.Lake5_2D;
import guiInterface.scene2D.lake.Lake6_2D;
import guiInterface.scene2D.lake.Lake7_2D;
import guiInterface.scene2D.lake.Lake8_2D;
import guiInterface.scene2D.lake.Lake9_2D;
import guiInterface.scene2D.sky.Sky10_2D;
import guiInterface.scene2D.sky.Sky11_2D;
import guiInterface.scene2D.sky.Sky12_2D;
import guiInterface.scene2D.sky.Sky13_2D;
import guiInterface.scene2D.sky.Sky14_2D;
import guiInterface.scene2D.sky.Sky15_2D;
import guiInterface.scene2D.sky.Sky16_2D;
import guiInterface.scene2D.sky.Sky1_2D;
import guiInterface.scene2D.sky.Sky2_2D;
import guiInterface.scene2D.sky.Sky3_2D;
import guiInterface.scene2D.sky.Sky4_2D;
import guiInterface.scene2D.sky.Sky5_2D;
import guiInterface.scene2D.sky.Sky6_2D;
import guiInterface.scene2D.sky.Sky7_2D;
import guiInterface.scene2D.sky.Sky8_2D;
import guiInterface.scene2D.sky.Sky9_2D;
import guiInterface.scene2D.volcano.Volcano10_2D;
import guiInterface.scene2D.volcano.Volcano11_2D;
import guiInterface.scene2D.volcano.Volcano12_2D;
import guiInterface.scene2D.volcano.Volcano13_2D;
import guiInterface.scene2D.volcano.Volcano14_2D;
import guiInterface.scene2D.volcano.Volcano15_2D;
import guiInterface.scene2D.volcano.Volcano16_2D;
import guiInterface.scene2D.volcano.Volcano1_2D;
import guiInterface.scene2D.volcano.Volcano2_2D;
import guiInterface.scene2D.volcano.Volcano3_2D;
import guiInterface.scene2D.volcano.Volcano4_2D;
import guiInterface.scene2D.volcano.Volcano5_2D;
import guiInterface.scene2D.volcano.Volcano6_2D;
import guiInterface.scene2D.volcano.Volcano7_2D;
import guiInterface.scene2D.volcano.Volcano8_2D;
import guiInterface.scene2D.volcano.Volcano9_2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantWorld;

import common.Direction;
import common.SoundManager;

public class WorldManager
{

	private Collection<AbstractCharacter> characterCollection;
	private ConcreteWorld currentWorld;
	private Collection<AbstractUtilityObject> utilityObjectCollection;

	public WorldManager() throws Exception
	{
		utilityObjectCollection = new ArrayList<AbstractUtilityObject>();
		characterCollection = new ArrayList<AbstractCharacter>();
		loadWorld();
	}

	private ConcreteWorld levelMuliplayer() throws Exception
	{
		loadLevel("Levels/Custom/Survival/");
		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		currentWorld.setLevelLogic(new SurvivalLogic());

		SoundManager.playSound(SoundManager.NETWORK_GAME, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	public WorldManager(String name) throws Exception
	{
		utilityObjectCollection = new ArrayList<AbstractUtilityObject>();
		characterCollection = new ArrayList<AbstractCharacter>();
		loadLevel(name);
	}

	public Collection<AbstractCharacter> getCharacterCollection()
	{
		return characterCollection;
	}

	public ConcreteWorld getCurrentWorld()
	{
		return currentWorld;
	}

	public ConcreteWorld getNextWorld() throws Exception
	{
		int tmp = StaticConstantGame.typeWorldChoose;
		tmp = ((tmp + 1) % StaticConstantID.NUMBER_WORLD) + 1;
		StaticConstantGame.typeWorldChoose = tmp;
		return loadWorld();
	}

	private ObjectScene getObject(final int typeObject, final int x, final int y)
	{
		switch (typeObject)
		{
		// Base World
			case StaticConstantID.EMPTY:
				return new Empty2D(x, y);
			case StaticConstantID.HOUSE_LARGE:
				return new HouseLarge2D(x, y);
			case StaticConstantID.HOUSE_SMALL:
				return new HouseSmall2D(x, y);
			case StaticConstantID.MARBLE_SCALE_DOWN:
				return new MarbleScaleDown2D(x, y);
			case StaticConstantID.MARBLE_SCALE_UP:
				return new MarbleScaleUp2D(x, y);
			case StaticConstantID.MARBLE_SCALE_LEFT:
				return new MarbleScaleLeft2D(x, y);
			case StaticConstantID.MARBLE_SCALE_RIGHT:
				return new MarbleScaleRight2D(x, y);
			case StaticConstantID.SKULL_HEAD:
				return new SkullHead2D(x, y);
			case StaticConstantID.STATUE:
				return new Statue2D(x, y);
			case StaticConstantID.SWORD_TRONE:
				return new SwordTrone2D(x, y);
			case StaticConstantID.TOWER_ROYAL:
				return new TowerRoyal2D(x, y);
			case StaticConstantID.TREE:
				return new Tree2D(x, y);
			case StaticConstantID.TREE_GREEN:
				return new TreeGreen2D(x, y);
			case StaticConstantID.TREE_VIOLET:
				return new TreeViolet2D(x, y);
			case StaticConstantID.TRUNK:
				return new Trunk2D(x, y);
			case StaticConstantID.BLOCK:
				return new Block2D(x, y);
			case StaticConstantID.XLAND:
				return new XLand2D(x, y);
				// Forest
			case StaticConstantID.LAND:
				return new Land2D(x, y);
			case StaticConstantID.WALL:
				return new Wall2D(x, y);
			case StaticConstantID.DISCESA_DOWN:
				return new DiscesaDown2D(x, y);
			case StaticConstantID.DISCESA_LEFT:
				return new DiscesaLeft2D(x, y);
			case StaticConstantID.DISCESA_RIGHT:
				return new DiscesaRight2D(x, y);
			case StaticConstantID.DISCESA_UP:
				return new DiscesaUp2D(x, y);
			case StaticConstantID.LAND_TERRAIN:
				return new LandTerrain2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_LEFT_INSIDE:
				return new LandTerrainCornerDownLeftInside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_LEFT_OUTSIDE:
				return new LandTerrainCornerDownLeftOutside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_RIGHT_INSIDE:
				return new LandTerrainCornerDownRightInside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_RIGHT_OUTSIDE:
				return new LandTerrainCornerDownRightOutside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_LEFT_INSIDE:
				return new LandTerrainCornerUpLeftInside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_LEFT_OUTSIDE:
				return new LandTerrainCornerUpLeftOutside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_RIGHT_INSIDE:
				return new LandTerrainCornerUpRightInside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_RIGHT_OUTSIDE:
				return new LandTerrainCornerUpRightOutside2D(x, y);
			case StaticConstantID.LAND_TERRAIN_DOWN:
				return new LandTerrainDown2D(x, y);
			case StaticConstantID.LAND_TERRAIN_LEFT:
				return new LandTerrainLeft2D(x, y);
			case StaticConstantID.LAND_TERRAIN_RIGHT:
				return new LandTerrainRight2D(x, y);
			case StaticConstantID.LAND_TERRAIN_UP:
				return new LandTerrainUp2D(x, y);
			case StaticConstantID.MOUNTAIN_DOWN:
				return new MountainDown2D(x, y);
			case StaticConstantID.MOUNTAIN_LEFT:
				return new MountainLeft2D(x, y);
			case StaticConstantID.MOUNTAIN_RIGHT:
				return new MountainRight2D(x, y);
			case StaticConstantID.MOUNTAIN_UP:
				return new MountainUp2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_DOWN:
				return new MountainCornerLeftDown2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_UP:
				return new MountainCornerLeftUp2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_DOWN:
				return new MountainCornerRightDown2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_UP:
				return new MountainCornerRightUp2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_DOWN_INSIDE:
				return new MountainCornerLeftDownInside2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_UP_INSIDE:
				return new MountainCornerLeftUpInside2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_DOWN_INSIDE:
				return new MountainCornerRightDownInside2D(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_UP_INSIDE:
				return new MountainCornerRightUpInside2D(x, y);
			case StaticConstantID.WATER:
				return new Water2D(x, y);
			case StaticConstantID.WATER_ANGLE_DOWN_LEFT:
				return new WaterAngleDownLeft2D(x, y);
			case StaticConstantID.WATER_ANGLE_DOWN_RIGHT:
				return new WaterAngleDownRight2D(x, y);
			case StaticConstantID.WATER_ANGLE_UP_LEFT:
				return new WaterAngleUpLeft2D(x, y);
			case StaticConstantID.WATER_ANGLE_UP_RIGHT:
				return new WaterAngleUpRight2D(x, y);
			case StaticConstantID.WATER_BORDER_DOWN:
				return new WaterBorderDown2D(x, y);
			case StaticConstantID.WATER_BORDER_LEFT:
				return new WaterBorderLeft2D(x, y);
			case StaticConstantID.WATER_BORDER_RIGHT:
				return new WaterBorderRight2D(x, y);
			case StaticConstantID.WATER_BORDER_UP:
				return new WaterBorderUp2D(x, y);
				// Kakariko Village
			case StaticConstantID.KAKARIKO_TWO:
				return new KakarikoTwo2D(x, y);
			case StaticConstantID.KAKARIKO_FOUR:
				return new KakarikoFour2D(x, y);
			case StaticConstantID.KAKARIKO_FIVE:
				return new KakarikoFive2D(x, y);
			case StaticConstantID.KAKARIKO_SIX:
				return new KakarikoSix2D(x, y);
			case StaticConstantID.KAKARIKO_SEVEN:
				return new KakarikoSeven2D(x, y);
			case StaticConstantID.KAKARIKO_EIGHT:
				return new KakarikoEight2D(x, y);
			case StaticConstantID.KAKARIKO_NINE:
				return new KakarikoNine2D(x, y);
				// Desert
			case StaticConstantID.DESERT_ONE:
				return new DesertOne2D(x, y);
			case StaticConstantID.DESERT_TWO:
				return new DesertTwo2D(x, y);
			case StaticConstantID.DESERT_THREE:
				return new DesertThree2D(x, y);
			case StaticConstantID.DESERT_FOUR:
				return new DesertFour2D(x, y);
			case StaticConstantID.DESERT_FIVE:
				return new DesertFive2D(x, y);
			case StaticConstantID.DESERT_SIX:
				return new DesertSix2D(x, y);
			case StaticConstantID.DESERT_SEVEN:
				return new DesertSeven2D(x, y);
			case StaticConstantID.DESERT_EIGHT:
				return new DesertEight2D(x, y);
			case StaticConstantID.DESERT_NINE:
				return new DesertNine2D(x, y);
			case StaticConstantID.DESERT_TEN:
				return new DesertTen2D(x, y);
			case StaticConstantID.DESERT_ELEVEN:
				return new DesertEleven2D(x, y);
			case StaticConstantID.DESERT_TWELVE:
				return new DesertTwelve2D(x, y);
			case StaticConstantID.QUICKSAND:
				return new QuickSand2D(x, y);
				// Dungeon
			case StaticConstantID.DUNGEON_1:
				return new Dungeon1_2D(x, y);
			case StaticConstantID.DUNGEON_2:
				return new Dungeon2_2D(x, y);
			case StaticConstantID.DUNGEON_3:
				return new Dungeon3_2D(x, y);
			case StaticConstantID.DUNGEON_4:
				return new Dungeon4_2D(x, y);
			case StaticConstantID.DUNGEON_5:
				return new Dungeon5_2D(x, y);
			case StaticConstantID.DUNGEON_6:
				return new Dungeon6_2D(x, y);
			case StaticConstantID.DUNGEON_7:
				return new Dungeon7_2D(x, y);
			case StaticConstantID.DUNGEON_8:
				return new Dungeon8_2D(x, y);
			case StaticConstantID.DUNGEON_9:
				return new Dungeon9_2D(x, y);
			case StaticConstantID.DUNGEON_10:
				return new Dungeon10_2D(x, y);
			case StaticConstantID.DUNGEON_11:
				return new Dungeon11_2D(x, y);
			case StaticConstantID.DUNGEON_12:
				return new Dungeon12_2D(x, y);
			case StaticConstantID.DUNGEON_13:
				return new Dungeon13_2D(x, y);
			case StaticConstantID.DUNGEON_14:
				return new Dungeon14_2D(x, y);
			case StaticConstantID.DUNGEON_15:
				return new Dungeon15_2D(x, y);
			case StaticConstantID.DUNGEON_16:
				return new Dungeon16_2D(x, y);
			case StaticConstantID.DUNGEON_17:
				return new Dungeon17_2D(x, y);
			case StaticConstantID.DUNGEON_18:
				return new Dungeon18_2D(x, y);
			case StaticConstantID.DUNGEON_19:
				return new Dungeon19_2D(x, y);
			case StaticConstantID.DUNGEON_20:
				return new Dungeon20_2D(x, y);
			case StaticConstantID.DUNGEON_21:
				return new Dungeon21_2D(x, y);
			case StaticConstantID.DUNGEON_22:
				return new Dungeon22_2D(x, y);
			case StaticConstantID.DUNGEON_23:
				return new Dungeon23_2D(x, y);
			case StaticConstantID.DUNGEON_24:
				return new Dungeon24_2D(x, y);
			case StaticConstantID.DUNGEON_25:
				return new Dungeon25_2D(x, y);
			case StaticConstantID.DUNGEON_26:
				return new Dungeon26_2D(x, y);
			case StaticConstantID.DUNGEON_27:
				return new Dungeon27_2D(x, y);
			case StaticConstantID.DUNGEON_28:
				return new Dungeon28_2D(x, y);
			case StaticConstantID.DUNGEON_29:
				return new Dungeon29_2D(x, y);
			case StaticConstantID.DUNGEON_30:
				return new Dungeon30_2D(x, y);
			case StaticConstantID.DUNGEON_31:
				return new Dungeon31_2D(x, y);
			case StaticConstantID.DUNGEON_32:
				return new Dungeon32_2D(x, y);
				// Forest
			case StaticConstantID.FOREST_1:
				return new Forest1_2D(x, y);
			case StaticConstantID.FOREST_2:
				return new Forest2_2D(x, y);
			case StaticConstantID.FOREST_3:
				return new Forest3_2D(x, y);
			case StaticConstantID.FOREST_4:
				return new Forest4_2D(x, y);
			case StaticConstantID.FOREST_5:
				return new Forest5_2D(x, y);
			case StaticConstantID.FOREST_6:
				return new Forest6_2D(x, y);
			case StaticConstantID.FOREST_7:
				return new Forest7_2D(x, y);
			case StaticConstantID.FOREST_8:
				return new Forest8_2D(x, y);
			case StaticConstantID.FOREST_9:
				return new Forest9_2D(x, y);
			case StaticConstantID.FOREST_10:
				return new Forest10_2D(x, y);
			case StaticConstantID.FOREST_11:
				return new Forest11_2D(x, y);
			case StaticConstantID.FOREST_12:
				return new Forest12_2D(x, y);
			case StaticConstantID.FOREST_13:
				return new Forest13_2D(x, y);
			case StaticConstantID.FOREST_14:
				return new Forest14_2D(x, y);
			case StaticConstantID.FOREST_15:
				return new Forest15_2D(x, y);
			case StaticConstantID.FOREST_16:
				return new Forest16_2D(x, y);
				// Lake
			case StaticConstantID.LAKE_1:
				return new Lake1_2D(x, y);
			case StaticConstantID.LAKE_2:
				return new Lake2_2D(x, y);
			case StaticConstantID.LAKE_3:
				return new Lake3_2D(x, y);
			case StaticConstantID.LAKE_4:
				return new Lake4_2D(x, y);
			case StaticConstantID.LAKE_5:
				return new Lake5_2D(x, y);
			case StaticConstantID.LAKE_6:
				return new Lake6_2D(x, y);
			case StaticConstantID.LAKE_7:
				return new Lake7_2D(x, y);
			case StaticConstantID.LAKE_8:
				return new Lake8_2D(x, y);
			case StaticConstantID.LAKE_9:
				return new Lake9_2D(x, y);
			case StaticConstantID.LAKE_10:
				return new Lake10_2D(x, y);
			case StaticConstantID.LAKE_11:
				return new Lake11_2D(x, y);
			case StaticConstantID.LAKE_12:
				return new Lake12_2D(x, y);
			case StaticConstantID.LAKE_13:
				return new Lake13_2D(x, y);
			case StaticConstantID.LAKE_14:
				return new Lake14_2D(x, y);
			case StaticConstantID.LAKE_15:
				return new Lake15_2D(x, y);
			case StaticConstantID.LAKE_16:
				return new Lake16_2D(x, y);
				// Volcano
			case StaticConstantID.VOLCANO_1:
				return new Volcano1_2D(x, y);
			case StaticConstantID.VOLCANO_2:
				return new Volcano2_2D(x, y);
			case StaticConstantID.VOLCANO_3:
				return new Volcano3_2D(x, y);
			case StaticConstantID.VOLCANO_4:
				return new Volcano4_2D(x, y);
			case StaticConstantID.VOLCANO_5:
				return new Volcano5_2D(x, y);
			case StaticConstantID.VOLCANO_6:
				return new Volcano6_2D(x, y);
			case StaticConstantID.VOLCANO_7:
				return new Volcano7_2D(x, y);
			case StaticConstantID.VOLCANO_8:
				return new Volcano8_2D(x, y);
			case StaticConstantID.VOLCANO_9:
				return new Volcano9_2D(x, y);
			case StaticConstantID.VOLCANO_10:
				return new Volcano10_2D(x, y);
			case StaticConstantID.VOLCANO_11:
				return new Volcano11_2D(x, y);
			case StaticConstantID.VOLCANO_12:
				return new Volcano12_2D(x, y);
			case StaticConstantID.VOLCANO_13:
				return new Volcano13_2D(x, y);
			case StaticConstantID.VOLCANO_14:
				return new Volcano14_2D(x, y);
			case StaticConstantID.VOLCANO_15:
				return new Volcano15_2D(x, y);
			case StaticConstantID.VOLCANO_16:
				return new Volcano16_2D(x, y);
				// Sky
			case StaticConstantID.SKY_1:
				return new Sky1_2D(x, y);
			case StaticConstantID.SKY_2:
				return new Sky2_2D(x, y);
			case StaticConstantID.SKY_3:
				return new Sky3_2D(x, y);
			case StaticConstantID.SKY_4:
				return new Sky4_2D(x, y);
			case StaticConstantID.SKY_5:
				return new Sky5_2D(x, y);
			case StaticConstantID.SKY_6:
				return new Sky6_2D(x, y);
			case StaticConstantID.SKY_7:
				return new Sky7_2D(x, y);
			case StaticConstantID.SKY_8:
				return new Sky8_2D(x, y);
			case StaticConstantID.SKY_9:
				return new Sky9_2D(x, y);
			case StaticConstantID.SKY_10:
				return new Sky10_2D(x, y);
			case StaticConstantID.SKY_11:
				return new Sky11_2D(x, y);
			case StaticConstantID.SKY_12:
				return new Sky12_2D(x, y);
			case StaticConstantID.SKY_13:
				return new Sky13_2D(x, y);
			case StaticConstantID.SKY_14:
				return new Sky14_2D(x, y);
			case StaticConstantID.SKY_15:
				return new Sky15_2D(x, y);
			case StaticConstantID.SKY_16:
				return new Sky16_2D(x, y);
				// Object World
			case StaticConstantID.BUTTON:
				return new Button2D(x, y);
			case StaticConstantID.COIN:
				return new Coin2D(x, y);
			case StaticConstantID.EMPTY_UTILITY:
				return new EmptyUtility2D(x, y);
			case StaticConstantID.FINAL_POINT:
				return new FinalPoint2D(x, y);
			case StaticConstantID.FLAME_TOWER:
				return new FlameTower2D(x, y);
			case StaticConstantID.FLOWER:
				return new Flower2D(x, y);
			case StaticConstantID.GATE:
				return new Gate2D(x, y);
			case StaticConstantID.HEART:
				return new Heart2D(x, y);
			case StaticConstantID.INVISIBLE_WALL:
				return new InvisibleWall2D(x, y);
			case StaticConstantID.MAGIC_SPHERE:
				return new MagicSphere2D(x, y);
			case StaticConstantID.PLATFORM:
				return new Platform2D(x, y);
			case StaticConstantID.START_POINT:
				return new StartPoint2D(x, y);
			case StaticConstantID.TREASURE:
				return new Treasure2D(x, y);
			case StaticConstantID.STATIC_PLATFORM:
				return new StaticPlatform2D(x, y);
			case StaticConstantID.MAGIC_GATE:
				return new MagicGate2D(x, y);
			case StaticConstantID.FALL_POINT:
				return new FallPoint2D(x, y);
			case StaticConstantID.TRIFORCE_PIECE:
				return new TriforcePiece2D(x, y);
				// Character
			case StaticConstantID.ATTACK_SPHERE:
				return new AttackSphere2D(x, y);
			case StaticConstantID.EMPTY_CHARACTER:
				return new EmptyCharacter2D(x, y);
			case StaticConstantID.GANONDORF:
				return new Ganondorf2D(x, y);
			case StaticConstantID.HERO:
				return new Hero2D(StaticConstantGame.nameHero, x, y);
			case StaticConstantID.SKULL:
				return new Skull2D(x, y);
			case StaticConstantID.WARRIOR:
				return new Warrior2D(x, y);
			case StaticConstantID.WIZARD:
				return new Wizard2D(x, y);

			default:
				throw new RuntimeException("Not supported " + typeObject);
		}
	}

	private ObjectScene getObject3D(final int typeObject, final int x, final int y)
	{
		switch (typeObject)
		{
		// Base World
			case StaticConstantID.EMPTY:
				return new Empty(x, y);
			case StaticConstantID.HOUSE_LARGE:
				return new HouseLarge(x, y);
			case StaticConstantID.HOUSE_SMALL:
				return new HouseSmall(x, y);
			case StaticConstantID.MARBLE_SCALE_DOWN:
				return new MarbleScaleDown(x, y);
			case StaticConstantID.MARBLE_SCALE_UP:
				return new MarbleScaleUp(x, y);
			case StaticConstantID.MARBLE_SCALE_LEFT:
				return new MarbleScaleLeft(x, y);
			case StaticConstantID.MARBLE_SCALE_RIGHT:
				return new MarbleScaleRight(x, y);
			case StaticConstantID.SKULL_HEAD:
				return new SkullHead(x, y);
			case StaticConstantID.STATUE:
				return new Statue(x, y);
			case StaticConstantID.SWORD_TRONE:
				return new SwordTrone(x, y);
			case StaticConstantID.TOWER_ROYAL:
				return new TowerRoyal(x, y);
			case StaticConstantID.TREE:
				return new Tree(x, y);
			case StaticConstantID.TREE_GREEN:
				return new TreeGreen(x, y);
			case StaticConstantID.TREE_VIOLET:
				return new TreeViolet(x, y);
			case StaticConstantID.TRUNK:
				return new Trunk(x, y);
			case StaticConstantID.BLOCK:
				return new Block3D(x, y);
			case StaticConstantID.XLAND:
				return new XLand(x, y);
				// Forest
			case StaticConstantID.LAND:
				return new Land(x, y);
			case StaticConstantID.WALL:
				return new Wall(x, y);
			case StaticConstantID.DISCESA_DOWN:
				return new DiscesaDown(x, y);
			case StaticConstantID.DISCESA_LEFT:
				return new DiscesaLeft(x, y);
			case StaticConstantID.DISCESA_RIGHT:
				return new DiscesaRight(x, y);
			case StaticConstantID.DISCESA_UP:
				return new DiscesaUp(x, y);
			case StaticConstantID.LAND_TERRAIN:
				return new LandTerrain(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_LEFT_INSIDE:
				return new LandTerrainCornerDownLeftInside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_LEFT_OUTSIDE:
				return new LandTerrainCornerDownLeftOutside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_RIGHT_INSIDE:
				return new LandTerrainCornerDownRightInside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_DOWN_RIGHT_OUTSIDE:
				return new LandTerrainCornerDownRightOutside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_LEFT_INSIDE:
				return new LandTerrainCornerUpLeftInside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_LEFT_OUTSIDE:
				return new LandTerrainCornerUpLeftOutside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_RIGHT_INSIDE:
				return new LandTerrainCornerUpRightInside(x, y);
			case StaticConstantID.LAND_TERRAIN_CORNER_UP_RIGHT_OUTSIDE:
				return new LandTerrainCornerUpRightOutside(x, y);
			case StaticConstantID.LAND_TERRAIN_DOWN:
				return new LandTerrainDown(x, y);
			case StaticConstantID.LAND_TERRAIN_LEFT:
				return new LandTerrainLeft(x, y);
			case StaticConstantID.LAND_TERRAIN_RIGHT:
				return new LandTerrainRight(x, y);
			case StaticConstantID.LAND_TERRAIN_UP:
				return new LandTerrainUp(x, y);
			case StaticConstantID.MOUNTAIN_DOWN:
				return new MountainDown(x, y);
			case StaticConstantID.MOUNTAIN_LEFT:
				return new MountainLeft(x, y);
			case StaticConstantID.MOUNTAIN_RIGHT:
				return new MountainRight(x, y);
			case StaticConstantID.MOUNTAIN_UP:
				return new MountainUp(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_DOWN:
				return new MountainCornerLeftDown(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_UP:
				return new MountainCornerLeftUp(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_DOWN:
				return new MountainCornerRightDown(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_UP:
				return new MountainCornerRightUp(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_DOWN_INSIDE:
				return new MountainCornerLeftDownInside(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_LEFT_UP_INSIDE:
				return new MountainCornerLeftUpInside(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_DOWN_INSIDE:
				return new MountainCornerRightDownInside(x, y);
			case StaticConstantID.MOUNTAIN_CORNER_RIGHT_UP_INSIDE:
				return new MountainCornerRightUpInside(x, y);
			case StaticConstantID.WATER:
				return new Water(x, y);
			case StaticConstantID.WATER_ANGLE_DOWN_LEFT:
				return new WaterAngleDownLeft(x, y);
			case StaticConstantID.WATER_ANGLE_DOWN_RIGHT:
				return new WaterAngleDownRight(x, y);
			case StaticConstantID.WATER_ANGLE_UP_LEFT:
				return new WaterAngleUpLeft(x, y);
			case StaticConstantID.WATER_ANGLE_UP_RIGHT:
				return new WaterAngleUpRight(x, y);
			case StaticConstantID.WATER_BORDER_DOWN:
				return new WaterBorderDown(x, y);
			case StaticConstantID.WATER_BORDER_LEFT:
				return new WaterBorderLeft(x, y);
			case StaticConstantID.WATER_BORDER_RIGHT:
				return new WaterBorderRight(x, y);
			case StaticConstantID.WATER_BORDER_UP:
				return new WaterBorderUp(x, y);
				// Kakariko Village
			case StaticConstantID.KAKARIKO_TWO:
				return new KakarikoTwo(x, y);
			case StaticConstantID.KAKARIKO_FOUR:
				return new KakarikoFour(x, y);
			case StaticConstantID.KAKARIKO_FIVE:
				return new KakarikoFive(x, y);
			case StaticConstantID.KAKARIKO_SIX:
				return new KakarikoSix(x, y);
			case StaticConstantID.KAKARIKO_SEVEN:
				return new KakarikoSeven(x, y);
			case StaticConstantID.KAKARIKO_EIGHT:
				return new KakarikoEight(x, y);
			case StaticConstantID.KAKARIKO_NINE:
				return new KakarikoNine(x, y);
				// Desert
			case StaticConstantID.DESERT_ONE:
				return new DesertOne(x, y);
			case StaticConstantID.DESERT_TWO:
				return new DesertTwo(x, y);
			case StaticConstantID.DESERT_THREE:
				return new DesertThree(x, y);
			case StaticConstantID.DESERT_FOUR:
				return new DesertFour(x, y);
			case StaticConstantID.DESERT_FIVE:
				return new DesertFive(x, y);
			case StaticConstantID.DESERT_SIX:
				return new DesertSix(x, y);
			case StaticConstantID.DESERT_SEVEN:
				return new DesertSeven(x, y);
			case StaticConstantID.DESERT_EIGHT:
				return new DesertEight(x, y);
			case StaticConstantID.DESERT_NINE:
				return new DesertNine(x, y);
			case StaticConstantID.DESERT_TEN:
				return new DesertTen(x, y);
			case StaticConstantID.DESERT_ELEVEN:
				return new DesertEleven(x, y);
			case StaticConstantID.DESERT_TWELVE:
				return new DesertTwelve(x, y);
			case StaticConstantID.QUICKSAND:
				return new QuickSand(x, y);
				// Dungeon
			case StaticConstantID.DUNGEON_1:
				return new Dungeon1(x, y);
			case StaticConstantID.DUNGEON_2:
				return new Dungeon2(x, y);
			case StaticConstantID.DUNGEON_3:
				return new Dungeon3(x, y);
			case StaticConstantID.DUNGEON_4:
				return new Dungeon4(x, y);
			case StaticConstantID.DUNGEON_5:
				return new Dungeon5(x, y);
			case StaticConstantID.DUNGEON_6:
				return new Dungeon6(x, y);
			case StaticConstantID.DUNGEON_7:
				return new Dungeon7(x, y);
			case StaticConstantID.DUNGEON_8:
				return new Dungeon8(x, y);
			case StaticConstantID.DUNGEON_9:
				return new Dungeon9(x, y);
			case StaticConstantID.DUNGEON_10:
				return new Dungeon10(x, y);
			case StaticConstantID.DUNGEON_11:
				return new Dungeon11(x, y);
			case StaticConstantID.DUNGEON_12:
				return new Dungeon12(x, y);
			case StaticConstantID.DUNGEON_13:
				return new Dungeon13(x, y);
			case StaticConstantID.DUNGEON_14:
				return new Dungeon14(x, y);
			case StaticConstantID.DUNGEON_15:
				return new Dungeon15(x, y);
			case StaticConstantID.DUNGEON_16:
				return new Dungeon16(x, y);
			case StaticConstantID.DUNGEON_17:
				return new Dungeon17(x, y);
			case StaticConstantID.DUNGEON_18:
				return new Dungeon18(x, y);
			case StaticConstantID.DUNGEON_19:
				return new Dungeon19(x, y);
			case StaticConstantID.DUNGEON_20:
				return new Dungeon20(x, y);
			case StaticConstantID.DUNGEON_21:
				return new Dungeon21(x, y);
			case StaticConstantID.DUNGEON_22:
				return new Dungeon22(x, y);
			case StaticConstantID.DUNGEON_23:
				return new Dungeon23(x, y);
			case StaticConstantID.DUNGEON_24:
				return new Dungeon24(x, y);
			case StaticConstantID.DUNGEON_25:
				return new Dungeon25(x, y);
			case StaticConstantID.DUNGEON_26:
				return new Dungeon26(x, y);
			case StaticConstantID.DUNGEON_27:
				return new Dungeon27(x, y);
			case StaticConstantID.DUNGEON_28:
				return new Dungeon28(x, y);
			case StaticConstantID.DUNGEON_29:
				return new Dungeon29(x, y);
			case StaticConstantID.DUNGEON_30:
				return new Dungeon30(x, y);
			case StaticConstantID.DUNGEON_31:
				return new Dungeon31(x, y);
			case StaticConstantID.DUNGEON_32:
				return new Dungeon32(x, y);
				// Forest
			case StaticConstantID.FOREST_1:
				return new Forest1(x, y);
			case StaticConstantID.FOREST_2:
				return new Forest2(x, y);
			case StaticConstantID.FOREST_3:
				return new Forest3(x, y);
			case StaticConstantID.FOREST_4:
				return new Forest4(x, y);
			case StaticConstantID.FOREST_5:
				return new Forest5(x, y);
			case StaticConstantID.FOREST_6:
				return new Forest6(x, y);
			case StaticConstantID.FOREST_7:
				return new Forest7(x, y);
			case StaticConstantID.FOREST_8:
				return new Forest8(x, y);
			case StaticConstantID.FOREST_9:
				return new Forest9(x, y);
			case StaticConstantID.FOREST_10:
				return new Forest10(x, y);
			case StaticConstantID.FOREST_11:
				return new Forest11(x, y);
			case StaticConstantID.FOREST_12:
				return new Forest12(x, y);
			case StaticConstantID.FOREST_13:
				return new Forest13(x, y);
			case StaticConstantID.FOREST_14:
				return new Forest14(x, y);
			case StaticConstantID.FOREST_15:
				return new Forest15(x, y);
			case StaticConstantID.FOREST_16:
				return new Forest16(x, y);
				// Lake
			case StaticConstantID.LAKE_1:
				return new Lake1(x, y);
			case StaticConstantID.LAKE_2:
				return new Lake2(x, y);
			case StaticConstantID.LAKE_3:
				return new Lake3(x, y);
			case StaticConstantID.LAKE_4:
				return new Lake4(x, y);
			case StaticConstantID.LAKE_5:
				return new Lake5(x, y);
			case StaticConstantID.LAKE_6:
				return new Lake6(x, y);
			case StaticConstantID.LAKE_7:
				return new Lake7(x, y);
			case StaticConstantID.LAKE_8:
				return new Lake8(x, y);
			case StaticConstantID.LAKE_9:
				return new Lake9(x, y);
			case StaticConstantID.LAKE_10:
				return new Lake10(x, y);
			case StaticConstantID.LAKE_11:
				return new Lake11(x, y);
			case StaticConstantID.LAKE_12:
				return new Lake12(x, y);
			case StaticConstantID.LAKE_13:
				return new Lake13(x, y);
			case StaticConstantID.LAKE_14:
				return new Lake14(x, y);
			case StaticConstantID.LAKE_15:
				return new Lake15(x, y);
			case StaticConstantID.LAKE_16:
				return new Lake16(x, y);
				// Volcano
			case StaticConstantID.VOLCANO_1:
				return new Volcano1(x, y);
			case StaticConstantID.VOLCANO_2:
				return new Volcano2(x, y);
			case StaticConstantID.VOLCANO_3:
				return new Volcano3(x, y);
			case StaticConstantID.VOLCANO_4:
				return new Volcano4(x, y);
			case StaticConstantID.VOLCANO_5:
				return new Volcano5(x, y);
			case StaticConstantID.VOLCANO_6:
				return new Volcano6(x, y);
			case StaticConstantID.VOLCANO_7:
				return new Volcano7(x, y);
			case StaticConstantID.VOLCANO_8:
				return new Volcano8(x, y);
			case StaticConstantID.VOLCANO_9:
				return new Volcano9(x, y);
			case StaticConstantID.VOLCANO_10:
				return new Volcano10(x, y);
			case StaticConstantID.VOLCANO_11:
				return new Volcano11(x, y);
			case StaticConstantID.VOLCANO_12:
				return new Volcano12(x, y);
			case StaticConstantID.VOLCANO_13:
				return new Volcano13(x, y);
			case StaticConstantID.VOLCANO_14:
				return new Volcano14(x, y);
			case StaticConstantID.VOLCANO_15:
				return new Volcano15(x, y);
			case StaticConstantID.VOLCANO_16:
				return new Volcano16(x, y);
				// Sky
			case StaticConstantID.SKY_1:
				return new Sky1(x, y);
			case StaticConstantID.SKY_2:
				return new Sky2(x, y);
			case StaticConstantID.SKY_3:
				return new Sky3(x, y);
			case StaticConstantID.SKY_4:
				return new Sky4(x, y);
			case StaticConstantID.SKY_5:
				return new Sky5(x, y);
			case StaticConstantID.SKY_6:
				return new Sky6(x, y);
			case StaticConstantID.SKY_7:
				return new Sky7(x, y);
			case StaticConstantID.SKY_8:
				return new Sky8(x, y);
			case StaticConstantID.SKY_9:
				return new Sky9(x, y);
			case StaticConstantID.SKY_10:
				return new Sky10(x, y);
			case StaticConstantID.SKY_11:
				return new Sky11(x, y);
			case StaticConstantID.SKY_12:
				return new Sky12(x, y);
			case StaticConstantID.SKY_13:
				return new Sky13(x, y);
			case StaticConstantID.SKY_14:
				return new Sky14(x, y);
			case StaticConstantID.SKY_15:
				return new Sky15(x, y);
			case StaticConstantID.SKY_16:
				return new Sky16(x, y);
				// Object World
			case StaticConstantID.BUTTON:
				return new Button(x, y);
			case StaticConstantID.COIN:
				return new Coin3D(x, y);
			case StaticConstantID.EMPTY_UTILITY:
				return new EmptyUtility(x, y);
			case StaticConstantID.FINAL_POINT:
				return new FinalPoint3D(x, y);
			case StaticConstantID.FLAME_TOWER:
				return new FlameTower(x, y);
			case StaticConstantID.FLOWER:
				return new Flower(x, y);
			case StaticConstantID.GATE:
				return new Gate3D(x, y);
			case StaticConstantID.HEART:
				return new Heart3D(x, y);
			case StaticConstantID.INVISIBLE_WALL:
				return new InvisibleWall3D(x, y);
			case StaticConstantID.MAGIC_SPHERE:
				return new MagicSphere3D(x, y);
			case StaticConstantID.PLATFORM:
				return new Platform(x, y);
			case StaticConstantID.START_POINT:
				return new StartPoint3D(x, y);
			case StaticConstantID.TREASURE:
				return new Treasure3D(x, y);
			case StaticConstantID.STATIC_PLATFORM:
				return new StaticPlatform(x, y);
			case StaticConstantID.MAGIC_GATE:
				return new MagicGate(x, y);
			case StaticConstantID.FALL_POINT:
				return new FallPoint(x, y);
				// Character
			case StaticConstantID.ATTACK_SPHERE:
				return new AttackSphere(x, y);
			case StaticConstantID.EMPTY_CHARACTER:
				return new EmptyCharacter(x, y);
			case StaticConstantID.GANONDORF:
				return new Ganondorf(x, y);
			case StaticConstantID.HERO:
				return new Hero3D(x, y);
			case StaticConstantID.SKULL:
				return new Skull(x, y);
			case StaticConstantID.WARRIOR:
				return new Warrior3D(x, y);
			case StaticConstantID.WIZARD:
				return new Wizard3D(x, y);

			default:
				throw new RuntimeException("Not supported " + typeObject);
		}
	}

	public Collection<AbstractUtilityObject> getUtilityObjectCollection()
	{
		return utilityObjectCollection;
	}

	private ConcreteWorld levelTest() throws Exception
	{
		loadLevel("Levels/Test/");
		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Platform2D)
			{
				((Platform2D) currentObject).setDirection(new Direction(Direction.UP));
			}
		}
		currentWorld.setLevelLogic(new WorldLogic());
		return currentWorld;
	}

	private ConcreteWorld loadLevel(String name) throws Exception
	{
		// Conto il numero di righe e colonne che caratterizzano il mondo
		Integer row = 0;
		Integer column = 0;
		final BufferedReader brBase = new BufferedReader(new FileReader((name + "/base.txt")));
		String bufferBase;
		StringTokenizer tokenBase = null;
		int numberRows = 0;
		while ((bufferBase = brBase.readLine()) != null)
		{
			tokenBase = new StringTokenizer(bufferBase);
			column = tokenBase.countTokens();
			numberRows++;
		}
		row = numberRows;
		// fine conteggio

		AbstractObjectScene[][] base = new AbstractObjectScene[row][column];
		AbstractUtilityObject[][] object = new AbstractUtilityObject[row][column];
		AbstractCharacter[][] character = new AbstractCharacter[row][column];

		readWorld(name + "/base.txt", name + "/object.txt", name + "/character.txt", base, object,
				character);

		currentWorld = new ConcreteWorld(StaticConstantWorld.DEFAULT_MOVEMENT, base, object,
				character, null);

		utilityObjectCollection = currentWorld.getObjectCollection();
		characterCollection = currentWorld.getCharacterCollection();

		return currentWorld;
	}

	public ConcreteWorld loadWorld() throws Exception
	{
		switch (StaticConstantGame.typeWorldChoose)
		{
			case 1:
				return levelOne();
			case 2:
				return levelTwo();
			case 3:
				return levelThree();
			case 4:
				return levelFour();
			case 5:
				return levelFive();
			case 6:
				return levelSix();
			case 7:
				return levelSeven();
			case 8:
				return levelMuliplayer();
			case 9:
				return levelForest3D();
			default:
				return levelTest();
		}
	}

	private ConcreteWorld levelThree() throws Exception
	{
		loadLevel("Levels/Dungeon/");

		int i = 0;
		int[] x = { 17, 12, 5, 2, 5 };
		int[] y = { 3, 3, 3, 30, 77 };
		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Gate)
			{
				((Gate) currentObject).setNextX(x[i]);
				((Gate) currentObject).setNextY(y[i]);
				((Gate) currentObject).setOpen(true);
				if (i != 4 && currentObject instanceof Gate2D)
				{
					((Gate2D) currentObject).setNullGate(true);
				}
				if (currentObject.getX() == 23 && currentObject.getY() == 3)
				{
					((Gate) currentObject).setOpen(false);
				}
				i++;
			}
			if (currentObject instanceof FlameTower)
			{
				((FlameTower) currentObject).setOpen(false);
			}
		}
		StaticConstantGame.darkness = true;
		StaticConstantGame.typeDarkness = 0;
		currentWorld.setLevelLogic(new LogicLevelThree());

		SoundManager.playSound(SoundManager.DUNGEON_1, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private ConcreteWorld levelFour() throws Exception
	{
		loadLevel("Levels/Forest/");

		int i = 0;
		int[] x = { 11, 12, 4, 4, 18, 18, 19, 19, 12, 12, 12, 12, 29, 30, 30, 34, 23, 26, 26, 26 };
		int[] y = { 13, 19, 13, 19, 5, 9, 24, 26, 5, 9, 24, 26, 19, 8, 10, 12, 19, 8, 10, 12 };

		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Gate)
			{
				((Gate) currentObject).setNextX(x[i]);
				((Gate) currentObject).setNextY(y[i]);
				((Gate) currentObject).setOpen(true);
				if (!StaticConstantGame.enable3D)
				{
					((Gate2D) currentObject).setNullGate(true);
				}
				i++;
			}
			if (currentObject instanceof FlameTower)
			{
				((FlameTower) currentObject).setOpen(false);
			}
		}

		currentWorld.setLevelLogic(new LogicLevelFour());
		StaticConstantGame.fog = true;

		SoundManager.playSound(SoundManager.FOREST, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private ConcreteWorld levelFive() throws Exception
	{
		loadLevel("Levels/Lake/");
		int[] x = { 8, 5, 5, 8, 8, 28, 8, 5, 8, 8, 11, 11, 32, 18, 20, 26, 26, 22, 30, 19 };
		int[] y = { 8, 32, 32, 19, 18, 8, 8, 32, 18, 21, 8, 11, 30, 31, 27, 7, 13, 34, 9, 28 };
		int[] b = { 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 };
		Iterator<AbstractUtilityObject> iterator = utilityObjectCollection.iterator();

		int i = 0;

		while (iterator.hasNext())
		{
			AbstractUtilityObject currentObject = iterator.next();
			if (currentObject instanceof MagicGate2D)
			{
				MagicGate2D magicGate2D = ((MagicGate2D) currentObject);
				magicGate2D.setNextX(x[i]);
				magicGate2D.setNextY(y[i]);
				if (b[i] == 1)
				{
					magicGate2D.setCorrect(true);
				}
				else
				{
					magicGate2D.setCorrect(false);
				}
				i++;
			}
		}

		currentWorld.setLevelLogic(new LogicLevelFive());
		StaticConstantGame.rain = true;

		SoundManager.playSound(SoundManager.LAKE, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private ConcreteWorld levelSix() throws Exception
	{
		loadLevel("Levels/Volcano/");
		currentWorld.setLevelLogic(new LogicLevelSix());

		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Gate)
			{
				int xGate = currentObject.getX();
				int yGate = currentObject.getY();

				int nextX = 0;
				int nextY = 0;

				if (xGate >= 30 && yGate >= 25)
				{
					nextX = 12;
					nextY = 30;
				}
				else if (xGate >= 30 && yGate >= 20)
				{
					nextX = 11;
					nextY = 21;
				}
				else if (xGate >= 30 && yGate >= 15)
				{
					nextX = 12;
					nextY = 14;
				}
				else if (xGate >= 30 && yGate >= 10)
				{
					nextX = 12;
					nextY = 6;
				}
				else if (xGate >= 20 && yGate >= 25)
				{
					nextX = 4;
					nextY = 30;
				}
				else if (xGate >= 20 && yGate >= 20)
				{
					nextX = 6;
					nextY = 22;
				}
				else if (xGate >= 20 && yGate >= 15)
				{
					nextX = 5;
					nextY = 14;
				}
				else if (xGate >= 20 && yGate >= 10)
				{
					nextX = 5;
					nextY = 5;
				}

				((Gate) currentObject).setNextX(nextX);
				((Gate) currentObject).setNextY(nextY);
				((Gate) currentObject).setOpen(true);
				if (currentObject instanceof Gate2D)
				{
					((Gate2D) currentObject).setNullGate(true);
				}
			}
		}

		StaticConstantGame.volcanoFog = true;

		SoundManager.playSound(SoundManager.VOLCANO, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private ConcreteWorld levelSeven() throws Exception
	{
		loadLevel("Levels/Sky/");
		currentWorld.setLevelLogic(new LogicLevelSeven());

		int i = 0;
		int[] x = { 38, 6, 6 };
		int[] y = { 5, 28, 28 };

		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Gate)
			{
				((Gate) currentObject).setNextX(x[i]);
				((Gate) currentObject).setNextY(y[i]);
				((Gate) currentObject).setOpen(true);
				((Gate2D) currentObject).setNullGate(true);
				i++;
			}
			if (currentObject instanceof FlameTower)
			{
				((FlameTower) currentObject).setOpen(false);
			}
		}
		StaticConstantGame.cloud = true;

		SoundManager.playSound(SoundManager.SKY, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private ConcreteWorld levelOne() throws Exception
	{
		loadLevel("Levels/Kakariko/");
		currentWorld.setLevelLogic(new LogicLevelOne());

		SoundManager.playSound(SoundManager.KAKARIKO, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private ConcreteWorld levelTwo() throws Exception
	{
		loadLevel("Levels/Desert/");
		QuickSand.resetX = 30;
		QuickSand.resetY = 6;

		int i = 0;
		int[] movements = { 2, 4, 4, 5, 5, 4, 2, 5, 5 };
		int[] directions = { 4, 3, 3, 5, 7, 7, 1, 1, 1 };

		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Platform2D)
			{
				((Platform2D) currentObject).setMovement(movements[i]);
				((Platform2D) currentObject).setDirection(new Direction(directions[i]));
				i++;
			}
		}
		currentWorld.setLevelLogic(new LogicLevelTwo());

		SoundManager.playSound(SoundManager.DESERT, 0, SoundManager.MUSIC);

		return currentWorld;
	}

	private void readWorld(String baseWorld, String objectWorld, String characterWorld,
			AbstractObjectScene[][] base, AbstractUtilityObject[][] object,
			AbstractCharacter[][] character) throws IOException
	{
		final BufferedReader brBase = new BufferedReader(new FileReader(baseWorld));
		final BufferedReader brObject = new BufferedReader(new FileReader(objectWorld));
		final BufferedReader brCharacter = new BufferedReader(new FileReader(characterWorld));
		String bufferBase;
		String bufferObject;
		String bufferCharacter;
		int x = 0;
		while (((bufferBase = brBase.readLine()) != null)
				&& ((bufferObject = brObject.readLine()) != null)
				&& ((bufferCharacter = brCharacter.readLine()) != null))
		{
			StringTokenizer tokenBase = new StringTokenizer(bufferBase);
			StringTokenizer tokenObject = new StringTokenizer(bufferObject);
			StringTokenizer tokenCharacter = new StringTokenizer(bufferCharacter);
			int number = tokenBase.countTokens();
			for (int y = 0; y < number; y++)
			{
				int code = Integer.parseInt(tokenBase.nextToken());
				if (StaticConstantGame.enable3D)
				{
					base[x][y] = (AbstractObjectScene) getObject3D(code, x, y);
					code = Integer.parseInt(tokenObject.nextToken());
					object[x][y] = (AbstractUtilityObject) getObject3D(code, x, y);
					code = Integer.parseInt(tokenCharacter.nextToken());
					character[x][y] = (AbstractCharacter) getObject3D(code, x, y);
				}
				else
				{
					base[x][y] = (AbstractObjectScene) getObject(code, x, y);
					code = Integer.parseInt(tokenObject.nextToken());
					object[x][y] = (AbstractUtilityObject) getObject(code, x, y);
					code = Integer.parseInt(tokenCharacter.nextToken());
					character[x][y] = (AbstractCharacter) getObject(code, x, y);
				}

			}
			x++;
		}
	}

	public void resetIndexWorld()
	{
		StaticConstantGame.typeWorldChoose = 1;
	}

	private ConcreteWorld levelForest3D() throws Exception
	{
		loadLevel("Levels/Forest3D/");

		int i = 0;
		int[] x = { 11, 12, 4, 4, 18, 18, 19, 19, 12, 12, 12, 12, 29, 30, 30, 34, 23, 26, 26, 26 };
		int[] y = { 13, 19, 13, 19, 5, 9, 24, 26, 5, 9, 24, 26, 19, 8, 10, 12, 19, 8, 10, 12 };

		Iterator<AbstractUtilityObject> iteratorObject = utilityObjectCollection.iterator();
		while (iteratorObject.hasNext())
		{
			AbstractUtilityObject currentObject = iteratorObject.next();
			if (currentObject instanceof Gate)
			{
				((Gate) currentObject).setNextX(x[i]);
				((Gate) currentObject).setNextY(y[i]);
				((Gate) currentObject).setOpen(true);
				if (!StaticConstantGame.enable3D)
				{
					((Gate2D) currentObject).setNullGate(true);
				}
				i++;
			}
		}

		currentWorld.setLevelLogic(new LogicLevelFour());

		SoundManager.playSound(SoundManager.FOREST, 0, SoundManager.MUSIC);

		return currentWorld;
	}
}
