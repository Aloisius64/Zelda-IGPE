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

import common.Sound;
import common.SoundManager;

import eventState.wiiMoteMenuPanelListener.WiiMoteListenerEndLevelPanel;

public class EndLevelPanel extends JPanel
{
	private static MainFrame mainFrame;
	private Image background;
	private final int gameTime;

	public EndLevelPanel(MainFrame mainFrame, boolean win)
	{
		EndLevelPanel.setMainFrame(mainFrame);

		gameTime = (int) (System.currentTimeMillis() / 1000)
				- (StaticConstantGame.gameManager.getInitTime());

		if (win)
		{
			try
			{
				background = ImageIO.read(new File(
						"Images/MenuPanel/EndLevelPanel/LevelComplete-01.png"));
			}
			catch (IOException e)
			{
				System.out
						.println("L'immagine di background non  può essere caricata (Non è presente nella cartella EndLevelPanel.)");
			}
		}
		else
		{
			try
			{
				background = ImageIO.read(new File(
						"Images/MenuPanel/EndLevelPanel/LevelCompleteLose-01.png"));
			}
			catch (IOException e)
			{
				System.out
						.println("L'immagine di background non  può essere caricata (Non è presente nella cartella EndLevelPanel.)");
			}
		}

		configureWiiMote();
		addKeyListener(new KeyAdapter()
		{
			Sound buttonSound;

			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				switch (e.getKeyCode())
				{
					case KeyEvent.VK_ENTER:
					{
						SoundManager.playSound(SoundManager.MENU_BACK_BUTTON, 1, 1);
						MainFrame mainFrame = EndLevelPanel.getMainFrame();
						mainFrame.switchPanel(new MenuPanel(mainFrame));
						break;
					}
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

		Font font = new Font("Arial", Font.BOLD, 30);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		FontRenderContext frc = g2.getFontRenderContext();

		float width = (this.getWidth() * 650) / 1366;

		// String s1 = "" + StaticConstantGame.gameManager.getEnemyDefeated();
		int tmp = (StaticConstantGame.gameManager.getCharacterCollection().size() - StaticConstantGame.gameManager
				.getConcreteWorld().getNumberEnemies());
		String s1 = "" + tmp;
		TextLayout text = new TextLayout(s1, font, frc);
		text.draw(g2, width, 300 * this.getHeight() / 768);

		String s2 = "" + gameTime;
		text = new TextLayout(s2, font, frc);
		text.draw(g2, width, 370 * this.getHeight() / 768);

		String s3 = "" + StaticConstantGame.gameManager.getTreasureOpen();
		text = new TextLayout(s3, font, frc);
		text.draw(g2, width, 440 * this.getHeight() / 768);

		String s4 = "" + StaticConstantGame.gameManager.getHero().getScore();
		text = new TextLayout(s4, font, frc);
		text.draw(g2, width, 510 * this.getHeight() / 768);

		if (StaticConstantGame.activeNavi)
		{
			StaticConstantGame.navi.drawObject(g, 0, 0, 0, 0);
		}
	}

	public static void setMainFrame(MainFrame mainFrame)
	{
		EndLevelPanel.mainFrame = mainFrame;
	}

	public static MainFrame getMainFrame()
	{
		return mainFrame;
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListenerEndLevelPanel(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}
}
