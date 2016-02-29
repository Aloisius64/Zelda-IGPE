package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.Flower;
import java.awt.Graphics;

public class Flower2D extends Flower implements Drawable
{

	private int indexCurrentImage = 0;

	public Flower2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.flower[indexCurrentImage], xPosition + (width / 4),
				yPosition + (height / 4), width / 2, height / 2, null);
	}

	@Override
	public void update()
	{
		indexCurrentImage = (indexCurrentImage + 1) % 2;
	}
}
