package gameLogic.scene.sky;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class Sky4 extends AbstractObjectScene
{
	public Sky4(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.SKY_4);
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
