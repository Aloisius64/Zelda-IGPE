package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.FallPoint;

import java.awt.Graphics;

import common.ImageProvider;

public class FallPoint2D extends FallPoint implements Drawable
{
	private int indexCurrentImage = 0;

	public FallPoint2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.fallPoint[indexCurrentImage], xPosition, yPosition, width,
				height, null);
	}

	@Override
	public void update()
	{
		if (isOpen())
		{
			indexCurrentImage = 1;
		}
	}

}
