package guiInterface.character2D;

import gameLogic.character.EmptyCharacter;
import java.awt.Graphics;
import javax.swing.JPanel;

public class EmptyCharacter2D extends EmptyCharacter implements Character2D
{

	private int deltaY = 0;
	private int deltaX = 0;

	public EmptyCharacter2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void update()
	{
	}

	@Override
	public JPanel getPanel()
	{
		return null;
	}

	@Override
	public void setDeltaX(int deltaX)
	{
		this.deltaX = deltaX;

	}

	@Override
	public void setDeltaY(int deltaY)
	{
		this.deltaY = deltaY;

	}

	@Override
	public void setPanel(JPanel panel)
	{
	}

	@Override
	public int getDeltaY()
	{

		return deltaY;
	}

	@Override
	public int getDeltaX()
	{
		return deltaX;
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
	}
}
