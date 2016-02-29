package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelOne.EnemyConditionOne;

import java.util.LinkedList;

public class LogicLevelOne extends AbstractWorldLogic
{

	public LogicLevelOne()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni
		conditionList.add(new EnemyConditionOne());

		setConditions(conditionList);
	}
}
