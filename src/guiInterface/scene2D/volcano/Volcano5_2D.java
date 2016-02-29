package guiInterface.scene2D.volcano;

import gameLogic.Drawable;
import gameLogic.scene.volcano.Volcano5;

import java.awt.Graphics;

import common.ImageProvider;

public class Volcano5_2D extends Volcano5 implements Drawable
{
	public Volcano5_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.volcano[4], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
