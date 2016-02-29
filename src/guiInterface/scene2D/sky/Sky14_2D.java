package guiInterface.scene2D.sky;

import gameLogic.Drawable;
import gameLogic.scene.sky.Sky14;

import java.awt.Graphics;

import common.ImageProvider;

public class Sky14_2D extends Sky14 implements Drawable
{
	public Sky14_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.sky[13], xPosition - (width * 9),
				yPosition - (height * 9), width * 10, height * 10, null);
	}
}
