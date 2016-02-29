package eventState.wiiMoteMenuPanelListener;

import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.ScoresPanel;

public class WiiMoteListenerScoresPanel extends WiiMoteMenuListener
{
	private final ScoresPanel scoresPanel;

	public WiiMoteListenerScoresPanel(ScoresPanel scoresPanel)
	{
		super();
		this.scoresPanel = scoresPanel;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = ScoresPanel.getMainFrame();
			mainFrame.switchPanel(ScoresPanel.getTrophiesPanel());
			ScoresPanel.getTrophiesPanel().configureWiiMote();
		}
		scoresPanel.repaint();
	}

	public ScoresPanel getScoresPanel()
	{
		return scoresPanel;
	}

}
