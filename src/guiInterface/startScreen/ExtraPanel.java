package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;

import common.Sound;
import common.SoundManager;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerExtraPanel;

public class ExtraPanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static MenuPanel menuPanel;
	private static int buttonSelected;

	// Button
	private static MenuButton extra1;
	private static MenuButton extra2;
	private static MenuButton extra3;
	private static MenuButton extra4;
	private static MenuButton extra5;
	private static MenuButton extra6;
	private static MenuButton extra7;
	private static MenuButton extra8;

	// Image
	private Image img1;
	private Image img2;
	private Image img3;
	private Image img4;

	public ExtraPanel(MainFrame mainFrame, final MenuPanel menuPanel)
	{
		this.mainFrame = mainFrame;
		this.menuPanel = menuPanel;
		if (StaticConstantGame.currentProfile.getExtraUnlocked()[0])
		{
			this.setButtonSelected(1);
			try
			{
				background = ImageIO.read(new File("Images/MenuPanel/ExtraPanel/extra1-01.png"));
				img1 = ImageIO.read(new File("Images/MenuPanel/ExtraPanel/img1.png"));
				img2 = ImageIO.read(new File("Images/MenuPanel/ExtraPanel/img2.png"));
				img3 = ImageIO.read(new File("Images/MenuPanel/ExtraPanel/img3.png"));
				img4 = ImageIO.read(new File("Images/MenuPanel/ExtraPanel/img4.png"));
			}
			catch (IOException e)
			{
				System.out
						.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default.)");
			}
		}
		else
		{
			setButtonSelected(0);
			extra1 = new MenuButton(StaticConstantURLMenuButton.EXTRA1);
			try
			{
				background = ImageIO
						.read(new File("Images/MenuPanel/ExtraPanel/background2-01.png"));
			}
			catch (IOException e)
			{
				System.out
						.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default.)");
			}
		}

		configureWiiMote();

		addKeyListener(new KeyAdapter()
		{
			boolean enter = false;
			Sound music;

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (!enter)
				{
					super.keyPressed(e);
					switch (e.getKeyCode())
					{
						case KeyEvent.VK_DOWN:
						{
							ExtraPanel.changeButtonSelected(1);
							SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_UP:
						{
							ExtraPanel.changeButtonSelected(-1);
							SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
							break;
						}
						case KeyEvent.VK_S:
						{
							String pathLevel = "C:/Desktop";
							JFileChooser fileChooser = new JFileChooser(pathLevel);
							int dialog = fileChooser.showSaveDialog(ExtraPanel.mainFrame);
							if (dialog == JFileChooser.APPROVE_OPTION)
							{
								File file = fileChooser.getSelectedFile();
								String filePath = file.getPath() + ".jpg";
								file = new File(filePath);
								try
								{
									// ImageIO.write((RenderedImage)
									// extra1.getImageSelected(), "PNG",
									// file);
									switch (getButtonSelected())
									{
										case 1:
											ImageIO.write((RenderedImage) img1, "PNG", file);
											break;
										case 2:
											ImageIO.write((RenderedImage) img2, "PNG", file);
											break;
										case 3:
											ImageIO.write((RenderedImage) img3, "PNG", file);
											break;
										case 4:
											ImageIO.write((RenderedImage) img4, "PNG", file);
											break;
									}
								}
								catch (IOException e1)
								{
									System.out.println("Impossibile salvare l'immagine.");
								}
							}
						}
						case KeyEvent.VK_ENTER:
						{
							switch (getButtonSelected())
							{
								case 5:
									try
									{
										ExtraPanel.extra5.setImageSelected(ImageIO.read(new File(
												StaticConstantURLMenuButton.EXTRA5STOP)));
									}
									catch (IOException e1)
									{
										System.out.println("Errore nel caricamento immagine.");
									}
									music = new Sound("Sounds/Music/Desert.wav", true);
									music.play(5);
									enter = true;
									break;
								case 6:
									try
									{
										ExtraPanel.extra6.setImageSelected(ImageIO.read(new File(
												StaticConstantURLMenuButton.EXTRA6STOP)));
									}
									catch (IOException e1)
									{
										System.out.println("Errore nel caricamento immagine.");
									}
									music = new Sound("Sounds/Music/Sky.wav", true);
									music.play(5);
									enter = true;
									break;
								case 7:
									try
									{
										ExtraPanel.extra7.setImageSelected(ImageIO.read(new File(
												StaticConstantURLMenuButton.EXTRA7STOP)));
									}
									catch (IOException e1)
									{
										System.out.println("Errore nel caricamento immagine.");
									}
									music = new Sound("Sounds/Music/Forest.wav", true);
									music.play(5);
									enter = true;
									break;
								case 8:
									try
									{
										ExtraPanel.extra8.setImageSelected(ImageIO.read(new File(
												StaticConstantURLMenuButton.EXTRA8STOP)));
									}
									catch (IOException e1)
									{
										System.out.println("Errore nel caricamento immagine.");
									}
									music = new Sound("Sounds/Music/Lake.wav", true);
									music.play(5);
									enter = true;
									break;
							}
							break;
						}
						case KeyEvent.VK_ESCAPE:
						{
							SoundManager.playSound(SoundManager.MAIN_MENU, 0, SoundManager.MUSIC);
							SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
							MainFrame mainFrame = ExtraPanel.getMainFrame();
							mainFrame.switchPanel(ExtraPanel.getMenuPanel());
							ExtraPanel.getMenuPanel().playThemeSong();
							break;
						}
					}
				}
				else
				{
					if (e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						switch (getButtonSelected())
						{
							case 5:
								try
								{
									ExtraPanel.extra5.setImageSelected(ImageIO.read(new File(
											StaticConstantURLMenuButton.EXTRA5PLAY)));
								}
								catch (IOException e1)
								{
									System.out.println("Errore nel caricamento immagine.");
								}
								music.stop();
								break;
							case 6:
								try
								{
									ExtraPanel.extra6.setImageSelected(ImageIO.read(new File(
											StaticConstantURLMenuButton.EXTRA6PLAY)));
								}
								catch (IOException e1)
								{
									System.out.println("Errore nel caricamento immagine.");
								}
								music.stop();
								break;
							case 7:
								try
								{
									ExtraPanel.extra7.setImageSelected(ImageIO.read(new File(
											StaticConstantURLMenuButton.EXTRA7PLAY)));
								}
								catch (IOException e1)
								{
									System.out.println("Errore nel caricamento immagine.");
								}
								music.stop();
								break;
							case 8:
								try
								{
									ExtraPanel.extra8.setImageSelected(ImageIO.read(new File(
											StaticConstantURLMenuButton.EXTRA8PLAY)));
								}
								catch (IOException e1)
								{
									System.out.println("Errore nel caricamento immagine.");
								}
								music.stop();
								break;
						}
						enter = false;
					}
				}
				repaint();
			}
		});

		extra1 = new MenuButton(StaticConstantURLMenuButton.EXTRA1);
		extra2 = new MenuButton(StaticConstantURLMenuButton.EXTRA2);
		extra3 = new MenuButton(StaticConstantURLMenuButton.EXTRA3);
		extra4 = new MenuButton(StaticConstantURLMenuButton.EXTRA4);
		extra5 = new MenuButton(StaticConstantURLMenuButton.EXTRA5PLAY);
		extra6 = new MenuButton(StaticConstantURLMenuButton.EXTRA6PLAY);
		extra7 = new MenuButton(StaticConstantURLMenuButton.EXTRA7PLAY);
		extra8 = new MenuButton(StaticConstantURLMenuButton.EXTRA8PLAY);
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public static MenuPanel getMenuPanel()
	{
		return menuPanel;
	}

	public static void changeButtonSelected(int change)
	{
		if (getButtonSelected() == 0)
		{
			return;
		}
		else if ((change == 1) || (change == -1))
		{
			if ((getButtonSelected() == 1) && (change == -1))
			{
				return;
			}
			if ((getButtonSelected() == 8) && (change == 1))
			{
				return;
			}
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[getButtonSelected() - 1])
			{
				changeButtonIsSelected(false);
				setButtonSelected(getButtonSelected() + change);
				changeButtonIsSelected(true);
			}
		}
	}

	public static void changeButtonIsSelected(boolean change)
	{
		switch (getButtonSelected())
		{
			case 1:
			{
				extra1.setSelected(change);
				break;
			}
			case 2:
			{
				extra2.setSelected(change);
				break;
			}
			case 3:
			{
				extra3.setSelected(change);
				break;
			}
			case 4:
			{
				extra4.setSelected(change);
				break;
			}
			case 5:
			{
				extra5.setSelected(change);
				break;
			}
			case 6:
			{
				extra6.setSelected(change);
				break;
			}
			case 7:
			{
				extra7.setSelected(change);
				break;
			}
			case 8:
			{
				extra8.setSelected(change);
				break;
			}
		}
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

		extra1.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra2.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra3.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra4.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra5.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra6.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra7.paintMenuButton(g, this.getWidth(), this.getHeight());
		extra8.paintMenuButton(g, this.getWidth(), this.getHeight());

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
			StaticConstantGame.wiimoteListener = new WiiMoteListenerExtraPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}

	public static int getButtonSelected()
	{
		return buttonSelected;
	}

	public static void setButtonSelected(int buttonSelected)
	{
		ExtraPanel.buttonSelected = buttonSelected;
	}

	public static MenuButton getExtra1()
	{
		return extra1;
	}

	public static MenuButton getExtra2()
	{
		return extra2;
	}

	public static MenuButton getExtra3()
	{
		return extra3;
	}

	public static MenuButton getExtra4()
	{
		return extra4;
	}

	public static MenuButton getExtra5()
	{
		return extra5;
	}

	public static MenuButton getExtra6()
	{
		return extra6;
	}

	public static MenuButton getExtra7()
	{
		return extra7;
	}

	public static MenuButton getExtra8()
	{
		return extra8;
	}

	public Image getImg1()
	{
		return img1;
	}

	public Image getImg2()
	{
		return img2;
	}

	public Image getImg3()
	{
		return img3;
	}

	public Image getImg4()
	{
		return img4;
	}

	public static void setMainFrame(MainFrame mainFrame)
	{
		ExtraPanel.mainFrame = mainFrame;
	}

	public static void setMenuPanel(MenuPanel menuPanel)
	{
		ExtraPanel.menuPanel = menuPanel;
	}

	public static void setExtra1(MenuButton extra1)
	{
		ExtraPanel.extra1 = extra1;
	}

	public static void setExtra2(MenuButton extra2)
	{
		ExtraPanel.extra2 = extra2;
	}

	public static void setExtra3(MenuButton extra3)
	{
		ExtraPanel.extra3 = extra3;
	}

	public static void setExtra4(MenuButton extra4)
	{
		ExtraPanel.extra4 = extra4;
	}

	public static void setExtra5(MenuButton extra5)
	{
		ExtraPanel.extra5 = extra5;
	}

	public static void setExtra6(MenuButton extra6)
	{
		ExtraPanel.extra6 = extra6;
	}

	public static void setExtra7(MenuButton extra7)
	{
		ExtraPanel.extra7 = extra7;
	}

	public static void setExtra8(MenuButton extra8)
	{
		ExtraPanel.extra8 = extra8;
	}

	public void setImg1(Image img1)
	{
		this.img1 = img1;
	}

	public void setImg2(Image img2)
	{
		this.img2 = img2;
	}

	public void setImg3(Image img3)
	{
		this.img3 = img3;
	}

	public void setImg4(Image img4)
	{
		this.img4 = img4;
	}
}
