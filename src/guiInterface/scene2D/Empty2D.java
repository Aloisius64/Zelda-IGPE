package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.Empty;
import java.awt.Color;
import java.awt.Graphics;

public class Empty2D extends Empty implements Drawable
{

	public Empty2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.setColor(Color.white);
		graphics.fillRect(xPosition, yPosition, width, height);
	}
}
