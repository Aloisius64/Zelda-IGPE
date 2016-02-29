package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.TowerRoyal;

import java.awt.Graphics;

import common.ImageProvider;

public class TowerRoyal2D extends TowerRoyal implements Drawable
{
	public TowerRoyal2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.towerRoyal, xPosition + (width / 4), yPosition
				+ (height / 4), null);
	}
}
