package guiInterface.startScreen;

import eventState.NetworkWiiMoteListener;
import gameLogic.Drawable;
import gameLogic.MultiplayerGameManager;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AttackSphere;
import gameLogic.character.CharacterState;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import guiInterface.character2D.AttackSphere2D;
import guiInterface.character2D.Character2D;
import guiInterface.character2D.Hero2D;
import guiInterface.character2D.Warrior2D;
import guiInterface.object2D.Heart2D;
import guiInterface.object2D.MagicSphere2D;

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
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import network.ConnectionManager;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;
import threadState.EnemyManager;
import threadState.ObjectManager;
import threadState.SphereManager;

import common.Direction;
import common.ImageProvider;

public class MultiplayerGamePanel extends JPanel implements Runnable
{
	private final ConnectionManager connectionManager;
	private static MainFrame mainFrame;
	private final MultiplayerGameManager gameManager;
	private final String player;
	private Thread thread;
	private final boolean isPlayerOne;
	private final MultiPlayerPanel panel;
	private Image overground;

	public MultiplayerGamePanel(final MainFrame mainFrame,
			final ConnectionManager connectionManager, final MultiplayerGameManager gameManager,
			final String player, MultiPlayerPanel panel, final boolean isPlayerOne)
	{
		this.connectionManager = connectionManager;
		this.mainFrame = mainFrame;
		new ImageProvider();
		this.player = player;
		this.panel = panel;
		StaticConstantGame.loading = false;
		StaticConstantGame.networkGame = true;

		this.isPlayerOne = isPlayerOne;

		this.gameManager = gameManager;

		assignToCharacter();

		new EnemyManager(0, 0, 5, 5);
		new EnemyManager(0, 5, 5, 5);
		new EnemyManager(5, 0, 5, 5);
		new EnemyManager(5, 5, 5, 5);

		new ObjectManager(this);

		StaticConstantGame.sphereManager = new SphereManager(this);
		StaticConstantGame.sphereManager.start();

		configureWiiMote();
		this.addKeyListener(new KeyAdapter()
		{
			@Override
			public void keyPressed(KeyEvent e)
			{
				super.keyPressed(e);
				int c = e.getKeyCode();

				Hero2D hero = null;
				if (isPlayerOne)
					hero = (Hero2D) StaticConstantGame.gameManager.getHero();
				else
					hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager)
							.getPlayer2();

				if (c == StaticConstantGame.moveUp)
				{
					hero.setCurrentDirection(Direction.UP);
					hero.setState(1);
				}
				else if (c == StaticConstantGame.moveDown)
				{
					hero.setCurrentDirection(Direction.DOWN);
					hero.setState(1);
				}
				else if (c == StaticConstantGame.moveLeft)
				{
					hero.setCurrentDirection(Direction.LEFT);
					hero.setState(1);
				}
				else if (c == StaticConstantGame.moveRight)
				{
					hero.setCurrentDirection(Direction.RIGHT);
					hero.setState(1);
				}
				if (c == StaticConstantGame.attack1)
				{
					// SoundManager.playSound("Sounds/Effetcs/Sword.wav", 1,
					// SoundManager.EFFECT);
					hero.setState(2);
				}
				else if (c == KeyEvent.VK_C)
				{
					hero.setState(6);
				}
				else if (c == StaticConstantGame.attack2)
				{
					// new Sound("Sounds/Effetcs/Sword.wav", true);
					hero.setState(3);
				}
				dispatch();
				repaint();
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				Hero2D hero = null;
				if (isPlayerOne)
					hero = (Hero2D) StaticConstantGame.gameManager.getHero();
				else
					hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager)
							.getPlayer2();

				super.keyReleased(e);
				if (hero.getState() != 4)
				{
					hero.setState(0);
				}
				dispatch();
				repaint();
			}
		});

		try
		{
			overground = ImageIO.read(new File("Images/Overground-01.png"));
		}
		catch (IOException e)
		{
			System.out
					.println("L'immagine di background non  può essere caricata (Non è presente nella cartella di default).");
		}

		this.start();
	}

	public void configureWiiMote()
	{
		if (StaticConstantGame.wiimoteActive)
		{
			StaticConstantGame.wiimote
					.removeWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
			StaticConstantGame.wiimoteListener = new NetworkWiiMoteListener(this);
			StaticConstantGame.wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		}
	}

	public void dispatch()
	{
		final StringBuilder sb = new StringBuilder();
		sb.append("Move");
		sb.append(";");
		sb.append(player);
		sb.append(";");

		Hero2D hero = null;
		if (isPlayerOne())
			hero = (Hero2D) StaticConstantGame.gameManager.getHero();
		else
			hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();

		Direction d = hero.getCurrentDirection();
		if (d.getTypeDirection() == Direction.UP)
			sb.append(1);
		else if (d.getTypeDirection() == Direction.DOWN)
			sb.append(7);
		else if (d.getTypeDirection() == Direction.LEFT)
			sb.append(3);
		else if (d.getTypeDirection() == Direction.RIGHT)
			sb.append(5);
		else if (d.getTypeDirection() == Direction.CENTER)
			sb.append(4);

		sb.append(";");
		sb.append(hero.getState());
		sb.append(";");
		connectionManager.dispatch(sb.toString());
	}

	private void start()
	{
		thread = new Thread(this);
		thread.start();

	}

	private void printMagicEnergy(Graphics g)
	{
		g.setColor(Color.BLUE);
		if (isPlayerOne())
		{
			int maximum = StaticConstantGame.gameManager.getHero().getMaximumMagicEnergy();
			int magic = StaticConstantGame.gameManager.getHero().getMagicEnergy();
			int initialX = 1080;

			g.drawImage(ImageProvider.energyBar, initialX, 94, 120, 20, null);
			initialX += 6;

			int widthSingleEnergy = (110 / maximum);
			for (int i = 0; i < magic; i++)
			{
				g.fillRect(initialX, 98, widthSingleEnergy, 12);
				initialX += widthSingleEnergy;
			}
		}
		else
		{
			int maximum = ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2()
					.getMaximumMagicEnergy();
			int magic = ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2()
					.getMagicEnergy();
			int initialX = 1080;

			g.drawImage(ImageProvider.energyBar, initialX, 94, 120, 20, null);
			initialX += 6;

			int widthSingleEnergy = (110 / maximum);
			for (int i = 0; i < magic; i++)
			{
				g.fillRect(initialX, 98, widthSingleEnergy, 12);
				initialX += widthSingleEnergy;
			}
		}
	}

	private void printHearts(Graphics g)
	{
		g.setColor(Color.RED);

		if (isPlayerOne())
		{
			int initialX = 1080;
			int numberHearts = StaticConstantGame.gameManager.getHero().getHearts();
			for (int i = 0; i < numberHearts; i++)
			{
				g.drawImage(ImageProvider.heart, initialX, 60, 20, 20, null);
				initialX += 25;
			}
		}
		else
		{
			int initialX = 1080;
			int numberHearts = ((MultiplayerGameManager) StaticConstantGame.gameManager)
					.getPlayer2().getHearts();
			for (int i = 0; i < numberHearts; i++)
			{
				g.drawImage(ImageProvider.heart, initialX, 60, 20, 20, null);
				initialX += 25;
			}
		}

	}

	private void printAttackSphere(Graphics g)
	{
		if (StaticConstantGame.sphereManager.getSpheres().size() == 0)
			return;

		Iterator<AttackSphere> iterator = new ArrayList<AttackSphere>(
				StaticConstantGame.sphereManager.getSpheres()).iterator();

		int widthSize = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int heightSize = (StaticConstantGame.yResolution / StaticConstantWorld.Y_DIMENSION) - 4;

		int translateX = 0;
		int translateY = 0;

		// int translateX = (this.getWidth() * 323) / 1366;
		// int translateY = (this.getHeight() * 30) / 768;

		while (iterator.hasNext())
		{
			AttackSphere sphere = iterator.next();
			int newX = (sphere.getX() % StaticConstantWorld.X_DIMENSION);
			int newY = (sphere.getY() % StaticConstantWorld.Y_DIMENSION);

			((AttackSphere2D) sphere).drawObject(g, translateX + newY * widthSize, translateY
					+ newX * heightSize, widthSize, heightSize);

		}
	}

	private void assignToCharacter()
	{
		for (int i = 0; i < gameManager.getCharacterCollection().size(); i++)
		{
			Iterator<AbstractCharacter> iteratorCharacter = StaticConstantGame.gameManager
					.getCharacterCollection().iterator();

			while (iteratorCharacter.hasNext())
			{
				((Character2D) iteratorCharacter.next()).setPanel(this);
			}
		}
	}

	@Override
	protected void paintComponent(final Graphics g)
	{
		super.paintComponent(g);
		World currentWorld = StaticConstantGame.gameManager.getConcreteWorld();
		paintWorld(g, currentWorld);

		printAttackSphere(g);

		g.drawImage(overground, 0, 0, this.getWidth(), this.getHeight(), null);
		printHearts(g);
		printMagicEnergy(g);

		Font font = new Font("Arial", Font.BOLD, 25);
		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(font);
		g2.setColor(Color.YELLOW);
		FontRenderContext frc = g2.getFontRenderContext();

		TextLayout playerName;
		playerName = new TextLayout(player, font, frc);

		playerName.draw(g2, (1080 * this.getWidth()) / 1366, (50 * this.getHeight()) / 768);
	}

	private void paintWorld(final Graphics g, final World currentWorld)
	{
		int translateX = 0;
		int translateY = 0;
		// int translateX = (this.getWidth() * 323) / 1366;
		// int translateY = (this.getHeight() * 30) / 768;

		int widthSize = StaticConstantGame.xResolution / StaticConstantWorld.X_DIMENSION;
		int heightSize = ((StaticConstantGame.yResolution) / StaticConstantWorld.Y_DIMENSION) - 4;
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
	}

	@Override
	public void run()
	{
		while (!gameManager.isGameOver() && gameManager.isConnection())
		{
			Hero2D hero = null;
			if (isPlayerOne())
				hero = (Hero2D) StaticConstantGame.gameManager.getHero();
			else
				hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager)
						.getPlayer2();

			final StringBuilder heroSB = new StringBuilder();
			heroSB.append("Hero");
			heroSB.append(";");
			heroSB.append(player);
			heroSB.append(";");
			heroSB.append(hero.getHearts());
			heroSB.append(";");
			heroSB.append(hero.getMagicEnergy());
			heroSB.append(";");
			heroSB.append(hero.getX());
			heroSB.append(";");
			heroSB.append(hero.getY());
			heroSB.append(";");
			connectionManager.dispatch(heroSB.toString());

			if (hero.getHearts() <= 0)
			{
				final StringBuilder gameSB = new StringBuilder();
				gameSB.append("Lose");
				gameSB.append(";");
				connectionManager.dispatch(gameSB.toString());
				mainFrame.switchPanel(new EndMultiplayerGamePanel(2, mainFrame, panel));
				mainFrame.setVisible(true);
			}

			if (isPlayerOne())
			{
				int hearts = 0;
				final StringBuilder enemySB = new StringBuilder();
				enemySB.append("Enemy");
				enemySB.append(";");

				Iterator<AbstractCharacter> iteratorCharacter = StaticConstantGame.gameManager
						.getCharacterCollection().iterator();

				while (iteratorCharacter.hasNext())
				{
					AbstractCharacter enemy = iteratorCharacter.next();
					Direction d = enemy.getCurrentDirection();

					if (d.getTypeDirection() == Direction.UP)
						enemySB.append(1);
					else if (d.getTypeDirection() == Direction.DOWN)
						enemySB.append(7);
					else if (d.getTypeDirection() == Direction.LEFT)
						enemySB.append(3);
					else if (d.getTypeDirection() == Direction.RIGHT)
						enemySB.append(5);
					else if (d.getTypeDirection() == Direction.CENTER)
						enemySB.append(4);
					enemySB.append(";");
					enemySB.append(enemy.getState());
					enemySB.append(";");
					enemySB.append(enemy.getHearts());
					enemySB.append(";");
					enemySB.append(enemy.getMagicEnergy());
					enemySB.append(";");
					enemySB.append(enemy.getX());
					enemySB.append(";");
					enemySB.append(enemy.getY());
					enemySB.append(";");

					hearts += enemy.getHearts();
				}
				connectionManager.dispatch(enemySB.toString());

				if (hearts % 12 == 0)
				{
					int pos = hearts % 8;
					if (pos > 0)
					{
						gameManager.getConcreteWorld().setCellObjectWorld(pos, pos,
								new MagicSphere2D(pos, pos));
						gameManager.getUtilityObjectCollection().add(new MagicSphere2D(pos, pos));
						final StringBuilder objectSB = new StringBuilder();
						objectSB.append("Magics");
						objectSB.append(";");
						objectSB.append(pos);
						objectSB.append(";");
						connectionManager.dispatch(objectSB.toString());
					}
				}

				if (hearts % 21 == 0)
				{
					int pos = hearts % 8;
					if (pos > 0)
					{
						gameManager.getConcreteWorld().setCellObjectWorld(pos, pos,
								new Heart2D(pos, pos));
						gameManager.getUtilityObjectCollection().add(new Heart2D(pos, pos));
						final StringBuilder objectSB = new StringBuilder();
						objectSB.append("Hearts");
						objectSB.append(";");
						objectSB.append(pos);
						objectSB.append(";");
						connectionManager.dispatch(objectSB.toString());
					}
				}
			}
			otherEnemy();
			try
			{
				// Precedentemente lo sleep era 40 ATTENTO
				Thread.sleep(80);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
		}

		Hero2D hero = null;
		if (isPlayerOne())
			hero = (Hero2D) StaticConstantGame.gameManager.getHero();
		else
			hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();

		StaticConstantGame.gameRunning = false;
		StaticConstantGame.networkGame = false;
		if (!gameManager.isConnection())
		{
			JOptionPane.showMessageDialog(mainFrame, "Ops! Connection closed");
			mainFrame.switchPanel(panel.getPlayPanel().getMenuPanel());
			mainFrame.setVisible(true);
		}
		else if (hero.getHearts() <= 0)
		{
			final StringBuilder gameSB = new StringBuilder();
			gameSB.append("Lose");
			gameSB.append(";");
			connectionManager.dispatch(gameSB.toString());
			mainFrame.switchPanel(new EndMultiplayerGamePanel(2, mainFrame, panel));
			mainFrame.setVisible(true);
		}
	}

	private void otherEnemy()
	{
		if (gameManager.getConcreteWorld().getNumberEnemies() == 0)
		{
			try
			{
				Thread.sleep(3000);
			}
			catch (InterruptedException exception)
			{
				System.out.println("Eccezione nella sleep di aggiungimento personaggi");
			}
			Warrior2D warrior1 = new Warrior2D(1, 1);
			warrior1.setPanel(this);
			gameManager.getConcreteWorld().setCellCharacterWorld(1, 1, warrior1);
			gameManager.getConcreteWorld().getCharacterCollection().add(warrior1);
			gameManager.getCharacterCollection().add(warrior1);

			Warrior2D warrior2 = new Warrior2D(1, 8);
			warrior2.setPanel(this);
			gameManager.getConcreteWorld().setCellCharacterWorld(1, 8, warrior2);
			gameManager.getConcreteWorld().getCharacterCollection().add(warrior2);
			gameManager.getCharacterCollection().add(warrior2);

			Warrior2D warrior3 = new Warrior2D(7, 8);
			warrior3.setPanel(this);
			gameManager.getConcreteWorld().setCellCharacterWorld(7, 8, warrior3);
			gameManager.getConcreteWorld().getCharacterCollection().add(warrior3);
			gameManager.getCharacterCollection().add(warrior3);

			Warrior2D warrior4 = new Warrior2D(7, 1);
			warrior4.setPanel(this);
			gameManager.getConcreteWorld().setCellCharacterWorld(7, 1, warrior4);
			gameManager.getConcreteWorld().getCharacterCollection().add(warrior4);
			gameManager.getCharacterCollection().add(warrior4);
		}
	}

	public boolean isPlayerOne()
	{
		return isPlayerOne;
	}
}
