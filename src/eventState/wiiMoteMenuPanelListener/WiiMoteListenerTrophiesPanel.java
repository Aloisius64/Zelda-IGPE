package eventState.wiiMoteMenuPanelListener;

import staticConstant.StaticConstantGame;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.ScoresPanel;
import guiInterface.startScreen.ScoresProfilePanel;
import guiInterface.startScreen.TrophiesPanel;

public class WiiMoteListenerTrophiesPanel extends WiiMoteMenuListener
{
	private final TrophiesPanel trophiesPanel;

	public WiiMoteListenerTrophiesPanel(TrophiesPanel trophiesPanel)
	{
		super();
		this.trophiesPanel = trophiesPanel;
		StaticConstantGame.wiimote.activateIRTRacking();
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
			MainFrame mainFrame = TrophiesPanel.getMainFrame();
			mainFrame.switchPanel(TrophiesPanel.getMenuPanel());
			TrophiesPanel.getMenuPanel().configureWiiMote();
		}
		trophiesPanel.repaint();
	}

	private void switchPanelto()
	{
		int buttonSelected = TrophiesPanel.getMenuButtonSelected();
		MainFrame mainFrame = TrophiesPanel.getMainFrame();

		switch (buttonSelected)
		{
			case 1:
				mainFrame.switchPanel(new ScoresProfilePanel(mainFrame, trophiesPanel));
				break;
			case 2:
				mainFrame.switchPanel(new ScoresPanel(mainFrame, trophiesPanel));
				break;
			case 3:

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
					TrophiesPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.UP:
				{
					TrophiesPanel.changeButtonSelected(-1);
					stop();
					break;
				}

			}
			trophiesPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Statistiche
		if (x >= 171 && y >= 125 && x <= 741 && y <= 177)
		{
			TrophiesPanel.changeButtonSelected(-1);
			// SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		}
		else if (x >= 169 && y >= 231 && x <= 416 && y <= 275)
		{ // Punteggi
			TrophiesPanel.changeButtonSelected(1);
			// SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		}

		trophiesPanel.repaint();
	}

	public TrophiesPanel getTrophiesPanel()
	{
		return trophiesPanel;
	}
}
