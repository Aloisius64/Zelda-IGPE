package gameLogic.scene;

import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class Statue extends AbstractObjectScene
{

	public Statue(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.STATUE);
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		if (x >= 1 && y >= 1)
			return true;
		return false;
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
		if (world instanceof ConcreteWorld)
		{
			ConcreteWorld currentWorld = (ConcreteWorld) world;

			XLand xLand = new XLand(0, 0);

			currentWorld.setCellBaseWorld(x - 1, y, xLand);
			currentWorld.setCellBaseWorld(x, y - 1, xLand);
			currentWorld.setCellBaseWorld(x - 1, y - 1, xLand);
		}
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
		if (world instanceof ConcreteWorld)
		{
			ConcreteWorld currentWorld = (ConcreteWorld) world;

			Empty empty = new Empty(0, 0);

			currentWorld.setCellBaseWorld(x - 1, y, empty);
			currentWorld.setCellBaseWorld(x, y - 1, empty);
			currentWorld.setCellBaseWorld(x - 1, y - 1, empty);
		}

	}
}
