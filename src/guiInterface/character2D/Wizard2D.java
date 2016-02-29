package guiInterface.character2D;

import common.Direction;
import common.ImageProvider;
import common.SoundManager;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.CharacterState;
import gameLogic.character.EmptyCharacter;
import gameLogic.character.Hero;
import gameLogic.character.Wizard;
import gameLogic.scene.AbstractObjectScene;
import inteligenceAI.AbstractAI;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantURL;
import staticConstant.StaticConstantWorld;

public class Wizard2D extends Wizard implements Character2D
{

	private int deltaY = 0;
	private int deltaX = 0;
	private JPanel panel;
	private Image currentImage = null;
	private final int animationStep = 4;

	public Wizard2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setDeltaX(0);
		setDeltaY(0);
	}

	public Wizard2D(final int xPosition, final int yPosition, AbstractAI inteligence)
	{
		super(xPosition, yPosition, inteligence);
		setDeltaX(0);
		setDeltaY(0);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		switch (getCurrentDirection().getTypeDirection())
		{
			case Direction.UP:
				currentImage = ImageProvider.wizardUp;
				break;
			case Direction.DOWN:
				currentImage = ImageProvider.wizardDown;
				break;
			case Direction.LEFT:
				currentImage = ImageProvider.wizardLeft;
				break;
			case Direction.RIGHT:
				currentImage = ImageProvider.wizardRight;
				break;
			default:
				currentImage = ImageProvider.wizardDown;
		}
		graphics.drawImage(currentImage, xPosition + (50 / 4) + getDeltaY(), yPosition + (50 / 4)
				+ getDeltaX(), width - 5, height + 25, null);

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
			switch (typeDirection)
			{
				case Direction.UP:
					currentImage = ImageProvider.wizardUp;
					break;
				case Direction.DOWN:
					currentImage = ImageProvider.wizardDown;
					break;
				case Direction.LEFT:
					currentImage = ImageProvider.wizardLeft;
					break;
				case Direction.RIGHT:
					currentImage = ImageProvider.wizardRight;
					break;
				default:
					currentImage = ImageProvider.wizardDown;
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
					SoundManager
							.playSound(SoundManager.WIZARD_ATTACK_SWORD, 1, SoundManager.EFFECT);

					int value = attack(1);
					if (value == 2)
					{
						new AttackSphere2D(this);
					}
					setState(CharacterState.STAND);
				}
					break;
				case CharacterState.DISTANCE_ATTACK:
				{
					int value = attack(1);
					if (value == 2)
					{
						new AdvancedAttackSphere2D(this);
					}
					setState(CharacterState.STAND);
				}
					break;
				case CharacterState.WALK:
				{
					SoundManager.playSound(SoundManager.WIZARD_WALK, 1, SoundManager.EFFECT);

					moveAnimation();
				}
					break;
				default:

					break;
			}
			try
			{
				Thread.sleep(StaticConstantGame.timeWizard);
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
