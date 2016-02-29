package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertSix;

import java.awt.Graphics;

import common.ImageProvider;

public class DesertSix2D extends DesertSix implements Drawable
{
	public DesertSix2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[5], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
