package guiInterface.scene2D.kakariko;

import gameLogic.Drawable;
import gameLogic.scene.kakariko.KakarikoSix;

import java.awt.Graphics;

import common.ImageProvider;

public class KakarikoSix2D extends KakarikoSix implements Drawable
{

	public KakarikoSix2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.kakariko[3], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}

}
