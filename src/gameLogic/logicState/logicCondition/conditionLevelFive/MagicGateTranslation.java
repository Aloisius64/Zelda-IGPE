package gameLogic.logicState.logicCondition.conditionLevelFive;

import java.util.Collection;
import java.util.Iterator;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import guiInterface.object2D.MagicGate2D;

public class MagicGateTranslation extends AbstractLogicCondition
{
	private final int[][] x = {
			{ 8, 5, 5, 8, 8, 28, 8, 5, 8, 8, 11, 11, 32, 18, 20, 26, 26, 22, 30, 19 },
			{ 8, 5, 8, 11, 8, 8, 5, 5, 28, 8, 8, 11, 22, 18, 20, 19, 26, 32, 30, 26 } };
	private final int[][] y = {
			{ 8, 32, 32, 19, 18, 8, 8, 32, 18, 21, 8, 11, 30, 31, 27, 7, 13, 34, 9, 28 },
			{ 8, 32, 8, 8, 18, 18, 32, 32, 8, 21, 19, 11, 34, 31, 27, 28, 13, 30, 9, 7 } };
	private final int[][] b = { { 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1 },
			{ 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0 } };
	private final int timeToChange;
	private final int initTime;
	private int indexCurrentSetting = 0;

	public MagicGateTranslation(int timeToChange)
	{
		this.initTime = (int) System.currentTimeMillis() / 1000;
		this.timeToChange = timeToChange;
	}

	@Override
	public boolean isLocked()
	{
		int tmpTime = (int) System.currentTimeMillis() / 1000;
		int diff = tmpTime - initTime;
		if (diff > timeToChange)
		{
			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		indexCurrentSetting = (indexCurrentSetting + 1) % 2;
		setNextPosition();
	}

	@Override
	public boolean mustBeRemoved()
	{
		return false;
	}

	private void setNextPosition()
	{
		Collection<AbstractUtilityObject> objectCollection = StaticConstantGame.gameManager
				.getConcreteWorld().getObjectCollection();
		Iterator<AbstractUtilityObject> iterator = objectCollection.iterator();

		int i = 0;

		while (iterator.hasNext())
		{
			AbstractUtilityObject currentObject = iterator.next();
			if (currentObject instanceof MagicGate2D)
			{
				MagicGate2D magicGate2D = ((MagicGate2D) currentObject);
				magicGate2D.setNextX(x[indexCurrentSetting][i]);
				magicGate2D.setNextY(y[indexCurrentSetting][i]);
				if (b[indexCurrentSetting][i] == 1)
				{
					magicGate2D.setCorrect(true);
				}
				else
				{
					magicGate2D.setCorrect(false);
				}
				i++;
			}
		}
	}

	public int getTimeToChange()
	{
		return timeToChange;
	}

	public int getInitTime()
	{
		return initTime;
	}

}
