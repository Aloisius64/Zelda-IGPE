package gameLogic.logicState.logicCondition.conditionLevelSeven;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import gameLogic.object.EmptyUtility;
import staticConstant.StaticConstantGame;

public class ButtonToWall extends AbstractLogicCondition
{
	public ButtonToWall()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(2, 4);
		if (cellObjectWorld instanceof Button)
		{
			if (((Button) cellObjectWorld).isActive())
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
		int xPosition = 6;
		int yPosition = 28;
		EmptyUtility emptyUtility = new EmptyUtility(xPosition, yPosition);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(xPosition, yPosition,
				emptyUtility);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
