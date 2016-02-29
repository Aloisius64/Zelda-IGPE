package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainRight;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainRight2D extends MountainRight implements Drawable
{
	public MountainRight2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainRight, xPosition - width, yPosition - height,
				width * 2, height * 2, null);
	}
}