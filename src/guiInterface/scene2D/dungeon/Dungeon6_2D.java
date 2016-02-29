package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon6;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon6_2D extends Dungeon6 implements Drawable
{
	public Dungeon6_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[5], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
