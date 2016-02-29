package gameLogic.logicState.logicCondition.conditionLevelFour;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import staticConstant.StaticConstantGame;

public class ResetMagicEnergy extends AbstractLogicCondition
{

	@Override
	public boolean isLocked()
	{
		return false;
	}

	@Override
	public void doEffect()
	{
		StaticConstantGame.gameManager.getHero().setMagicEnergy(0);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
