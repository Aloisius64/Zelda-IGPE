package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;

import common.SoundManager;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerTrophiesPanel;

public class TrophiesPanel extends JPanel
{
	private static MainFrame mainFrame;
	private static MenuPanel menuPanel;
	private static int menuButtonSelected;

	// Buttons
	private static MenuButton statisticheProfilo;
	private static MenuButton punteggi;

	public TrophiesPanel(MainFrame mainFrame, final MenuPanel menuPanel)
	{
		this.mainFrame = mainFrame;
		this.menuPanel = menuPanel;
		this.menuButtonSelected = 1;

		configureWiiMote();
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_DOWN:
					{
						TrophiesPanel.changeButtonSelected(1);
						break;
					}
					case KeyEvent.VK_UP:
					{
						TrophiesPanel.changeButtonSelected(-1);
						break;
					}
					case KeyEvent.VK_ENTER:
					{
						SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
						switchPanelto();
						break;
					}
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = TrophiesPanel.getMainFrame();
						mainFrame.switchPanel(TrophiesPanel.menuPanel);
						TrophiesPanel.getMenuPanel().playThemeSong();
						break;
					}
				}
				repaint();
			}

			private void switchPanelto()
			{
				int buttonSelected = TrophiesPanel.getMenuButtonSelected();
				MainFrame mainFrame = TrophiesPanel.getMainFrame();

				switch (buttonSelected)
				{
					case 1:
						mainFrame
								.switchPanel(new ScoresProfilePanel(mainFrame, TrophiesPanel.this));
						break;
					case 2:
						mainFrame.switchPanel(new ScoresPanel(mainFrame, TrophiesPanel.this));
						break;
				}
			}
		});

		statisticheProfilo = new MenuButton(
				StaticConstantURLMenuButton.STATISTICHE_PROFILO_SELECTED, true);
		punteggi = new MenuButton(StaticConstantURLMenuButton.PUNTEGGI_SELECTED);
	}

	public static MenuPanel getMenuPanel()
	{
		return menuPanel;
	}

	public static int getMenuButtonSelected()
	{
		return menuButtonSelected;
	}

	public static void changeButtonSelected(int change)
	{
		if ((change == 1) || (change == -1))
		{
			if ((menuButtonSelected == 1) && (change == -1))
			{
				return;
			}
			if ((menuButtonSelected == 2) && (change == 1))
			{
				return;
			}
			changeButtonIsSelected(false);
			menuButtonSelected += change;
			changeButtonIsSelected(true);
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		}
	}

	private static void changeButtonIsSelected(boolean change)
	{
		switch (menuButtonSelected)
		{
			case 1:
			{
				statisticheProfilo.setSelected(change);
				break;
			}
			case 2:
			{
				punteggi.setSelected(change);
				break;
			}
		}

	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);

		statisticheProfilo.paintMenuButton(g, this.getWidth(), this.getHeight());
		punteggi.paintMenuButton(g, this.getWidth(), this.getHeight());

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
			StaticConstantGame.wiimoteListener = new WiiMoteListenerTrophiesPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
