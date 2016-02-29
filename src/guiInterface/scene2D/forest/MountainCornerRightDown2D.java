package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerRightDown;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerRightDown2D extends MountainCornerRightDown implements Drawable
{
	public MountainCornerRightDown2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerRightDown, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
