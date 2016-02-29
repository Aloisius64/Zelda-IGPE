package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon5;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon5_2D extends Dungeon5 implements Drawable
{
	public Dungeon5_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[4], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
