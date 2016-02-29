package threadState;

import gameLogic.object.AbstractUtilityObject;
import gameLogic.world.ConcreteWorld;
import guiInterface.object2D.Platform2D;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;

public class PlatformManager implements Runnable
{

	Thread manager;

	public PlatformManager()
	{
		manager = new Thread(this, "Object Manager");
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
				for (int i = concreteWorld.getxPrint(); i < concreteWorld.getxPrint()
						+ StaticConstantWorld.X_DIMENSION; i++)
				{
					for (int j = concreteWorld.getyPrint(); j < concreteWorld.getyPrint()
							+ StaticConstantWorld.Y_DIMENSION; j++)
					{
						AbstractUtilityObject object = concreteWorld.getCellObjectWorld(i, j);
						if (object instanceof Platform2D)
						{
							((Platform2D) object).update();
							try
							{
								Thread.sleep(150);
							}
							catch (InterruptedException exception)
							{
								exception.printStackTrace();
							}
						}
					}
				}
			}
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}

		}
	}
}
