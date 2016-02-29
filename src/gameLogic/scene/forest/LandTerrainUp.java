package gameLogic.scene.forest;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class LandTerrainUp extends AbstractObjectScene
{
	@Override
	public boolean canAdd(int x, int y)
	{
		// TODO Auto-generated method stub
		return true;
	}

	public LandTerrainUp(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.LAND_TERRAIN_UP);
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
