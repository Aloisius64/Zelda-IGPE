package inteligenceAI;

import gameLogic.Update;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.Hero;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantWorld;

import common.Direction;

public abstract class AbstractAI implements Update
{

	private AbstractCharacter enemy;

	public AbstractAI()
	{

	}

	public AbstractAI(AbstractCharacter enemy)
	{
		this.setEnemy(enemy);
	}

	private int calculateDistance(int xEnemy, int yEnemy)
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		int xHero = hero.getX();
		int yHero = hero.getY();

		return (int) Math.sqrt(Math.pow((xHero - xEnemy), 2) + Math.pow((yHero - yEnemy), 2));
	}

	protected void correctDirection()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero.getY() < getEnemy().getY())
		{
			getEnemy().setCurrentDirection(Direction.LEFT);
		}
		if (hero.getY() > getEnemy().getY())
		{
			getEnemy().setCurrentDirection(Direction.RIGHT);
		}
		if (hero.getX() < getEnemy().getX())
		{
			getEnemy().setCurrentDirection(Direction.UP);
		}
		if (hero.getX() > getEnemy().getX())
		{
			getEnemy().setCurrentDirection(Direction.DOWN);
		}
	}

	protected boolean areInTheSameDirection()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		int xHero = hero.getX();
		int yHero = hero.getY();
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		return (xEnemy == xHero) || (yEnemy == yHero);
	}

	protected Direction getDirection()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		int xHero = hero.getX();
		int yHero = hero.getY();
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		if (xEnemy == xHero)
		{
			if (yEnemy < yHero)
			{
				return new Direction(Direction.RIGHT);
			}
			if (yEnemy > yHero)
			{
				return new Direction(Direction.LEFT);
			}
		}
		if (yEnemy == yHero)
		{
			if (xEnemy < xHero)
			{
				return new Direction(Direction.DOWN);
			}
			if (xEnemy > xHero)
			{
				return new Direction(Direction.UP);
			}
		}

		return getEnemy().getCurrentDirection();
	}

	protected int getDistance()
	{
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		return calculateDistance(xEnemy, yEnemy);
	}

	protected Direction getShortDirection()
	{
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		int currentDistance = 0;
		int minimumDistance = 11;

		Direction resultDirection = new Direction();

		for (int i = 0; i < 8; i++)
		{
			if (i == Direction.UP)
			{
				int tmpX = xEnemy - 1;
				if (isAccessible(tmpX, yEnemy))
				{
					currentDistance = calculateDistance(tmpX, yEnemy);
					if (currentDistance < minimumDistance)
					{
						minimumDistance = currentDistance;
						resultDirection.setDirection(Direction.UP);
					}
				}
			}
			else if (i == Direction.DOWN)
			{
				int tmpX = xEnemy + 1;
				if (isAccessible(tmpX, yEnemy))
				{
					currentDistance = calculateDistance(tmpX, yEnemy);
					if (currentDistance < minimumDistance)
					{
						minimumDistance = currentDistance;
						resultDirection.setDirection(Direction.DOWN);
					}
				}
			}
			else if (i == Direction.LEFT)
			{
				int tmpY = yEnemy - 1;
				if (isAccessible(xEnemy, tmpY))
				{
					currentDistance = calculateDistance(xEnemy, tmpY);
					if (currentDistance < minimumDistance)
					{
						minimumDistance = currentDistance;
						resultDirection.setDirection(Direction.LEFT);
					}
				}
			}
			else if (i == Direction.RIGHT)
			{
				int tmpY = yEnemy + 1;
				if (isAccessible(xEnemy, tmpY))
				{
					currentDistance = calculateDistance(xEnemy, tmpY);
					if (currentDistance < minimumDistance)
					{
						minimumDistance = currentDistance;
						resultDirection.setDirection(Direction.RIGHT);
					}
				}
			}
		}
		return resultDirection;
	}

	public Direction getNewDirection()
	{
		return new Direction();
	}

	protected boolean isAccessible(int xEnemy, int yEnemy)
	{
		return StaticConstantGame.gameManager.getConcreteWorld().getCellBaseWorld(xEnemy, yEnemy)
				.getId() >= StaticConstantID.LIMITS_ACCESSIBLE;
	}

	protected boolean isInSameBox()
	{
		int x1 = getEnemy().getX();
		int y1 = getEnemy().getY();
		int x2 = StaticConstantGame.gameManager.getConcreteWorld().getxPrint();
		int y2 = StaticConstantGame.gameManager.getConcreteWorld().getyPrint();

		if ((x1 > x2 && x1 <= x2 + StaticConstantWorld.X_DIMENSION)
				&& (y1 > y2 && y1 <= y2 + StaticConstantWorld.Y_DIMENSION))
		{
			return true;

		}
		return false;
	}

	@Override
	public void update()
	{
	}

	public AbstractCharacter getEnemy()
	{
		return enemy;
	}

	public void setEnemy(AbstractCharacter enemy)
	{
		this.enemy = enemy;
	}
}
