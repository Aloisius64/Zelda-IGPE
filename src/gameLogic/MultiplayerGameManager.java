package gameLogic;

import gameLogic.character.Hero;
import gameLogic.logicState.MultiplayerGameLogic;
import gameLogic.world.WorldManager;
import guiInterface.character2D.Hero2D;
import staticConstant.StaticConstantGame;

public class MultiplayerGameManager extends GameManager implements Update
{
	private Hero hero2;
	private MultiplayerGameLogic gameLogic;
	private boolean connection;

	public MultiplayerGameManager()
	{
		this.setConnection(true);
	}

	@Override
	public boolean isEndLevel()
	{
		return gameLogic.isEndLevel();
	}

	@Override
	public boolean isGameOver()
	{
		return gameLogic.isGameOver();
	}

	public void setGameLogic(MultiplayerGameLogic gameLogic)
	{
		this.gameLogic = gameLogic;
	}

	@Override
	public void start()
	{
		try
		{
			// StaticConstantGame.typeWorldChoose = 8;
			setWorldManager(new WorldManager());
		}
		catch (Exception e)
		{
			System.out.println("Errore nel caricamento del livello");
		}
		setUtilityObjectCollection(getWorldManager().getUtilityObjectCollection());
		setConcreteWorld(getWorldManager().getCurrentWorld());

		setHero(new Hero2D(StaticConstantGame.nameHero, 4, 5));
		getHero().setHearts(5);
		getConcreteWorld().setCellCharacterWorld(4, 5, getHero());
		setPlayer2(new Hero2D(StaticConstantGame.nameHero, 5, 5));
		getPlayer2().setHearts(5);
		getConcreteWorld().setCellCharacterWorld(5, 5, getPlayer2());

		setCharacterCollection(getWorldManager().getCharacterCollection());

		gameLogic = new MultiplayerGameLogic(this);

		StaticConstantGame.gameManager = this;
	}

	@Override
	public void update()
	{
		gameLogic.update();
	}

	public void setPlayer2(Hero player2)
	{
		this.hero2 = player2;
	}

	public Hero getPlayer2()
	{
		return hero2;
	}

	public void setConnection(boolean connection)
	{
		this.connection = connection;
	}

	public boolean isConnection()
	{
		return connection;
	}
}
