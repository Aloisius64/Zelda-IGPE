package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon18;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon18_2D extends Dungeon18 implements Drawable
{
	public Dungeon18_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[17], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
