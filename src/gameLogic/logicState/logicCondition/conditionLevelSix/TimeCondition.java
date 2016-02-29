package gameLogic.logicState.logicCondition.conditionLevelSix;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;

public class TimeCondition extends AbstractLogicCondition
{
	private final int timeLimits;
	private final int initTime;

	public TimeCondition(int timeLimits)
	{
		this.timeLimits = timeLimits;
		this.initTime = (int) System.currentTimeMillis() / 1000;
		StaticConstantGame.timerActive = true;
	}

	@Override
	public boolean isLocked()
	{
		int tmpTime = (int) System.currentTimeMillis() / 1000;
		int diff = tmpTime - initTime;
		StaticConstantGame.time = timeLimits - diff;
		if (diff > timeLimits)
		{
			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		StaticConstantGame.gameManager.getHero().setHearts(0);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

	public int getTimeLimits()
	{
		return timeLimits;
	}

	public long getInitTime()
	{
		return initTime;
	}

}
