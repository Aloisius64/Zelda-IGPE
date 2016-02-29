package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.SwordTrone;

import java.awt.Graphics;

import common.ImageProvider;

public class SwordTrone2D extends SwordTrone implements Drawable
{
	public SwordTrone2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.swordTrone, xPosition, yPosition, width, height, null);
	}
}
