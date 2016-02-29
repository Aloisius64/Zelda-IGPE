package gameLogic.logicState.logicCondition.conditionLevelFour;

import common.SoundManager;

import gameLogic.logicState.logicCondition.conditionLevelTree.TriforcePiecesCondition;
import guiInterface.character2D.Hero2D;
import guiInterface.object2D.EmptyUtility2D;
import staticConstant.StaticConstantGame;

public class TriforcePiecesConditionForest extends TriforcePiecesCondition
{
	public TriforcePiecesConditionForest(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
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
		SoundManager.playSound(SoundManager.SECRET_SOLVE, 1, SoundManager.EFFECT);
		EmptyUtility2D emptyUtility2D = new EmptyUtility2D(getxPosition(), getxPosition());
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getxPosition(),
				getxPosition(), emptyUtility2D);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(emptyUtility2D);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}
}
