package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.Heart;

import java.awt.Graphics;

import common.ImageProvider;

public class Heart2D extends Heart implements Drawable
{
	public Heart2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 100);
	}

	public Heart2D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.heart, xPosition + (width / 4), yPosition + (height / 4),
				width / 2, height / 2, null);
	}
}
