package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.Coin;

import java.awt.Graphics;

import common.ImageProvider;

public class Coin2D extends Coin implements Drawable
{

	private int indexCurrentImage = 0;
	private final int typeCoin = 0;

	public Coin2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
	}

	public Coin2D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		switch (typeCoin)
		{
			case 1:
				graphics.drawImage(ImageProvider.blueCoin[indexCurrentImage], xPosition + (50 / 4),
						yPosition + (50 / 4), null);
				break;
			case 2:
				graphics.drawImage(ImageProvider.redCoin[indexCurrentImage], xPosition + (50 / 4),
						yPosition + (50 / 4), null);
				break;
			default:
				graphics.drawImage(ImageProvider.greenCoin[indexCurrentImage],
						xPosition + (50 / 4), yPosition + (50 / 4), null);
				break;
		}
	}

	@Override
	public void update()
	{
		super.update();
		indexCurrentImage = (indexCurrentImage + 1) % 3;
	}
}
