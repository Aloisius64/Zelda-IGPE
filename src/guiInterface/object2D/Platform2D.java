package guiInterface.object2D;

import common.Direction;
import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.character.CharacterState;
import gameLogic.character.Hero;
import gameLogic.object.Platform;
import guiInterface.character2D.Hero2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURL;
import staticConstant.StaticConstantWorld;

public class Platform2D extends Platform implements Drawable
{

	private int deltaX;
	private int deltaY;
	private JPanel gamePanel;

	public Platform2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	public Platform2D(final int xPosition, final int yPosition, final Direction direction)
	{
		super(xPosition, yPosition, direction);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.platform, xPosition + getDeltaY(),
				yPosition + getDeltaX(), width, height, null);
	}

	public void moveAnimation(int width, int height, int nextStepX, int nextStepY)
	{
		Hero2D hero2D = null;
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero instanceof Hero2D)
		{
			hero2D = (Hero2D) hero;
		}

		int typeDirection = getDirection().getTypeDirection();

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

		if (hero2D.getX() == getX() && hero2D.getY() == getY() && hero.getState() != -1)
		{
			hero2D.setDeltaX(getDeltaX());
			hero2D.setDeltaY(getDeltaY());
			if (hero.getState() != CharacterState.WALK)
			{
				hero2D.setCurrentDirection(getDirection().getTypeDirection());
			}
			hero2D.positionUpdate(false, width, height);
			try
			{
				Thread.sleep(60);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

		getGamePanel().repaint();

		int x = getX();
		int y = getY();
		boolean move = false;

		if (getDeltaX() > (height - 20))
		{
			setDeltaX(getDeltaX() - height);
			x = getX() + getDirection().getXSelected();
			move = true;
		}
		else if (getDeltaX() < (-height + 20))
		{
			setDeltaX(getDeltaX() + height);
			x = getX() + getDirection().getXSelected();
			move = true;
		}

		if (getDeltaY() > (width - 20))
		{
			setDeltaY(getDeltaY() - width);
			y = getY() + getDirection().getYSelected();
			move = true;
		}
		else if (getDeltaY() < (-width + 20))
		{
			setDeltaY(getDeltaY() + width);
			y = getY() + getDirection().getYSelected();
			move = true;
		}

		if (move)
		{
			StaticConstantGame.gameManager.getConcreteWorld().swapCell(1, getX(), getY(), x, y);
			setX(x);
			setY(y);

			setDeltaX(0);
			setDeltaY(0);
			increaseMovement();
			if (getCurrentMovement() % (getMovement()) == 0)
			{
				setDirection(getDirection().getReverse());
			}
		}
	}

	@Override
	public void update()
	{
		int numberSteps = StaticConstantURL.HERO_WALK_BACK.length;
		int width = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int height = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;
		int nextStepX = (width / numberSteps) + 1;
		int nextStepY = height / numberSteps;

		moveAnimation(width, height, nextStepX, nextStepY);
	}

	public void setDeltaX(int deltaX)
	{
		this.deltaX = deltaX;
	}

	public int getDeltaX()
	{
		return deltaX;
	}

	public void setDeltaY(int deltaY)
	{
		this.deltaY = deltaY;
	}

	public int getDeltaY()
	{
		return deltaY;
	}

	public void setGamePanel()
	{
		gamePanel = ((Hero2D) StaticConstantGame.gameManager.getHero()).getPanel();
	}

	public JPanel getGamePanel()
	{
		return gamePanel;
	}
}
