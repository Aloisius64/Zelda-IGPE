package gameLogic.logicState.logicCondition.conditionLevelFour;

import staticConstant.StaticConstantGame;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import guiInterface.object2D.TriforcePiece2D;

public class ButtonToTriforcePiece extends AbstractLogicCondition
{
	private final int xPosition;
	private final int yPosition;

	public ButtonToTriforcePiece(int xPosition, int yPosition)
	{
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	@Override
	public boolean isLocked()
	{
		AbstractUtilityObject cellObjectWorld = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(37, 15);
		if (cellObjectWorld instanceof Button)
		{
			if (((Button) cellObjectWorld).isActive())
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();

		TriforcePiece2D triforcePiece2D = new TriforcePiece2D(xPosition, yPosition);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(xPosition, yPosition,
				triforcePiece2D);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(triforcePiece2D);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
