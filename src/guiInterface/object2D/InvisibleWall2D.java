package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.InvisibleWall;

import java.awt.Graphics;

import common.ImageProvider;

public class InvisibleWall2D extends InvisibleWall implements Drawable
{
	private int counter = 0;

	public InvisibleWall2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		// graphics.setColor(Color.cyan);
		// graphics.fillRect(xPosition, yPosition, width, height);

		if (isActive())
		{
			if (counter % 2 == 0)
				graphics.drawImage(ImageProvider.fog[1], xPosition, yPosition, width, height, null);
			counter++;
		}
		if (counter > 10)
		{
			setActive(false);
			counter = 0;
		}
	}
}
