package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon15;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon15_2D extends Dungeon15 implements Drawable
{
	public Dungeon15_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[14], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
