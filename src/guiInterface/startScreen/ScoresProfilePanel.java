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

import profile.Profile;

import staticConstant.StaticConstantGame;

import common.SoundManager;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerMenuPanel;
import eventState.wiiMoteMenuPanelListener.WiiMoteListenerScoresProfilePanel;

public class ScoresProfilePanel extends JPanel
{
	private Image background;
	private static MainFrame mainFrame;
	private static TrophiesPanel panel;

	public ScoresProfilePanel(MainFrame mainFrame, TrophiesPanel trophiesPanel)
	{
		this.setMainFrame(mainFrame);
		this.setPanel(trophiesPanel);

		try
		{
			background = ImageIO.read(new File(
					"Images/MenuPanel/TrophiesPanel/StatisticheProfilo-01.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
		}

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
						MainFrame mainFrame = ScoresProfilePanel.getMainFrame();
						mainFrame.switchPanel(ScoresProfilePanel.getPanel());
						break;
					}
				}
				repaint();
			}
		});
	}

	public static void setMainFrame(MainFrame mainFrame)
	{
		ScoresProfilePanel.mainFrame = mainFrame;
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
		Profile profile = StaticConstantGame.currentProfile;

		Font font = new Font("Triforce", Font.BOLD, 80);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout scores;

		if (profile == null)
		{
			scores = new TextLayout("Nessun profilo selezionato", font, frc);
			scores.draw(g2, 30 * this.getWidth() / 1366, 100 * this.getHeight() / 768);
		}
		else
		{
			scores = new TextLayout("Statistiche: " + profile.getName(), font, frc);
			scores.draw(g2, 30 * this.getWidth() / 1366, 100 * this.getHeight() / 768);

			Font font2 = new Font("Arial", Font.BOLD, 30);
			Graphics2D g3 = (Graphics2D) g;
			g3.setFont(font2);
			g3.setColor(Color.WHITE);
			FontRenderContext frc2 = g2.getFontRenderContext();
			TextLayout text;

			String string;
			float x = 570 * this.getWidth() / 1366;

			string = "" + profile.getEnemyDefeated();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 223 * this.getHeight() / 768);

			string = "" + profile.getGameTime();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 293 * this.getHeight() / 768);

			string = "" + profile.getTreasureOpen();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 366 * this.getHeight() / 768);

			string = "" + profile.getTotalScore();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 435 * this.getHeight() / 768);

			string = "" + profile.getHeartsCollected();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 510 * this.getHeight() / 768);

			string = "" + profile.getMagicSphereCollected();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 580 * this.getHeight() / 768);

			string = "" + profile.getLevelsUnlocked();
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 650 * this.getHeight() / 768);

			int cont = 0;
			for (int i = 0; i < profile.getExtraUnlocked().length; i++)
				if (profile.getExtraUnlocked()[i])
					cont++;
			string = "" + cont;
			text = new TextLayout(string, font2, frc2);
			text.draw(g3, x, 720 * this.getHeight() / 768);
		}
	}

	public static void setPanel(TrophiesPanel panel)
	{
		ScoresProfilePanel.panel = panel;
	}

	public static TrophiesPanel getPanel()
	{
		return panel;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerScoresProfilePanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
