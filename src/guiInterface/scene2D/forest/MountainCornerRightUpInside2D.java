package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainCornerRightUpInside;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainCornerRightUpInside2D extends MountainCornerRightUpInside implements Drawable
{
	public MountainCornerRightUpInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainCornerRightUpInside, xPosition - width, yPosition
				- height, width * 2, height * 2, null);
	}
}
