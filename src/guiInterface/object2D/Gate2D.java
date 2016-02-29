package guiInterface.object2D;

import gameLogic.Drawable;
import gameLogic.object.Gate;

import java.awt.Graphics;

import common.ImageProvider;

public class Gate2D extends Gate implements Drawable
{

	private int indexCurrentImage = 0;
	private boolean nullGate = false;

	public Gate2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
	}

	public Gate2D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		if (!nullGate)
		{
			graphics.drawImage(ImageProvider.gate[indexCurrentImage], xPosition, yPosition
					- (height / 4), width, height, null);
		}
	}

	@Override
	public void update()
	{
		super.update();
		if (isOpen())
		{
			indexCurrentImage = 1;
		}
		else
		{
			indexCurrentImage = 0;
		}
	}

	public void setNullGate(boolean nullGate)
	{
		this.nullGate = nullGate;
	}

	public boolean isNullGate()
	{
		return nullGate;
	}
}
