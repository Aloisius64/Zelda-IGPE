package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.HouseLarge;

import java.awt.Graphics;

import common.ImageProvider;

public class HouseLarge2D extends HouseLarge implements Drawable
{
	public HouseLarge2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.houseLarge, xPosition - width, yPosition - height,
				width * 2, height * 2, null);
	}
}
