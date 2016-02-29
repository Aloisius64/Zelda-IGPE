package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertNine;

import java.awt.Graphics;

import common.ImageProvider;

public class DesertNine2D extends DesertNine implements Drawable
{
	public DesertNine2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[8], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
