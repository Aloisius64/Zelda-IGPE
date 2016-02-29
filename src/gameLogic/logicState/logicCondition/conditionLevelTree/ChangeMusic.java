package gameLogic.logicState.logicCondition.conditionLevelTree;

import common.SoundManager;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;

public class ChangeMusic extends AbstractLogicCondition
{
	public ChangeMusic()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		if (StaticConstantGame.gameManager.getHero().getY() > 40)
		{
			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		SoundManager.stop();
		SoundManager.playSound(SoundManager.DUNGEON_2, 0, SoundManager.MUSIC);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
