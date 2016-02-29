package gameLogic.scene;

import gameLogic.world.World;

import java.awt.Color;
import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

/**
 * <p>
 * <b>Classe</b> per le zone della mappa in cui non vi si può accedere
 * </p>
 * 
 * @author Aloisius
 * 
 */

public class XLand extends AbstractObjectScene
{

	public XLand(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		// TODO Auto-generated constructor stub
		setId(StaticConstantID.XLAND);
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
