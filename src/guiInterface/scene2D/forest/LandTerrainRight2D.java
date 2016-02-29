package guiInterface.scene2D.forest;

import gameLogic.Drawable;
import gameLogic.scene.forest.LandTerrainRight;

import java.awt.Graphics;

import common.ImageProvider;

public class LandTerrainRight2D extends LandTerrainRight implements Drawable
{
	public LandTerrainRight2D(int xPosition, int yPosition)
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