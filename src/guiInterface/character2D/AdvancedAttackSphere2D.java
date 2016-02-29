package guiInterface.character2D;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.AdvancedAttackSphere;

import java.awt.Graphics;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURL;
import staticConstant.StaticConstantWorld;

import common.Direction;
import common.ImageProvider;

public class AdvancedAttackSphere2D extends AdvancedAttackSphere implements Character2D
{
	private static final int animationSteps = 10;
	private final int correctionX = 0;
	private final int correctionY = 0;
	private int deltaY = 0;
	private int deltaX = 0;
	private int indexCurrentImage = 0;

	public AdvancedAttackSphere2D(AbstractCharacter throwingCharacter)
	{
		super(throwingCharacter);

		if (throwingCharacter instanceof Character2D)
		{
			int newDeltaX = ((Character2D) throwingCharacter).getDeltaX();
			int newDeltaY = ((Character2D) throwingCharacter).getDeltaY();
			setDeltaX(newDeltaX);
			setDeltaY(newDeltaY);
		}
	}

	public AdvancedAttackSphere2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.attack_sphere[indexCurrentImage], correctionX + xPosition
				+ (50 / 4) + getDeltaY(), correctionY + yPosition + (50 / 4) + getDeltaX(), null);
	}

	@Override
	public void update()
	{
		int xTarget = getX();
		int yTarget = getY();
		super.update();

		int width = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int height = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;
		int nextStepX = width / animationSteps;
		int nextStepY = height / animationSteps;
		int typeDirection = getCurrentDirection().getTypeDirection();

		if (typeDirection == Direction.UP)
		{
			setDeltaX(getDeltaX() - nextStepX);
		}
		else if (typeDirection == Direction.LEFT)
		{
			setDeltaY(getDeltaY() - nextStepY);
		}
		else if (typeDirection == Direction.RIGHT)
		{
			setDeltaY(getDeltaY() + nextStepY);
		}
		else if (typeDirection == Direction.DOWN)
		{
			setDeltaX(getDeltaX() + nextStepX);
		}

		if (Math.abs(getDeltaX()) > width)
		{
			setX(xTarget + getCurrentDirection().getXSelected());
			setDeltaX(0);

		}
		if (Math.abs(getDeltaY()) > height)
		{
			setY(yTarget + getCurrentDirection().getYSelected());
			setDeltaY(0);

		}

		if (isExploded())
		{

			indexCurrentImage++;
			if (indexCurrentImage > (StaticConstantURL.ATTACK_SPHERE.length - 1))
			{
				StaticConstantGame.sphereManager.removeSphere(this);
			}
		}
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
}
