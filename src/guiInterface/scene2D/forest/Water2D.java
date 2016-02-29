package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.Water;

import java.awt.Graphics;

import staticConstant.StaticConstantID;

import common.ImageProvider;

public class Water2D extends Water implements Drawable
{
	public Water2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.WATER);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.water, xPosition, yPosition, width, height, null);
	}
}
