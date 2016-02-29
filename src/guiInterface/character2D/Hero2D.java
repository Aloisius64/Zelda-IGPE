package guiInterface.character2D;

import gameLogic.character.CharacterState;
import gameLogic.character.Hero;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import gameLogic.object.FlameTower;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.ConcreteWorld;

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

/**
 * <p>
 * <b>Classe</b> che rappresenta il/i giocatore/i
 * </p>
 * 
 * @author Aloisius
 * 
 */
public class Hero2D extends Hero implements Runnable, Character2D
{

	private int deltaY = 0;
	private int deltaX = 0;
	private Thread animation;
	private JPanel panel;
	private int deltaWidth;
	private int deltaHeight;
	private Image currentImage = null;
	private int indexCurrentImage = 0;
	private int counter;

	public Hero2D()
	{
		super();
		currentImage = ImageProvider.hero_front;
	}

	public Hero2D(String name, final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		start();
		switch (getCurrentDirection().getTypeDirection())
		{
			case Direction.UP:
				currentImage = ImageProvider.hero_back;
				break;
			case Direction.DOWN:
				currentImage = ImageProvider.hero_front;
				break;
			case Direction.LEFT:
				currentImage = ImageProvider.hero_left;
				break;
			case Direction.RIGHT:
				currentImage = ImageProvider.hero_right;
				break;
			default:
				currentImage = ImageProvider.hero_front;
				break;
		}
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(currentImage, xPosition + (width / 4) + getDeltaY() - 28, yPosition
				+ (height / 4) + getDeltaX() - 28, null);
	}

	public Image getCurrentImage()
	{
		return currentImage;
	}

	public void moveAnimation()
	{
		int animationSteps = StaticConstantURL.HERO_WALK_BACK.length;
		int width = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int height = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;

		int nextStepX = (width / animationSteps) + 1;
		int nextStepY = height / animationSteps;
		int typeDirection = getCurrentDirection().getTypeDirection();

		boolean falseAnimation = false;

		int xTarget = getX() + getCurrentDirection().getXSelected();
		int yTarget = getY() + getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(xTarget, yTarget);
		AbstractUtilityObject utilityObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(xTarget, yTarget);

		if (sceneObject.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
		{
			falseAnimation = true;
		}

		if (utilityObject instanceof FlameTower)
		{
			falseAnimation = true;
		}

		if (utilityObject instanceof Button)
		{
			falseAnimation = true;
		}

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

		// / ATTENTO
		indexCurrentImage = (indexCurrentImage + 1) % animationSteps;

		switch (typeDirection)
		{
			case Direction.UP:
				currentImage = ImageProvider.heroWalkBack[indexCurrentImage];
				break;
			case Direction.DOWN:
				currentImage = ImageProvider.heroWalkFront[indexCurrentImage];
				break;
			case Direction.LEFT:
				currentImage = ImageProvider.heroWalkLeft[indexCurrentImage];
				break;
			case Direction.RIGHT:
				currentImage = ImageProvider.heroWalkRight[indexCurrentImage];
				break;
			default:
				currentImage = ImageProvider.heroWalkFront[indexCurrentImage];
				break;
		}

		getPanel().repaint();
		try
		{
			Thread.sleep(35);
		}
		catch (final InterruptedException e)
		{
			// do nothing
		}

		positionUpdate(falseAnimation, width, height);
		manageChangeScreen();
	}

	public void physicAttackAnimation(int numberSteps)
	{
		for (int i = 0; i < numberSteps; i++)
		{
			indexCurrentImage = i;
			switch (getCurrentDirection().getTypeDirection())
			{
				case Direction.UP:
					currentImage = ImageProvider.heroPhysicAttackBack[indexCurrentImage];
					break;
				case Direction.DOWN:
					currentImage = ImageProvider.heroPhysicAttackFront[indexCurrentImage];
					break;
				case Direction.LEFT:
					currentImage = ImageProvider.heroPhysicAttackLeft[indexCurrentImage];
					break;
				case Direction.RIGHT:
					currentImage = ImageProvider.heroPhysicAttackRight[indexCurrentImage];
					break;
				default:
					currentImage = ImageProvider.heroPhysicAttackBack[indexCurrentImage];
					break;
			}
			getPanel().repaint();
			try
			{
				Thread.sleep(25);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
		attack(0);
		setState(CharacterState.STAND);
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.gameRunning)
		{
			update();
			try
			{
				Thread.sleep(5);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
	}

	public void hited()
	{
		Image tmpImage = currentImage;
		for (int i = 0; i < 5; i++)
		{
			if (i % 2 == 0)
			{
				currentImage = null;
			}
			else
			{
				currentImage = tmpImage;
			}
			getPanel().repaint();
			try
			{
				Thread.sleep(20);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
		currentImage = tmpImage;
		setState(CharacterState.STAND);
	}

	public void fall()
	{
		for (int i = 1; i < 5; i++)
		{
			setDeltaHeight(getDeltaHeight() - (5));
			setDeltaX(getDeltaX() + 1);
			setDeltaWidth(getDeltaWidth() - (5));
			setDeltaY(getDeltaY() + 1);
			getPanel().repaint();
			try
			{
				Thread.sleep(200);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}
		setCurrentDirection(getCurrentDirection().getReverse());
		move();
		setDeltaHeight(0);
		setDeltaWidth(0);
		modifyHearts(-1);
		setState(CharacterState.HITTED);
	}

	public void setCurrentImage(Image currentImage)
	{
		this.currentImage = currentImage;
	}

	@Override
	public void update()
	{
		if (!StaticConstantGame.paused)
		{

			if (getHearts() <= 2)
			{
				if (counter % 50 == 0)
					SoundManager.playSound(SoundManager.HERO_LOW_LIFE, 1, SoundManager.EFFECT);
				counter++;
			}
			else
			{
				counter = 0;
			}

			switch (getState())
			{
				case CharacterState.WALK:
					SoundManager.playSound(SoundManager.HERO_WALK, 1, SoundManager.EFFECT);
					moveAnimation();
					break;
				case CharacterState.PHYSIC_ATTACK:
					SoundManager.playSound(SoundManager.HERO_FALLING, 1, SoundManager.EFFECT);
					physicAttackAnimation(StaticConstantURL.HERO_PHYSIC_ATTACK_BACK.length);
					// setState(CharacterState.STAND);
					break;
				case CharacterState.DISTANCE_ATTACK:
					int value = attack(1);
					if (value == 2)
					{
						new AttackSphere2D(this);
					}
					setState(CharacterState.STAND);
					break;
				case CharacterState.FALLING:
					SoundManager.playSound(SoundManager.HERO_FALLING, 1, SoundManager.EFFECT);

					fall();
					break;
				case CharacterState.HITTED:
					SoundManager.playSound(SoundManager.HERO_HITTED, 1, SoundManager.EFFECT);

					hited();
					moveAnimation();
					break;
				case CharacterState.SPECIAL_ATTACK:
					if (getMagicEnergy() > 3)
					{
						SoundManager.playSound(SoundManager.HERO_CHARGING, 1, SoundManager.EFFECT);
						SoundManager.playSound(SoundManager.HERO_SPECIAL_ATTACK, 1,
								SoundManager.EFFECT);

						modifyMagicEnergy(-3);
						attackTwo();
						setState(CharacterState.STAND);
					}
					break;
				default:
					switch (getCurrentDirection().getTypeDirection())
					{
						case Direction.UP:
							currentImage = ImageProvider.hero_back;
							break;
						case Direction.DOWN:
							currentImage = ImageProvider.hero_front;
							break;
						case Direction.LEFT:
							currentImage = ImageProvider.hero_left;
							break;
						case Direction.RIGHT:
							currentImage = ImageProvider.hero_right;
							break;
						default:
							currentImage = ImageProvider.hero_front;
							break;
					}
					break;
			}
		}
	}

	public void positionUpdate(boolean falseAnimation, int width, int height)
	{
		// MODIFICARE I DELTA
		if (getDeltaX() > (height - 20))
		{
			if (!falseAnimation)
			{
				move();
				setDeltaX(getDeltaX() - height);
			}

			indexCurrentImage = 0;

		}
		else if (getDeltaX() < (-height + 20))
		{
			if (!falseAnimation)
			{
				move();
				setDeltaX(getDeltaX() + height);
			}

			indexCurrentImage = 0;
		}

		if (getDeltaY() > (width - 20))
		{
			if (!falseAnimation)
			{
				move();
				setDeltaY(getDeltaY() - width);
			}

			indexCurrentImage = 0;

		}
		else if (getDeltaY() < (-width + 20))
		{
			if (!falseAnimation)
			{
				move();
				setDeltaY(getDeltaY() + width);
			}

			indexCurrentImage = 0;
		}

	}

	public int getDeltaWidth()
	{
		return deltaWidth;
	}

	public void setDeltaWidth(int deltaWidth)
	{
		this.deltaWidth = deltaWidth;
	}

	public int getDeltaHeight()
	{
		return deltaHeight;
	}

	public void setDeltaHeight(int deltaHeight)
	{
		this.deltaHeight = deltaHeight;
	}

	public void start()
	{
		animation = new Thread(this);
		animation.start();
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

	private void manageChangeScreen()
	{
		// ///////////// GESTIONE CAMBIO SCHERMO //////////////
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		int xPrint = concreteWorld.getxPrint();
		int yPrint = concreteWorld.getyPrint();
		boolean changed = false;

		final int time = 80;

		if (getX() < xPrint)
		{

			StaticConstantGame.paused = true;
			// getConcreteWorld().moveToUp();
			for (int i = 1; i <= StaticConstantWorld.X_DIMENSION; i++)
			{
				concreteWorld.moveToUp(1);
				getPanel().repaint();
				try
				{
					Thread.sleep(time);
				}
				catch (final InterruptedException e)
				{
					// ignore
				}
			}
			changed = true;
		}
		if (getX() >= (xPrint + StaticConstantWorld.X_DIMENSION))
		{

			StaticConstantGame.paused = true;
			for (int i = 1; i <= StaticConstantWorld.X_DIMENSION; i++)
			{
				concreteWorld.moveToDown(1);
				getPanel().repaint();
				try
				{
					Thread.sleep(time);
				}
				catch (final InterruptedException e)
				{
					// ignore
				}
			}
			changed = true;
		}
		if (getY() >= (yPrint + StaticConstantWorld.Y_DIMENSION))
		{

			StaticConstantGame.paused = true;
			for (int i = 1; i <= StaticConstantWorld.X_DIMENSION; i++)
			{
				concreteWorld.moveToRight(1);
				getPanel().repaint();

				try
				{
					Thread.sleep(time);
				}
				catch (final InterruptedException e)
				{
					// ignore
				}
			}
			changed = true;
		}
		if (getY() < yPrint)
		{

			StaticConstantGame.paused = true;
			for (int i = 1; i <= StaticConstantWorld.X_DIMENSION; i++)
			{
				concreteWorld.moveToLeft(1);
				getPanel().repaint();
				try
				{
					Thread.sleep(time);
				}
				catch (final InterruptedException e)
				{
					// ignore
				}
			}
			changed = true;
		}

		StaticConstantGame.paused = false;
		if (changed)
		{
			StaticConstantGame.sphereManager.getSpheres().clear();
		}
	}

	@Override
	public int attackTwo()
	{
		int result = super.attackTwo();
		Image[] animationTwist = new Image[11];
		animationTwist[0] = ImageProvider.heroPhysicAttackLeft[0];
		animationTwist[1] = ImageProvider.heroPhysicAttackLeft[3];
		animationTwist[2] = ImageProvider.heroPhysicAttackFront[0];
		animationTwist[3] = ImageProvider.heroPhysicAttackFront[1];
		animationTwist[4] = ImageProvider.heroPhysicAttackFront[2];
		animationTwist[5] = ImageProvider.heroPhysicAttackRight[0];
		animationTwist[6] = ImageProvider.heroPhysicAttackRight[3];
		animationTwist[7] = ImageProvider.heroPhysicAttackBack[0];
		animationTwist[8] = ImageProvider.heroPhysicAttackBack[1];
		animationTwist[9] = ImageProvider.heroPhysicAttackBack[2];
		animationTwist[10] = ImageProvider.heroPhysicAttackBack[4];

		for (int i = 0; i < animationTwist.length; i++)
		{
			currentImage = animationTwist[i];
			panel.repaint();
			try
			{
				Thread.sleep(25);
			}
			catch (final InterruptedException e)
			{
				// do nothing
			}
		}

		setState(CharacterState.STAND);
		return result;
	}
}