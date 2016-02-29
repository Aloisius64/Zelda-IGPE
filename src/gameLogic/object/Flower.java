package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class Flower extends AbstractUtilityObject
{

	public Flower(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.FLOWER);
	}

	@Override
	public void update()
	{
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
