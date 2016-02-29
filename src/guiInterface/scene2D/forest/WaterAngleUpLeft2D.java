package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.WaterAngleUpLeft;

import java.awt.Graphics;

import common.ImageProvider;

public class WaterAngleUpLeft2D extends WaterAngleUpLeft implements Drawable
{
	private int index = 0;

	public WaterAngleUpLeft2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.waterAngleUpLeft[index], xPosition - width, yPosition
				- height, width * 2, height * 2, null);
		index = (index + 1) % 2;
	}
}