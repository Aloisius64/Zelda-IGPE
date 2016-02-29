package eventState.wiiMoteMenuPanelListener;

import staticConstant.StaticConstantGame;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.GamePanel3D;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MultiPlayerPanel;
import guiInterface.startScreen.PlayPanel;
import guiInterface.startScreen.SinglePlayerPanel;

public class WiiMoteListenerPlayPanel extends WiiMoteMenuListener
{
	private final PlayPanel playPanel;

	public WiiMoteListenerPlayPanel(PlayPanel playPanel)
	{
		super();
		this.playPanel = playPanel;
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
			MainFrame mainFrame = PlayPanel.getMainFrame();
			mainFrame.switchPanel(PlayPanel.getMenuPanel());
			PlayPanel.getMenuPanel().configureWiiMote();
		}
		playPanel.repaint();
	}

	private void switchPanelto()
	{
		int buttonSelected = PlayPanel.getMenuButtonSelected();
		MainFrame mainFrame = PlayPanel.getMainFrame();

		switch (buttonSelected)
		{
			case 1:
				mainFrame.switchPanel(new SinglePlayerPanel(mainFrame, playPanel));
				break;
			case 2:
				mainFrame.switchPanel(new MultiPlayerPanel(mainFrame, playPanel));
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
					PlayPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.RIGHT:
				{
					PlayPanel.changeButtonSelected(1);
					stop();
					break;
				}
				case Direction.LEFT:
				{
					PlayPanel.changeButtonSelected(-1);
					stop();
					break;
				}
				case Direction.UP:
				{
					PlayPanel.changeButtonSelected(-1);
					stop();
					break;
				}

			}
			playPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Singolo
		if (x >= 887 && y >= 243 && x <= 1038 && y <= 374)
		{
			if (PlayPanel.getMenuButtonSelected() != 1)
			{
				PlayPanel.changeButtonIsSelected(false);
				PlayPanel.setMenuButtonSelected(1);
				PlayPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 723 && y >= 489 && x <= 874 && y <= 636)
		{ // MultiGiocatore
			if (PlayPanel.getMenuButtonSelected() != 2)
			{
				PlayPanel.changeButtonIsSelected(false);
				PlayPanel.setMenuButtonSelected(2);
				PlayPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 1012 && y >= 498 && x <= 1187 && y <= 635)
		{ // 3D
			if (PlayPanel.getMenuButtonSelected() != 3)
			{
				PlayPanel.changeButtonIsSelected(false);
				PlayPanel.setMenuButtonSelected(3);
				PlayPanel.changeButtonIsSelected(true);
			}
		}
		playPanel.repaint();
	}

	public PlayPanel getPlayPanel()
	{
		return playPanel;
	}
}
