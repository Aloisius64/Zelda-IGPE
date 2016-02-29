package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainLeft;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainLeft2D extends MountainLeft implements Drawable
{
	public MountainLeft2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainLeft, xPosition - width, yPosition - height,
				width * 2, height * 2, null);
	}
}