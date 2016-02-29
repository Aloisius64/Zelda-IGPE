package gameLogic.logicState;

import gameLogic.character.Hero;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import gameLogic.logicState.logicCondition.conditionLevelTwo.ButtonConditionOne;
import gameLogic.logicState.logicCondition.conditionLevelTwo.ButtonConditionTwo;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.EmptyUtility;
import gameLogic.scene.AbstractObjectScene;
import guiInterface.scene2D.desert.QuickSand2D;
import java.util.LinkedList;
import staticConstant.StaticConstantGame;

public class LogicLevelTwo extends AbstractWorldLogic
{

	public LogicLevelTwo()
	{
		super();
		LinkedList<AbstractLogicCondition> conditionList = new LinkedList<AbstractLogicCondition>();

		// Scrivere le condizioni
		conditionList.add(new ButtonConditionOne());
		conditionList.add(new ButtonConditionTwo());

		setConditions(conditionList);
	}

	@Override
	public void update()
	{
		super.update();
		Hero hero = StaticConstantGame.gameManager.getHero();
		AbstractUtilityObject object = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(hero.getX(), hero.getY());

		if (object instanceof EmptyUtility)
		{
			AbstractObjectScene scene = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellBaseWorld(hero.getX(), hero.getY());

			if (scene instanceof QuickSand2D)
			{
				((QuickSand2D) scene).update();
			}
		}
	}
}
