package gameLogic;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.Hero;
import gameLogic.logicState.AbstractGameLogic;
import gameLogic.logicState.GameLevelLogic;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.WorldManager;
import guiInterface.character2D.Hero2D;
import guiInterface.character3D.Hero3D;
import guiInterface.object2D.Platform2D;

import inteligenceAI.AdvancedAI;
import inteligenceAI.BasicAI;
import inteligenceAI.RandomAI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import profile.Profile;
import profile.Score;
import staticConstant.StaticConstantGame;

public class GameManager implements Update
{
	private Collection<AbstractCharacter> characterCollection;
	private Collection<AbstractUtilityObject> utilityObjectCollection;
	private WorldManager worldManager;
	private ConcreteWorld concreteWorld;
	private AbstractGameLogic gameLogic;
	private Hero hero;

	public void setCharacterCollection(Collection<AbstractCharacter> characterCollection)
	{
		this.characterCollection = characterCollection;
	}

	public void setUtilityObjectCollection(Collection<AbstractUtilityObject> utilityObjectCollection)
	{
		this.utilityObjectCollection = utilityObjectCollection;
	}

	public void setConcreteWorld(ConcreteWorld concreteWorld)
	{
		this.concreteWorld = concreteWorld;
	}

	private int initTime;
	private int enemyDefeated;
	private int heartsCollected;
	private int magicSphereCollected;
	private int treasureOpen;

	public GameManager()
	{
		resetStatistic();
	}

	public Collection<AbstractCharacter> getCharacterCollection()
	{
		return characterCollection;
	}

	public ConcreteWorld getConcreteWorld()
	{
		return concreteWorld;
	}

	public AbstractGameLogic getGameLogic()
	{
		return gameLogic;
	}

	public Hero getHero()
	{
		return hero;
	}

	public Collection<AbstractUtilityObject> getUtilityObjectCollection()
	{
		return utilityObjectCollection;
	}

	public WorldManager getWorldManager()
	{
		return worldManager;
	}

	public boolean isEndLevel()
	{
		return gameLogic.isEndLevel();
	}

	public boolean isGameOver()
	{
		return gameLogic.isGameOver();
	}

	public void setGameLogic(AbstractGameLogic gameLogic)
	{
		this.gameLogic = gameLogic;
	}

	public void setHero(Hero hero)
	{
		this.hero = hero;
	}

	public void setWorldManager(WorldManager worldManager)
	{
		this.worldManager = worldManager;
	}

	public void start()
	{
		try
		{
			worldManager = new WorldManager();
		}
		catch (Exception e)
		{
			// Finire Gestione Eccezzione Mondo non trovato
			e.printStackTrace();
			System.exit(-1);
		}

		concreteWorld = worldManager.getCurrentWorld();
		utilityObjectCollection = worldManager.getUtilityObjectCollection();
		characterCollection = worldManager.getCharacterCollection();

		AbstractUtilityObject startPoint = this.getConcreteWorld().getStartPoint();
		if (!StaticConstantGame.enable3D)
		{
			hero = new Hero2D(StaticConstantGame.nameHero, startPoint.getX(), startPoint.getY());
		}
		else
		{
			hero = new Hero3D(startPoint.getX(), startPoint.getY());
		}
		getConcreteWorld().setCellCharacterWorld(startPoint.getX(), startPoint.getY(), hero);
		gameLogic = new GameLevelLogic();
		StaticConstantGame.gameRunning = true;
		StaticConstantGame.gameManager = this;
	}

	public void startPlatform()
	{
		Iterator<AbstractUtilityObject> iteratorPlatform = new ArrayList<AbstractUtilityObject>(
				getUtilityObjectCollection()).iterator();

		while (iteratorPlatform.hasNext())
		{
			AbstractUtilityObject object = iteratorPlatform.next();
			if (object instanceof Platform2D)
			{
				((Platform2D) object).setGamePanel();
			}
		}
	}

	@Override
	public void update()
	{
		if (StaticConstantGame.gameRunning)
			gameLogic.update();
	}

	public void resetStatistic()
	{
		setInitTime((int) (System.currentTimeMillis() / 1000));
		setEnemyDefeated(0);
		setHeartsCollected(0);
		setMagicSphereCollected(0);
		setTreasureOpen(0);
	}

	public void manageStatistic()
	{
		Profile profile = StaticConstantGame.currentProfile;
		int gameTime = (int) (System.currentTimeMillis() / 1000) - initTime;
		profile.setGameTime(profile.getGameTime() + gameTime);
		profile.setEnemyDefeated(profile.getEnemyDefeated() + enemyDefeated);
		profile.setHeartsCollected(profile.getHeartsCollected() + heartsCollected);
		profile.setMagicSphereCollected(profile.getMagicSphereCollected() + magicSphereCollected);
		profile.setTreasureOpen(profile.getTreasureOpen() + treasureOpen);
		profile.setTotalScore(profile.getTotalScore() + hero.getScore());
	}

	public void manageNextLevel()
	{
		int typeWorldChoose = StaticConstantGame.typeWorldChoose;

		if (typeWorldChoose < 7)
		{
			StaticConstantGame.currentProfile.setLevelsUnlocked(typeWorldChoose + 1);
		}
	}

	public void addScoreToPlayer()
	{
		Profile profile = StaticConstantGame.currentProfile;
		int gameTime = (int) (System.currentTimeMillis() / 1000) - initTime;
		int scores = hero.getScore();

		Score finalScore = new Score(profile.getName(), StaticConstantGame.typeWorldChoose, scores,
				gameTime);

		switch (StaticConstantGame.typeWorldChoose)
		{
			case 1:
				profile.getKakariko().add(finalScore);
				break;
			case 2:
				profile.getDesert().add(finalScore);
				break;
			case 3:
				profile.getDungeon().add(finalScore);
				break;
			case 4:
				profile.getForest().add(finalScore);
				break;
			case 5:
				profile.getLake().add(finalScore);
				break;
			case 6:
				profile.getVolcano().add(finalScore);
				break;
			case 7:
				profile.getSky().add(finalScore);
				break;
			default:
				break;
		}
	}

	public int getInitTime()
	{
		return initTime;
	}

	public int getEnemyDefeated()
	{
		return enemyDefeated;
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

	public void setInitTime(int initTime)
	{
		this.initTime = initTime;
	}

	public void setEnemyDefeated(int enemyDefeated)
	{
		this.enemyDefeated = enemyDefeated;
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

	public void modifiyInitTime(int initTime)
	{
		this.initTime += initTime;
	}

	public void modifiyEnemyDefeated(int enemyDefeated)
	{
		this.enemyDefeated += enemyDefeated;
	}

	public void modifiyHeartsCollected(int heartsCollected)
	{
		this.heartsCollected += heartsCollected;
	}

	public void modifiyMagicSphereCollected(int magicSphereCollected)
	{
		this.magicSphereCollected += magicSphereCollected;
	}

	public void modifiyTreasureOpen(int treasureOpen)
	{
		this.treasureOpen += treasureOpen;
	}

	public void modifyAIEnemies()
	{
		ConcreteWorld concreteWorld = getConcreteWorld();

		for (int i = 0; i < concreteWorld.getWidth(); i++)
		{
			for (int j = 0; j < concreteWorld.getWidth(); j++)
			{
				AbstractCharacter character = concreteWorld.getCellCharacterWorld(i, j);
				if (character instanceof AbstractEnemy)
				{
					AbstractEnemy enemy = ((AbstractEnemy) character);

					switch (StaticConstantGame.typeAI)
					{
						case 1:
							enemy.setInteligence(new BasicAI(enemy));
							break;
						case 2:
							enemy.setInteligence(new AdvancedAI(enemy));
							break;
						default:
							enemy.setInteligence(new RandomAI(enemy));
							break;
					}
				}
			}
		}
	}
}
