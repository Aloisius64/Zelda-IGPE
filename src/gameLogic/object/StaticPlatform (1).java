package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class StaticPlatform extends AbstractUtilityObject
{

	public StaticPlatform(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.STATIC_PLATFORM);
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
