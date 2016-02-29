package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class EmptyUtility extends AbstractUtilityObject
{

	public EmptyUtility(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.EMPTY_UTILITY);
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
