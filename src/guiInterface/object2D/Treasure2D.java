package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.Treasure;
import java.awt.Graphics;

public class Treasure2D extends Treasure implements Drawable
{
	private int indexCurrentImage = 0;

	public Treasure2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 500);
	}

	public Treasure2D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.treasure[indexCurrentImage], xPosition + (width / 4),
				yPosition + (height / 4), width / 2, height / 2, null);
	}

	@Override
	public void update()
	{
		if (isOpen())
		{
			indexCurrentImage = 1;
		}
		else
		{
			indexCurrentImage = 0;
		}
	}
}
