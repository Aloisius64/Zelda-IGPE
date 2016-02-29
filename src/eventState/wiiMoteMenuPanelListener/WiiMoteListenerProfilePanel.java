package eventState.wiiMoteMenuPanelListener;

import common.SoundManager;

import staticConstant.StaticConstantGame;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.ProfilePanel;

public class WiiMoteListenerProfilePanel extends WiiMoteMenuListener
{
	private final ProfilePanel profilePanel;

	public WiiMoteListenerProfilePanel(ProfilePanel profilePanel)
	{
		super();
		this.profilePanel = profilePanel;
		StaticConstantGame.wiimote.activateIRTRacking();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBJustPressed())
		{
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = ProfilePanel.getMainFrame();
			mainFrame.switchPanel(ProfilePanel.getMenuPanel());
			ProfilePanel.getMenuPanel().configureWiiMote();
		}
		profilePanel.repaint();
	}

	public ProfilePanel getProfilePanel()
	{
		return profilePanel;
	}
}
