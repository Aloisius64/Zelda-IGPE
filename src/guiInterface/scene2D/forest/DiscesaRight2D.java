package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.DiscesaRight;

import java.awt.Graphics;

import common.ImageProvider;

public class DiscesaRight2D extends DiscesaRight implements Drawable
{
	public DiscesaRight2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.discesaRight, xPosition - (width * 2), yPosition - height,
				width * 3, height * 2, null);
	}
}