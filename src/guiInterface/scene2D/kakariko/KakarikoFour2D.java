package guiInterface.scene2D.kakariko;

import gameLogic.Drawable;
import gameLogic.scene.kakariko.KakarikoFour;

import java.awt.Graphics;

import common.ImageProvider;

public class KakarikoFour2D extends KakarikoFour implements Drawable
{
	public KakarikoFour2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.kakariko[1], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
