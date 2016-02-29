package eventState.wiiMoteMenuPanelListener;

import java.awt.event.KeyEvent;

import staticConstant.StaticConstantGame;

import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.OptionPanel;

public class WiiMoteListenerOptionPanel extends WiiMoteMenuListener
{
	private final OptionPanel optionPanel;

	public WiiMoteListenerOptionPanel(OptionPanel optionPanel)
	{
		super();
		this.optionPanel = optionPanel;
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBJustPressed())
		{
			if (!StaticConstantGame.gameRunning)
			{
				SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
				OptionPanel.getMainFrame().switchPanel(OptionPanel.getMenuPanel());
				OptionPanel.getMenuPanel().configureWiiMote();
			}
			else
			{
				OptionPanel.getMainFrame().switchPanel(StaticConstantGame.gamePanel2D);
				StaticConstantGame.gamePanel2D.configureWiiMote();
			}
		}
		optionPanel.repaint();
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
					OptionPanel.changeCategories(1);
					SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
					stop();
					break;
				}
				case Direction.RIGHT:
				{
					OptionPanel.changeButtonSelected(1);
					SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
					stop();
					break;
				}
				case Direction.LEFT:
				{
					OptionPanel.changeButtonSelected(-1);
					SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
					stop();
					break;
				}
				case Direction.UP:
				{
					OptionPanel.changeCategories(-1);
					SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
					stop();
					break;
				}
			}
			optionPanel.repaint();
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Suono Attivo
		if (x >= 211 && y >= 133 && x <= 306 && y <= 163)
		{
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(1);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(1);
			SoundManager.activeSound = true;
		}
		else if (x >= 416 && y >= 130 && x <= 570 && y <= 163)
		{ // Suono Disattivo
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(1);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(2);
			SoundManager.activeSound = false;
		}
		else if (x >= 206 && y >= 300 && x <= 305 && y <= 330)
		{ // Effetti attivi
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(2);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(1);
			SoundManager.activeEffect = true;
		}
		else if (x >= 421 && y >= 298 && x <= 568 && y <= 330)
		{ // Effetti disattivi
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(2);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(2);
			SoundManager.activeEffect = false;
		}
		else if (x >= 153 && y >= 467 && x <= 286 && y <= 496)
		{ // Diff Random
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(3);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(0);
			StaticConstantGame.typeAI = 0;
		}
		else if (x >= 336 && y >= 468 && x <= 439 && y <= 495)
		{ // Diff Media
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(3);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(1);
			StaticConstantGame.typeAI = 1;
		}
		else if (x >= 487 && y >= 470 && x <= 629 && y <= 500)
		{ // Diff Difficile
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(3);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(2);
			StaticConstantGame.typeAI = 2;
		}
		else if (x >= 185 && y >= 624 && x <= 380 && y <= 666)
		{ // Controller Tastiera
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(4);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(1);
			StaticConstantGame.wiimoteActive = false;
		}
		else if (x >= 475 && y >= 616 && x <= 611 && y <= 676)
		{ // Controller WiiMote
			OptionPanel.changeCategorySelected(false);
			OptionPanel.setCategory(4);
			OptionPanel.changeCategorySelected(true);
			OptionPanel.changeButtonSelectedFromWiiMote(2);
			StaticConstantGame.wiimoteActive = true;
		}
		optionPanel.repaint();
	}

	public OptionPanel getOptionPanel()
	{
		return optionPanel;
	}
}
