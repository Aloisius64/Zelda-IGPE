package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.scene.desert.DesertFour;

import java.awt.Graphics;

import staticConstant.StaticConstantID;

import common.ImageProvider;

public class DesertFour2D extends DesertFour implements Drawable
{
	public DesertFour2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.DESERT_FOUR);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.desert[3], xPosition - (width * 9), yPosition
				- (height * 9), width * 10, height * 10, null);
	}
}
