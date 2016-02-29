package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerLeftUp;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerLeftUp2D extends MountainCornerLeftUp implements Drawable
{
	public MountainCornerLeftUp2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerLeftUp, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
