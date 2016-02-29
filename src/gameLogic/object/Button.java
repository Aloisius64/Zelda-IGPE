package gameLogic.object;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class Button extends AbstractUtilityObject
{

	private boolean active;
	private long activationTime = 0;

	public Button(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
		setId(StaticConstantID.BUTTON);
		active = false;
	}

	public Button(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
		setId(StaticConstantID.BUTTON);
		active = false;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
		if (active)
		{
			setActivationTime(System.currentTimeMillis());
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

	public long getActivationTime()
	{
		return activationTime;
	}

	public void setActivationTime(long activationTime)
	{
		this.activationTime = activationTime;
	}
}
