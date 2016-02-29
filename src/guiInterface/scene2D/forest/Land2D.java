package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.Land;

import java.awt.Graphics;

import staticConstant.StaticConstantID;

import common.ImageProvider;

public class Land2D extends Land implements Drawable
{
	public Land2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.LAND);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.land, xPosition, yPosition, width, height, null);
	}
}
