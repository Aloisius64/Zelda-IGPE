package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon13;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon13_2D extends Dungeon13 implements Drawable
{
	public Dungeon13_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[12], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
