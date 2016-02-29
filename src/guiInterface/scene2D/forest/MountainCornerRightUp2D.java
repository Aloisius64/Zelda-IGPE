package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerRightUp;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerRightUp2D extends MountainCornerRightUp implements Drawable
{
	public MountainCornerRightUp2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerRightUp, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
