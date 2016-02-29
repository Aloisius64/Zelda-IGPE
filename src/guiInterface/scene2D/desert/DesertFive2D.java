package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertFive;

import java.awt.Graphics;

import common.ImageProvider;

public class DesertFive2D extends DesertFive implements Drawable
{
	public DesertFive2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[4], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
