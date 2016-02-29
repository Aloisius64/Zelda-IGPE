package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainDown;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainDown2D extends LandTerrainDown implements Drawable
{
	public LandTerrainDown2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainDown, xPosition, yPosition, width, height, null);
	}
}