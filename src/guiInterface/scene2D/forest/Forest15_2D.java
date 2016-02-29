package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.Forest15;

import java.awt.Graphics;

import common.ImageProvider;

public class Forest15_2D extends Forest15 implements Drawable
{
	public Forest15_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.forest[14], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
