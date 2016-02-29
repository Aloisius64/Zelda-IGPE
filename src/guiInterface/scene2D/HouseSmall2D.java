package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.HouseSmall;

import java.awt.Graphics;

import common.ImageProvider;

public class HouseSmall2D extends HouseSmall implements Drawable
{
	public HouseSmall2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.houseSmall, xPosition - width, yPosition - height,
				width * 2, height * 2, null);
	}
}
