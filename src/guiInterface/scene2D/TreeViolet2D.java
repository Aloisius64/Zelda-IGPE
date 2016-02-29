package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.TreeViolet;

import java.awt.Graphics;

import common.ImageProvider;

public class TreeViolet2D extends TreeViolet implements Drawable
{
	public TreeViolet2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.land, xPosition, yPosition, width, height, null);
		graphics.drawImage(ImageProvider.land, xPosition - width, yPosition, width, height, null);
		graphics.drawImage(ImageProvider.treeViolet, xPosition - width, yPosition - height,
				width * 2, height * 2, null);
	}
}
