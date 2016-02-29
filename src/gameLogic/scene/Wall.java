package gameLogic.scene;

import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class Wall extends AbstractObjectScene
{

	public Wall(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
		setId(StaticConstantID.WALL);
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		// TODO Auto-generated method stub
		return true;
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
