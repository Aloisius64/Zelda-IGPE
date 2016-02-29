package gameLogic.object;

import staticConstant.StaticConstantID;
import gameLogic.world.World;

public class FallPoint extends AbstractUtilityObject
{
	private boolean open = false;

	public FallPoint(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.FALL_POINT);
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{

	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}
}
