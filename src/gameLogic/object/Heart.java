package gameLogic.object;

import common.SoundManager;

import gameLogic.MultiplayerGameManager;
import gameLogic.character.Hero;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Heart extends AbstractUtilityObject
{

	public Heart(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
		setId(StaticConstantID.HEART);
	}

	public Heart(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
		setId(StaticConstantID.HEART);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			if (hero.getHearts() >= hero.getMaximumHeart())
			{
				hero.modifyScores(-(this.getScore() * 2));
			}
			else
			{
				hero.modifyHearts(1);
				hero.modifyScores(-(this.getScore()));
			}
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);
			StaticConstantGame.gameManager.modifiyHeartsCollected(+1);

			SoundManager.playSound(SoundManager.HEART_CATCHED, 1, SoundManager.EFFECT);

		}
		if (StaticConstantGame.networkGame)
		{
			Hero hero2 = ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();
			if ((getX() == hero2.getX()) && (getY() == hero2.getY()))
			{
				if (hero2.getHearts() >= hero.getMaximumHeart())
				{
					hero2.modifyScores(-(this.getScore() * 2));
				}
				else
				{
					hero2.modifyHearts(1);
					hero2.modifyScores(-(this.getScore()));
				}
				StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(),
						getY(), new EmptyUtility(getX(), getY()));
				StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);
			}
		}
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}
}
