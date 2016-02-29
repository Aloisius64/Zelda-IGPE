package guiInterface.character2D;

import gameLogic.character.CharacterState;
import gameLogic.character.Skull;
import gameLogic.scene.AbstractObjectScene;
import inteligenceAI.AbstractAI;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantURL;
import staticConstant.StaticConstantWorld;

import common.Direction;
import common.ImageProvider;
import common.SoundManager;

public class Skull2D extends Skull implements Character2D
{

	private int deltaY = 0;
	private int deltaX = 0;
	private JPanel panel;
	private Image currentImage = null;
	private int indexCurrentImage = 0;
	private final int animationStep = 5;

	public Skull2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setDeltaX(0);
		setDeltaY(0);
	}

	public Skull2D(final int xPosition, final int yPosition, AbstractAI inteligence)
	{
		super(xPosition, yPosition, inteligence);

		setState(0);
		setDeltaX(0);
		setDeltaY(0);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		switch (getCurrentDirection().getTypeDirection())
		{
			case Direction.UP:
				currentImage = ImageProvider.skullWalkUp[indexCurrentImage];
				break;
			case Direction.DOWN:
				currentImage = ImageProvider.skullWalkDown[indexCurrentImage];
				break;
			case Direction.LEFT:
				currentImage = ImageProvider.skullWalkLeft[indexCurrentImage];
				break;
			case Direction.RIGHT:
				currentImage = ImageProvider.skullWalkRight[indexCurrentImage];
				break;
			default:
				currentImage = ImageProvider.skullWalkDown[indexCurrentImage];
		}
		graphics.drawImage(currentImage, xPosition + (50 / 4) + getDeltaY(), yPosition + (50 / 4)
				+ getDeltaX(), width - 30, height - 10, null);
	}

	public void moveAnimation()
	{
		int width = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int height = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;
		int nextStepX = width / animationStep;
		int nextStepY = height / animationStep;
		int typeDirection = getCurrentDirection().getTypeDirection();
		boolean falseAnimation = false;

		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(x, y);
		if (sceneObject.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
		{
			falseAnimation = true;
		}

		for (int i = 0; i < animationStep; i++)
		{
			if (!falseAnimation)
			{
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
			}
			indexCurrentImage = ((i) % StaticConstantURL.SKULL_WALK_DOWN.length);

			getPanel().repaint();
			try
			{
				Thread.sleep(150);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}

		}
		if (!falseAnimation)
		{
			move();
		}
		setDeltaX(0);
		setDeltaY(0);
	}

	@Override
	public void update()
	{
		if (logicUpdate())
		{
			switch (getState())
			{
				case CharacterState.STAND:
				{
					break;
				}
				case CharacterState.PHYSIC_ATTACK:
				{
					SoundManager.playSound(SoundManager.SKULL_ATTACK_SWORD, 1, SoundManager.EFFECT);

					attack(0);
					setState(CharacterState.STAND);
				}
					break;
				case CharacterState.DISTANCE_ATTACK:
				{
					int value = attack(1);
					if (value == 2)
					{
						new AttackSphere2D(this);
					}
					setState(CharacterState.STAND);
				}
					break;
				case CharacterState.WALK:
				{
					SoundManager.playSound(SoundManager.SKULL_WALK, 1, SoundManager.EFFECT);
					moveAnimation();
				}
					break;
				default:

					break;
			}
			try
			{
				Thread.sleep(StaticConstantGame.timeSkull);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
	}

	@Override
	public JPanel getPanel()
	{
		return panel;
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
		this.panel = panel;
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
