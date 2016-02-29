package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerDownLeftOutside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerDownLeftOutside2D extends LandTerrainCornerDownLeftOutside implements
		Drawable
{
	public LandTerrainCornerDownLeftOutside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerDownLeftOutside, xPosition, yPosition,
				width, height, null);
	}
}