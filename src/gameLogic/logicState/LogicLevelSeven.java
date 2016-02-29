package gameLogic.logicState;

import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelSeven.ButtonToWall;
import gameLogic.logicState.logicCondition.conditionLevelSeven.EndCondition;
import gameLogic.logicState.logicCondition.conditionLevelSeven.FlameTowerCreateObject;
import gameLogic.logicState.logicCondition.conditionLevelSeven.GanondorfCreation;
import gameLogic.logicState.logicCondition.conditionLevelSeven.ManageCloud;
import gameLogic.object.AbstractUtilityObject;
import guiInterface.object2D.Button2D;
import guiInterface.object2D.Treasure2D;

import java.util.LinkedList;

public class LogicLevelSeven extends AbstractWorldLogic
{
	public LogicLevelSeven()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni
		conditionList.add(new ButtonToWall());
		int[] x1 = { 2, 2 };
		int[] y1 = { 3, 5 };
		AbstractUtilityObject newObject = new Button2D(2, 4);
		conditionList.add(new FlameTowerCreateObject(x1, y1, newObject));
		int[] x2 = { 34, 34 };
		int[] y2 = { 11, 13 };
		newObject = new Treasure2D(12, 34);
		conditionList.add(new FlameTowerCreateObject(x2, y2, newObject));
		int[] x3 = { 37, 37 };
		int[] y3 = { 11, 13 };
		newObject = new Treasure2D(12, 37);
		conditionList.add(new FlameTowerCreateObject(x3, y3, newObject));
		conditionList.add(new ManageCloud());
		conditionList.add(new GanondorfCreation());
		conditionList.add(new EndCondition());

		setConditions(conditionList);
	}
}
