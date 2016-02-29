package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainUp;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainUp2D extends LandTerrainUp implements Drawable
{
	public LandTerrainUp2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainUp, xPosition, yPosition, width, height, null);
	}
}