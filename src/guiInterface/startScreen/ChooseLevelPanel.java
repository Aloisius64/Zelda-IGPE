package guiInterface.startScreen;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerChooseLevelPanel;
import gameLogic.GameManager;
import guiInterface.GamePanel2D;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;
import threadState.PlatformManager;

import common.ImageProvider;
import common.Sound;
import common.SoundManager;

public class ChooseLevelPanel extends JPanel
{
	private static MainFrame mainFrame;
	private static SinglePlayerPanel panel;
	private static int menuButtonSelected;

	// Pulsanti
	private static MenuButton level1;
	private static MenuButton level2;
	private static MenuButton level3;
	private static MenuButton level4;
	private static MenuButton level5;
	private static MenuButton level6;
	private static MenuButton level7;

	public ChooseLevelPanel(MainFrame mainFrame, SinglePlayerPanel panel)
	{
		this.mainFrame = mainFrame;
		this.panel = panel;
		this.setMenuButtonSelected(1);

		level1 = new MenuButton(StaticConstantURLMenuButton.LEVEL1, true);
		level2 = new MenuButton(StaticConstantURLMenuButton.LEVEL2);
		level3 = new MenuButton(StaticConstantURLMenuButton.LEVEL3);
		level4 = new MenuButton(StaticConstantURLMenuButton.LEVEL4);
		level5 = new MenuButton(StaticConstantURLMenuButton.LEVEL5);
		level6 = new MenuButton(StaticConstantURLMenuButton.LEVEL6);
		level7 = new MenuButton(StaticConstantURLMenuButton.LEVEL7);

		configureWiiMote();

		this.addKeyListener(new KeyAdapter()
		{
			Sound menuButtonSound;

			private void switchPanelto()
			{
				int buttonSelected = ChooseLevelPanel.getMenuButtonSelected();
				// MainFrame mainFrame = ChooseLevelPanel.getMainFrame();

				switch (buttonSelected)
				{
					case 1:
						StaticConstantGame.typeWorldChoose = 1;
						break;
					case 2:
						StaticConstantGame.typeWorldChoose = 2;
						break;
					case 3:
						StaticConstantGame.typeWorldChoose = 3;
						break;
					case 4:
						StaticConstantGame.typeWorldChoose = 4;
						break;
					case 5:
						StaticConstantGame.typeWorldChoose = 5;
						break;
					case 6:
						StaticConstantGame.typeWorldChoose = 6;
						break;
					case 7:
						StaticConstantGame.typeWorldChoose = 7;
						break;
				}

				startLevel(buttonSelected);
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
						ChooseLevelPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						ChooseLevelPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_LEFT:
					{
						ChooseLevelPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_UP:
					{
						ChooseLevelPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = ChooseLevelPanel.getMainFrame();
						mainFrame.switchPanel(ChooseLevelPanel.getSinglePlayerPanel());
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
	}

	public static void changeButtonSelected(int change)
	{
		if ((change == 1) || (change == -1))
		{
			if ((getMenuButtonSelected() == 1) && (change == -1))
			{
				return;
			}
			if ((getMenuButtonSelected() == 7) && (change == 1))
			{
				return;
			}
			if (!(getMenuButtonSelected() + 1 > StaticConstantGame.currentProfile
					.getLevelsUnlocked()))
			{
				changeButtonISSelected(false);
				setMenuButtonSelected(getMenuButtonSelected() + change);
				changeButtonISSelected(true);
			}
		}
	}

	public static void changeButtonISSelected(boolean change)
	{
		if (change)
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		switch (getMenuButtonSelected())
		{
			case 1:
			{
				level1.setSelected(change);
				break;
			}
			case 2:
			{
				level2.setSelected(change);
				break;
			}
			case 3:
			{
				level3.setSelected(change);
				break;
			}
			case 4:
			{
				level4.setSelected(change);
				break;
			}
			case 5:
			{
				level5.setSelected(change);
				break;
			}
			case 6:
			{
				level6.setSelected(change);
				break;
			}
			case 7:
			{
				level7.setSelected(change);
				break;
			}
		}
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static SinglePlayerPanel getSinglePlayerPanel()
	{
		return panel;
	}

	public static int getMenuButtonSelected()
	{
		return menuButtonSelected;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);

		level1.paintMenuButton(g, this.getWidth(), this.getHeight());
		level2.paintMenuButton(g, this.getWidth(), this.getHeight());
		level3.paintMenuButton(g, this.getWidth(), this.getHeight());
		level4.paintMenuButton(g, this.getWidth(), this.getHeight());
		level5.paintMenuButton(g, this.getWidth(), this.getHeight());
		level6.paintMenuButton(g, this.getWidth(), this.getHeight());
		level7.paintMenuButton(g, this.getWidth(), this.getHeight());

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerChooseLevelPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}

	public static void setMenuButtonSelected(int menuButtonSelected)
	{
		ChooseLevelPanel.menuButtonSelected = menuButtonSelected;
	}

	public void startLevel(int index)
	{
		if ((index - 1) < StaticConstantGame.currentProfile.getLevelsUnlocked())
		{
			SoundManager.stop();
			StaticConstantGame.enable3D = false;
			StaticConstantGame.gameRunning = true;
			new ImageProvider();
			final GameManager gameManager = new GameManager();
			StaticConstantGame.gameManager = gameManager;
			gameManager.start();
			GamePanel2D gamePanel = new GamePanel2D(mainFrame);
			gameManager.startPlatform();
			new PlatformManager();
			StaticConstantGame.sphereManager.start();
			StaticConstantGame.paused = false;

			mainFrame.switchPanel(gamePanel);
		}
	}
}
