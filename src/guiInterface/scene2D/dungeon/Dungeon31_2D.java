package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon31;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon31_2D extends Dungeon31 implements Drawable
{
	public Dungeon31_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[30], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
