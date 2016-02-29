package gameLogic.scene.dungeon;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.XLand;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import java.awt.Graphics;
import common.ImageProvider;

import staticConstant.StaticConstantID;

public class Dungeon15 extends AbstractObjectScene
{
	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	public Dungeon15(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.DUNGEON_15);
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
