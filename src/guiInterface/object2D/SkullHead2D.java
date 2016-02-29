package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.SkullHead;
import java.awt.Graphics;
import staticConstant.StaticConstantGame;

public class SkullHead2D extends SkullHead implements Drawable
{
	private int indexCurrentImage = 0;

	public SkullHead2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.skullHead[indexCurrentImage], xPosition - 10, yPosition,
				null);
	}

	@Override
	public void update()
	{
		int x = StaticConstantGame.gameManager.getHero().getX();
		int y = StaticConstantGame.gameManager.getHero().getY();
		int distance = (int) Math.sqrt(Math.pow((x - getX()), 2) + Math.pow((y - getY()), 2));

		if (distance <= 2)
		{
			indexCurrentImage = (indexCurrentImage + 1) % 3;
		}
		else
		{
			indexCurrentImage = 0;
		}

	}
}
