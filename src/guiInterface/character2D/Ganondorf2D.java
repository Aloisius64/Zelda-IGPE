package guiInterface.character2D;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.CharacterState;
import gameLogic.character.Ganondorf;
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

public class Ganondorf2D extends Ganondorf implements Character2D, Runnable
{
	private int deltaY = 0;
	private int deltaX = 0;
	private JPanel panel;
	private Image currentImage = null;
	private int indexCurrentImage = 0;
	private final int animationStep = 4;
	private Thread manager;
	private boolean sleeping = false;

	public Ganondorf2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setDeltaX(0);
		setDeltaY(0);
		currentImage = ImageProvider.ganondorfWalkDown[0];
	}

	public Ganondorf2D(final int xPosition, final int yPosition, AbstractAI inteligence)
	{
		super(xPosition, yPosition, inteligence);
		setDeltaX(0);
		setDeltaY(0);
		currentImage = ImageProvider.ganondorfWalkDown[0];
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		if (currentImage == null)
		{
			currentImage = ImageProvider.ganondorfWalkDown[0];
		}
		graphics.drawImage(currentImage, xPosition + (50 / 4) + getDeltaY() - 39, yPosition
				+ (50 / 4) + getDeltaX() - 41, null);
	}

	public void physicAttackAnimation(int numberSteps)
	{
		for (int i = 0; i < numberSteps; i++)
		{
			indexCurrentImage = i;
			switch (getCurrentDirection().getTypeDirection())
			{
				case Direction.UP:
					currentImage = ImageProvider.ganondorfAttackUp[indexCurrentImage];
					break;
				case Direction.DOWN:
					currentImage = ImageProvider.ganondorfAttackDown[indexCurrentImage];
					break;
				case Direction.LEFT:
					currentImage = ImageProvider.ganondorfAttackLeft[indexCurrentImage];
					break;
				case Direction.RIGHT:
					currentImage = ImageProvider.ganondorfAttackRight[indexCurrentImage];
					break;
				default:
					currentImage = ImageProvider.ganondorfAttackDown[indexCurrentImage];
					break;
			}
			// changeImage(indexCurrentImage);
			getPanel().repaint();
			try
			{
				Thread.sleep(50);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
		switch (getCurrentDirection().getTypeDirection())
		{
			case Direction.UP:
				currentImage = ImageProvider.ganondorfWalkUp[0];
				break;
			case Direction.DOWN:
				currentImage = ImageProvider.ganondorfWalkDown[0];
				break;
			case Direction.LEFT:
				currentImage = ImageProvider.ganondorfWalkLeft[0];
				break;
			case Direction.RIGHT:
				currentImage = ImageProvider.ganondorfWalkRight[0];
				break;
			default:
				currentImage = ImageProvider.ganondorfWalkDown[0];
				break;

		}
		attack(0);
	}

	public void moveAnimation()
	{
		int width = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int height = ((StaticConstantGame.yResolution) / StaticConstantWorld.Y_DIMENSION) - 4;
		int nextStepX = width / animationStep;
		int nextStepY = height / animationStep;
		int typeDirection = getCurrentDirection().getTypeDirection();
		boolean falseAnimation = false;

		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(x, y);
		AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(x, y);

		if (sceneObject.getId() < StaticConstantID.LIMITS_ACCESSIBLE || character.getId() == 206
				|| character.getId() == 200)
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
			indexCurrentImage = (i) % StaticConstantURL.GANONDORF_WALK_UP.length;
			switch (typeDirection)
			{
				case Direction.UP:
					currentImage = ImageProvider.ganondorfWalkUp[indexCurrentImage];
					break;
				case Direction.DOWN:
					currentImage = ImageProvider.ganondorfWalkDown[indexCurrentImage];
					break;
				case Direction.LEFT:
					currentImage = ImageProvider.ganondorfWalkLeft[indexCurrentImage];
					break;
				case Direction.RIGHT:
					currentImage = ImageProvider.ganondorfWalkRight[indexCurrentImage];
					break;
				default:
					currentImage = ImageProvider.ganondorfWalkDown[indexCurrentImage];
					break;
			}

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
			super.move();
		}
		setDeltaX(0);
		setDeltaY(0);
		// setState(0);

	}

	public void startGanondorf()
	{
		manager = new Thread(this, "Ganondorf Manager");
		manager.start();
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.gameRunning)
		{
			if (!StaticConstantGame.paused)
			{
				if (!sleeping)
				{
					update();
					sleeping = true;
				}
				else
				{
					sleeping = false;
				}
			}
			try
			{
				Thread.sleep(StaticConstantGame.timeGanondorf);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
	}

	@Override
	public void update()
	{
		if (logicUpdate())
		{
			switch (getState())
			{
				case CharacterState.STAND:
					break;
				case CharacterState.PHYSIC_ATTACK:
					SoundManager.playSound(SoundManager.WARRIOR_ATTACK_SWORD, 1,
							SoundManager.EFFECT);

					try
					{
						Thread.sleep(150);
					}
					catch (final InterruptedException e)
					{
						// do nothing
					}
					physicAttackAnimation(StaticConstantURL.GANONDORF_ATTACK_UP.length);
					setState(CharacterState.STAND);
					break;
				case CharacterState.DISTANCE_ATTACK:
					switch (getCurrentDirection().getTypeDirection())
					{
						case Direction.UP:
							currentImage = ImageProvider.ganondorfWalkUp[0];
							break;
						case Direction.DOWN:
							currentImage = ImageProvider.ganondorfWalkDown[0];
							break;
						case Direction.LEFT:
							currentImage = ImageProvider.ganondorfWalkLeft[0];
							break;
						case Direction.RIGHT:
							currentImage = ImageProvider.ganondorfWalkRight[0];
							break;
						default:
							currentImage = ImageProvider.ganondorfWalkDown[0];
							break;
					}
					new AdvancedAttackSphere2D(this);
					try
					{
						Thread.sleep(500);
					}
					catch (final InterruptedException e)
					{
						// do nothing
					}
					setState(CharacterState.STAND);
					break;
				case CharacterState.WALK:
					SoundManager.playSound(SoundManager.WARRIOR_WALK, 1, SoundManager.EFFECT);
					moveAnimation();
					break;
				default:
					setState(CharacterState.STAND);
					break;
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
