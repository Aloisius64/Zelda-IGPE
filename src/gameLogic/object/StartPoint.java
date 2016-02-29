package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class StartPoint extends AbstractUtilityObject
{
	public StartPoint(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.START_POINT);
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
