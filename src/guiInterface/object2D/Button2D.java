package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.Button;
import java.awt.Graphics;

public class Button2D extends Button implements Drawable
{

	private int indexCurrentImage = 0;

	public Button2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
	}

	public Button2D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.button[indexCurrentImage], xPosition + (width / 4),
				yPosition + (height / 4), width / 2, height / 2, null);
	}

	@Override
	public void setActive(boolean active)
	{
		super.setActive(active);
		indexCurrentImage = (active) ? 1 : 0;
	}
}
