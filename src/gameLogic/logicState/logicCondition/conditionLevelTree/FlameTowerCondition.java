package gameLogic.logicState.logicCondition.conditionLevelTree;

import staticConstant.StaticConstantGame;
import gameLogic.character.Hero;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.FlameTower;
import gameLogic.world.ConcreteWorld;

public class FlameTowerCondition extends AbstractLogicCondition
{
	private final int initX;
	private final int initY;
	private final int dimension;
	private final int minimumTower;

	public FlameTowerCondition(int initX, int initY, int dimension, int minimumTower)
	{
		super();
		this.initX = initX;
		this.initY = initY;
		this.dimension = dimension;
		this.minimumTower = minimumTower;
	}

	@Override
	public boolean isLocked()
	{
		int numberFlame = 0;
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		for (int i = initX; i < (initX + dimension); i++)
		{
			for (int j = initY; j < (initY + dimension); j++)
			{
				AbstractUtilityObject cellObjectWorld = concreteWorld.getCellObjectWorld(i, j);
				if (cellObjectWorld instanceof FlameTower)
				{
					if (((FlameTower) cellObjectWorld).isOpen())
					{
						numberFlame++;
					}
				}
			}
		}

		if (numberFlame >= minimumTower)
		{
			return false;
		}

		Hero hero = StaticConstantGame.gameManager.getHero();
		int x = hero.getX();
		int y = hero.getY();
		if (x >= initX && x < (initX + dimension) && y >= initY && y < (initY + dimension))
		{
			StaticConstantGame.typeDarkness = 0;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		StaticConstantGame.typeDarkness = 1;
	}

	@Override
	public boolean mustBeRemoved()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero.getY() >= 40)
		{
			return true;
		}
		return false;
	}

	public int getInitX()
	{
		return initX;
	}

	public int getInitY()
	{
		return initY;
	}

	public int getDimension()
	{
		return dimension;
	}

	public int getMinimumTower()
	{
		return minimumTower;
	}
}
