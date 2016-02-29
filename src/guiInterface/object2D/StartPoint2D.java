package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.StartPoint;
import java.awt.Graphics;

public class StartPoint2D extends StartPoint implements Drawable
{
	public StartPoint2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.start_point, xPosition + (width / 4), yPosition
				+ (height / 4), width / 2, height / 2, null);
	}
}
