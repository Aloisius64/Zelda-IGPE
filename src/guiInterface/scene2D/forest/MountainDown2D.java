package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.MountainDown;

import java.awt.Graphics;

import common.ImageProvider;

public class MountainDown2D extends MountainDown implements Drawable
{
	public MountainDown2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.mountainDown, xPosition - width, yPosition - height,
				width * 2, height * 2, null);
	}
}