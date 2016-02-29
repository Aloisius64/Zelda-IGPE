package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon21;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon21_2D extends Dungeon21 implements Drawable
{
	public Dungeon21_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[20], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
