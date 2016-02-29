package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerDownLeftInside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerDownLeftInside2D extends LandTerrainCornerDownLeftInside implements
		Drawable
{
	public LandTerrainCornerDownLeftInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerDownLeftInside, xPosition, yPosition,
				width, height, null);
	}
}