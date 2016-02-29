package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrain;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrain2D extends LandTerrain implements Drawable
{
	public LandTerrain2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrain, xPosition, yPosition, width, height, null);
	}
}