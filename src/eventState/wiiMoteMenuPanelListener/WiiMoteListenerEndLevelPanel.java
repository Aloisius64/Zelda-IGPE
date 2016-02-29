package eventState.wiiMoteMenuPanelListener;

import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.EndLevelPanel;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;

public class WiiMoteListenerEndLevelPanel extends WiiMoteMenuListener
{
	private final EndLevelPanel endLevelPanel;

	public WiiMoteListenerEndLevelPanel(EndLevelPanel endLevelPanel)
	{
		super();
		this.endLevelPanel = endLevelPanel;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonAJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = EndLevelPanel.getMainFrame();
			mainFrame.switchPanel(new MenuPanel(mainFrame));
		}
		endLevelPanel.repaint();
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);
		endLevelPanel.repaint();
	}

	public EndLevelPanel getEndLevelPanel()
	{
		return endLevelPanel;
	}
}
