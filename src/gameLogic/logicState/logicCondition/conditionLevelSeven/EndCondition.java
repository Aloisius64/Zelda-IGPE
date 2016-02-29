package gameLogic.logicState.logicCondition.conditionLevelSeven;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.Ganondorf;
import gameLogic.character.Hero;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import guiInterface.object2D.FinalPoint2D;

import java.util.ArrayList;
import java.util.Iterator;

import staticConstant.StaticConstantGame;

public class EndCondition extends AbstractLogicCondition
{

	public EndCondition()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero.getX() > 20 && hero.getY() < 20)
		{
			for (int i = 20; i < 40; i++)
			{
				for (int j = 0; j <= 20; j++)
				{
					AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
							.getCellCharacterWorld(i, j);
					if (character instanceof Ganondorf)
					{
						return true;
					}
				}
			}

			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		FinalPoint2D finalPoint = new FinalPoint2D(22, 8);
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(22, 8, finalPoint);
		StaticConstantGame.gameManager.getUtilityObjectCollection().add(finalPoint);
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
