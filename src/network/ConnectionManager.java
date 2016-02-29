package network;

import gameLogic.MultiplayerGameManager;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.EmptyCharacter;
import guiInterface.character2D.Hero2D;
import guiInterface.object2D.Heart2D;
import guiInterface.object2D.MagicSphere2D;
import guiInterface.startScreen.EndMultiplayerGamePanel;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MultiPlayerPanel;
import guiInterface.startScreen.MultiplayerGamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Iterator;

import common.SoundManager;

import staticConstant.StaticConstantGame;

public class ConnectionManager implements Runnable
{
	private boolean isPlayerOne;
	private String enemy;
	private final MainFrame mainFrame;
	private final String name;
	private PrintWriter pw;
	private final Socket socket;
	private final MultiPlayerPanel panel;

	public ConnectionManager(final Socket socket, final String name, MainFrame mainFrame,
			MultiPlayerPanel panel)
	{
		this.socket = socket;
		this.name = name;
		this.mainFrame = mainFrame;
		this.panel = panel;
		StaticConstantGame.typeWorldChoose = 8;
	}

	public void close()
	{
		try
		{
			getSocket().close();
		}
		catch (final IOException e)
		{
			// do nothing
		}
	}

	public void dispatch(final String message)
	{
		getPw().println(message);
	}

	public String getEnemyName()
	{
		return enemy;
	}

	public String getPlayerName()
	{
		return getName();
	}

	@Override
	public void run()
	{
		try
		{
			final BufferedReader br = new BufferedReader(new InputStreamReader(getSocket()
					.getInputStream()));
			setPw(new PrintWriter(getSocket().getOutputStream(), true));
			getPw().println(getPlayerName());
			String buffer = br.readLine();
			StaticConstantGame.gameRunning = true;

			// SoundManager.stop();

			while (!buffer.equals("#START"))
			{
				final String[] split = buffer.split(";");
				if (split.length != 0)
				{
					for (final String name : split)
					{
						if (!name.equals(getPlayerName()))
						{
							enemy = name;
						}
					}
				}
				buffer = br.readLine();
			}

			if (getName().compareTo(this.enemy) < 0)
			{
				setPlayerOne(true);
				StaticConstantGame.isPlayerOne = true;
			}
			else
			{
				setPlayerOne(false);
				StaticConstantGame.isPlayerOne = false;
			}

			if (isPlayerOne)
				StaticConstantGame.typeAI = 3;
			else
				StaticConstantGame.typeAI = 4;

			final MultiplayerGameManager gameManager = new MultiplayerGameManager();
			StaticConstantGame.gameManager = gameManager;

			gameManager.start();
			MultiplayerGamePanel panel = new MultiplayerGamePanel(getMainFrame(), this,
					gameManager, getName(), this.panel, isPlayerOne);
			if (((MultiplayerGameManager) StaticConstantGame.gameManager).getHero() instanceof Hero2D)
			{
				((Hero2D) StaticConstantGame.gameManager.getHero()).setPanel(panel);
			}
			if (((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2() instanceof Hero2D)
			{
				((Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2())
						.setPanel(panel);
			}
			getMainFrame().switchPanel(panel);

			buffer = br.readLine();

			Hero2D hero = null;
			if (isPlayerOne)
			{
				hero = (Hero2D) gameManager.getPlayer2();
				setPlayerOne(true);
			}
			else
			{
				hero = (Hero2D) gameManager.getHero();
				setPlayerOne(false);
			}

			// Parte lettura messaggi dall'altro client
			while (buffer != null)
			{
				final String[] split = buffer.split(";");

				if (split[0].equals("Move"))
				{
					hero.setCurrentDirection(Integer.parseInt(split[2]));
					hero.setState(Integer.parseInt(split[3]));
					panel.repaint();
				}
				else if (split[0].equals("Enemy"))
				{
					int gap = 0;
					Iterator<AbstractCharacter> iteratorCharacter = StaticConstantGame.gameManager
							.getCharacterCollection().iterator();

					if (StaticConstantGame.gameManager.getCharacterCollection().size() != 0)
					{
						while (iteratorCharacter.hasNext())
						{
							AbstractEnemy enemy = (AbstractEnemy) iteratorCharacter.next();

							enemy.setCurrentDirection(Integer.parseInt(split[1 + gap]));
							enemy.setState(Integer.parseInt(split[2 + gap]));
							enemy.setHearts(Integer.parseInt(split[3 + gap]));
							enemy.setMagicEnergy(Integer.parseInt(split[4 + gap]));

							if (enemy.getX() != Integer.parseInt(split[5 + gap])
									|| enemy.getY() != Integer.parseInt(split[6 + gap]))
							{
								int x = enemy.getX() + enemy.getCurrentDirection().getXSelected();
								int y = enemy.getY() + enemy.getCurrentDirection().getYSelected();

								if (x != Integer.parseInt(split[5 + gap])
										|| y != Integer.parseInt(split[6 + gap]))
								{
									gameManager.getConcreteWorld().setCellCharacterWorld(
											enemy.getX(), enemy.getY(),
											new EmptyCharacter(enemy.getX(), enemy.getY()));
									gameManager.getConcreteWorld().setCellCharacterWorld(
											Integer.parseInt(split[5 + gap]),
											Integer.parseInt(split[6 + gap]), enemy);
									enemy.setX(Integer.parseInt(split[5 + gap]));
									enemy.setY(Integer.parseInt(split[6 + gap]));
								}
							}
							gap += 6;
							panel.repaint();
						}
					}
				}
				else if (split[0].equals("Hearts"))
				{
					gameManager.getConcreteWorld().setCellObjectWorld(Integer.parseInt(split[1]),
							Integer.parseInt(split[1]),
							new Heart2D(Integer.parseInt(split[1]), Integer.parseInt(split[1])));
					gameManager.getUtilityObjectCollection().add(
							new Heart2D(Integer.parseInt(split[1]), Integer.parseInt(split[1])));
					panel.repaint();
				}
				else if (split[0].equals("Magics"))
				{
					gameManager.getConcreteWorld().setCellObjectWorld(
							Integer.parseInt(split[1]),
							Integer.parseInt(split[1]),
							new MagicSphere2D(Integer.parseInt(split[1]), Integer
									.parseInt(split[1])));
					gameManager.getUtilityObjectCollection().add(
							new MagicSphere2D(Integer.parseInt(split[1]), Integer
									.parseInt(split[1])));
					panel.repaint();
				}
				else if (split[0].equals("Hero"))
				{
					hero.setHearts(Integer.parseInt(split[2]));
					hero.setMagicEnergy(Integer.parseInt(split[3]));
					if (hero.getX() != Integer.parseInt(split[4])
							|| hero.getY() != Integer.parseInt(split[5]))
					{
						int x = hero.getX() + hero.getCurrentDirection().getXSelected();
						int y = hero.getY() + hero.getCurrentDirection().getYSelected();

						if (x != Integer.parseInt(split[4]) || y != Integer.parseInt(split[5]))
						{
							gameManager.getConcreteWorld().setCellCharacterWorld(hero.getX(),
									hero.getY(), new EmptyCharacter(hero.getX(), hero.getY()));
							gameManager.getConcreteWorld().setCellCharacterWorld(
									Integer.parseInt(split[4]), Integer.parseInt(split[5]), hero);
							hero.setX(Integer.parseInt(split[4]));
							hero.setY(Integer.parseInt(split[5]));
						}
					}
					panel.repaint();
				}

				else if (split[0].equals("Lose"))
				{
					mainFrame.switchPanel(new EndMultiplayerGamePanel(1, mainFrame, this.panel));
					mainFrame.setVisible(true);
				}
				else if (split[0].equals("Connection"))
				{
					((MultiplayerGameManager) StaticConstantGame.gameManager).setConnection(false);
				}
				panel.repaint();
				buffer = br.readLine();
			}

		}
		catch (final IOException e)
		{
			System.out.println("Connection closed");
		}
	}

	public Socket getSocket()
	{
		return socket;
	}

	public void setPw(PrintWriter pw)
	{
		this.pw = pw;
	}

	public PrintWriter getPw()
	{
		return pw;
	}

	public String getName()
	{
		return name;
	}

	public void setPlayerOne(boolean isPlayerOne)
	{
		this.isPlayerOne = isPlayerOne;
	}

	public boolean isPlayerOne()
	{
		return isPlayerOne;
	}

	public MainFrame getMainFrame()
	{
		return mainFrame;
	}
}