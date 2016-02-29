package gameLogic.scene;

import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class TowerRoyal extends AbstractObjectScene
{
	@Override
	public boolean canAdd(int x, int y)
	{
		// TODO Auto-generated method stub
		return true;
	}

	public TowerRoyal(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.TOWER_ROYAL);
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
		// TODO Auto-generated method stub

	}
}
