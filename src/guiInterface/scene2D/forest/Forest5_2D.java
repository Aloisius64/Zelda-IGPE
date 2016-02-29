package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.Forest5;

import java.awt.Graphics;

import common.ImageProvider;

public class Forest5_2D extends Forest5 implements Drawable
{
	public Forest5_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.forest[4], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
