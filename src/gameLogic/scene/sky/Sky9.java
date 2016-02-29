package gameLogic.scene.sky;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class Sky9 extends AbstractObjectScene
{
	public Sky9(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.SKY_9);
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
