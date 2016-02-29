package gameLogic.logicState.logicCondition.conditionLevelFour;

import gameLogic.ObjectScene;
import gameLogic.character.AbstractCharacter;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.FlameTower;
import gameLogic.object.Gate;
import gameLogic.world.ConcreteWorld;
import guiInterface.character2D.AttackSphere2D;
import guiInterface.character2D.Character2D;
import guiInterface.character2D.EmptyCharacter2D;
import guiInterface.character2D.Ganondorf2D;
import guiInterface.character2D.Hero2D;
import guiInterface.character2D.Skull2D;
import guiInterface.character2D.Warrior2D;
import guiInterface.character2D.Wizard2D;
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
import guiInterface.object2D.StartPoint2D;
import guiInterface.object2D.StaticPlatform2D;
import guiInterface.object2D.Treasure2D;
import guiInterface.object2D.TriforcePiece2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class ChangeWorld extends AbstractLogicCondition
{
	public ChangeWorld()
	{

	}

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(13, 36);

		if (cellObjectWorld instanceof FlameTower && !((FlameTower) cellObjectWorld).isOpen())
		{
			return true;
		}
		return false;
	}

	@Override
	public void doEffect()
	{
		try
		{
			readWorld("Levels/Forest/object2.txt", "Levels/Forest/character2.txt");
			int i = 0;
			int[] x = { 11, 12, 4, 4, 18, 18, 19, 19, 12, 12, 12, 12, 29, 30, 30, 34, 23, 26, 26,
					26 };
			int[] y = { 13, 19, 13, 19, 5, 9, 24, 26, 5, 9, 24, 26, 19, 8, 10, 12, 19, 8, 10, 12 };

			Iterator<AbstractUtilityObject> iteratorObject = StaticConstantGame.gameManager
					.getUtilityObjectCollection().iterator();
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
			}
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
			System.exit(0);
		}
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

	private ObjectScene getObject(final int typeObject, final int x, final int y)
	{
		switch (typeObject)
		{

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

	private void readWorld(String objectWorld, String characterWorld) throws IOException
	{
		final BufferedReader brObject = new BufferedReader(new FileReader(objectWorld));
		final BufferedReader brCharacter = new BufferedReader(new FileReader(characterWorld));
		String bufferObject;
		String bufferCharacter;
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		int x = 0;
		while (((bufferObject = brObject.readLine()) != null)
				&& ((bufferCharacter = brCharacter.readLine()) != null))
		{
			StringTokenizer tokenObject = new StringTokenizer(bufferObject);
			StringTokenizer tokenCharacter = new StringTokenizer(bufferCharacter);
			int number = tokenObject.countTokens();
			for (int y = 0; y < number; y++)
			{
				int code = Integer.parseInt(tokenObject.nextToken());
				AbstractUtilityObject object = (AbstractUtilityObject) getObject(code, x, y);
				if (!(object instanceof Gate))
				{
					concreteWorld.setCellObjectWorld(x, y, object);
					StaticConstantGame.gameManager.getUtilityObjectCollection().add(object);
				}
				if (object instanceof FlameTower)
				{
					((FlameTower) object).setOpen(false);
				}

				code = Integer.parseInt(tokenCharacter.nextToken());
				AbstractCharacter character = (AbstractCharacter) getObject(code, x, y);
				concreteWorld.setCellCharacterWorld(x, y, character);
				StaticConstantGame.gameManager.getCharacterCollection().add(character);

				if (character instanceof Character2D)
				{
					((Character2D) character).setPanel(((Hero2D) StaticConstantGame.gameManager
							.getHero()).getPanel());
				}
			}
			x++;
		}

		Hero2D hero = (Hero2D) StaticConstantGame.gameManager.getHero();
		concreteWorld.setCellCharacterWorld(hero.getX(), hero.getY(), hero);
	}
}
