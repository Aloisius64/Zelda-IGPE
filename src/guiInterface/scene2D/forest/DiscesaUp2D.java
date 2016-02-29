package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.DiscesaUp;

import java.awt.Graphics;

import common.ImageProvider;

public class DiscesaUp2D extends DiscesaUp implements Drawable
{
	public DiscesaUp2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.discesaUp, xPosition - (width * 2), yPosition - height,
				width * 3, height * 2, null);
	}
}
