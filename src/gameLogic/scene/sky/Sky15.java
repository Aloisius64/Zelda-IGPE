package gameLogic.scene.sky;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class Sky15 extends AbstractObjectScene
{
	public Sky15(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.SKY_15);
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return false;
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
