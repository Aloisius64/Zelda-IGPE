package gameLogic.logicState.logicCondition;

import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Block;
import guiInterface.character2D.Hero2D;
import guiInterface.character2D.Wizard2D;
import staticConstant.StaticConstantGame;

public class BlockCondition extends AbstractLogicCondition
{
	public BlockCondition()
	{
	}

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject object1 = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(11, 3);
		AbstractUtilityObject object2 = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(11, 5);

		if (object1 instanceof Block && object2 instanceof Block)
		{
			return false;
		}

		return true;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		Wizard2D wizard = new Wizard2D(3, 3);
		StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(3, 3, wizard);
		wizard.setPanel(((Hero2D) StaticConstantGame.gameManager.getHero()).getPanel());
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}
}
