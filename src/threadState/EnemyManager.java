package threadState;

import gameLogic.character.AbstractCharacter;
import gameLogic.world.ConcreteWorld;
import guiInterface.character2D.Ganondorf2D;
import guiInterface.character2D.Hero2D;
import staticConstant.StaticConstantGame;

public class EnemyManager implements Runnable
{

	private final Thread manager;
	private final int initX;
	private final int initY;
	private final int dimensionX;
	private final int dimensionY;

	public EnemyManager(final int initX, final int initY, final int dimensionX, final int dimensionY)
	{
		this.initX = initX;
		this.initY = initY;
		this.dimensionY = dimensionY;
		this.dimensionX = dimensionX;
		manager = new Thread(this, "Enemy Manager");
		manager.start();
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.gameRunning)
		{
			if (!StaticConstantGame.paused)
			{
				ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
				for (int i = (concreteWorld.getxPrint() + initX); i < (concreteWorld.getxPrint()
						+ initX + dimensionX); i++)
				{
					for (int j = (concreteWorld.getyPrint() + initY); j < (concreteWorld
							.getyPrint() + dimensionY + initY); j++)
					{
						AbstractCharacter character = concreteWorld.getCellCharacterWorld(i, j);
						if (!(character instanceof Hero2D) && !(character instanceof Ganondorf2D))
						{
							character.update();
						}
					}
				}
			}
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

	}

	public Thread getManager()
	{
		return manager;
	}

	public int getInitX()
	{
		return initX;
	}

	public int getInitY()
	{
		return initY;
	}

	public int getDimension()
	{
		return dimensionX;
	}
}
