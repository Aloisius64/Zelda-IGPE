package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerLeftDownInside;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerLeftDownInside2D extends MountainCornerLeftDownInside implements
		Drawable
{
	public MountainCornerLeftDownInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerLeftDownInside, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
