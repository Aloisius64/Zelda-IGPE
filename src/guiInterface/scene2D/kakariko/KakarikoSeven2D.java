package guiInterface.scene2D.kakariko;

import gameLogic.Drawable;
import gameLogic.scene.kakariko.KakarikoSeven;

import java.awt.Graphics;

import common.ImageProvider;

public class KakarikoSeven2D extends KakarikoSeven implements Drawable
{
	public KakarikoSeven2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.kakariko[4], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
