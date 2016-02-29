package gameLogic.logicState.logicCondition;

import common.SoundManager;

public abstract class AbstractLogicCondition
{

	public abstract boolean isLocked();

	public void doEffect()
	{
		SoundManager.playSound(SoundManager.SECRET_SOLVE, 1, SoundManager.EFFECT);
	}

	public abstract boolean mustBeRemoved();
}
