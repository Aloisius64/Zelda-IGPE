package guiInterface.startScreen;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerPlayPanel;
import guiInterface.GamePanel3D;

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

public class PlayPanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static MenuPanel menuPanel;
	private static int menuButtonSelected;

	// Pulsanti Menù Gioca
	private static MenuButton singoloButton;
	private static MenuButton multigiocatoreButton;
	private static MenuButton singolo3DButton;

	public static void changeButtonIsSelected(boolean change)
	{
		if (change)
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		switch (getMenuButtonSelected())
		{
			case 1:
			{
				singoloButton.setSelected(change);
				break;
			}
			case 2:
			{
				multigiocatoreButton.setSelected(change);
				break;
			}
			case 3:
			{
				singolo3DButton.setSelected(change);
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
			if ((getMenuButtonSelected() == 3) && (change == 1))
			{
				return;
			}
			changeButtonIsSelected(false);
			menuButtonSelected += change;
			changeButtonIsSelected(true);
		}
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static int getMenuButtonSelected()
	{
		return menuButtonSelected;
	}

	public static MenuPanel getMenuPanel()
	{
		return menuPanel;
	}

	public static MenuButton getMultigiocatoreButton()
	{
		return multigiocatoreButton;
	}

	public static MenuButton getSingolo3DButton()
	{
		return singolo3DButton;
	}

	public static MenuButton getSingoloButton()
	{
		return singoloButton;
	}

	protected static void setClicked(boolean change)
	{
		switch (getMenuButtonSelected())
		{
			case 1:
			{
				singoloButton.setClicked(change);
				break;
			}
			case 2:
			{
				multigiocatoreButton.setClicked(change);
				break;
			}
			case 3:
			{
				singolo3DButton.setClicked(change);
				break;
			}
		}
	}

	public static void setMenuButtonSelected(int menuButtonSelected)
	{
		PlayPanel.menuButtonSelected = menuButtonSelected;
	}

	public static void setMultigiocatoreButton(MenuButton multigiocatoreButton)
	{
		PlayPanel.multigiocatoreButton = multigiocatoreButton;
	}

	public static void setSingolo3DButton(MenuButton singolo3dButton)
	{
		singolo3DButton = singolo3dButton;
	}

	public static void setSingoloButton(MenuButton singoloButton)
	{
		PlayPanel.singoloButton = singoloButton;
	}

	public PlayPanel(MainFrame mainFrame, final MenuPanel menuPanel)
	{
		this.setMainFrame(mainFrame);
		this.setMenuPanel(menuPanel);
		menuButtonSelected = 1;
		try
		{
			background = ImageIO.read(new File("Images/MenuPanel/PlayPanel/background.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default.");
		}

		// this.addKeyListener(new PlayPanelKeyListener(this));
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
					case KeyEvent.VK_DOWN:
					{
						PlayPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_RIGHT:
					{
						PlayPanel.changeButtonSelected(1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_LEFT:
					{
						PlayPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_UP:
					{
						PlayPanel.changeButtonSelected(-1);
						SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = PlayPanel.getMainFrame();
						mainFrame.switchPanel(PlayPanel.getMenuPanel());
						PlayPanel.getMenuPanel().playThemeSong();
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

			private void switchPanelto()
			{
				int buttonSelected = PlayPanel.getMenuButtonSelected();
				MainFrame mainFrame = PlayPanel.getMainFrame();

				switch (buttonSelected)
				{
					case 1:
						mainFrame.switchPanel(new SinglePlayerPanel(mainFrame, PlayPanel.this));
						break;
					case 2:
						mainFrame.switchPanel(new MultiPlayerPanel(mainFrame, PlayPanel.this));
						break;
					case 3:
						StaticConstantGame.enable3D = true;
						StaticConstantGame.typeWorldChoose = 9;
						GamePanel3D main3D = new GamePanel3D();
						main3D.configure();
						main3D.start();
						mainFrame.dispose();
						break;
				}
			}

		});

		singoloButton = new MenuButton(StaticConstantURLMenuButton.SINGOLO_SELECTED, true);
		multigiocatoreButton = new MenuButton(StaticConstantURLMenuButton.MULTIGIOCATORE_SELECTED);
		singolo3DButton = new MenuButton(StaticConstantURLMenuButton.SINGOLO3D_SELECTED);
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerPlayPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
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

		singoloButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		multigiocatoreButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		singolo3DButton.paintMenuButton(g, this.getWidth(), this.getHeight());

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public void setMainFrame(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}

	public void setMenuPanel(MenuPanel menuPanel)
	{
		this.menuPanel = menuPanel;
	}
}
