package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertEight;

import java.awt.Graphics;

import common.ImageProvider;

public class DesertEight2D extends DesertEight implements Drawable
{
	public DesertEight2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[7], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
