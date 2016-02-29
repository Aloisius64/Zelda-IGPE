package gameLogic.logicState;

import gameLogic.Update;

public abstract class AbstractGameLogic implements Update
{

	public AbstractGameLogic()
	{
	}

	public abstract boolean isEndLevel();

	public abstract boolean isGameOver();

	@Override
	public void update()
	{
		// TODO Auto-generated method stub

	}
}
