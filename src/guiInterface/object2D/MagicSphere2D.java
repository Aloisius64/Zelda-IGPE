package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.MagicSphere;

import java.awt.Graphics;

import staticConstant.StaticConstantURL;

import common.ImageProvider;

public class MagicSphere2D extends MagicSphere implements Drawable
{
	private int counter = 0;

	public MagicSphere2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	public MagicSphere2D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.magic_sphere[counter
				% StaticConstantURL.MAGIC_SPHERE.length], xPosition + (50 / 4), yPosition
				+ (50 / 4), null);
		counter++;
	}
}
