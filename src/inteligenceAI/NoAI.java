package inteligenceAI;

import gameLogic.character.AbstractEnemy;

import common.Direction;

public class NoAI extends AbstractAI
{
	public NoAI()
	{
		super();
	}

	public NoAI(AbstractEnemy enemy)
	{
		super(enemy);
	}

	@Override
	protected boolean areInTheSameDirection()
	{
		return false;
	}

	@Override
	protected Direction getDirection()
	{
		return null;
	}

	@Override
	protected int getDistance()
	{
		return 0;
	}

	@Override
	protected Direction getShortDirection()
	{
		return null;
	}

	@Override
	protected boolean isAccessible(int xEnemy, int yEnemy)
	{
		return false;
	}

	@Override
	protected boolean isInSameBox()
	{
		return false;

	}

	@Override
	public void update()
	{
	}
}
