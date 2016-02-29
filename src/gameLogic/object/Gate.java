package gameLogic.object;

import common.SoundManager;

import gameLogic.character.EmptyCharacter;
import gameLogic.character.Hero;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantWorld;

public class Gate extends AbstractUtilityObject
{

	private boolean open;
	private int nextX;
	private int nextY;

	public Gate(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
		setId(StaticConstantID.GATE);
	}

	public Gate(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
		setId(StaticConstantID.GATE);
	}

	public int getNextX()
	{
		return nextX;
	}

	public int getNextY()
	{
		return nextY;
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setNextX(int nextX)
	{
		this.nextX = nextX;
	}

	public void setNextY(int nextY)
	{
		this.nextY = nextY;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}

	@Override
	public void update()
	{
		if (open)
		{
			Hero hero = StaticConstantGame.gameManager.getHero();
			if ((getX() == hero.getX()) && (getY() == hero.getY()))
			{
				hero.setX(nextX);
				hero.setY(nextY);
				StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(),
						getY(), new EmptyCharacter(getX(), getY()));
				StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(
						hero.getX(), hero.getY(), hero);

				StaticConstantGame.gameManager.getConcreteWorld().setxPrint(
						nextX - (nextX % StaticConstantWorld.X_DIMENSION));
				StaticConstantGame.gameManager.getConcreteWorld().setyPrint(
						nextY - (nextY % StaticConstantWorld.Y_DIMENSION));

				SoundManager.playSound(SoundManager.ENTER_INTO_GATE, 1, SoundManager.EFFECT);

			}

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
}
