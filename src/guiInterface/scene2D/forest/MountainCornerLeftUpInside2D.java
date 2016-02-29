package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerLeftUpInside;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerLeftUpInside2D extends MountainCornerLeftUpInside implements Drawable
{
	public MountainCornerLeftUpInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerLeftUpInside, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
