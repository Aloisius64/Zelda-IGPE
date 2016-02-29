package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertSeven;

import java.awt.Graphics;

import common.ImageProvider;

public class DesertSeven2D extends DesertSeven implements Drawable
{
	public DesertSeven2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[6], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
