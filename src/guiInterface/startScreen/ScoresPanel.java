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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import profile.Profile;
import profile.Score;

import staticConstant.StaticConstantGame;

import common.SoundManager;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerScoresPanel;

public class ScoresPanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static TrophiesPanel panel;

	public ScoresPanel(MainFrame mainFrame, TrophiesPanel panel)
	{
		this.mainFrame = mainFrame;
		this.setTrophiesPanel(panel);

		try
		{
			background = ImageIO.read(new File("Images/MenuPanel/TrophiesPanel/punteggi-01.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
		}

		loadScores();

		configureWiiMote();
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_ESCAPE:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = ScoresPanel.getMainFrame();
						mainFrame.switchPanel(ScoresPanel.getTrophiesPanel());
						break;
					}
				}
				repaint();
			}
		});
	}

	@SuppressWarnings("null")
	private void loadScores()
	{
		List<Score> scores = new ArrayList<Score>();
		Profile p = null;
		String s;
		Score score;

		Iterator<Profile> iterator = StaticConstantGame.profileManager.getProfiles().iterator();
		while (iterator.hasNext())
		{
			p = iterator.next();

			Iterator<Score> i1 = p.getKakariko().iterator();
			while (i1.hasNext())
			{
				score = i1.next();
				// s = score.getPlayer() + "  " + score.getScores() + "  " +
				// score.getTime() + "  "
				// + score.getTime();
				scores.add(score);
			}
			Iterator<Score> i2 = p.getDesert().iterator();
			while (i2.hasNext())
			{
				score = i2.next();
				// s = score.getPlayer() + "  " + score.getScores() + "  " +
				// score.getTime() + "  "
				// + score.getTime();
				scores.add(score);
			}
			Iterator<Score> i3 = p.getDungeon().iterator();
			while (i3.hasNext())
			{
				score = i3.next();
				// s = score.getPlayer() + "  " + score.getScores() + "  " +
				// score.getTime() + "  "
				// + score.getTime();
				scores.add(score);
			}
			Iterator<Score> i4 = p.getForest().iterator();
			while (i4.hasNext())
			{
				score = i4.next();
				// s = score.getPlayer() + "  " + score.getScores() + "  " +
				// score.getTime() + "  "
				// + score.getTime();
				scores.add(score);
			}
			Iterator<Score> i5 = p.getLake().iterator();
			while (i5.hasNext())
			{
				score = i5.next();
				// s = score.getPlayer() + "  " + score.getScores() + "  " +
				// score.getTime() + "  "
				// + score.getTime();
				scores.add(score);
			}
			Iterator<Score> i6 = p.getVolcano().iterator();
			while (i6.hasNext())
			{
				score = i6.next();
				// s = score.getPlayer() + "  " + score.getScores() + "  " +
				// score.getTime() + "  "
				// + score.getTime();
				scores.add(score);
			}
			Iterator<Score> i7 = p.getSky().iterator();
			while (i7.hasNext())
			{
				score = i7.next();
				scores.add(score);
			}
		}
		List<Score> sortedRanking = scores;
		Collections.sort(sortedRanking);

		int i = 0;
		// while (i < 0)
		// {
		String string = "";
		Score sco;
		FileWriter writer = null;
		try
		{
			writer = new FileWriter("Images/MenuPanel/TrophiesPanel/punteggi.txt");
		}
		catch (IOException e)
		{
			System.out.println("Non è possibile caricare il file");
		}
		Iterator<Score> is = sortedRanking.iterator();
		while (is.hasNext() && i < 10)
		{
			sco = is.next();
			string = sco.getPlayer() + " " + sco.getScores() + " " + sco.getTimeToClock() + " "
					+ sco.getLevel();
			try
			{
				writer.write(string + "\n");
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
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

	public static MainFrame getMainFrame()
	{
		return mainFrame;
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

		drawScores(g);

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	private void drawScores(Graphics g)
	{
		Font font = new Font("Arial", Font.BOLD, 30);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout scores;

		String s;
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader(
					"Images/MenuPanel/TrophiesPanel/punteggi.txt"));
		}
		catch (FileNotFoundException e2)
		{
			System.out.println("Exception");
		}
		try
		{
			int i = 0;
			float width = 180;
			float height = 265;
			while ((s = reader.readLine()) != null && i < 10)
			{
				String[] string = s.split(" ");
				for (int j = 0; j < string.length; j++)
				{
					if (j == 1)
						width = 500;
					else if (j == 2)
						width = 880;
					else if (j == 3)
						width = 1200;
					scores = new TextLayout(string[j], font, frc);
					scores.draw(g2, width * this.getWidth() / 1366, height * this.getHeight() / 768);
				}
				i++;
				width = 180;
				height += 50;
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
			StaticConstantGame.wiimoteListener = new WiiMoteListenerScoresPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}

	public static TrophiesPanel getTrophiesPanel()
	{
		return panel;
	}

	public static void setTrophiesPanel(TrophiesPanel panel)
	{
		ScoresPanel.panel = panel;
	}
}
