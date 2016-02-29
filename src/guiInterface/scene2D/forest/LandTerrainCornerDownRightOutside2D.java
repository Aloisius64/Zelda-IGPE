package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerDownRightOutside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerDownRightOutside2D extends LandTerrainCornerDownRightOutside
		implements Drawable
{
	public LandTerrainCornerDownRightOutside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerDownRightOutside, xPosition, yPosition,
				width, height, null);
	}
}