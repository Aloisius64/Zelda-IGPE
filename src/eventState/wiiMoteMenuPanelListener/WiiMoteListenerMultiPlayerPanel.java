package eventState.wiiMoteMenuPanelListener;

import staticConstant.StaticConstantGame;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.ChooseLevelPanel;
import guiInterface.startScreen.ConnectionPanel;
import guiInterface.startScreen.CreateConnectionPanel;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MultiPlayerPanel;

public class WiiMoteListenerMultiPlayerPanel extends WiiMoteMenuListener
{
	private final MultiPlayerPanel multiPlayerPanel;

	public WiiMoteListenerMultiPlayerPanel(MultiPlayerPanel multiPlayerPanel)
	{
		super();
		this.multiPlayerPanel = multiPlayerPanel;
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
			MainFrame mainFrame = MultiPlayerPanel.getMainFrame();
			mainFrame.switchPanel(MultiPlayerPanel.getPlayPanel());
			MultiPlayerPanel.getPlayPanel().configureWiiMote();
		}
		multiPlayerPanel.repaint();
	}

	private void switchPanelto()
	{
		int buttonSelected = MultiPlayerPanel.getMenuButtonSelected();
		MainFrame mainFrame = MultiPlayerPanel.getMainFrame();

		switch (buttonSelected)
		{
			case 1:
				mainFrame.switchPanel(new CreateConnectionPanel(mainFrame, multiPlayerPanel));
				break;
			case 2:
				mainFrame.switchPanel(new ConnectionPanel(mainFrame, multiPlayerPanel));
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
					MultiPlayerPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.RIGHT:
				{
					MultiPlayerPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.LEFT:
				{
					MultiPlayerPanel.changeButtonSelected(-1);
					stop();
					break;
				}
				case Direction.UP:
				{
					MultiPlayerPanel.changeButtonSelected(-1);
					stop();
					break;
				}

			}
			multiPlayerPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Crea
		if (x >= 387 && y >= 474 && x <= 607 && y <= 663)
		{
			if (MultiPlayerPanel.getMenuButtonSelected() != 1)
			{
				MultiPlayerPanel.changeButtonISSelected(false);
				MultiPlayerPanel.setMenuButtonSelected(1);
				MultiPlayerPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 733 && y >= 463 && x <= 932 && y <= 646)
		{ // Unisciti
			if (MultiPlayerPanel.getMenuButtonSelected() != 2)
			{
				MultiPlayerPanel.changeButtonISSelected(false);
				MultiPlayerPanel.setMenuButtonSelected(2);
				MultiPlayerPanel.changeButtonISSelected(true);
			}
		}
		multiPlayerPanel.repaint();
	}

	public MultiPlayerPanel getMultiPlayerPanel()
	{
		return multiPlayerPanel;
	}

}
