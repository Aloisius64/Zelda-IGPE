package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import staticConstant.StaticConstantGame;

public class LoadingPanel extends JPanel implements Runnable
{
	private Image background;
	private static MainFrame mainFrame;
	private static int refreshState;
	private Thread thread;

	private void start()
	{
		thread = new Thread(this);
		thread.start();

	}

	public LoadingPanel(MainFrame mainFrame)
	{
		StaticConstantGame.loading = true;
		this.setMainFrame(mainFrame);
		refreshState = 1;
		try
		{
			background = ImageIO.read(new File("Images/MenuPanel/Loading/Loading-01.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
		}
		start();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		if (background == null)
		{
			return;
		}

		refreshBackground();
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	private void refreshBackground()
	{
		switch (refreshState)
		{
			case 1:
				try
				{
					background = ImageIO.read(new File("Images/MenuPanel/Loading/Loading1-01.png"));
				}
				catch (IOException e)
				{
					System.out
							.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
				}
				break;
			case 5:
				try
				{
					background = ImageIO.read(new File("Images/MenuPanel/Loading/Loading2-01.png"));
				}
				catch (IOException e)
				{
					System.out
							.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
				}
				break;
			case 10:
				try
				{
					background = ImageIO.read(new File("Images/MenuPanel/Loading/Loading3-01.png"));
				}
				catch (IOException e)
				{
					System.out
							.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
				}
				break;
			case 15:
				try
				{
					background = ImageIO.read(new File("Images/MenuPanel/Loading/Loading4-01.png"));
				}
				catch (IOException e)
				{
					System.out
							.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
				}
				break;
		}
		refreshState++;
		if (refreshState > 15)
			refreshState = 0;
	}

	public static void setMainFrame(MainFrame mainFrame)
	{
		LoadingPanel.mainFrame = mainFrame;
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.loading)
		{
			try
			{
				Thread.sleep(40);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
			repaint();
		}
	}
}
