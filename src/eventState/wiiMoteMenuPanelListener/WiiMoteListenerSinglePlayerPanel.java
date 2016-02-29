package eventState.wiiMoteMenuPanelListener;

import java.awt.event.KeyEvent;

import staticConstant.StaticConstantGame;

import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.ChooseLevelPanel;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.PlayPanel;
import guiInterface.startScreen.SinglePlayerPanel;

public class WiiMoteListenerSinglePlayerPanel extends WiiMoteMenuListener
{
	private final SinglePlayerPanel singlePlayerPanel;

	public WiiMoteListenerSinglePlayerPanel(SinglePlayerPanel singlePlayerPanel)
	{
		super();
		this.singlePlayerPanel = singlePlayerPanel;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonAJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
			switchPanelto();
		}
		else if (e.isButtonBJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = SinglePlayerPanel.getMainFrame();
			mainFrame.switchPanel(SinglePlayerPanel.getPlayPanel());
			SinglePlayerPanel.getPlayPanel().configureWiiMote();
		}
		singlePlayerPanel.repaint();
	}

	private void switchPanelto()
	{
		int buttonSelected = SinglePlayerPanel.getMenuButtonSelected();
		MainFrame mainFrame = SinglePlayerPanel.getMainFrame();

		switch (buttonSelected)
		{
			case 1:
				mainFrame.switchPanel(new ChooseLevelPanel(mainFrame, singlePlayerPanel));
				StaticConstantGame.currentProfile.setEmptyProfile();
				break;
			case 2:
				mainFrame.switchPanel(new ChooseLevelPanel(mainFrame, singlePlayerPanel));
				break;
		}
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
					SinglePlayerPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.RIGHT:
				{
					SinglePlayerPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.LEFT:
				{
					SinglePlayerPanel.changeButtonSelected(-1);
					stop();
					break;
				}
				case Direction.UP:
				{
					SinglePlayerPanel.changeButtonSelected(-1);
					stop();
					break;
				}
			}
			singlePlayerPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Nuova
		if (x >= 220 && y >= 498 && x <= 372 && y <= 638)
		{
			if (SinglePlayerPanel.getMenuButtonSelected() != 1)
			{
				SinglePlayerPanel.changeButtonISSelected(false);
				SinglePlayerPanel.setMenuButtonSelected(1);
				SinglePlayerPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 549 && y >= 502 && x <= 713 && y <= 637)
		{ // Carica
			if (SinglePlayerPanel.getMenuButtonSelected() != 2)
			{
				SinglePlayerPanel.changeButtonISSelected(false);
				SinglePlayerPanel.setMenuButtonSelected(2);
				SinglePlayerPanel.changeButtonISSelected(true);
			}
		}
		singlePlayerPanel.repaint();
	}

	public SinglePlayerPanel getSinglePlayerPanel()
	{
		return singlePlayerPanel;
	}

}
