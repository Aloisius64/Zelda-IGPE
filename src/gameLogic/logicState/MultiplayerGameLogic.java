package gameLogic.logicState;

import gameLogic.MultiplayerGameManager;
import gameLogic.Update;
import gameLogic.character.Hero;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Platform;
import gameLogic.world.ConcreteWorld;
import guiInterface.object2D.Coin2D;
import guiInterface.object2D.Platform2D;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;

public class MultiplayerGameLogic implements Update
{
	private MultiplayerGameManager gameManager;

	public MultiplayerGameLogic(MultiplayerGameManager gameManager)
	{
		this.setGameManager(gameManager);
	}

	public boolean isEndLevel()
	{
		return false;
	}

	public boolean isGameOver()
	{
		Hero p1 = getGameManager().getHero();
		Hero p2 = getGameManager().getPlayer2();
		if (p1.getHearts() <= 0 || p2.getHearts() <= 0)
		{
			return true;
		}
		return false;
	}

	@Override
	public void update()
	{
		if (!isGameOver())
		{
			if (!isEndLevel())
			{
				ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
				for (int i = concreteWorld.getxPrint(); i < concreteWorld.getxPrint()
						+ StaticConstantWorld.X_DIMENSION; i++)
				{
					for (int j = concreteWorld.getyPrint(); j < concreteWorld.getyPrint()
							+ StaticConstantWorld.Y_DIMENSION; j++)
					{
						AbstractUtilityObject object = concreteWorld.getCellObjectWorld(i, j);
						if (!(object instanceof Platform2D))
						{
							if (object instanceof Coin2D)
							{
								((Coin2D) object).update();
							}
							else if (!(object instanceof Platform))
							{
								object.update();
							}

						}
					}
				}
			}
			else
			{
				// fine livello
			}
		}
		else
		{
			// fine partita
		}
	}

	public void setGameManager(MultiplayerGameManager gameManager)
	{
		this.gameManager = gameManager;
	}

	public MultiplayerGameManager getGameManager()
	{
		return gameManager;
	}

}
