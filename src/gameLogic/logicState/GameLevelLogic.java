package gameLogic.logicState;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.Hero;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.FlameTower;
import gameLogic.object.Platform;
import gameLogic.world.ConcreteWorld;
import guiInterface.startScreen.EndLevelPanel;

import java.io.FileNotFoundException;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;

import common.SoundManager;

public class GameLevelLogic extends AbstractGameLogic
{

	public GameLevelLogic()
	{
		super();
	}

	private void handleEndLevel()
	{
		StaticConstantGame.gameManager.manageStatistic();
		StaticConstantGame.gameManager.addScoreToPlayer();
		StaticConstantGame.gameManager.manageNextLevel();
		manageExtra();
		try
		{
			StaticConstantGame.profileManager.close();
		}
		catch (FileNotFoundException exception)
		{
			exception.printStackTrace();
		}

		SoundManager.stop();

		if (!StaticConstantGame.enable3D)
		{
			EndLevelPanel endLevelPanel = new EndLevelPanel(StaticConstantGame.mainFrame, true);
			StaticConstantGame.mainFrame.switchPanel(endLevelPanel);
		}

		// if (StaticConstantGame.wiimoteActive)
		// {
		// StaticConstantGame.wiimote
		// .removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		// StaticConstantGame.wiimoteListener = new WiiMoteMenuListener();
		// StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		// StaticConstantGame.wiimote.activateIRTRacking();
		// }

		StaticConstantGame.gameRunning = false;
		System.gc();
	}

	private void manageExtra()
	{
		switch (StaticConstantGame.typeWorldChoose)
		{
			case 1:
				extraLevelOne();
				break;
			case 2:
				extraLevelTwo();
				break;
			case 3:
				extraLevelThree();
				break;
			case 4:
				extraLevelFour();
				break;
			case 5:
				extraLevelFive();
				break;
			case 6:
				extraLevelSix();
				break;
			case 7:
				extraLevelSeven();
				break;
			default:
				break;
		}
	}

	private void extraLevelSeven()
	{
		if (StaticConstantGame.gameManager.getTreasureOpen() > 5)
		{
			StaticConstantGame.currentProfile.unlockExtra(6);
		}

		if (StaticConstantGame.gameManager.getMagicSphereCollected() == 0)
		{
			StaticConstantGame.currentProfile.unlockExtra(7);
		}
	}

	private void extraLevelSix()
	{
		int initTime = StaticConstantGame.gameManager.getInitTime();
		int currentTimeMillis = (int) System.currentTimeMillis() / 1000;

		if (currentTimeMillis - initTime <= 180)
		{
			StaticConstantGame.currentProfile.unlockExtra(5);
		}
	}

	private void extraLevelFive()
	{
		int initTime = StaticConstantGame.gameManager.getInitTime();
		int currentTimeMillis = (int) System.currentTimeMillis() / 1000;

		if (currentTimeMillis - initTime <= 120)
		{
			StaticConstantGame.currentProfile.unlockExtra(4);
		}
	}

	private void extraLevelFour()
	{
		if (StaticConstantGame.gameManager.getHeartsCollected() <= 2)
		{
			StaticConstantGame.currentProfile.unlockExtra(3);
		}
	}

	private void extraLevelThree()
	{
		boolean allOpen = true;

		for (int i = 0; i < 80 && allOpen; i++)
		{
			for (int j = 0; j < 80 && allOpen; j++)
			{
				AbstractUtilityObject object = StaticConstantGame.gameManager.getConcreteWorld()
						.getCellObjectWorld(i, j);
				if (object instanceof FlameTower)
				{
					allOpen = ((FlameTower) object).isOpen();
				}
			}
		}

		if (allOpen)
		{
			StaticConstantGame.currentProfile.unlockExtra(2);
		}

	}

	private void extraLevelTwo()
	{
		boolean allKilled = true;

		for (int i = 0; i < 60 && allKilled; i++)
		{
			for (int j = 0; j < 60 && allKilled; j++)
			{
				AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
						.getCellCharacterWorld(i, j);
				if (character instanceof AbstractEnemy)
				{
					allKilled = false;
				}
			}
		}

		if (allKilled)
		{
			StaticConstantGame.currentProfile.unlockExtra(1);
		}
	}

	private void extraLevelOne()
	{
		if (StaticConstantGame.gameManager.getHero().getScore() > 3500)
		{
			StaticConstantGame.currentProfile.unlockExtra(0);
		}
	}

	private void handleGameOver()
	{
		StaticConstantGame.gameManager.manageStatistic();
		try
		{
			StaticConstantGame.profileManager.close();
		}
		catch (FileNotFoundException exception)
		{
			exception.printStackTrace();
		}

		SoundManager.stop();

		SoundManager.playSound(SoundManager.HERO_DIED, 1, SoundManager.EFFECT);

		StaticConstantGame.gameRunning = false;

		try
		{
			Thread.sleep(1500);
		}
		catch (InterruptedException exception)
		{
			exception.printStackTrace();
		}

		if (!StaticConstantGame.enable3D)
		{
			EndLevelPanel endLevelPanel = new EndLevelPanel(StaticConstantGame.mainFrame, false);
			StaticConstantGame.mainFrame.switchPanel(endLevelPanel);
		}

		// if (StaticConstantGame.wiimoteActive)
		// {
		// StaticConstantGame.wiimote
		// .removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		// StaticConstantGame.wiimoteListener = new WiiMoteMenuListener();
		// StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		// StaticConstantGame.wiimote.activateIRTRacking();
		// }
		System.gc();
	}

	@Override
	public boolean isEndLevel()
	{
		AbstractUtilityObject endPoint = StaticConstantGame.gameManager.getConcreteWorld()
				.getFinalPoint();

		if (endPoint == null)
		{
			return false;
		}

		Hero hero = StaticConstantGame.gameManager.getHero();
		return (hero.getX() == endPoint.getX()) && (hero.getY() == endPoint.getY());
	}

	@Override
	public boolean isGameOver()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		return hero.getHearts() <= 0;
	}

	@Override
	public void update()
	{
		if (StaticConstantGame.enable3D)
		{
			if (isGameOver())
			{
				handleGameOver();
			}
			else if (isEndLevel())
			{
				handleEndLevel();
			}

		}
		else
		{
			if (!isGameOver())
			{
				if (!isEndLevel())
				{
					ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
					for (int i = concreteWorld.getxPrint(); i < concreteWorld.getxPrint()
							+ StaticConstantWorld.X_DIMENSION; i++)
					{
						for (int j = concreteWorld.getyPrint(); j < concreteWorld.getyPrint()
								+ StaticConstantWorld.Y_DIMENSION; j++)
						{
							AbstractUtilityObject object = concreteWorld.getCellObjectWorld(i, j);
							if (!(object instanceof Platform))
							{
								object.update();
							}
						}
					}
				}
				else
				{
					handleEndLevel();
				}
			}
			else
			{
				handleGameOver();
			}
		}
	}
}
