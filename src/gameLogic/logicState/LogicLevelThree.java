package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelTree.ButtonToGateCondition;
import gameLogic.logicState.logicCondition.conditionLevelTree.ChangeMusic;
import gameLogic.logicState.logicCondition.conditionLevelTree.FlameTowerCondition;
import gameLogic.logicState.logicCondition.conditionLevelTree.RemoveDarkness;
import gameLogic.logicState.logicCondition.conditionLevelTree.TreasureCondition;
import gameLogic.logicState.logicCondition.conditionLevelTree.TriforcePiecesCondition;

import java.util.LinkedList;

public class LogicLevelThree extends AbstractWorldLogic
{

	public LogicLevelThree()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni
		conditionList.add(new FlameTowerCondition(0, 0, 20, 1));
		conditionList.add(new FlameTowerCondition(0, 20, 20, 2));
		conditionList.add(new FlameTowerCondition(20, 0, 20, 2));
		conditionList.add(new FlameTowerCondition(20, 20, 20, 1));
		conditionList.add(new TreasureCondition());
		conditionList.add(new ButtonToGateCondition());
		conditionList.add(new RemoveDarkness());
		conditionList.add(new TriforcePiecesCondition(3, 46));
		conditionList.add(new ChangeMusic());

		setConditions(conditionList);
	}
}
