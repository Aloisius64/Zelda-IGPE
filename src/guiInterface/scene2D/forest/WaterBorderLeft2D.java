package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.WaterBorderLeft;

import java.awt.Graphics;

import common.ImageProvider;

public class WaterBorderLeft2D extends WaterBorderLeft implements Drawable
{
	private int index = 0;

	public WaterBorderLeft2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.waterBorderLeft[index], xPosition - width, yPosition
				- height, width * 2, height * 2, null);
		index = (index + 1) % 2;
	}
}