package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.MarbleScaleRight;

import java.awt.Graphics;

import staticConstant.StaticConstantID;

import common.ImageProvider;

public class MarbleScaleRight2D extends MarbleScaleRight implements Drawable
{
	public MarbleScaleRight2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.MARBLE_SCALE_RIGHT);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.marbleScaleRight, xPosition, yPosition, width, height,
				null);
	}
}
