package zelda;

import guiInterface.Navi;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;

import java.io.IOException;

import javax.swing.JFrame;

import profile.ProfileManager;
import staticConstant.StaticConstantGame;

import common.ImageProvider;

public class Main extends JFrame
{
	public static void main(String[] args)
	{
		new ImageProvider();
		StaticConstantGame.navi = new Navi();

		MainFrame mainFrame = new MainFrame();
		StaticConstantGame.mainFrame = mainFrame;

		if (mainFrame.initWiiMote() > 0)
		{
			StaticConstantGame.wiimoteActive = true;
		}

		MenuPanel menuPanel = new MenuPanel(mainFrame);
		mainFrame.switchPanel(menuPanel);

		try
		{
			StaticConstantGame.profileManager = new ProfileManager();
			StaticConstantGame.currentProfile = StaticConstantGame.profileManager.getProfiles()
					.iterator().next();
			StaticConstantGame.nameHero = StaticConstantGame.currentProfile.getName();
		}
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
	}
}
