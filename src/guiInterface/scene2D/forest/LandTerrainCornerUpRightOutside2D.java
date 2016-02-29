package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerUpRightOutside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerUpRightOutside2D extends LandTerrainCornerUpRightOutside implements
		Drawable
{
	public LandTerrainCornerUpRightOutside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerUpRightOutside, xPosition, yPosition,
				width, height, null);
	}
}