package gameLogic.logicState.logicCondition.conditionLevelSeven;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.FlameTower;

public class FlameTowerCreateObject extends AbstractLogicCondition
{
	private final int[] x;
	private final int[] y;
	private final AbstractUtilityObject newObject;

	public FlameTowerCreateObject(int[] x, int y[], AbstractUtilityObject newObject)
	{
		super();
		this.x = x;
		this.y = y;
		this.newObject = newObject;
	}

	@Override
	public boolean isLocked()
	{
		int openFlame = 0;
		for (int i = 0; i < x.length; i++)
		{
			AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager
					.getConcreteWorld().getCellObjectWorld(x[i], y[i]);
			if (cellObjectWorld instanceof FlameTower)
			{
				if (((FlameTower) cellObjectWorld).isOpen())
				{
					openFlame++;
				}
			}
		}
		if (openFlame >= 2)
		{
			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		int xPosition = x[0];
		int yPosition = (y[0] + y[1]) / 2;

		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(xPosition, yPosition,
				newObject);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(newObject);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
