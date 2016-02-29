package eventState.wiiMoteMenuPanelListener;

import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;
import guiInterface.startScreen.ScoresProfilePanel;

public class WiiMoteListenerScoresProfilePanel extends WiiMoteMenuListener
{
	private final ScoresProfilePanel scoresProfilePanel;

	public WiiMoteListenerScoresProfilePanel(ScoresProfilePanel scoresProfilePanel)
	{
		super();
		this.scoresProfilePanel = scoresProfilePanel;

	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = ScoresProfilePanel.getMainFrame();
			mainFrame.switchPanel(ScoresProfilePanel.getPanel());
			ScoresProfilePanel.getPanel().configureWiiMote();
		}
		scoresProfilePanel.repaint();
	}

	public ScoresProfilePanel getScoresProfilePanel()
	{
		return scoresProfilePanel;
	}

}
