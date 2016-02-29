package guiInterface.scene2D.sky;

import gameLogic.Drawable;
import gameLogic.scene.sky.Sky3;

import java.awt.Graphics;

import common.ImageProvider;

public class Sky3_2D extends Sky3 implements Drawable
{
	public Sky3_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.sky[2], xPosition - (width * 9), yPosition - (height * 9),
				width * 10, height * 10, null);
	}
}
