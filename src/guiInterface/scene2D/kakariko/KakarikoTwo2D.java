package guiInterface.scene2D.kakariko;

import gameLogic.Drawable;
import gameLogic.scene.kakariko.KakarikoTwo;

import java.awt.Graphics;

import common.ImageProvider;

public class KakarikoTwo2D extends KakarikoTwo implements Drawable
{

	public KakarikoTwo2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.kakariko[0], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}

}
