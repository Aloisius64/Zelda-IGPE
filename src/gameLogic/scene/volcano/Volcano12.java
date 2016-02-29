package gameLogic.scene.volcano;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import staticConstant.StaticConstantID;

public class Volcano12 extends AbstractObjectScene
{
	public Volcano12(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.VOLCANO_12);
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
