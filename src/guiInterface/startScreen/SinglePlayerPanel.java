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

import common.Sound;
import common.SoundManager;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerSinglePlayerPanel;

import zelda.Main;

public class SinglePlayerPanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static PlayPanel playPanel;
	private static int menuButtonSelected;

	// Pulsanti Menù Gioca
	private static MenuButton nuovaPartitaButton;
	private static MenuButton caricaPartitaButton;

	public SinglePlayerPanel(MainFrame mainFrame, final PlayPanel playPanel)
	{
		this.setMainFrame(mainFrame);
		this.setPlayPanel(playPanel);
		setMenuButtonSelected(1);
		try
		{
			background = ImageIO.read(new File(
					"Images/MenuPanel/PlayPanel/SinglePlayerPanel/background.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default.");
		}

		configureWiiMote();

		this.addKeyListener(new KeyAdapter()
		{
			Sound menuButtonSound;

			private void switchPanelto()
			{
				int buttonSelected = SinglePlayerPanel.getMenuButtonSelected();
				MainFrame mainFrame = SinglePlayerPanel.getMainFrame();

				switch (buttonSelected)
				{
					case 1:
						mainFrame.switchPanel(new ChooseLevelPanel(mainFrame,
								SinglePlayerPanel.this));
						StaticConstantGame.currentProfile.setEmptyProfile();
						break;
					case 2:
						mainFrame.switchPanel(new ChooseLevelPanel(mainFrame,
								SinglePlayerPanel.this));
						break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				menuButtonSound = new Sound("Suoni/Click.wav", true);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_DOWN:
					{
						SinglePlayerPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						SinglePlayerPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_LEFT:
					{
						SinglePlayerPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_UP:
					{
						SinglePlayerPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = SinglePlayerPanel.getMainFrame();
						mainFrame.switchPanel(SinglePlayerPanel.getPlayPanel());
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
		nuovaPartitaButton = new MenuButton(StaticConstantURLMenuButton.NUOVA_PARTITA_SELECTED,
				true);
		caricaPartitaButton = new MenuButton(StaticConstantURLMenuButton.CARICA_PARTITA_SELECTED);
	}

	public static void changeButtonISSelected(boolean change)
	{
		if (change)
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		switch (getMenuButtonSelected())
		{
			case 1:
			{
				nuovaPartitaButton.setSelected(change);
				break;
			}
			case 2:
			{
				caricaPartitaButton.setSelected(change);
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

	public static MenuButton getCaricaPartitaButton()
	{
		return caricaPartitaButton;
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static MenuButton getNuovaPartitaButton()
	{
		return nuovaPartitaButton;
	}

	public static PlayPanel getPlayPanel()
	{
		return playPanel;
	}

	public static void setCaricaPartitaButton(MenuButton caricaPartitaButton)
	{
		SinglePlayerPanel.caricaPartitaButton = caricaPartitaButton;
	}

	public static void setNuovaPartitaButton(MenuButton nuovaPartitaButton)
	{
		SinglePlayerPanel.nuovaPartitaButton = nuovaPartitaButton;
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

		nuovaPartitaButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		caricaPartitaButton.paintMenuButton(g, this.getWidth(), this.getHeight());

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
		SinglePlayerPanel.menuButtonSelected = menuButtonSelected;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerSinglePlayerPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
