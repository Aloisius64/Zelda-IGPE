package guiInterface;

import eventState.KeyBoardListener;
import eventState.WiiMoteListener2D;
import gameLogic.Drawable;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AttackSphere;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.dungeon.Dungeon13;
import gameLogic.scene.dungeon.Dungeon15;
import gameLogic.scene.dungeon.Dungeon6;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import guiInterface.character2D.Character2D;
import guiInterface.character2D.Hero2D;
import guiInterface.startScreen.MainFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;
import threadState.EnemyManager;
import threadState.MessageBoardManager;
import threadState.ObjectManager;
import threadState.SphereManager;
import threadState.WorldUpdater;

import common.ImageProvider;

public class GamePanel2D extends JPanel
{
	private final MainFrame mainFrame;
	private boolean pause;
	private int pauseSelected = 1;

	int fogEffect = 0;
	int rainEffect = 0;
	int temporized = 0;
	int translateLongX = 0;
	int counter = 0;
	int typeTriforce = 0;

	public GamePanel2D(MainFrame mainFrame)
	{
		this.mainFrame = mainFrame;

		configureWiiMote();
		addKeyListener(new KeyBoardListener(this));
		assignToCharacter();

		// Object
		StaticConstantGame.objectManager = new ObjectManager(this);

		// Character
		new EnemyManager(0, 0, 5, 5);
		new EnemyManager(0, 5, 5, 5);
		new EnemyManager(5, 0, 5, 5);
		new EnemyManager(5, 5, 5, 5);

		// /// SPHERE //////
		StaticConstantGame.sphereManager = new SphereManager(this);

		// WorldUpdater
		StaticConstantGame.worldUpdater = new WorldUpdater(
				StaticConstantGame.gameManager.getConcreteWorld());

		StaticConstantGame.messageBoardManager = new MessageBoardManager();
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new WiiMoteListener2D();
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}

	private void assignToCharacter()
	{
		if (StaticConstantGame.gameManager.getHero() instanceof Hero2D)
		{
			((Hero2D) StaticConstantGame.gameManager.getHero()).setPanel(this);
		}

		Iterator<AbstractCharacter> iteratorCharacter = StaticConstantGame.gameManager
				.getCharacterCollection().iterator();

		while (iteratorCharacter.hasNext())
		{
			((Character2D) iteratorCharacter.next()).setPanel(this);
		}
	}

	private void paintWorld(final Graphics g, final World currentWorld)
	{
		int translateX = (this.getWidth() * 320) / 1366;
		int translateY = (this.getHeight() * 30) / 768;
		int widthSize = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int heightSize = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION);
		int xPrint = StaticConstantGame.gameManager.getConcreteWorld().getxPrint();
		int yPrint = StaticConstantGame.gameManager.getConcreteWorld().getyPrint();

		int i = xPrint;
		int j = yPrint;

		int dim = StaticConstantGame.gameManager.getConcreteWorld().getWidth();
		for (int x = xPrint - StaticConstantWorld.X_DIMENSION; x < (xPrint + (StaticConstantWorld.X_DIMENSION * 2)); x++)
		{
			for (int y = yPrint - StaticConstantWorld.Y_DIMENSION; y < (yPrint + (StaticConstantWorld.Y_DIMENSION * 2)); y++)
			{
				if (x >= 0 && x < dim && y >= 0 && y < dim)
				{
					AbstractObjectScene baseCell = currentWorld.getCellBaseWorld(x, y);
					if (baseCell instanceof Drawable)
					{
						((Drawable) baseCell).drawObject(g, translateX + y * widthSize
								- (j * widthSize), translateY + x * heightSize - (i * heightSize),
								widthSize, heightSize);
					}

				}
			}
		}

		for (int x = xPrint; x < (xPrint + StaticConstantWorld.X_DIMENSION); x++)
		{
			for (int y = yPrint; y < (yPrint + StaticConstantWorld.Y_DIMENSION); y++)
			{
				AbstractUtilityObject objectCell = currentWorld.getCellObjectWorld(x, y);
				if (objectCell instanceof Drawable)
				{
					((Drawable) objectCell).drawObject(g, translateX + y * widthSize
							- (j * widthSize), translateY + x * heightSize - (i * heightSize),
							widthSize, heightSize);
				}
			}
		}

		for (int x = xPrint; x < (xPrint + StaticConstantWorld.X_DIMENSION); x++)
		{
			for (int y = yPrint; y < (yPrint + StaticConstantWorld.Y_DIMENSION); y++)
			{
				AbstractCharacter characterCell = currentWorld.getCellCharacterWorld(x, y);
				if (characterCell instanceof Character2D)
				{
					((Character2D) characterCell).drawObject(g, translateX + y * widthSize
							- (j * widthSize), translateY + x * heightSize - (i * heightSize),
							widthSize, heightSize);
				}

			}
		}

		if (StaticConstantGame.typeWorldChoose == 3)
		{
			int x = ((ConcreteWorld) currentWorld).getxPrint() + 9;
			int y = ((ConcreteWorld) currentWorld).getyPrint() + 9;

			AbstractObjectScene baseCell = currentWorld.getCellBaseWorld(x, y);
			if (baseCell instanceof Dungeon6)
			{
				g.drawImage(ImageProvider.dungeonAbove[0], translateX + y, translateY + x,
						widthSize * 9, heightSize * 9, null);
			}
			else if (baseCell instanceof Dungeon13)
			{
				g.drawImage(ImageProvider.dungeonAbove[1], translateX + y, translateY + x,
						widthSize * 9, heightSize * 9, null);
			}
			else if (baseCell instanceof Dungeon15)
			{
				g.drawImage(ImageProvider.dungeonAbove[2], translateX + y, translateY + x,
						widthSize * 9, heightSize * 9, null);
			}
		}
	}

	@Override
	protected void paintComponent(final Graphics g)
	{
		super.paintComponent(g);

		Hero2D hero = (Hero2D) StaticConstantGame.gameManager.getHero();

		World currentWorld = StaticConstantGame.gameManager.getConcreteWorld();
		paintWorld(g, currentWorld);

		printAttackSphere(g);

		int translateX = (this.getWidth() * 310) / 1366;
		int translateY = (this.getHeight() * 30) / 768;

		int dimensionEffect = 900;

		// Gestione effetti
		if (StaticConstantGame.rain)
		{

			g.drawImage(ImageProvider.rain[rainEffect], translateX, translateY, dimensionEffect,
					dimensionEffect, null);
			if (temporized % 10 == 0)
				rainEffect = (rainEffect + 1) % ImageProvider.rain.length;
		}

		if (StaticConstantGame.fog)
		{

			g.drawImage(ImageProvider.fog[fogEffect], translateX, translateY, dimensionEffect,
					dimensionEffect, null);
			if (temporized % 50 == 0)
				fogEffect = (fogEffect + 1) % ImageProvider.fog.length;
		}

		if (StaticConstantGame.volcanoFog)
		{
			g.drawImage(ImageProvider.volcanoFog[fogEffect], translateX, translateY,
					dimensionEffect, dimensionEffect, null);
			if (temporized % 50 == 0)
				fogEffect = (fogEffect + 1) % ImageProvider.volcanoFog.length;
		}

		if (StaticConstantGame.cloud)
		{
			g.drawImage(ImageProvider.cloud, ((translateX + translateLongX) % 1760) - 520,
					translateY, 720, 680, null);
			translateLongX++;
		}

		if (StaticConstantGame.darkness)
		{
			int x = hero.getX() % StaticConstantWorld.X_DIMENSION;
			int y = hero.getY() % StaticConstantWorld.Y_DIMENSION;
			int width = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
			int height = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;

			g.drawImage(ImageProvider.darkness[StaticConstantGame.typeDarkness], y * height - 410
					+ hero.getDeltaY(), x * width - 720 + hero.getDeltaX(), null);
		}
		// Fine Gestione Effetti

		if (isPause())
		{
			g.drawImage(ImageProvider.gamePause, translateX, translateY, 740, 740, null);
			if (pauseSelected == 1)
				g.drawImage(ImageProvider.gamePauseContinue, translateX, translateY, 740, 740, null);
			if (pauseSelected == 2)
				g.drawImage(ImageProvider.gamePauseOption, translateX, translateY, 740, 740, null);
			if (pauseSelected == 3)
				g.drawImage(ImageProvider.gamePauseExit, translateX, translateY, 740, 740, null);
		}

		g.drawImage(ImageProvider.overground, 0, 0, getWidth(), getHeight(), null);

		g.setColor(Color.gray);
		Font font = new Font("HT_Hylian", Font.ITALIC, 24);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		FontRenderContext frc = g2.getFontRenderContext();
		TextLayout nameHero = new TextLayout(StaticConstantGame.nameHero, font, frc);
		nameHero.draw(g2, 1150, 60);

		g.setColor(Color.YELLOW);
		TextLayout score = new TextLayout(StaticConstantGame.gameManager.getHero().getScore() + "",
				font, frc);
		score.draw(g2, 1170, 90);

		printHearts(g);

		printMagicEnergy(g);

		g.drawImage(ImageProvider.printInfoArea, 1100, 250, 220, 400, null);

		g.setColor(Color.YELLOW);
		printMessage(StaticConstantGame.currentMessage, font, frc, g2, 1120, 300);

		if (StaticConstantGame.timerActive)
		{
			if (StaticConstantGame.time < 60)
			{
				g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.YELLOW);
			}

			int hh = StaticConstantGame.time / 3600;
			int mm = (int) ((((float) StaticConstantGame.time / 3600) - hh) * 60);
			int ss = (int) ((((((float) StaticConstantGame.time / 3600) - hh) * 60) - mm) * 60);

			TextLayout messageTime = new TextLayout(" * Time *  " + hh + ":" + mm + ":" + ss, font,
					frc);
			messageTime.draw(g2, 1110, 720);
		}

		int pieces = hero.getTriforcePieceCollected();
		if (pieces > 0)
		{
			g.drawImage(ImageProvider.triforcePiece[typeTriforce], 1115, 575, 30, 30, null);
			g.setColor(Color.YELLOW);
			TextLayout messageTriforcePieces = new TextLayout("x " + pieces, font, frc);
			messageTriforcePieces.draw(g2, 1150, 600);

			if ((counter++) % 20 == 0)
				typeTriforce = (typeTriforce + 1) % ImageProvider.triforcePiece.length;
		}

		temporized++;
	}

	private void printMessage(String currentMessage, Font font, FontRenderContext frc,
			Graphics2D g2, int x, int y)
	{
		int i = 0;
		int dim = currentMessage.length();
		while (i < currentMessage.length())
		{
			int lentgh = 12;
			if (dim - i < 12)
			{
				lentgh = dim - i;
			}
			TextLayout message = new TextLayout(currentMessage.substring(i, i + lentgh) + "", font,
					frc);
			message.draw(g2, x, y);
			i += 12;
			y += 20;

		}
	}

	private void printAttackSphere(Graphics g)
	{
		Iterator<AttackSphere> iterator = new ArrayList<AttackSphere>(
				StaticConstantGame.sphereManager.getSpheres()).iterator();

		int widthSize = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int heightSize = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;
		int translateX = (this.getWidth() * 323) / 1366;
		int translateY = (this.getHeight() * 30) / 768;

		while (iterator.hasNext())
		{
			AttackSphere sphere = iterator.next();
			int newX = (sphere.getX() % StaticConstantWorld.X_DIMENSION);
			int newY = (sphere.getY() % StaticConstantWorld.Y_DIMENSION);

			if (sphere instanceof Drawable)
			{
				((Drawable) sphere).drawObject(g, translateX + newY * widthSize, translateY + newX
						* heightSize, widthSize, heightSize);
			}
		}
	}

	private void printMagicEnergy(Graphics g)
	{
		g.setColor(Color.BLUE);
		int maximum = StaticConstantGame.gameManager.getHero().getMaximumMagicEnergy();
		int magic = StaticConstantGame.gameManager.getHero().getMagicEnergy();
		int initialX = 1150;

		g.drawImage(ImageProvider.energyBar, initialX, 134, 120, 20, null);
		initialX += 6;

		int widthSingleEnergy = (110 / maximum);
		for (int i = 0; i < magic; i++)
		{
			g.fillRect(initialX, 138, widthSingleEnergy, 12);
			initialX += widthSingleEnergy;
		}
	}

	private void printHearts(Graphics g)
	{
		g.setColor(Color.RED);
		int initialX = 1150;
		int numberHearts = StaticConstantGame.gameManager.getHero().getHearts();
		for (int i = 0; i < numberHearts; i++)
		{
			g.drawImage(ImageProvider.heart, initialX, 100, 20, 20, null);
			initialX += 25;
		}
	}

	public void addPauseSelected(int change)
	{
		if (pauseSelected == 1 && change == -1)
			return;
		if (pauseSelected == 3 && change == 1)
			return;
		pauseSelected += change;
	}

	public void setPause(boolean pause)
	{
		this.pause = pause;
	}

	public boolean isPause()
	{
		return pause;
	}

	public int getPauseSelected()
	{
		return pauseSelected;
	}

	public MainFrame getMainFrame()
	{
		return mainFrame;
	}
}
