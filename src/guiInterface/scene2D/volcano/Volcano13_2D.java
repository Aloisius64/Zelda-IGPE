package guiInterface.scene2D.volcano;

import gameLogic.Drawable;
import gameLogic.scene.volcano.Volcano13;

import java.awt.Graphics;

import common.ImageProvider;

public class Volcano13_2D extends Volcano13 implements Drawable
{
	public Volcano13_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.volcano[12], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
