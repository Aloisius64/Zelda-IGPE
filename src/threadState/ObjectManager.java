package threadState;

import javax.swing.JPanel;
import staticConstant.StaticConstantGame;

public class ObjectManager implements Runnable
{
	Thread manager;
	private final JPanel gamePanel;

	public ObjectManager(JPanel gamePanel)
	{
		this.gamePanel = gamePanel;
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
				StaticConstantGame.gameManager.update();
				gamePanel.repaint();
			}
			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}
	}

	public JPanel getGamePanel()
	{
		return gamePanel;
	}
}