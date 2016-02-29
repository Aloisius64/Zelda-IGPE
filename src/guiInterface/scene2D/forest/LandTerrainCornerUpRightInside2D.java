package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerUpRightInside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerUpRightInside2D extends LandTerrainCornerUpRightInside implements
		Drawable
{
	public LandTerrainCornerUpRightInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerUpRightInside, xPosition, yPosition,
				width, height, null);
	}
}