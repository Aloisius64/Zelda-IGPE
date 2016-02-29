package gameLogic.logicState.logicCondition.conditionLevelTree;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import gameLogic.object.Gate;
import staticConstant.StaticConstantGame;

public class ButtonToGateCondition extends AbstractLogicCondition
{

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(2, 21);
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
		AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(23, 3);
		if (cellObjectWorld instanceof Gate)
		{
			((Gate) cellObjectWorld).setOpen(true);
		}
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
