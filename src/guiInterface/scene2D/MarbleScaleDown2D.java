package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.MarbleScaleDown;

import java.awt.Graphics;

import common.ImageProvider;

public class MarbleScaleDown2D extends MarbleScaleDown implements Drawable
{
	public MarbleScaleDown2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.marbleScaleDown, xPosition, yPosition, width, height, null);
	}
}
