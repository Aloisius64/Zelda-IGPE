package gameLogic.logicState.logicCondition.conditionLevelTree;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;

public class RemoveDarkness extends AbstractLogicCondition
{
	public RemoveDarkness()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		if (StaticConstantGame.gameManager.getHero().getY() < 40)
			return true;
		return false;
	}

	@Override
	public void doEffect()
	{
		StaticConstantGame.darkness = false;
		StaticConstantGame.fog = true;
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
