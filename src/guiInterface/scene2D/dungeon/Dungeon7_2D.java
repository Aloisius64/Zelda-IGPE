package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon7;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon7_2D extends Dungeon7 implements Drawable
{
	public Dungeon7_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[6], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
