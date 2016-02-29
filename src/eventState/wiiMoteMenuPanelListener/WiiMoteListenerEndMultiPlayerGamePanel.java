package eventState.wiiMoteMenuPanelListener;

import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.EndMultiplayerGamePanel;
import guiInterface.startScreen.MainFrame;

public class WiiMoteListenerEndMultiPlayerGamePanel extends WiiMoteMenuListener
{
	private final EndMultiplayerGamePanel endMultiplayerGamePanel;

	public WiiMoteListenerEndMultiPlayerGamePanel(EndMultiplayerGamePanel endMultiplayerGamePanel)
	{
		super();
		this.endMultiplayerGamePanel = endMultiplayerGamePanel;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonAJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = EndMultiplayerGamePanel.getMainFrame();
			mainFrame.switchPanel(EndMultiplayerGamePanel.getPanel());
			EndMultiplayerGamePanel.getPanel().configureWiiMote();
		}
		endMultiplayerGamePanel.repaint();
	}

	public EndMultiplayerGamePanel getEndMultiplayerGamePanel()
	{
		return endMultiplayerGamePanel;
	}
}
