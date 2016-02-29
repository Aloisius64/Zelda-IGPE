package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.Block;
import java.awt.Graphics;

public class Block2D extends Block implements Drawable
{

	public Block2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	public Block2D(int xPosition, int yPosition, boolean movable)
	{
		super(xPosition, yPosition, false);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.block, xPosition, yPosition, width, height, null);
	}
}
