package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelFive.MagicGateTranslation;

import java.util.LinkedList;

public class LogicLevelFive extends AbstractWorldLogic
{
	public LogicLevelFive()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni
		conditionList.add(new MagicGateTranslation(30));

		setConditions(conditionList);
	}
}
