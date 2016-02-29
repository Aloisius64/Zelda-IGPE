package guiInterface.scene2D.sky;

import gameLogic.Drawable;
import gameLogic.scene.sky.Sky7;

import java.awt.Graphics;

import common.ImageProvider;

public class Sky7_2D extends Sky7 implements Drawable
{
	public Sky7_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.sky[6], xPosition - (width * 9), yPosition - (height * 9),
				width * 10, height * 10, null);
	}
}
