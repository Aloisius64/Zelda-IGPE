package gameLogic.scene;

import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class SwordTrone extends AbstractObjectScene
{

	public SwordTrone(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.SWORD_TRONE);
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
