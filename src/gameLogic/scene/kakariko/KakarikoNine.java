package gameLogic.scene.kakariko;

import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.XLand;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;

import java.awt.Graphics;

import common.ImageProvider;

import staticConstant.StaticConstantID;

public class KakarikoNine extends AbstractObjectScene
{

	public KakarikoNine(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.KAKARIKO_NINE);
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
