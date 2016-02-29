package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.Forest7;

import java.awt.Graphics;

import common.ImageProvider;

public class Forest7_2D extends Forest7 implements Drawable
{
	public Forest7_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.forest[6], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
