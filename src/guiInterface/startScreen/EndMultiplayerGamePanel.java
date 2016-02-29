package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import staticConstant.StaticConstantGame;

import common.Sound;
import common.SoundManager;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerEndMultiPlayerGamePanel;

public class EndMultiplayerGamePanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static MultiPlayerPanel panel;

	public EndMultiplayerGamePanel(int end, MainFrame mainFrame, MultiPlayerPanel panel)
	{
		this.setMainFrame(mainFrame);
		this.setPanel(panel);

		switch (end)
		{
			case 1:
				try
				{
					background = ImageIO
							.read(new File(
									"Images/MenuPanel/PlayPanel/MultiPlayerPanel/WinMultiplayerGame-01.png"));
				}
				catch (IOException e)
				{
					System.out
							.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
				}
				break;
			case 2:
				try
				{
					background = ImageIO
							.read(new File(
									"Images/MenuPanel/PlayPanel/MultiPlayerPanel/LoseMultiplayerGame-01.png"));
				}
				catch (IOException e)
				{
					System.out
							.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
				}
				break;
		}

		configureWiiMote();
		addKeyListener(new KeyAdapter()
		{
			Sound buttonSound;

			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = EndMultiplayerGamePanel.getMainFrame();
						mainFrame.switchPanel(EndMultiplayerGamePanel.getPanel());
						break;
					}
				}
				repaint();
			}
		});
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		if (background == null)
		{
			return;
		}

		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public void setMainFrame(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public void setPanel(MultiPlayerPanel panel)
	{
		this.panel = panel;
	}

	public static MultiPlayerPanel getPanel()
	{
		return panel;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerEndMultiPlayerGamePanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
