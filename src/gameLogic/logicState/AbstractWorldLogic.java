package gameLogic.logicState;

import gameLogic.Update;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractWorldLogic implements Update
{
	private List<AbstractLogicCondition> conditions;

	public AbstractWorldLogic()
	{
	}

	@Override
	public void update()
	{
		Iterator<AbstractLogicCondition> iterator = new ArrayList(getConditions()).iterator();

		while (iterator.hasNext())
		{
			AbstractLogicCondition condition = iterator.next();
			if (!condition.isLocked())
			{
				condition.doEffect();
				if (condition.mustBeRemoved())
				{
					getConditions().remove(condition);
				}
			}
		}
	}

	public void setConditions(List<AbstractLogicCondition> conditions)
	{
		this.conditions = conditions;
	}

	public List<AbstractLogicCondition> getConditions()
	{
		return conditions;
	}

}
