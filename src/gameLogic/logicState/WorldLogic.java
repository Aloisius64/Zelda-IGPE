package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.BlockCondition;
import gameLogic.logicState.logicCondition.EnemyCondition;
import java.util.Iterator;
import java.util.LinkedList;

public class WorldLogic extends AbstractWorldLogic
{

	public WorldLogic()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();
		conditionList.add(new EnemyCondition());
		conditionList.add(new BlockCondition());
		setConditions(conditionList);
	}
}
