package gameLogic.logicState.logicCondition.conditionLevelTree;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.FlameTower;
import guiInterface.object2D.Treasure2D;

public class TreasureCondition extends AbstractLogicCondition
{
	public TreasureCondition()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(15, 12);
		if (cellObjectWorld instanceof FlameTower)
		{
			if (((FlameTower) cellObjectWorld).isOpen())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		int xPosition = 12;
		int yPosition = 15;
		Treasure2D treasure1 = new Treasure2D(xPosition, yPosition);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(xPosition, yPosition,
				treasure1);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(treasure1);
		Treasure2D treasure2 = new Treasure2D(xPosition, yPosition - 1);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(xPosition,
				yPosition - 1, treasure2);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(treasure2);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
