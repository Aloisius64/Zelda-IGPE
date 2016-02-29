package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerUpLeftInside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerUpLeftInside2D extends LandTerrainCornerUpLeftInside implements
		Drawable
{
	public LandTerrainCornerUpLeftInside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerUpLeftInside, xPosition, yPosition,
				width, height, null);
	}
}