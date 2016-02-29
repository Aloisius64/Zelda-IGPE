package inteligenceAI;

import gameLogic.MultiplayerGameManager;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.Hero;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

import common.Direction;

public class NetworkAI extends AbstractAI
{
	public NetworkAI()
	{
		super();
	}

	public NetworkAI(AbstractEnemy enemy)
	{
		super(enemy);
	}

	protected boolean areInTheSameDirection(int xEnemy, int yEnemy, Hero hero)
	{
		int xHero = hero.getX();
		int yHero = hero.getY();

		return (xEnemy == xHero) || (yEnemy == yHero);
	}

	@Override
	protected Direction getDirection()
	{
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		Hero hero = heroMinDistance(getEnemy().getX(), getEnemy().getY());

		int xHero = hero.getX();
		int yHero = hero.getY();

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

	private int calculateDistance(int xEnemy, int yEnemy)
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		int xHero = hero.getX();
		int yHero = hero.getY();
		int distancePlayer1 = (int) Math.sqrt(Math.pow((xHero - xEnemy), 2)
				+ Math.pow((yHero - yEnemy), 2));

		hero = ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();
		xHero = hero.getX();
		yHero = hero.getY();
		int distancePlayer2 = (int) Math.sqrt(Math.pow((xHero - xEnemy), 2)
				+ Math.pow((yHero - yEnemy), 2));

		if (distancePlayer1 < distancePlayer2 && distancePlayer1 != 0 && distancePlayer2 != 0)
			return distancePlayer1;
		else if (distancePlayer1 > distancePlayer2 && distancePlayer1 != 0 && distancePlayer2 != 0)
			return distancePlayer2;
		else
			return distancePlayer1;
	}

	@Override
	protected int getDistance()
	{
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		return calculateDistance(xEnemy, yEnemy);
	}

	private Hero heroMinDistance(int xEnemy, int yEnemy)
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		int xHero = hero.getX();
		int yHero = hero.getY();

		int player1 = (int) Math
				.sqrt(Math.pow((xHero - xEnemy), 2) + Math.pow((yHero - yEnemy), 2));

		hero = ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();
		xHero = hero.getX();
		yHero = hero.getY();

		int player2 = (int) Math
				.sqrt(Math.pow((xHero - xEnemy), 2) + Math.pow((yHero - yEnemy), 2));

		if (player1 < player2)
			return StaticConstantGame.gameManager.getHero();
		else
			return ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();
	}

	private int calculateDistance(int xEnemy, int yEnemy, Hero hero)
	{
		int xHero = hero.getX();
		int yHero = hero.getY();

		return (int) Math.sqrt(Math.pow((xHero - xEnemy), 2) + Math.pow((yHero - yEnemy), 2));
	}

	private void setNextState()
	{
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();
		Hero hero = heroMinDistance(xEnemy, yEnemy);

		int distance = calculateDistance(xEnemy, yEnemy, hero);
		if (distance <= 1)
		{
			if (areInTheSameDirection(xEnemy, yEnemy, hero))
			{
				getEnemy().setState(2);
			}
			else
			{
				getEnemy().setState(1);
			}
		}
		else if (areInTheSameDirection(xEnemy, yEnemy, hero))
		{
			getEnemy().setState(3);
		}
		else if (distance <= 4)
		{
			getEnemy().setState(1);
		}
		else
			getEnemy().setState(0);
	}

	@Override
	protected Direction getShortDirection()
	{
		int xEnemy = getEnemy().getX();
		int yEnemy = getEnemy().getY();

		int currentDistance = 0;
		int minimumDistance = 15;

		Direction resultDirection = new Direction();

		Hero hero = heroMinDistance(xEnemy, yEnemy);

		for (int i = 0; i < 8; i++)
		{
			if (i == Direction.UP)
			{
				int tmpX = xEnemy - 1;
				if (isAccessible(tmpX, yEnemy))
				{
					currentDistance = calculateDistance(tmpX, yEnemy, hero);
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
					currentDistance = calculateDistance(tmpX, yEnemy, hero);
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
					currentDistance = calculateDistance(xEnemy, tmpY, hero);
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
					currentDistance = calculateDistance(xEnemy, tmpY, hero);
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

	@Override
	protected boolean isAccessible(int xEnemy, int yEnemy)
	{
		return StaticConstantGame.gameManager.getConcreteWorld().getCellBaseWorld(xEnemy, yEnemy)
				.getId() >= StaticConstantID.LIMITS_ACCESSIBLE;
	}

	@Override
	public void update()
	{
		int state = getEnemy().getState();
		switch (state)
		{
			case 0:
				setNextState();
				break;
			case 1:
				getEnemy().setCurrentDirection(getShortDirection());
				setNextState();
				break;
			case 2:
				setNextState();
				break;
			case 3:
				setNextState();
				break;
			default:
				getEnemy().setState(0);
				break;
		}
	}
}
