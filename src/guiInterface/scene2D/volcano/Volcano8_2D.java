package guiInterface.scene2D.volcano;

import gameLogic.Drawable;
import gameLogic.scene.volcano.Volcano8;

import java.awt.Graphics;

import common.ImageProvider;

public class Volcano8_2D extends Volcano8 implements Drawable
{
	public Volcano8_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.volcano[7], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
