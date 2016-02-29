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
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiimoteListenerCreditsPanel;

public class CreditsPanel extends JPanel implements Runnable
{
	private static MainFrame mainFrame;
	private static MenuPanel panel;
	private Image background;
	private Image text;
	private int positionText;
	private static boolean animation;
	private Thread thread;

	private void start()
	{
		thread = new Thread(this);
		thread.start();
	}

	public CreditsPanel(MainFrame frame, MenuPanel panel)
	{
		this.setMainFrame(frame);
		this.setPanel(panel);
		this.animation = true;
		this.positionText = 768;

		try
		{
			background = ImageIO.read(new File("Images/MenuPanel/CreditsPanel/background-01.png"));
			text = ImageIO.read(new File("Images/MenuPanel/CreditsPanel/text.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella).");
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
						CreditsPanel.animation = false;
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = CreditsPanel.getMainFrame();
						mainFrame.switchPanel(CreditsPanel.getPanel());
						break;
					}
				}
				repaint();
			}
		});
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
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawImage(text, 138, positionText, null);

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	@Override
	public void run()
	{
		while (animation)
		{
			try
			{
				Thread.sleep(60);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
			positionText -= 2;
			repaint();

			if (positionText == -1500)
				animation = false;
		}
		getMainFrame().switchPanel(getPanel());
		getPanel().playThemeSong();
	}

	public static void setMainFrame(MainFrame mainFrame)
	{
		CreditsPanel.mainFrame = mainFrame;
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static void setPanel(MenuPanel panel)
	{
		CreditsPanel.panel = panel;
	}

	public static MenuPanel getPanel()
	{
		return panel;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiimoteListenerCreditsPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
