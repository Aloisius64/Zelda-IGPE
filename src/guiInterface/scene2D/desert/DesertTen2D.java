package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertTen;

import java.awt.Graphics;

import common.ImageProvider;

public class DesertTen2D extends DesertTen implements Drawable
{
	public DesertTen2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[9], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
