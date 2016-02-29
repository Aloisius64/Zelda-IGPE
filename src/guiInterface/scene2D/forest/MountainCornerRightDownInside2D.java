package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerRightDownInside;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerRightDownInside2D extends MountainCornerRightDownInside implements
		Drawable
{
	public MountainCornerRightDownInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerRightDownInside, xPosition - width,
				yPosition - height, width * 2, height * 2, null);
	}
}
