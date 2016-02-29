package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainLeft;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainLeft2D extends LandTerrainLeft implements Drawable
{
	public LandTerrainLeft2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainLeft, xPosition, yPosition, width, height, null);
	}
}