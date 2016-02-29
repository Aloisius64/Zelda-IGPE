package guiInterface.startScreen;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;

import common.SoundManager;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerOptionPanel;

public class OptionPanel extends JPanel
{
	private static MainFrame mainFrame;
	private static MenuPanel menuPanel;
	private static int category;
	private static int controllerSelected;
	private static int difficoltaSelected;
	private static int effettiSelected;
	private static int keySelected;
	private static int suonoSelected;
	private final TriforceFont triforceFont;

	// Pulsanti
	private static MenuButton suonoButton;
	private static MenuButton suonoAttivoButton;
	private static MenuButton suonoDisattivoButton;

	private static MenuButton effettiButton;
	private static MenuButton effettiAttivoButton;
	private static MenuButton effettiDisattivoButton;

	private static MenuButton difficolt‡Button;
	private static MenuButton difficolt‡DifficileButton;
	private static MenuButton difficolt‡MediaButton;
	private static MenuButton difficolt‡RandomButton;

	private static MenuButton controllerButton;
	private static MenuButton controllerTastieraButton;
	private static MenuButton controllerJoypad;

	private static MenuButton comandiButton;
	private static MenuButton moveUpButton;
	private static MenuButton moveDownButton;
	private static MenuButton moveLeftButton;
	private static MenuButton moveRightButton;
	private static MenuButton attack1Button;
	private static MenuButton attack2Button;

	private Image background;

	public static void changeButtonSelected(int change)
	{
		switch (getCategory())
		{
			case 1:
				if (suonoSelected == 1 && change == -1 || suonoSelected == 2 && change == 1)
					return;
				setButtonSelected(false);
				suonoSelected += change;
				setButtonSelected(true);
				break;
			case 2:
				if (effettiSelected == 1 && change == -1 || effettiSelected == 2 && change == 1)
					return;
				setButtonSelected(false);
				effettiSelected += change;
				setButtonSelected(true);
				break;
			case 3:
				if (difficoltaSelected == 1 && change == -1 || difficoltaSelected == 3
						&& change == 1)
					return;
				setButtonSelected(false);
				difficoltaSelected += change;
				// Modifica alla variabile che decide l'AI
				StaticConstantGame.typeAI = difficoltaSelected;
				setButtonSelected(true);
				if (StaticConstantGame.gameRunning)
				{
					StaticConstantGame.gameManager.modifyAIEnemies();
				}
				break;
			case 4:
				if (controllerSelected == 1 && change == -1 || controllerSelected == 2
						&& change == 1)
					return;
				setButtonSelected(false);
				controllerSelected += change;
				setButtonSelected(true);
				break;
			case 5:
				if (keySelected == 1 && change == -1 || keySelected == 6 && change == 1)
					return;
				setButtonSelected(false);
				keySelected += change;
				setButtonSelected(true);
				break;
		}
	}

	public static void changeButtonSelectedFromWiiMote(int change)
	{
		switch (getCategory())
		{
			case 1:
			{
				setButtonSelected(false);
				suonoSelected = change;
				setButtonSelected(true);
				break;
			}
			case 2:
			{
				setButtonSelected(false);
				effettiSelected = change;
				setButtonSelected(true);
				break;
			}
			case 3:
			{
				setButtonSelected(false);
				difficoltaSelected = change;
				StaticConstantGame.typeAI = difficoltaSelected;
				if (StaticConstantGame.gameRunning)
				{
					StaticConstantGame.gameManager.modifyAIEnemies();
				}
				setButtonSelected(true);
				break;
			}
			case 4:
				setButtonSelected(false);
				controllerSelected = change;
				setButtonSelected(true);
				break;
		}
	}

	public static void changeCategories(int change)
	{
		if (getCategory() == 1 && change == -1 || getCategory() == 5 && change == 1)
			return;
		changeCategorySelected(false);
		setCategory(getCategory() + change);
		changeCategorySelected(true);
	}

	public static void changeCategorySelected(boolean b)
	{
		switch (getCategory())
		{
			case 1:
				suonoButton.setSelected(b);
				break;
			case 2:
				effettiButton.setSelected(b);
				break;
			case 3:
				difficolt‡Button.setSelected(b);
				break;
			case 4:
				controllerButton.setSelected(b);
				break;
			case 5:
				comandiButton.setSelected(b);
				break;
		}
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static MenuPanel getMenuPanel()
	{
		return menuPanel;
	}

	private static void setButtonSelected(boolean change)
	{
		if (getCategory() == 1)
		{
			if (suonoSelected == 1)
			{
				suonoAttivoButton.setSelected(change);
				// Modifica alla variabile del suono
				SoundManager.activeSound = change;
			}
			else
			{
				suonoDisattivoButton.setSelected(change);
			}
		}
		else if (getCategory() == 2)
		{
			if (effettiSelected == 1)
			{
				effettiAttivoButton.setSelected(change);
				// Modifica agli effetti
				SoundManager.activeEffect = change;
			}
			else
				effettiDisattivoButton.setSelected(change);
		}
		else if (getCategory() == 3)
		{
			if (difficoltaSelected == 1)
				difficolt‡MediaButton.setSelected(change);
			else if (difficoltaSelected == 2)
				difficolt‡DifficileButton.setSelected(change);
			else
				difficolt‡RandomButton.setSelected(change);

		}
		else if (getCategory() == 4)
		{
			if (controllerSelected == 1)
			{
				controllerTastieraButton.setSelected(change);
				StaticConstantGame.wiimoteActive = false;
			}
			else if (controllerSelected == 2)
			{
				controllerJoypad.setSelected(change);
				StaticConstantGame.wiimoteActive = true;
			}
		}
		else if (getCategory() == 5)
		{
			if (keySelected == 1)
				moveUpButton.setSelected(change);
			else if (keySelected == 2)
				moveDownButton.setSelected(change);
			else if (keySelected == 3)
				moveLeftButton.setSelected(change);
			else if (keySelected == 4)
				moveRightButton.setSelected(change);
			else if (keySelected == 5)
				attack1Button.setSelected(change);
			else if (keySelected == 6)
				attack2Button.setSelected(change);
		}
	}

	public OptionPanel(MainFrame mainFrame, final MenuPanel menuPanel)
	{
		triforceFont = new TriforceFont();
		this.setCategory(1);
		this.keySelected = 1;
		this.mainFrame = mainFrame;
		this.setMenuPanel(menuPanel);
		try
		{
			background = ImageIO.read(new File(StaticConstantURLMenuButton.OPTION_BACKGROUND));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  puÚ essere caricata (Non Ë presente nella cartella di default.");
		}

		// Inizializzazione pulsanti pannello
		suonoButton = new MenuButton(StaticConstantURLMenuButton.SUONO, true);
		effettiButton = new MenuButton(StaticConstantURLMenuButton.EFFETTI);
		difficolt‡Button = new MenuButton(StaticConstantURLMenuButton.DIFFICOLTA);
		controllerButton = new MenuButton(StaticConstantURLMenuButton.CONTROLLER);
		comandiButton = new MenuButton(StaticConstantURLMenuButton.COMANDI);

		moveUpButton = new MenuButton(StaticConstantURLMenuButton.MOVE_UP, true);
		moveDownButton = new MenuButton(StaticConstantURLMenuButton.MOVE_DOWN);
		moveLeftButton = new MenuButton(StaticConstantURLMenuButton.MOVE_LEFT);
		moveRightButton = new MenuButton(StaticConstantURLMenuButton.MOVE_RIGHT);
		attack1Button = new MenuButton(StaticConstantURLMenuButton.ATTACK1);
		attack2Button = new MenuButton(StaticConstantURLMenuButton.ATTACK2);

		if (SoundManager.activeSound)
		{
			this.suonoAttivoButton = new MenuButton(StaticConstantURLMenuButton.SUONO_ATTIVO, true);
			this.suonoDisattivoButton = new MenuButton(StaticConstantURLMenuButton.SUONO_DISATTIVO,
					false);
			suonoSelected = 1;
		}
		else
		{
			this.suonoAttivoButton = new MenuButton(StaticConstantURLMenuButton.SUONO_ATTIVO, false);
			this.suonoDisattivoButton = new MenuButton(StaticConstantURLMenuButton.SUONO_DISATTIVO,
					true);
			suonoSelected = 2;
		}
		if (SoundManager.activeEffect)
		{
			this.effettiAttivoButton = new MenuButton(StaticConstantURLMenuButton.EFFETTI_ATTIVI,
					true);
			this.effettiDisattivoButton = new MenuButton(
					StaticConstantURLMenuButton.EFFETTI_DISATTIVI, false);
			effettiSelected = 1;
		}
		else
		{
			this.effettiAttivoButton = new MenuButton(StaticConstantURLMenuButton.EFFETTI_ATTIVI,
					false);
			this.effettiDisattivoButton = new MenuButton(
					StaticConstantURLMenuButton.EFFETTI_DISATTIVI, true);
			effettiSelected = 2;
		}
		if (StaticConstantGame.typeAI == 2)
		{
			this.difficolt‡RandomButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_RANDOM);
			this.difficolt‡MediaButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_MEDIA);
			this.difficolt‡DifficileButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_DIFFICILE, true);
			difficoltaSelected = 2;
		}
		else if (StaticConstantGame.typeAI == 3)
		{
			this.difficolt‡RandomButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_RANDOM, true);
			this.difficolt‡MediaButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_MEDIA);
			this.difficolt‡DifficileButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_DIFFICILE);
			difficoltaSelected = 3;
		}
		else
		{
			this.difficolt‡RandomButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_RANDOM);
			this.difficolt‡MediaButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_MEDIA, true);
			this.difficolt‡DifficileButton = new MenuButton(
					StaticConstantURLMenuButton.DIFFICOLTA_DIFFICILE);
			difficoltaSelected = 1;
		}

		if (!StaticConstantGame.wiimoteActive)
		{
			this.controllerTastieraButton = new MenuButton(
					StaticConstantURLMenuButton.CONTROLLER_TASTIERA, true);
			this.controllerJoypad = new MenuButton(StaticConstantURLMenuButton.CONTROLLER_JOYPAD,
					false);
			controllerSelected = 1;
		}
		else
		{
			this.controllerTastieraButton = new MenuButton(
					StaticConstantURLMenuButton.CONTROLLER_TASTIERA, false);
			this.controllerJoypad = new MenuButton(StaticConstantURLMenuButton.CONTROLLER_JOYPAD,
					true);
			controllerSelected = 2;
		}

		configureWiiMote();
		addKeyListener(new KeyAdapter()
		{
			boolean enterPressed = false;

			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				if (!enterPressed)
				{
					switch (e.getKeyCode())
					{
						case KeyEvent.VK_DOWN:
						{
							OptionPanel.changeCategories(1);
							SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_RIGHT:
						{
							OptionPanel.changeButtonSelected(1);
							SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_LEFT:
						{
							OptionPanel.changeButtonSelected(-1);
							SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_UP:
						{
							OptionPanel.changeCategories(-1);
							SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_ESCAPE:
						{
							if (!StaticConstantGame.gameRunning)
							{
								SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
								OptionPanel.getMainFrame().switchPanel(menuPanel);
								OptionPanel.getMenuPanel().playThemeSong();
							}
							else
							{
								OptionPanel.getMainFrame().switchPanel(
										StaticConstantGame.gamePanel2D);
								StaticConstantGame.gamePanel2D.configureWiiMote();
							}
							break;
						}
						case KeyEvent.VK_ENTER:
						{
							if (getCategory() == 5)
							{
								enterPressed = true;
							}

						}
					}
				}
				else
				{
					int c = e.getKeyCode();
					switch (keySelected)
					{
						case 1:
							StaticConstantGame.moveUp = c;
							break;
						case 2:
							StaticConstantGame.moveDown = c;
							break;
						case 3:
							StaticConstantGame.moveLeft = c;
							break;
						case 4:
							StaticConstantGame.moveRight = c;
							break;
						case 5:
							StaticConstantGame.attack1 = c;
							break;
						case 6:
							StaticConstantGame.attack2 = c;
							break;
					}
					enterPressed = false;
				}
				repaint();
			}
		});
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

		suonoButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		suonoAttivoButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		suonoDisattivoButton.paintMenuButton(g, this.getWidth(), this.getHeight());

		effettiButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		effettiAttivoButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		effettiDisattivoButton.paintMenuButton(g, this.getWidth(), this.getHeight());

		difficolt‡Button.paintMenuButton(g, this.getWidth(), this.getHeight());
		difficolt‡DifficileButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		difficolt‡MediaButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		difficolt‡RandomButton.paintMenuButton(g, this.getWidth(), this.getHeight());

		controllerButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		controllerTastieraButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		controllerJoypad.paintMenuButton(g, this.getWidth(), this.getHeight());

		comandiButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		moveUpButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		moveDownButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		moveLeftButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		moveRightButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		attack1Button.paintMenuButton(g, this.getWidth(), this.getHeight());
		attack2Button.paintMenuButton(g, this.getWidth(), this.getHeight());

		Font font = new Font("Arial", Font.BOLD, 25);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.YELLOW);
		FontRenderContext frc = g2.getFontRenderContext();

		TextLayout moveUp;
		if (StaticConstantGame.moveUp == 38)
		{
			moveUp = new TextLayout(String.valueOf(Character.toChars(94)), font, frc);
		}
		else
		{
			moveUp = new TextLayout(String.valueOf(Character.toChars(StaticConstantGame.moveUp)),
					font, frc);
		}
		TextLayout moveDown;
		if (StaticConstantGame.moveDown == 40)
		{
			moveDown = new TextLayout(String.valueOf(Character.toChars(118)), font, frc);
		}
		else
		{
			moveDown = new TextLayout(
					String.valueOf(Character.toChars(StaticConstantGame.moveDown)), font, frc);
		}
		TextLayout moveLeft;
		if (StaticConstantGame.moveLeft == 37)
		{
			moveLeft = new TextLayout(String.valueOf(Character.toChars(60)), font, frc);
		}
		else
		{
			moveLeft = new TextLayout(
					String.valueOf(Character.toChars(StaticConstantGame.moveLeft)), font, frc);
		}
		TextLayout moveRight;
		if (StaticConstantGame.moveRight == 39)
		{
			moveRight = new TextLayout(String.valueOf(Character.toChars(62)), font, frc);
		}
		else
		{
			moveRight = new TextLayout(String.valueOf(Character
					.toChars(StaticConstantGame.moveRight)), font, frc);
		}
		TextLayout attack1 = new TextLayout(String.valueOf(Character
				.toChars(StaticConstantGame.attack1)), font, frc);
		TextLayout attack2 = new TextLayout(String.valueOf(Character
				.toChars(StaticConstantGame.attack2)), font, frc);

		float width = (this.getWidth() * 1251) / 1366;
		moveUp.draw(g2, width, (288 * this.getHeight() / 768));
		moveDown.draw(g2, width, (348 * this.getHeight() / 768));
		moveLeft.draw(g2, width, (418 * this.getHeight() / 768));
		moveRight.draw(g2, width, (483 * this.getHeight() / 768));
		attack1.draw(g2, width, (548 * this.getHeight() / 768));
		attack2.draw(g2, width, (608 * this.getHeight() / 768));

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerOptionPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}

	public static void setMenuPanel(MenuPanel menuPanel)
	{
		OptionPanel.menuPanel = menuPanel;
	}

	public static int getCategory()
	{
		return category;
	}

	public static void setCategory(int category)
	{
		OptionPanel.category = category;
	}
}
