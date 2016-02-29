package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon12;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon12_2D extends Dungeon12 implements Drawable
{
	public Dungeon12_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[11], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
