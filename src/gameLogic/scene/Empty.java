package gameLogic.scene;

import gameLogic.world.World;

import java.awt.Color;
import java.awt.Graphics;

public class Empty extends AbstractObjectScene
{

	public Empty(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
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
