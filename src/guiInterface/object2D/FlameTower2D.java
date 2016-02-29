package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.FlameTower;
import java.awt.Graphics;

public class FlameTower2D extends FlameTower implements Drawable
{
	private int indexCurrentImage = 0;

	public FlameTower2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.flameTower[indexCurrentImage], xPosition + (width / 4),
				yPosition + (height / 4), width * 3 / 4, height * 3 / 4, null);
	}

	@Override
	public void update()
	{
		if (isOpen())
			indexCurrentImage = (indexCurrentImage + 1) % 3;
	}
}
