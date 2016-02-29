package guiInterface.scene2D.volcano;

import gameLogic.Drawable;
import gameLogic.scene.volcano.Volcano12;

import java.awt.Graphics;

import common.ImageProvider;

public class Volcano12_2D extends Volcano12 implements Drawable
{
	public Volcano12_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.volcano[11], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
