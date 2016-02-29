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
import staticConstant.StaticConstantURLMenuButton;

import common.SoundManager;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMultiPlayerPanel;

public class MultiPlayerPanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static PlayPanel playPanel;
	private static int menuButtonSelected;

	// Pulsanti Menù Gioca
	private static MenuButton creaPartitaButton;
	private static MenuButton uniscitiButton;

	public MultiPlayerPanel(MainFrame mainFrame, final PlayPanel playPanel)
	{
		this.setMainFrame(mainFrame);
		this.setPlayPanel(playPanel);
		setMenuButtonSelected(1);
		try
		{
			background = ImageIO.read(new File(
					"Images/MenuPanel/PlayPanel/MultiPlayerPanel/background.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default.");
		}

		configureWiiMote();

		this.addKeyListener(new KeyAdapter()
		{

			private void switchPanelto()
			{
				int buttonSelected = MultiPlayerPanel.getMenuButtonSelected();
				MainFrame mainFrame = MultiPlayerPanel.getMainFrame();

				switch (buttonSelected)
				{
					case 1:
						mainFrame.switchPanel(new CreateConnectionPanel(mainFrame,
								MultiPlayerPanel.this));
						break;
					case 2:
						mainFrame
								.switchPanel(new ConnectionPanel(mainFrame, MultiPlayerPanel.this));
						break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_DOWN:
					{
						MultiPlayerPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						MultiPlayerPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_LEFT:
					{
						MultiPlayerPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_UP:
					{
						MultiPlayerPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = MultiPlayerPanel.getMainFrame();
						mainFrame.switchPanel(MultiPlayerPanel.getPlayPanel());
						break;
					}
					case KeyEvent.VK_ENTER:
					{
						SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
						switchPanelto();
						break;
					}
				}
				repaint();
			}
		});
		creaPartitaButton = new MenuButton(StaticConstantURLMenuButton.CREA_PARTITA_SELECTED, true);
		uniscitiButton = new MenuButton(StaticConstantURLMenuButton.UNISCITI_SELECTED);
	}

	public static void changeButtonISSelected(boolean change)
	{
		if (change)
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		switch (getMenuButtonSelected())
		{
			case 1:
			{
				creaPartitaButton.setSelected(change);
				break;
			}
			case 2:
			{
				uniscitiButton.setSelected(change);
				break;
			}
		}
	}

	public static void changeButtonSelected(int change)
	{
		if ((change == 1) || (change == -1))
		{
			if ((getMenuButtonSelected() == 1) && (change == -1))
			{
				return;
			}
			if ((getMenuButtonSelected() == 2) && (change == 1))
			{
				return;
			}
			changeButtonISSelected(false);
			setMenuButtonSelected(getMenuButtonSelected() + change);
			changeButtonISSelected(true);
		}
	}

	public static int getMenuButtonSelected()
	{
		return menuButtonSelected;
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static PlayPanel getPlayPanel()
	{
		return playPanel;
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

		creaPartitaButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		uniscitiButton.paintMenuButton(g, this.getWidth(), this.getHeight());

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public void setMainFrame(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}

	public void setPlayPanel(PlayPanel playPanel)
	{
		this.playPanel = playPanel;
	}

	public static void setMenuButtonSelected(int menuButtonSelected)
	{
		MultiPlayerPanel.menuButtonSelected = menuButtonSelected;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerMultiPlayerPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
