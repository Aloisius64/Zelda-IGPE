package eventState.wiiMoteMenuPanelListener;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURLMenuButton;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;
import common.Sound;
import common.SoundManager;

import eventState.WiiMoteMenuListener;
import guiInterface.startScreen.ExtraPanel;
import guiInterface.startScreen.MainFrame;

public class WiiMoteListenerExtraPanel extends WiiMoteMenuListener
{
	private final ExtraPanel extraPanel;
	private boolean enter = false;
	private Sound music;

	public WiiMoteListenerExtraPanel(ExtraPanel extraPanel)
	{
		super();
		this.extraPanel = extraPanel;
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
					ExtraPanel.changeButtonSelected(1);
					SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
					stop();
					break;
				}
				case Direction.UP:
				{
					ExtraPanel.changeButtonSelected(-1);
					SoundManager.playSound(SoundManager.MENU_CHANGE_BUTTON, 1, 1);
					stop();
					break;
				}

			}
			extraPanel.repaint();
		}
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonAJustPressed())
		{
			if (!enter)
			{
				switch (ExtraPanel.getButtonSelected())
				{
					case 5:
						try
						{
							ExtraPanel.getExtra5().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA5STOP)));
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
							ExtraPanel.getExtra6().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA6STOP)));
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
							ExtraPanel.getExtra7().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA7STOP)));
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
							ExtraPanel.getExtra8().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA8STOP)));
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
			}
			else
			{
				switch (ExtraPanel.getButtonSelected())
				{
					case 5:
						try
						{
							ExtraPanel.getExtra5().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA5PLAY)));
						}
						catch (IOException e1)
						{
							System.out.println("Errore nel caricamento immagine.");
						}
						music.stop();
						enter = false;
						break;
					case 6:
						try
						{
							ExtraPanel.getExtra6().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA6PLAY)));
						}
						catch (IOException e1)
						{
							System.out.println("Errore nel caricamento immagine.");
						}
						music.stop();
						enter = false;
						break;
					case 7:
						try
						{
							ExtraPanel.getExtra7().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA7PLAY)));
						}
						catch (IOException e1)
						{
							System.out.println("Errore nel caricamento immagine.");
						}
						music.stop();
						enter = false;
						break;
					case 8:
						try
						{
							ExtraPanel.getExtra8().setImageSelected(
									ImageIO.read(new File(StaticConstantURLMenuButton.EXTRA8PLAY)));
						}
						catch (IOException e1)
						{
							System.out.println("Errore nel caricamento immagine.");
						}
						music.stop();
						enter = false;
						break;
				}
			}
		}
		else if (e.isButtonBJustPressed())
		{
			SoundManager.playSound(SoundManager.MAIN_MENU, 0, SoundManager.MUSIC);
			SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
			MainFrame mainFrame = ExtraPanel.getMainFrame();
			mainFrame.switchPanel(ExtraPanel.getMenuPanel());
			ExtraPanel.getMenuPanel().configureWiiMote();
		}
		else if (e.isButtonHomeJustPressed())
		{
			String pathLevel = "C:/Desktop";
			JFileChooser fileChooser = new JFileChooser(pathLevel);
			int dialog = fileChooser.showSaveDialog(ExtraPanel.getMainFrame());
			if (dialog == JFileChooser.APPROVE_OPTION)
			{
				File file = fileChooser.getSelectedFile();
				String filePath = file.getPath() + ".jpg";
				file = new File(filePath);
				try
				{
					switch (ExtraPanel.getButtonSelected())
					{
						case 1:
							ImageIO.write((RenderedImage) extraPanel.getImg1(), "PNG", file);
							break;
						case 2:
							ImageIO.write((RenderedImage) extraPanel.getImg2(), "PNG", file);
							break;
						case 3:
							ImageIO.write((RenderedImage) extraPanel.getImg3(), "PNG", file);
							break;
						case 4:
							ImageIO.write((RenderedImage) extraPanel.getImg4(), "PNG", file);
							break;
					}
				}
				catch (IOException e1)
				{
					System.out.println("Impossibile salvare l'immagine.");
				}
			}
		}
		extraPanel.repaint();
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		super.onIrEvent(e);

		int x = getxPosition();
		int y = getyPosition();

		// Extra 1
		if (x >= 137 && y >= 100 && x <= 345 && y <= 140)
		{
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[0])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(1);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 124 && y >= 174 && x <= 344 && y <= 218)
		{ // Extra 2
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[1])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(2);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 125 && y >= 245 && x <= 334 && y <= 287)
		{ // Extra 3
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[2])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(3);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 127 && y >= 323 && x <= 334 && y <= 364)
		{ // Extra 4
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[3])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(4);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 125 && y >= 411 && x <= 330 && y <= 449)
		{ // Extra 5
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[4])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(5);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 122 && y >= 488 && x <= 330 && y <= 526)
		{ // Extra 6
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[5])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(6);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 128 && y >= 569 && x <= 326 && y <= 607)
		{ // Extra 7
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[6])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(7);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}
		else if (x >= 125 && y >= 643 && x <= 331 && y <= 675)
		{ // Extra 8
			if (StaticConstantGame.currentProfile.getExtraUnlocked()[7])
			{
				ExtraPanel.changeButtonIsSelected(false);
				ExtraPanel.setButtonSelected(8);
				ExtraPanel.changeButtonIsSelected(true);
			}
		}

		extraPanel.repaint();
	}

	public ExtraPanel getExtraPanel()
	{
		return extraPanel;
	}
}
