package threadState;

import staticConstant.StaticConstantGame;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;

public class WorldUpdater implements Runnable
{
	private World world;
	private Thread worldManager;

	public WorldUpdater(World world)
	{
		this.world = world;
		startUpdater();
	}

	public void startUpdater()
	{
		worldManager = new Thread(this, "WorldUpdater");
		worldManager.start();
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.gameRunning)
		{
			((ConcreteWorld) world).update();
			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}
	}

	public World getWorld()
	{
		return world;
	}

	public void setWorld(World world)
	{
		this.world = world;
	}

}
