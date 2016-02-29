package eventState.wiiMoteMenuPanelListener;

import staticConstant.StaticConstantGame;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.editor.EditorJFrameContainer;
import guiInterface.startScreen.CreditsPanel;
import guiInterface.startScreen.ExtraPanel;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;
import guiInterface.startScreen.OptionPanel;
import guiInterface.startScreen.PlayPanel;
import guiInterface.startScreen.ProfilePanel;
import guiInterface.startScreen.TrophiesPanel;

public class WiiMoteListenerMenuPanel extends WiiMoteMenuListener
{
	private final MenuPanel menuPanel;

	public WiiMoteListenerMenuPanel(MenuPanel menuPanel)
	{
		super();
		this.menuPanel = menuPanel;
		StaticConstantGame.wiimote.activateIRTRacking();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonAJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
			switchPanel();
		}
		else if (e.isButtonBJustPressed())
		{
			System.exit(0);
		}
		menuPanel.repaint();
	}

	@Override
	public void onExpansionEvent(ExpansionEvent e)
	{
		int typeDirection = getDirectionJoystick(e);

		if (typeDirection != -1)
		{
			switch (typeDirection)
			{
				case Direction.DOWN:
				{
					menuPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.UP:
				{
					menuPanel.changeButtonSelected(-1);
					stop();
					break;
				}

			}
			menuPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Profilo
		if (x >= 492 && y >= 117 && x <= 648 && y <= 150)
		{
			if (menuPanel.getMenuButtonSelected() != 1)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(1);
				menuPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 472 && y >= 191 && x <= 599 && y <= 232)
		{ // Gioca
			if (menuPanel.getMenuButtonSelected() != 2)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(2);
				menuPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 404 && y >= 271 && x <= 548 && y <= 302)
		{ // Editor
			if (menuPanel.getMenuButtonSelected() != 3)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(3);
				menuPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 369 && y >= 348 && x <= 500 && y <= 386)
		{ // Trofei
			if (menuPanel.getMenuButtonSelected() != 4)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(4);
				menuPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 313 && y >= 421 && x <= 451 && y <= 460)
		{ // Extra
			if (menuPanel.getMenuButtonSelected() != 5)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(5);
				menuPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 254 && y >= 502 && x <= 405 && y <= 539)
		{ // Crediti
			if (menuPanel.getMenuButtonSelected() != 6)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(6);
				menuPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 198 && y >= 583 && x <= 349 && y <= 615)
		{ // Opzioni
			if (menuPanel.getMenuButtonSelected() != 7)
			{
				menuPanel.changeButtonIsSelected(false);
				menuPanel.setMenuButtonSelected(7);
				menuPanel.changeButtonIsSelected(true);
			}
		}

		menuPanel.repaint();
	}

	private void switchPanel()
	{
		int buttonSelected = getMenuPanel().getMenuButtonSelected();
		MainFrame mainFrame = getMenuPanel().getMainFrame();
		switch (buttonSelected)
		{
			case 1:
				mainFrame.switchPanel(new ProfilePanel(mainFrame, getMenuPanel()));
				break;
			case 2:
				mainFrame.switchPanel(new PlayPanel(mainFrame, getMenuPanel()));
				break;
			case 3:
				EditorJFrameContainer.createBaseEditor();
				mainFrame.setVisible(false);
				mainFrame.dispose();
				StaticConstantGame.mainFrame = null;
				SoundManager.stop();
				break;
			case 4:
				mainFrame.switchPanel(new TrophiesPanel(mainFrame, getMenuPanel()));
				break;
			case 5:
				SoundManager.stop();
				mainFrame.switchPanel(new ExtraPanel(mainFrame, getMenuPanel()));
				break;
			case 6:
				mainFrame.switchPanel(new CreditsPanel(mainFrame, menuPanel));
				break;
			case 7:
				mainFrame.switchPanel(new OptionPanel(mainFrame, getMenuPanel()));
				break;

			default:
				break;
		}
	}

	public MenuPanel getMenuPanel()
	{
		return menuPanel;
	}
}
