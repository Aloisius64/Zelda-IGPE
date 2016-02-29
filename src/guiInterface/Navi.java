package guiInterface;

import gameLogic.Drawable;

import java.awt.Container;
import java.awt.Graphics;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURL;

import common.ImageProvider;

public class Navi implements Drawable, Runnable
{
	private int xPosition;
	private int yPosition;
	private int indexCurrentImage = 0;
	private Thread manager;
	private JPanel panel;

	public Navi()
	{
		setxPosition(100);
		setyPosition(100);
		startNavi();
	}

	public Navi(int xPosition, int yPosition)
	{
		setxPosition(xPosition);
		setyPosition(yPosition);
		startNavi();
	}

	public void startNavi()
	{
		manager = new Thread(this, "Navi Manager");
		manager.start();
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.navi[indexCurrentImage], getxPosition(), getyPosition(),
				null);
	}

	public int getxPosition()
	{
		return xPosition;
	}

	public int getyPosition()
	{
		return yPosition;
	}

	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}

	public JPanel getPanel()
	{
		return panel;
	}

	public void setPanel(JPanel panel)
	{
		this.panel = panel;
	}

	@Override
	public void run()
	{
		while (true)
		{
			indexCurrentImage = (indexCurrentImage + 1) % StaticConstantURL.NAVI.length;
			if (StaticConstantGame.mainFrame != null)
			{
				Container contentPane = StaticConstantGame.mainFrame.getContentPane();
				if (contentPane != null)
				{
					contentPane.repaint();
				}
			}
			try
			{
				Thread.sleep(125);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

	}
}
