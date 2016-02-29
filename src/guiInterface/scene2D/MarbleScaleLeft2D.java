package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.MarbleScaleLeft;

import java.awt.Graphics;

import common.ImageProvider;

public class MarbleScaleLeft2D extends MarbleScaleLeft implements Drawable
{
	public MarbleScaleLeft2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.marbleScaleLeft, xPosition, yPosition, width, height, null);
	}
}
