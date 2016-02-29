package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.EmptyUtility;
import java.awt.Graphics;

public class EmptyUtility2D extends EmptyUtility implements Drawable
{

	public EmptyUtility2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
	}
}
