package gameLogic.logicState.logicCondition.conditionLevelSix;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.Button;
import gameLogic.object.EmptyUtility;
import gameLogic.world.ConcreteWorld;
import guiInterface.object2D.Block2D;

public class ResetBlock extends AbstractLogicCondition
{
	public ResetBlock()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		Button button = (Button) concreteWorld.getCellObjectWorld(10, 33);
		if (button.isActive())
		{
			button.setActive(false);
			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		Button button1 = (Button) concreteWorld.getCellObjectWorld(10, 33);
		Button button2 = (Button) concreteWorld.getCellObjectWorld(15, 33);

		for (int i = 10; i < 16; i++)
		{
			for (int j = 30; j < 37; j++)
			{
				EmptyUtility emptyUtility = new EmptyUtility(i, j);
				StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(i, j,
						emptyUtility);
				StaticConstantGame.gameManager.getUtilityObjectCollection().add(emptyUtility);
			}
		}

		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(10, 33, button1);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(button1);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(15, 33, button2);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(button2);

		int[] x = { 12, 12, 12, 15, 15 };
		int[] y = { 32, 33, 34, 32, 34 };

		for (int i = 0; i < 5; i++)
		{
			Block2D block = new Block2D(x[i], y[i]);
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(x[i], y[i], block);
			StaticConstantGame.gameManager.getUtilityObjectCollection().add(block);
		}
	}

	@Override
	public boolean mustBeRemoved()
	{
		return false;
	}

}
