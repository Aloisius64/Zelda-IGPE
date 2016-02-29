package guiInterface.scene2D.dungeon;

import gameLogic.Drawable;
import gameLogic.scene.dungeon.Dungeon23;

import java.awt.Graphics;

import common.ImageProvider;

public class Dungeon23_2D extends Dungeon23 implements Drawable
{
	public Dungeon23_2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.dungeon[22], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
