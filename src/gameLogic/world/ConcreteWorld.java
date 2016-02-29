package gameLogic.world;

import gameLogic.Update;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.EmptyCharacter;
import gameLogic.logicState.AbstractWorldLogic;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.EmptyUtility;
import gameLogic.object.FinalPoint;
import gameLogic.object.StartPoint;
import gameLogic.scene.AbstractObjectScene;
import java.util.ArrayList;
import java.util.Collection;
import staticConstant.StaticConstantWorld;

public class ConcreteWorld implements World, Update
{

	public static final int CODE_BASE_WORLD = 0;
	public static final int CODE_OBJECT_WORLD = 1;
	public static final int CODE_CHARACTER_WORLD = 2;

	private int xPrint;
	private int yPrint;
	private int defaultMovement;
	private AbstractWorldLogic levelLogic;
	private final AbstractObjectScene[][] baseWorld;
	private final AbstractUtilityObject[][] objectWorld;
	private final AbstractCharacter[][] characterWorld;

	public ConcreteWorld(int defaultMovement, AbstractObjectScene[][] baseWorld,
			AbstractUtilityObject[][] objectWorld, AbstractCharacter[][] characterWorld,
			AbstractWorldLogic levelLogic)
	{

		this.baseWorld = baseWorld;
		this.objectWorld = objectWorld;
		this.characterWorld = characterWorld;
		this.setXYPrint();
		this.setDefaultMovement(defaultMovement);
		this.levelLogic = levelLogic;
	}

	public ConcreteWorld(int width, int height)
	{
		baseWorld = new AbstractObjectScene[width][height];
		objectWorld = new AbstractUtilityObject[width][height];
		characterWorld = new AbstractCharacter[width][height];
		this.setXYPrint();
	}

	private void setXYPrint()
	{
		for (int x = 0; x < objectWorld.length; x++)
		{
			for (int y = 0; y < objectWorld.length; y++)
			{
				if (objectWorld[x][y] instanceof StartPoint)
				{
					int surplusX = x % StaticConstantWorld.X_DIMENSION;
					int surplusY = y % StaticConstantWorld.Y_DIMENSION;
					setxPrint(x - surplusX);
					setyPrint(y - surplusY);
					return;
				}
			}
		}
		setxPrint(0);
		setyPrint(0);
	}

	private void swapCellCharacterWorld(int x1, int y1, int x2, int y2)
	{
		AbstractCharacter tmp = getCellCharacterWorld(x1, y1);
		setCellCharacterWorld(x1, y1, getCellCharacterWorld(x2, y2));
		setCellCharacterWorld(x2, y2, tmp);
	}

	private void swapCellObjectWorld(int x1, int y1, int x2, int y2)
	{
		AbstractUtilityObject tmp = getCellObjectWorld(x1, y1);
		setCellObjectWorld(x1, y1, getCellObjectWorld(x2, y2));
		setCellObjectWorld(x2, y2, tmp);
	}

	public AbstractObjectScene[][] getBaseWorld()
	{
		return baseWorld;
	}

	@Override
	public AbstractObjectScene getCellBaseWorld(int x, int y)
	{
		return baseWorld[x][y];
	}

	@Override
	public AbstractCharacter getCellCharacterWorld(int x, int y)
	{
		return characterWorld[x][y];
	}

	@Override
	public AbstractUtilityObject getCellObjectWorld(int x, int y)
	{
		return objectWorld[x][y];
	}

	public Collection<AbstractCharacter> getCharacterCollection()
	{
		ArrayList<AbstractCharacter> character = new ArrayList<AbstractCharacter>();

		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				if (!(getCellCharacterWorld(i, j) instanceof EmptyCharacter))
				{
					character.add(getCellCharacterWorld(i, j));
				}
			}
		}
		return character;
	}

	public AbstractCharacter[][] getCharacterWorld()
	{
		return characterWorld;
	}

	public int getDefaultMovement()
	{
		return defaultMovement;
	}

	@Override
	public AbstractUtilityObject getFinalPoint()
	{
		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				if (objectWorld[i][j] instanceof FinalPoint)
				{
					return objectWorld[i][j];
				}
			}
		}
		return null;
	}

	@Override
	public int getHeight()
	{
		return baseWorld.length;
	}

	public AbstractWorldLogic getLevelLogic()
	{
		return levelLogic;
	}

	@Override
	public int getNumberEnemies()
	{
		int number = 0;

		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				if (getCellCharacterWorld(i, j) instanceof AbstractEnemy)
				{
					number++;
				}
			}
		}

		return number;
	}

	@Override
	public int getNumberObject(int typeObject)
	{
		int number = 0;

		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				AbstractUtilityObject object = getCellObjectWorld(i, j);
				if ((object instanceof AbstractUtilityObject) && !(object instanceof EmptyUtility))
				{
					if (object.getId() == typeObject)
					{
						number++;
					}
				}
			}
		}
		return number;
	}

	public Collection<AbstractUtilityObject> getObjectCollection()
	{
		ArrayList<AbstractUtilityObject> objects = new ArrayList<AbstractUtilityObject>();

		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				if (!(getCellObjectWorld(i, j) instanceof EmptyUtility))
				{
					objects.add(getCellObjectWorld(i, j));
				}
			}
		}

		return objects;
	}

	public AbstractUtilityObject[][] getObjectWorld()
	{
		return objectWorld;
	}

	@Override
	public AbstractUtilityObject getStartPoint()
	{
		for (int i = 0; i < getWidth(); i++)
		{
			for (int j = 0; j < getHeight(); j++)
			{
				if (objectWorld[i][j] instanceof StartPoint)
				{
					return objectWorld[i][j];
				}
			}
		}
		return null;
	}

	@Override
	public int getWidth()
	{
		return baseWorld.length;
	}

	public int getxPrint()
	{
		return xPrint;
	}

	public int getyPrint()
	{
		return yPrint;
	}

	public void moveToDown()
	{
		xPrint += StaticConstantWorld.X_DIMENSION;
	}

	public void moveToDown(int units)
	{
		xPrint += units;
	}

	public void moveToLeft()
	{
		yPrint -= StaticConstantWorld.Y_DIMENSION;
	}

	public void moveToLeft(int units)
	{
		yPrint -= units;
	}

	public void moveToRight()
	{
		yPrint += StaticConstantWorld.Y_DIMENSION;
	}

	public void moveToRight(int units)
	{
		yPrint += units;
	}

	public void moveToUp()
	{
		xPrint -= StaticConstantWorld.X_DIMENSION;
	}

	public void moveToUp(int units)
	{
		xPrint -= units;
	}

	@Override
	public void setCellBaseWorld(int xPosition, int yPosition, AbstractObjectScene objectScene)
	{
		baseWorld[xPosition][yPosition] = objectScene;
	}

	@Override
	public void setCellCharacterWorld(int xPosition, int yPosition, AbstractCharacter character)
	{
		characterWorld[xPosition][yPosition] = character;
	}

	@Override
	public void setCellObjectWorld(int xPosition, int yPosition, AbstractUtilityObject utilityObject)
	{
		objectWorld[xPosition][yPosition] = utilityObject;
	}

	public void setDefaultMovement(int defaultMovement)
	{
		this.defaultMovement = defaultMovement;
	}

	@Override
	public void setFinalPoint(int xPosition, int yPosition)
	{
		objectWorld[xPosition][yPosition] = new FinalPoint(xPosition, yPosition);
	}

	public void setLevelLogic(AbstractWorldLogic levelLogic)
	{
		this.levelLogic = levelLogic;
	}

	public void setxPrint(int xPrint)
	{
		this.xPrint = xPrint;
	}

	public void setyPrint(int yPrint)
	{
		this.yPrint = yPrint;
	}

	public void swapCell(int typeWorld, int x1, int y1, int x2, int y2)
	{
		switch (typeWorld)
		{
			case ConcreteWorld.CODE_BASE_WORLD:
				return;
			case ConcreteWorld.CODE_OBJECT_WORLD:
				swapCellObjectWorld(x1, y1, x2, y2);
				break;
			case ConcreteWorld.CODE_CHARACTER_WORLD:
				swapCellCharacterWorld(x1, y1, x2, y2);
				break;
			default:
				return;
		}
	}

	@Override
	public void update()
	{
		levelLogic.update();
	}
}
