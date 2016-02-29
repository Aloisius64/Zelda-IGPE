package gameLogic.object;

import common.Direction;
import gameLogic.character.Hero;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Platform extends AbstractUtilityObject
{

	private Direction direction; // necessaria per prossima mossa
	private int currentMovement;
	private int movement;

	public Platform(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.PLATFORM);
		this.direction = new Direction(Direction.CENTER);
		setCurrentMovement(1);
		setMovement(3);
	}

	public Platform(final int xPosition, final int yPosition, final Direction direction)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.PLATFORM);
		this.direction = direction;
		setCurrentMovement(1);
		setMovement(3);
	}

	public Platform(final int xPosition, final int yPosition, final Direction direction,
			final int movement)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.PLATFORM);
		this.direction = direction;
		setCurrentMovement(1);
		setMovement(movement);
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	@Override
	public void update()
	{
		if (direction.getTypeDirection() != Direction.CENTER)
		{
			boolean move = false;
			if (getCurrentMovement() % getMovement() == 0)
			{
				this.direction = this.direction.getReverse();
			}

			Hero hero = StaticConstantGame.gameManager.getHero();
			if ((hero.getX() == getX()) && (hero.getY() == getY()))
			{
				move = true;
			}

			int x = getX() + direction.getXSelected();
			int y = getY() + direction.getYSelected();
			StaticConstantGame.gameManager.getConcreteWorld().swapCell(
					ConcreteWorld.CODE_OBJECT_WORLD, getX(), getY(), x, y);
			setX(x);
			setY(y);

			if (move)
			{
				if (hero.getState() < 1)
				{
					hero.setCurrentDirection(direction.getTypeDirection());
					hero.move();
				}
				move = false;
			}
			setMovement(getMovement() + 1);
		}
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}

	public void setCurrentMovement(int movement)
	{
		this.currentMovement = movement;
	}

	public int getCurrentMovement()
	{
		return currentMovement;
	}

	public void increaseMovement()
	{
		++currentMovement;
	}

	public void setMovement(int movement)
	{
		this.movement = movement;
	}

	public int getMovement()
	{
		return movement;
	}
}
