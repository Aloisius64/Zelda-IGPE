package threadState;

import staticConstant.StaticConstantGame;

public class MessageBoardManager implements Runnable
{
	private Thread thread;

	public MessageBoardManager()
	{
		startMessageBoradManager();
	}

	public void startMessageBoradManager()
	{
		thread = new Thread(this, "MessageBoardManager");
		thread.start();
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.gameRunning)
		{
			StaticConstantGame.currentMessage = " ";
			try
			{
				Thread.sleep(30000);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

	}
}
