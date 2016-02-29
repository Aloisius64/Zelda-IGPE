package gameLogic.scene.forest;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.XLand;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class MountainLeft extends AbstractObjectScene
{
	@Override
	public boolean canAdd(int x, int y)
	{
		if (y >= 1)
			return true;
		return false;
	}

	public MountainLeft(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.MOUNTAIN_LEFT);
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
		if (world instanceof ConcreteWorld)
		{
			ConcreteWorld currentWorld = (ConcreteWorld) world;

			XLand xLand = new XLand(0, 0);

			currentWorld.setCellBaseWorld(x, y - 1, xLand);
		}
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
		if (world instanceof ConcreteWorld)
		{
			ConcreteWorld currentWorld = (ConcreteWorld) world;

			Empty empty = new Empty(0, 0);

			currentWorld.setCellBaseWorld(x, y - 1, empty);
		}
	}
}
