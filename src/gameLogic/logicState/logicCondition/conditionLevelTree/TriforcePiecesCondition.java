package gameLogic.logicState.logicCondition.conditionLevelTree;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import guiInterface.character2D.Hero2D;
import guiInterface.object2D.FinalPoint2D;
import staticConstant.StaticConstantGame;

public class TriforcePiecesCondition extends AbstractLogicCondition
{
	private final int xPosition;
	private final int yPosition;

	public TriforcePiecesCondition(int xPosition, int yPosition)
	{
		super();
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	@Override
	public boolean isLocked()
	{
		int triforcePieceCollected = ((Hero2D) StaticConstantGame.gameManager.getHero())
				.getTriforcePieceCollected();
		if (triforcePieceCollected < 3)
			return true;

		return false;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();

		FinalPoint2D finalPoint2D = new FinalPoint2D(getxPosition(), getyPosition());
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getxPosition(),
				getyPosition(), finalPoint2D);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(finalPoint2D);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

	public int getxPosition()
	{
		return xPosition;
	}

	public int getyPosition()
	{
		return yPosition;
	}

}
