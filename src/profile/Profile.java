package profile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Profile
{
	private String name;
	private int gameTime;
	private int enemyDefeated;
	private int totalScore;
	private int heartsCollected;
	private int magicSphereCollected;
	private int treasureOpen;
	private int levelsUnlocked;
	private final int numberExtra = 8;
	private boolean[] extraUnlocked = new boolean[numberExtra];
	private List<Score> kakariko = new ArrayList<Score>();
	private List<Score> desert = new ArrayList<Score>();
	private List<Score> dungeon = new ArrayList<Score>();
	private List<Score> forest = new ArrayList<Score>();
	private List<Score> lake = new ArrayList<Score>();
	private List<Score> volcano = new ArrayList<Score>();
	private List<Score> sky = new ArrayList<Score>();

	public Profile()
	{
		name = "empty";
		setEmptyProfile();
	}

	public Profile(String nameFile) throws IOException
	{
		readProfileFromFile(nameFile);
	}

	public void setEmptyProfile()
	{
		gameTime = 0;
		enemyDefeated = 0;
		totalScore = 0;
		heartsCollected = 0;
		magicSphereCollected = 0;
		treasureOpen = 0;
		levelsUnlocked = 1;
		for (int i = 0; i < numberExtra; i++)
		{
			extraUnlocked[i] = false;
		}
		kakariko = new ArrayList<Score>();
		desert = new ArrayList<Score>();
		dungeon = new ArrayList<Score>();
		forest = new ArrayList<Score>();
		lake = new ArrayList<Score>();
		volcano = new ArrayList<Score>();
		sky = new ArrayList<Score>();
	}

	public void readProfileFromFile(String nameFile) throws IOException
	{
		final BufferedReader base = new BufferedReader(new FileReader(nameFile));
		String tmpString = null;
		int tmpInt = 0;
		int k = 1;

		while ((tmpString = base.readLine()) != null)
		{
			switch (k)
			{
				case 1: // Leggo il nome
				{
					setName(tmpString);
				}
					break;
				case 2: // Leggo il tempo di gioco
				{
					setGameTime(Integer.parseInt(tmpString));
				}
					break;
				case 3: // Leggo i nemici sconfitti
				{
					setEnemyDefeated(Integer.parseInt(tmpString));
				}
					break;
				case 4:// Leggo i punti totali
				{
					setTotalScore(Integer.parseInt(tmpString));
				}
					break;
				case 5: // Leggo i cuori raccolti
				{
					setHeartsCollected(Integer.parseInt(tmpString));
				}
					break;
				case 6: // Leggo le sfere raccolte
				{
					setMagicSphereCollected(Integer.parseInt(tmpString));
				}
					break;
				case 7: // Leggo i tesori aperti
				{
					setTreasureOpen(Integer.parseInt(tmpString));
				}
					break;
				case 8: // Leggo i livelli sbloccati
				{
					setLevelsUnlocked(Integer.parseInt(tmpString));
				}
					break;
				case 9: // Leggo gli extra sbloccati
				{
					StringTokenizer tokenBase = new StringTokenizer(tmpString);
					int number = tokenBase.countTokens();
					for (int i = 0; i < number; i++)
					{
						tmpInt = Integer.parseInt(tokenBase.nextToken());
						extraUnlocked[i] = (tmpInt == 0) ? false : true;
					}
				}
					break;
				// Leggo i punteggi per ogni livello
				case 10: // Livello 1 "Kakariko"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						kakariko.add(new Score(getName(), 1, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				case 11: // Livello 2 "Desert"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						desert.add(new Score(getName(), 2, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				case 12: // Livello 3 "Dungeon"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						dungeon.add(new Score(getName(), 3, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				case 13: // Livello 4 "Forest"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						forest.add(new Score(getName(), 4, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				case 14: // Livello 5 "Lake"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						lake.add(new Score(getName(), 5, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				case 15: // Livello 6 "Volcano"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						volcano.add(new Score(getName(), 6, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				case 16: // Livello 7 "Sky"
				{
					tmpInt = Integer.parseInt(tmpString);
					for (int i = 0; i < tmpInt; i++)
					{
						// Leggi Punteggio
						tmpString = base.readLine();
						StringTokenizer tokenBase = new StringTokenizer(tmpString);
						String scores = tokenBase.nextToken();
						String time = tokenBase.nextToken();
						sky.add(new Score(getName(), 7, Integer.parseInt(scores), Integer
								.parseInt(time)));
					}
				}
					break;
				default:
					break;
			}
			k++;
		}
	}

	public void writeProfileToFile() throws FileNotFoundException
	{
		PrintWriter printWriter = new PrintWriter("profiles/" + getName() + ".txt");
		printWriter.println(getName());
		printWriter.println(getGameTime());
		printWriter.println(getEnemyDefeated());
		printWriter.println(getTotalScore());
		printWriter.println(getHeartsCollected());
		printWriter.println(getMagicSphereCollected());
		printWriter.println(getTreasureOpen());
		printWriter.println(getLevelsUnlocked());
		boolean[] tmpExtraUnlocked = getExtraUnlocked();
		for (int i = 0; i < numberExtra; i++)
		{
			if (tmpExtraUnlocked[i])
				printWriter.print("1 ");
			else
				printWriter.print("0 ");
		}
		printWriter.println();

		int size = kakariko.size();
		printWriter.println(size);
		Iterator<Score> iterator = kakariko.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		size = desert.size();
		printWriter.println(size);
		iterator = desert.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		size = dungeon.size();
		printWriter.println(size);
		iterator = dungeon.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		size = forest.size();
		printWriter.println(size);
		iterator = forest.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		size = lake.size();
		printWriter.println(size);
		iterator = lake.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		size = volcano.size();
		printWriter.println(size);
		iterator = volcano.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		size = sky.size();
		printWriter.println(size);
		iterator = sky.iterator();
		for (int i = 0; i < size; i++)
		{
			Score next = iterator.next();
			printWriter.println(next.getScores() + " " + next.getTime());
		}

		printWriter.close();
	}

	public void printProfile()
	{
		System.out.println("*** Profile ***");
		System.out.println("Name: " + getName());
		System.out.println("Tempo di gioco: " + getGameTimeToClock());
		System.out.println("Punti Totali: " + getTotalScore());
		System.out.println("Cuori Raccolti: " + getHeartsCollected());
		System.out.println("Sfere Raccolte: " + getMagicSphereCollected());
		System.out.println("Tesori Aperti: " + getTreasureOpen());
		System.out.println("Livelli sbloccati: ");
		for (int i = 1; i <= getLevelsUnlocked(); i++)
		{
			System.out.print(i + " ");
		}
		System.out.println("\nExtra Sbloccati: ");
		for (int i = 1; i <= numberExtra; i++)
		{
			System.out.println("Extra - " + i + ": " + extraUnlocked[i - 1]);
		}
		for (int i = 1; i <= 7; i++)
		{
			System.out.print("Punteggi Livello " + i + " - ");
			Iterator<Score> iterator = null;
			boolean error = false;
			switch (i)
			{
				case 1:
					System.out.println("Kakariko -");
					iterator = kakariko.iterator();
					break;
				case 2:
					System.out.println("Desert -");
					iterator = desert.iterator();
					break;
				case 3:
					System.out.println("Dungeon -");
					iterator = dungeon.iterator();
					break;
				case 4:
					System.out.println("Forest -");
					iterator = forest.iterator();
					break;
				case 5:
					System.out.println("Lake -");
					iterator = lake.iterator();
					break;
				case 6:
					System.out.println("Volcano -");
					iterator = volcano.iterator();
					break;
				case 7:
					System.out.println("Sky -");
					iterator = sky.iterator();
					break;
				default:
					error = true;
					break;
			}
			if (!error)
			{
				while (iterator.hasNext())
				{
					Score tmp = iterator.next();
					System.out.print("****" + tmp.getScores() + "\t" + tmp.getTimeToClock() + "\n");
				}
			}
		}
	}

	public boolean unlockExtra(int ex)
	{
		if (ex >= 0 && ex < 8)
		{
			extraUnlocked[ex] = true;
		}
		return false;
	}

	public String getName()
	{
		return name;
	}

	public int getGameTime()
	{
		return gameTime;
	}

	public String getGameTimeToClock()
	{
		int hh = gameTime / 3600;
		int mm = (int) ((((float) gameTime / 3600) - hh) * 60);
		int ss = (int) ((((((float) gameTime / 3600) - hh) * 60) - mm) * 60);
		return "\"" + hh + ":" + mm + ":" + ss;
	}

	public int getEnemyDefeated()
	{
		return enemyDefeated;
	}

	public int getTotalScore()
	{
		return totalScore;
	}

	public int getHeartsCollected()
	{
		return heartsCollected;
	}

	public int getMagicSphereCollected()
	{
		return magicSphereCollected;
	}

	public int getTreasureOpen()
	{
		return treasureOpen;
	}

	public int getLevelsUnlocked()
	{
		return levelsUnlocked;
	}

	public boolean[] getExtraUnlocked()
	{
		return extraUnlocked;
	}

	public List<Score> getKakariko()
	{
		return kakariko;
	}

	public List<Score> getDesert()
	{
		return desert;
	}

	public List<Score> getDungeon()
	{
		return dungeon;
	}

	public List<Score> getForest()
	{
		return forest;
	}

	public List<Score> getLake()
	{
		return lake;
	}

	public List<Score> getVolcano()
	{
		return volcano;
	}

	public List<Score> getSky()
	{
		return sky;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public void setGameTime(int gameTime)
	{
		this.gameTime = gameTime;
	}

	public void setEnemyDefeated(int enemyDefeated)
	{
		this.enemyDefeated = enemyDefeated;
	}

	public void setTotalScore(int totalScore)
	{
		this.totalScore = totalScore;
	}

	public void setHeartsCollected(int heartsCollected)
	{
		this.heartsCollected = heartsCollected;
	}

	public void setMagicSphereCollected(int magicSphereCollected)
	{
		this.magicSphereCollected = magicSphereCollected;
	}

	public void setTreasureOpen(int treasureOpen)
	{
		this.treasureOpen = treasureOpen;
	}

	public void setLevelsUnlocked(int levelsUnlocked)
	{
		this.levelsUnlocked = levelsUnlocked;
	}

	public void setExtraUnlocked(boolean[] extraUnlocked)
	{
		this.extraUnlocked = extraUnlocked;
	}

	public void setKakariko(List<Score> kakariko)
	{
		this.kakariko = kakariko;
	}

	public void setDesert(List<Score> desert)
	{
		this.desert = desert;
	}

	public void setDungeon(List<Score> dungeon)
	{
		this.dungeon = dungeon;
	}

	public void setForest(List<Score> forest)
	{
		this.forest = forest;
	}

	public void setLake(List<Score> lake)
	{
		this.lake = lake;
	}

	public void setVolcano(List<Score> volcano)
	{
		this.volcano = volcano;
	}

	public void setSky(List<Score> sky)
	{
		this.sky = sky;
	}
}
