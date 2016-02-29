package guiInterface.scene2D.volcano;

import gameLogic.Drawable;
import gameLogic.scene.volcano.Volcano15;

import java.awt.Graphics;

import common.ImageProvider;

public class Volcano15_2D extends Volcano15 implements Drawable
{
	public Volcano15_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.volcano[14], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
