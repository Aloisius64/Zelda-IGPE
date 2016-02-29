package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.Statue;

import java.awt.Graphics;

import common.ImageProvider;

public class Statue2D extends Statue implements Drawable
{
	public Statue2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.statue, xPosition - width, yPosition - height, width * 2,
				height * 2, null);
	}
}
