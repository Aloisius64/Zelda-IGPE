package gameLogic.scene.desert;

import staticConstant.StaticConstantID;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;

public class QuickSand extends AbstractObjectScene
{
	public static int resetX = 0;
	public static int resetY = 0;

	public QuickSand(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.QUICKSAND);
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

}
