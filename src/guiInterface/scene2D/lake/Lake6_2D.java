package guiInterface.scene2D.lake;

import gameLogic.Drawable;
import gameLogic.scene.lake.Lake6;

import java.awt.Graphics;

import common.ImageProvider;

public class Lake6_2D extends Lake6 implements Drawable
{
	private static int updater = 20;
	private int counter = 0;
	private int index = 0;

	public Lake6_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.lake[index % 2][6 - 1], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
		if (counter % updater == 0)
		{
			index = (index + 1) % 2;
		}
		counter++;
	}
}
