package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class FinalPoint extends AbstractUtilityObject
{

	public FinalPoint(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.FINAL_POINT);
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
