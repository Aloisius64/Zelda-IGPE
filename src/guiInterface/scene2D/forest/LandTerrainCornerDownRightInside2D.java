package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerDownRightInside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerDownRightInside2D extends LandTerrainCornerDownRightInside implements
		Drawable
{
	public LandTerrainCornerDownRightInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerDownRightInside, xPosition, yPosition,
				width, height, null);
	}
}