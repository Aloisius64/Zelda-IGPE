package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelFour.ButtonToTriforcePiece;
import gameLogic.logicState.logicCondition.conditionLevelFour.ChangeWorld;
import gameLogic.logicState.logicCondition.conditionLevelFour.ResetMagicEnergy;
import gameLogic.logicState.logicCondition.conditionLevelFour.TriforcePiecesConditionForest;
import gameLogic.logicState.logicCondition.conditionLevelSeven.FlameTowerCreateObject;
import guiInterface.object2D.TriforcePiece2D;

import java.util.LinkedList;

public class LogicLevelFour extends AbstractWorldLogic
{
	public LogicLevelFour()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni

		conditionList.add(new ResetMagicEnergy());
		conditionList.add(new ChangeWorld());

		int[] x1 = { 2, 2 };
		int[] y1 = { 4, 6 };
		TriforcePiece2D triforcePiece2D = new TriforcePiece2D(2, 5);
		conditionList.add(new FlameTowerCreateObject(x1, y1, triforcePiece2D));
		conditionList.add(new ButtonToTriforcePiece(37, 35));
		conditionList.add(new TriforcePiecesConditionForest(23, 21));

		setConditions(conditionList);
	}
}
