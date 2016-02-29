package gameLogic.scene.forest;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.XLand;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import java.awt.Graphics;
import common.ImageProvider;
import staticConstant.StaticConstantID;

public class Forest6 extends AbstractObjectScene
{
	public Forest6(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.FOREST_6);
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
