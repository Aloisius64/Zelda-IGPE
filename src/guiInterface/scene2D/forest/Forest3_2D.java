package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.Forest3;

import java.awt.Graphics;

import common.ImageProvider;

public class Forest3_2D extends Forest3 implements Drawable
{
	public Forest3_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.forest[2], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
