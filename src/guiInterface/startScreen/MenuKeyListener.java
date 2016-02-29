package guiInterface.startScreen;

import guiInterface.editor.EditorJFrameContainer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import staticConstant.StaticConstantGame;

import common.SoundManager;

public class MenuKeyListener extends KeyAdapter
{
	private final MenuPanel menuPanel;

	public MenuKeyListener(MenuPanel panel)
	{
		this.menuPanel = panel;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_DOWN:
			{
				menuPanel.changeButtonSelected(1);
				SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
				break;
			}
			case KeyEvent.VK_UP:
			{
				menuPanel.changeButtonSelected(-1);
				SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
				break;
			}
			case KeyEvent.VK_ENTER:
			{
				SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
				switchPanel();
				break;
			}
			case KeyEvent.VK_ESCAPE:
			{
				System.exit(0);
				break;
			}
		}
		menuPanel.repaint();
	}

	private void switchPanel()
	{
		int buttonSelected = menuPanel.getMenuButtonSelected();
		MainFrame mainFrame = menuPanel.getMainFrame();
		switch (buttonSelected)
		{
			case 1:
				mainFrame.switchPanel(new ProfilePanel(mainFrame, menuPanel));
				break;
			case 2:
				mainFrame.switchPanel(new PlayPanel(mainFrame, menuPanel));
				break;
			case 3:
				EditorJFrameContainer.createBaseEditor();
				mainFrame.setVisible(false);
				mainFrame.dispose();
				StaticConstantGame.mainFrame = null;
				SoundManager.stop();
				break;
			case 4:
				mainFrame.switchPanel(new TrophiesPanel(mainFrame, menuPanel));
				break;
			case 5:
				SoundManager.stop();
				mainFrame.switchPanel(new ExtraPanel(mainFrame, menuPanel));
				break;
			case 6:
				mainFrame.switchPanel(new CreditsPanel(mainFrame, menuPanel));
				break;
			case 7:
				mainFrame.switchPanel(new OptionPanel(mainFrame, menuPanel));
				break;
			default:
				break;
		}
	}
}
