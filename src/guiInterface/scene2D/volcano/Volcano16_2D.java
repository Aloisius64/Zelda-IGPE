package guiInterface.scene2D.volcano;

import gameLogic.Drawable;
import gameLogic.scene.volcano.Volcano16;

import java.awt.Graphics;

import common.ImageProvider;

public class Volcano16_2D extends Volcano16 implements Drawable
{
	public Volcano16_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.volcano[15], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
