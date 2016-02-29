package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelSix.GateFromButtons;
import gameLogic.logicState.logicCondition.conditionLevelSix.ResetBlock;
import gameLogic.logicState.logicCondition.conditionLevelSix.TimeCondition;

import java.util.LinkedList;

public class LogicLevelSix extends AbstractWorldLogic
{

	public LogicLevelSix()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni
		conditionList.add(new TimeCondition(300));
		conditionList.add(new ResetBlock());
		conditionList.add(new GateFromButtons());

		setConditions(conditionList);
	}
}
