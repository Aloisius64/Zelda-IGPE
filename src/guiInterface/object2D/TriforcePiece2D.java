package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.TriforcePiece;

import java.awt.Graphics;

import common.ImageProvider;

public class TriforcePiece2D extends TriforcePiece implements Drawable
{
	private int indexCurrentImage = 0;

	public TriforcePiece2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.triforcePiece[indexCurrentImage], xPosition, yPosition,
				width / 2, height / 2, null);
	}

	@Override
	public void update()
	{
		super.update();
		indexCurrentImage = (indexCurrentImage + 1) % ImageProvider.triforcePiece.length;
	}
}
