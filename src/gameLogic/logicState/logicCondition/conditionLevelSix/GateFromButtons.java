package gameLogic.logicState.logicCondition.conditionLevelSix;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.Button;
import gameLogic.world.ConcreteWorld;
import guiInterface.object2D.Gate2D;
import staticConstant.StaticConstantGame;

public class GateFromButtons extends AbstractLogicCondition
{
	public GateFromButtons()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		Button button1 = (Button) concreteWorld.getCellObjectWorld(3, 36);
		Button button2 = (Button) concreteWorld.getCellObjectWorld(15, 33);

		if (button1.isActive() && button2.isActive())
		{
			return false;
		}

		return true;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		Gate2D gate = new Gate2D(5, 32);
		gate.setOpen(true);
		gate.setNullGate(false);
		gate.setNextX(34);
		gate.setNextY(4);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(5, 32, gate);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(gate);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}
}
