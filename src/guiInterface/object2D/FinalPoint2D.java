package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.FinalPoint;
import java.awt.Graphics;
import staticConstant.StaticConstantID;

public class FinalPoint2D extends FinalPoint implements Drawable
{

	public FinalPoint2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.FINAL_POINT);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.final_point, xPosition + (width / 4), yPosition
				+ (height / 4), width / 2, height / 2, null);
	}
}
