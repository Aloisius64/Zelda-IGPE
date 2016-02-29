package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerLeftDown;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerLeftDown2D extends MountainCornerLeftDown implements Drawable
{
	public MountainCornerLeftDown2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerLeftDown, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
