package eventState.wiiMoteMenuPanelListener;

import staticConstant.StaticConstantGame;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.ChooseLevelPanel;
import guiInterface.startScreen.MainFrame;

public class WiiMoteListenerChooseLevelPanel extends WiiMoteMenuListener
{
	private final ChooseLevelPanel chooseLevelPanel;

	public WiiMoteListenerChooseLevelPanel(ChooseLevelPanel chooseLevelPanel)
	{
		super();
		this.chooseLevelPanel = chooseLevelPanel;
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
			MainFrame mainFrame = ChooseLevelPanel.getMainFrame();
			mainFrame.switchPanel(ChooseLevelPanel.getSinglePlayerPanel());
			ChooseLevelPanel.getSinglePlayerPanel().configureWiiMote();
		}
		chooseLevelPanel.repaint();
	}

	private void switchPanelto()
	{
		int buttonSelected = ChooseLevelPanel.getMenuButtonSelected();

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

		chooseLevelPanel.startLevel(buttonSelected);
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
					ChooseLevelPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.RIGHT:
				{
					ChooseLevelPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.LEFT:
				{
					ChooseLevelPanel.changeButtonSelected(-1);
					stop();
					break;
				}
				case Direction.UP:
				{
					ChooseLevelPanel.changeButtonSelected(-1);
					stop();
					break;
				}

			}
			chooseLevelPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Kakariko
		if (x >= 536 && y >= 159 && x <= 650 && y <= 330)
		{
			if (ChooseLevelPanel.getMenuButtonSelected() != 1)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(1);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 683 && y >= 174 && x <= 800 && y <= 331)
		{ // Desert
			if (ChooseLevelPanel.getMenuButtonSelected() != 2)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(2);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 380 && y >= 426 && x <= 485 && y <= 593)
		{ // Dungeon
			if (ChooseLevelPanel.getMenuButtonSelected() != 3)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(3);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 514 && y >= 500 && x <= 595 && y <= 588)
		{ // Forest
			if (ChooseLevelPanel.getMenuButtonSelected() != 4)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(4);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 733 && y >= 505 && x <= 818 && y <= 600)
		{ // Lake
			if (ChooseLevelPanel.getMenuButtonSelected() != 5)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(5);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 842 && y >= 453 && x <= 953 && y <= 594)
		{ // Volcano
			if (ChooseLevelPanel.getMenuButtonSelected() != 6)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(6);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		else if (x >= 609 && y >= 384 && x <= 699 && y <= 477)
		{ // Sky
			if (ChooseLevelPanel.getMenuButtonSelected() != 7)
			{
				ChooseLevelPanel.changeButtonISSelected(false);
				ChooseLevelPanel.setMenuButtonSelected(7);
				ChooseLevelPanel.changeButtonISSelected(true);
			}
		}
		chooseLevelPanel.repaint();
	}

	public ChooseLevelPanel getChooseLevelPanel()
	{
		return chooseLevelPanel;
	}
}
