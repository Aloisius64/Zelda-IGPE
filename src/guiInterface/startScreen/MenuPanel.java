package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;

import common.SoundManager;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;

public class MenuPanel extends JPanel
{
	private Image background;
	private final MenuKeyListener keyListener;
	private int menuButtonSelected;
	private MainFrame mainFrame;

	// Pulsanti menù
	private final MenuButton profiloMenuButton;
	private final MenuButton giocaMenuButton;
	private final MenuButton editorMenuButton;
	private final MenuButton extraMenuButton;
	private final MenuButton trofeiMenuButton;
	private final MenuButton creditiMenuButton;
	private final MenuButton opzioniMenuButton;

	public MenuPanel(MainFrame mainFrame)
	{
		StaticConstantGame.enable3D = false;

		SoundManager.playSound(SoundManager.MAIN_MENU, 0, SoundManager.MUSIC);

		try
		{
			background = ImageIO.read(new File("Images/MenuPanel/Menu/MenuBackground.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default)");
		}
		setMenuButtonSelected(1);
		keyListener = new MenuKeyListener(this);

		this.setFocusable(true);
		this.setMainFrame(mainFrame);

		// Creazioni pulsanti menù
		profiloMenuButton = new MenuButton(StaticConstantURLMenuButton.PROFILO_SELECTED, true);
		giocaMenuButton = new MenuButton(StaticConstantURLMenuButton.GIOCA_SELECTED);
		editorMenuButton = new MenuButton(StaticConstantURLMenuButton.EDITOR_SELECTED);
		extraMenuButton = new MenuButton(StaticConstantURLMenuButton.EXTRA_SELECTED);
		trofeiMenuButton = new MenuButton(StaticConstantURLMenuButton.TROFEI_SELECTED);
		creditiMenuButton = new MenuButton(StaticConstantURLMenuButton.CREDITI_SELECTED);
		opzioniMenuButton = new MenuButton(StaticConstantURLMenuButton.OPZIONI_SELECTED);

		configureWiiMote();
		addKeyListener(keyListener);
	}

	public void changeButtonIsSelected(boolean change)
	{
		if (change)
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
		switch (getMenuButtonSelected())
		{
			case 1:
			{
				profiloMenuButton.setSelected(change);
				break;
			}
			case 2:
			{
				giocaMenuButton.setSelected(change);
				break;
			}
			case 3:
			{
				editorMenuButton.setSelected(change);
				break;
			}
			case 4:
			{
				trofeiMenuButton.setSelected(change);
				break;
			}
			case 5:
			{
				extraMenuButton.setSelected(change);
				break;
			}
			case 6:
			{
				creditiMenuButton.setSelected(change);
				break;
			}
			case 7:
			{
				opzioniMenuButton.setSelected(change);
				break;
			}
		}
	}

	public void changeButtonSelected(int change)
	{
		if ((change == 1) || (change == -1))
		{
			SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);

			if ((getMenuButtonSelected() == 1) && (change == -1))
			{
				changeButtonIsSelected(false);
				setMenuButtonSelected(7);
				changeButtonIsSelected(true);
				return;
			}
			if ((getMenuButtonSelected() == 7) && (change == 1))
			{
				changeButtonIsSelected(false);
				setMenuButtonSelected(1);
				changeButtonIsSelected(true);
				return;
			}
			changeButtonIsSelected(false);
			setMenuButtonSelected(getMenuButtonSelected() + change);
			changeButtonIsSelected(true);
		}
	}

	public MenuButton getCreditiMenuButton()
	{
		return creditiMenuButton;
	}

	public MenuButton getEditorMenuButton()
	{
		return editorMenuButton;
	}

	public MenuButton getExtraMenuButton()
	{
		return extraMenuButton;
	}

	public MenuButton getGiocaMenuButton()
	{
		return giocaMenuButton;
	}

	public MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public int getMenuButtonSelected()
	{
		return menuButtonSelected;
	}

	public MenuButton getOpzioniMenuButton()
	{
		return opzioniMenuButton;
	}

	public MenuButton getProfiloMenuButton()
	{
		return profiloMenuButton;
	}

	public MenuButton getTrofeiMenuButton()
	{
		return trofeiMenuButton;
	}

	public boolean isAnyoneClicked()
	{
		if (profiloMenuButton.isClicked() || giocaMenuButton.isClicked()
				|| editorMenuButton.isClicked() || extraMenuButton.isClicked()
				|| trofeiMenuButton.isClicked() || trofeiMenuButton.isClicked()
				|| opzioniMenuButton.isClicked())
		{
			return true;
		}
		return false;
	}

	public boolean isAnyoneSelected()
	{
		if (profiloMenuButton.isSelected() || giocaMenuButton.isSelected()
				|| editorMenuButton.isSelected() || extraMenuButton.isSelected()
				|| trofeiMenuButton.isSelected() || trofeiMenuButton.isSelected()
				|| opzioniMenuButton.isSelected())
		{
			return true;
		}
		return false;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		if (background == null)
		{
			return;
		}
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		profiloMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		giocaMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		editorMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		extraMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		trofeiMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		creditiMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		opzioniMenuButton.paintMenuButton(g, this.getWidth(), this.getHeight());

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public void playThemeSong()
	{

	}

	public void setMainFrame(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}

	public void setClicked(boolean b)
	{
	}

	public void setMenuButtonSelected(int menuButtonSelected)
	{
		this.menuButtonSelected = menuButtonSelected;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerMenuPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}