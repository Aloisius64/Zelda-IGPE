package gameLogic.character;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class EmptyCharacter extends AbstractCharacter
{

	public EmptyCharacter(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.EMPTY_CHARACTER);
	}

	@Override
	public int attack(int typeAttack)
	{
		return 0;
	}

	@Override
	public int getScore()
	{
		return 0;
	}

	@Override
	public boolean move()
	{
		return true;
	}

	@Override
	public void update()
	{
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

}
