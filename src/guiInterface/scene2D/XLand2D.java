package guiInterface.scene2D;

import gameLogic.Drawable;
import gameLogic.scene.XLand;

import java.awt.Color;
import java.awt.Graphics;

/**
 * <p>
 * <b>Classe</b> per le zone della mappa in cui non vi si può accedere
 * </p>
 * 
 * @author Aloisius
 * 
 */

public class XLand2D extends XLand implements Drawable
{
	public XLand2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.setColor(Color.red);
		graphics.fillRect(xPosition, yPosition, width, height);
	}
}
