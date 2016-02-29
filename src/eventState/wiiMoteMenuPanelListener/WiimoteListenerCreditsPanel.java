package eventState.wiiMoteMenuPanelListener;

import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.CreditsPanel;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;
import guiInterface.startScreen.ScoresPanel;

public class WiimoteListenerCreditsPanel extends WiiMoteMenuListener
{
	private final CreditsPanel creditsPanel;

	public WiimoteListenerCreditsPanel(CreditsPanel creditsPanel)
	{
		super();
		this.creditsPanel = creditsPanel;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = creditsPanel.getMainFrame();
			mainFrame.switchPanel(new MenuPanel(mainFrame));
		}
		creditsPanel.repaint();
	}

	public CreditsPanel getCreditsPanel()
	{
		return creditsPanel;
	}
}
