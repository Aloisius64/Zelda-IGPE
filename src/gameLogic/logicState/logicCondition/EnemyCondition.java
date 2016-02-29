package gameLogic.logicState.logicCondition;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.Hero;
import java.util.Iterator;
import staticConstant.StaticConstantGame;

public class EnemyCondition extends AbstractLogicCondition
{

	public EnemyCondition()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		return StaticConstantGame.gameManager.getConcreteWorld().getNumberEnemies() > 0;
	}

	@Override
	public void doEffect()
	{
		super.doEffect();
		Iterator<AbstractCharacter> iterator = StaticConstantGame.gameManager.getConcreteWorld()
				.getCharacterCollection().iterator();
		while (iterator.hasNext())
		{
			AbstractCharacter character = iterator.next();
			if (character instanceof Hero)
			{
				((Hero) character).modifyHearts(+3);
				((Hero) character).modifyScores(500);
				StaticConstantGame.currentMessage = "All enemy   destoyed";
			}
		}
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}
}
