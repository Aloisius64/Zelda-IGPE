package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.Wall;

import java.awt.Graphics;

import common.ImageProvider;

public class Wall2D extends Wall implements Drawable
{
	public Wall2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.wall, xPosition + (width / 2), yPosition + (height / 2),
				(width / 2), (height / 2), null);
		graphics.drawImage(ImageProvider.wall, xPosition + (width / 2), yPosition, (width / 2),
				(height / 2), null);
		graphics.drawImage(ImageProvider.wall, xPosition, yPosition + (height / 2), (width / 2),
				(height / 2), null);
		graphics.drawImage(ImageProvider.wall, xPosition, yPosition, (width / 2), (height / 2),
				null);
	}
}
