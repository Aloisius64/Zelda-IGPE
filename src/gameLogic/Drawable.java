package gameLogic;

import java.awt.Graphics;

public interface Drawable
{

	public abstract void drawObject(Graphics graphics, int xPosition, int yPosition, int width,
			int height);
}
