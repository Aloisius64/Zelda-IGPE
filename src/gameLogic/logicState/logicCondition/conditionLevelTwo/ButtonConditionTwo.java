package gameLogic.logicState.logicCondition.conditionLevelTwo;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.Button;
import gameLogic.object.EmptyUtility;
import gameLogic.world.ConcreteWorld;

public class ButtonConditionTwo extends AbstractLogicCondition
{
	public ButtonConditionTwo()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();

		Button button1 = (Button) concreteWorld.getCellObjectWorld(2, 2);
		Button button2 = (Button) concreteWorld.getCellObjectWorld(2, 4);
		Button button3 = (Button) concreteWorld.getCellObjectWorld(2, 21);
		Button button4 = (Button) concreteWorld.getCellObjectWorld(2, 23);

		if (button1.isActive() && button2.isActive() && button3.isActive() && button4.isActive())
		{
			if (button2.getActivationTime() < button3.getActivationTime())
			{
				if (button3.getActivationTime() < button1.getActivationTime())
				{
					if (button1.getActivationTime() < button4.getActivationTime())
					{
						return false;
					}
				}
			}
		}

		return true;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		EmptyUtility emptyUtility = new EmptyUtility(3, 13);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(3, 13, emptyUtility);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(emptyUtility);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
