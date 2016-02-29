package gameLogic.logicState.logicCondition.conditionLevelTwo;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import gameLogic.object.EmptyUtility;

public class ButtonConditionOne extends AbstractLogicCondition
{
	public ButtonConditionOne()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject objectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(4, 26);
		if (objectWorld instanceof Button)
		{
			if (((Button) objectWorld).isActive())
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
		EmptyUtility emptyUtility = new EmptyUtility(10, 13);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(10, 13, emptyUtility);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(emptyUtility);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
