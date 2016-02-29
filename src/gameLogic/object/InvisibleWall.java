package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class InvisibleWall extends AbstractUtilityObject
{
	private boolean active = false;

	public InvisibleWall(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.INVISIBLE_WALL);
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

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}
}
