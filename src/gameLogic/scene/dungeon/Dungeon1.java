package gameLogic.scene.dungeon;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.XLand;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class Dungeon1 extends AbstractObjectScene
{
	public Dungeon1(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.DUNGEON_1);
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
}
