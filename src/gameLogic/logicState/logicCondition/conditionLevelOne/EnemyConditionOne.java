package gameLogic.logicState.logicCondition.conditionLevelOne;

import gameLogic.logicState.logicCondition.EnemyCondition;
import guiInterface.object2D.FinalPoint2D;
import staticConstant.StaticConstantGame;

public class EnemyConditionOne extends EnemyCondition
{
	public EnemyConditionOne()
	{
		super();
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		int xPosition = 5;
		int yPosition = 14;
		FinalPoint2D finalPoint = new FinalPoint2D(xPosition, yPosition);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(xPosition, yPosition,
				finalPoint);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(finalPoint);
	}
}
