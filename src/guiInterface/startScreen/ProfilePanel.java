package guiInterface.startScreen;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerProfilePanel;
import gameLogic.character.AttackSphere;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import profile.Profile;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;

import common.Sound;
import common.SoundManager;

public class ProfilePanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static MenuPanel menuPanel;
	private static int menuButtonSelected;
	private static String profileName = "";

	// Pulsanti
	private static MenuButton creaProfiloButton;
	private static MenuButton profilo1Button;
	private static MenuButton profilo2Button;
	private static MenuButton profilo3Button;
	private static MenuButton profilo4Button;

	public ProfilePanel(MainFrame mainFrame, final MenuPanel menuPanel)
	{
		this.mainFrame = mainFrame;
		this.menuPanel = menuPanel;
		menuButtonSelected = 1;
		try
		{
			background = ImageIO.read(new File("Images/MenuPanel/ProfilePanel/background2-01.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default.");
		}

		configureWiiMote();
		// this.addKeyListener(new PlayPanelKeyListener(this));
		addKeyListener(new KeyAdapter()
		{
			Sound buttonSound;
			boolean insertiProfileName = false;

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (!insertiProfileName)
				{
					super.keyPressed(e);
					switch (e.getKeyCode())
					{
						case KeyEvent.VK_DOWN:
						{
							ProfilePanel.changeButtonSelected(1);
							SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_RIGHT:
						{
							ProfilePanel.changeButtonSelected(1);
							SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_LEFT:
						{
							ProfilePanel.changeButtonSelected(-1);
							SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_UP:
						{
							ProfilePanel.changeButtonSelected(-1);
							SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_ESCAPE:
						{
							SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
							MainFrame mainFrame = ProfilePanel.getMainFrame();
							mainFrame.switchPanel(ProfilePanel.getMenuPanel());
							ProfilePanel.getMenuPanel().playThemeSong();
							break;
						}
						case KeyEvent.VK_ENTER:
						{
							SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
							if (creaProfiloButton.isSelected() && insertProfile())
								insertiProfileName = true;
							else
							{
								String name = null;
								String string;
								BufferedReader reader = null;
								try
								{
									reader = new BufferedReader(new FileReader(
											"profiles/profili.txt"));
								}
								catch (FileNotFoundException e2)
								{
									System.out.println("Exception");
								}
								try
								{
									int i = 0;
									while ((string = reader.readLine()) != null && i < 4)
									{
										if (i == menuButtonSelected - 2)
											name = string;
										i++;
									}

									Iterator<Profile> iterator = StaticConstantGame.profileManager
											.getProfiles().iterator();
									while (iterator.hasNext())
									{
										Profile tmp = iterator.next();
										if (tmp.getName().equals(name))
											StaticConstantGame.currentProfile = tmp;
									}

								}
								catch (IOException e2)
								{
									e2.printStackTrace();
								}
								try
								{
									reader.close();
								}
								catch (IOException e1)
								{
									System.out.println("Exception");
								}
							}
							break;
						}
						case KeyEvent.VK_DELETE:
						{
							SoundManager.playSound(SoundManager.MENU_CLICK_BUTTON, 1, 1);
							ProfilePanel.deleteProfile();
							break;
						}
					}
				}
				else
				{
					super.keyPressed(e);
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						buttonSound = new Sound("Suoni/Enter.wav", true);
						buttonSound.play(1);
						insertiProfileName = false;
						ProfilePanel.writeToFile();
						profileName = "";
					}
					else
					{
						char c = e.getKeyChar();
						profileName = profileName + c;
					}
				}
				repaint();
			}
		});

		creaProfiloButton = new MenuButton(StaticConstantURLMenuButton.CREA_PROFILO, true);
		profilo1Button = new MenuButton(StaticConstantURLMenuButton.SELEZIONA_PROFILO1);
		profilo2Button = new MenuButton(StaticConstantURLMenuButton.SELEZIONA_PROFILO2);
		profilo3Button = new MenuButton(StaticConstantURLMenuButton.SELEZIONA_PROFILO3);
		profilo4Button = new MenuButton(StaticConstantURLMenuButton.SELEZIONA_PROFILO4);
	}

	public static void deleteProfile()
	{
		String s;
		String[] string = new String[4];
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader("profiles/profili.txt"));
		}
		catch (FileNotFoundException e2)
		{
			System.out.println("Exception");
		}
		try
		{
			int i = 0;
			while ((s = reader.readLine()) != null && i < 4)
			{
				string[i] = s;
				i++;
			}
		}
		catch (IOException e2)
		{
			e2.printStackTrace();
		}
		try
		{
			reader.close();
		}
		catch (IOException e1)
		{
			System.out.println("Exception");
		}

		FileWriter writer = null;
		try
		{
			writer = new FileWriter("profiles/profili.txt");
		}
		catch (IOException e)
		{
			System.out.println("Non è possibile caricare il file (deleteProfile)");
		}
		int i = 0;
		String name = null;
		while (i < string.length)
		{
			if (i == menuButtonSelected - 2)
				name = string[i];
			if (((i != menuButtonSelected - 2)) && string[i] != null)
			{
				s = string[i];
				try
				{
					writer.write(s + "\n");
				}
				catch (IOException e)
				{
					System.out.println("Errore nella scrittura del file (deleteProfile)");
				}
			}
			i++;
		}

		CopyOnWriteArrayList<Profile> profiles = new CopyOnWriteArrayList<Profile>();
		profiles.addAll(StaticConstantGame.profileManager.getProfiles());
		Iterator<Profile> iterator = profiles.iterator();
		while (iterator.hasNext())
		{
			Profile tmp = iterator.next();
			if (tmp.getName().equals(name) && tmp != StaticConstantGame.currentProfile)
				try
				{
					StaticConstantGame.profileManager.deleteProfile(name);
				}
				catch (FileNotFoundException e)
				{
					System.out.println("Non riesco a cancellare un profilo dal profile manager.");
				}
		}

		try
		{
			writer.close();
		}
		catch (IOException e)
		{
			System.out.println("Errore nella chiusura del file");
		}
	}

	protected boolean insertProfile()
	{
		int i = 0;
		String s;
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader("profiles/profili.txt"));
		}
		catch (FileNotFoundException e2)
		{
			System.out.println("Exception");
		}
		try
		{
			float width = (this.getWidth() * 200) / 1366;
			while ((s = reader.readLine()) != null)
				i++;
		}
		catch (IOException e2)
		{
			e2.printStackTrace();
		}
		try
		{
			reader.close();
		}
		catch (IOException e1)
		{
			System.out.println("Exception");
		}

		if (i < 4)
			return true;
		return false;
	}

	protected static void changeButtonSelected(int change)
	{
		if ((change == 1) || (change == -1))
		{
			if ((menuButtonSelected == 1) && (change == -1))
			{
				return;
			}
			if ((menuButtonSelected == 5) && (change == 1))
			{
				return;
			}
			changeButtonIsSelected(false);
			menuButtonSelected += change;
			changeButtonIsSelected(true);
		}
	}

	private static void changeButtonIsSelected(boolean change)
	{
		switch (menuButtonSelected)
		{
			case 1:
			{
				creaProfiloButton.setSelected(change);
				break;
			}
			case 2:
			{
				profilo1Button.setSelected(change);
				break;
			}
			case 3:
			{
				profilo2Button.setSelected(change);
				break;
			}
			case 4:
			{
				profilo3Button.setSelected(change);
				break;
			}
			case 5:
			{
				profilo4Button.setSelected(change);
				break;
			}

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

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		if (background == null)
		{
			return;
		}
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		creaProfiloButton.paintMenuButton(g, this.getWidth(), this.getHeight());
		profilo1Button.paintMenuButton(g, this.getWidth(), this.getHeight());
		profilo2Button.paintMenuButton(g, this.getWidth(), this.getHeight());
		profilo3Button.paintMenuButton(g, this.getWidth(), this.getHeight());
		profilo4Button.paintMenuButton(g, this.getWidth(), this.getHeight());

		Font font = new Font("Triforce", Font.BOLD, 50);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.RED);
		FontRenderContext frc = g2.getFontRenderContext();
		float width = (this.getWidth() * 100) / 1366;
		if (!profileName.isEmpty())
		{
			TextLayout profileInsert = new TextLayout(profileName, font, frc);
			profileInsert.draw(g2, width, 230 * this.getHeight() / 768);
		}

		printProfiles(g);

		TextLayout currentProfile = new TextLayout("Profilo corrente:", font, frc);
		currentProfile.draw(g2, 40 * this.getWidth() / 1366, 730 * this.getHeight() / 768);

		Font font2 = new Font("Triforce", Font.BOLD, 60);
		Graphics2D g3 = (Graphics2D) g;
		g3.setFont(font2);
		g3.setColor(Color.RED);
		FontRenderContext frc2 = g2.getFontRenderContext();
		// String currentProfileName = "Profilo corrente: "
		// + StaticConstantGame.currentProfile.getName();

		if (StaticConstantGame.currentProfile != null)
			currentProfile = new TextLayout(StaticConstantGame.currentProfile.getName(), font, frc);
		else
			currentProfile = new TextLayout("Nessun profilo", font, frc);
		currentProfile.draw(g3, 460 * this.getWidth() / 1366, 730 * this.getHeight() / 768);

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	private static void writeToFile()
	{
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter(new BufferedWriter(
					new FileWriter("profiles/profili.txt", true)));
		}
		catch (IOException e)
		{
			System.out.println("problem");
		}
		if (profileName != "")
		{
			writer.println(profileName);

			Profile newProfile = new Profile();
			newProfile.setName(profileName);
			try
			{
				newProfile.writeProfileToFile();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			StaticConstantGame.profileManager.getProfiles().add(newProfile);
		}
		writer.close();
	}

	private void printProfiles(Graphics g)
	{
		Font font = new Font("Triforce", Font.BOLD, 50);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout profile;

		String s;
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader("profiles/profili.txt"));
		}
		catch (FileNotFoundException e2)
		{
			System.out.println("Exception");
		}
		try
		{
			int i = 0;
			float width = (this.getWidth() * 200) / 1366;
			while ((s = reader.readLine()) != null && s.length() != 0)
			{
				profile = new TextLayout(s, font, frc);
				if (i == 0)
					profile.draw(g2, width, 430 * this.getHeight() / 768);
				else if (i == 1)
					profile.draw(g2, width, 495 * this.getHeight() / 768);
				else if (i == 2)
					profile.draw(g2, width, 560 * this.getHeight() / 768);
				else if (i == 3)
					profile.draw(g2, width, 625 * this.getHeight() / 768);

				i++;
			}
		}
		catch (IOException e2)
		{
			e2.printStackTrace();
		}
		try
		{
			reader.close();
		}
		catch (IOException e1)
		{
			System.out.println("Exception");
		}

	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerProfilePanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
