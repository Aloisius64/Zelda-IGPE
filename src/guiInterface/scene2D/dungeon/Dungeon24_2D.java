package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon24;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon24_2D extends Dungeon24 implements Drawable
{
	public Dungeon24_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[23], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
