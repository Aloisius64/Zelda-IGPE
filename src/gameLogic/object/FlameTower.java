package gameLogic.object;

import common.SoundManager;

import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class FlameTower extends AbstractUtilityObject
{
	private boolean open;

	public FlameTower(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
		setId(StaticConstantID.FLAME_TOWER);
		this.open = true;
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

	public boolean isOpen()
	{
		return open;
	}

	public void setOpen(boolean open)
	{
		if (open)
		{
			SoundManager.playSound(SoundManager.FLAME_TOWER_OPEN, 1, SoundManager.EFFECT);
		}
		this.open = open;
	}
}
