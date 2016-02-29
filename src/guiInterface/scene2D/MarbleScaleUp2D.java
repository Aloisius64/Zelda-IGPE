package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.MarbleScaleUp;

import java.awt.Graphics;

import common.ImageProvider;

public class MarbleScaleUp2D extends MarbleScaleUp implements Drawable
{
	public MarbleScaleUp2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.marbleScaleUp, xPosition, yPosition, width, height, null);
	}
}
