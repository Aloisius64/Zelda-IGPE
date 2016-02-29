package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainCornerUpLeftOutside;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainCornerUpLeftOutside2D extends LandTerrainCornerUpLeftOutside implements
		Drawable
{
	public LandTerrainCornerUpLeftOutside2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.landTerrainCornerUpLeftOutside, xPosition, yPosition,
				width, height, null);
	}
}