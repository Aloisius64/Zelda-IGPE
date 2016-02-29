package gameLogic.logicState.logicCondition.conditionLevelSeven;

import staticConstant.StaticConstantGame;
import gameLogic.character.Hero;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;

public class ManageCloud extends AbstractLogicCondition
{
	public ManageCloud()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero.getX() >= 20 && hero.getY() < 20)
		{
			return false;
		}
		else
		{
			StaticConstantGame.cloud = true;
		}

		return true;
	}

	@Override
	public void doEffect()
	{
		StaticConstantGame.cloud = false;
	}

	@Override
	public boolean mustBeRemoved()
	{
		return false;
	}

}
