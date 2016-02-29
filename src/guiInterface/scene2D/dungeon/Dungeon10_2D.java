package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon10;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon10_2D extends Dungeon10 implements Drawable
{
	public Dungeon10_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[9], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
