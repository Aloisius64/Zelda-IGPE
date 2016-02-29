package gameLogic.object;

import common.SoundManager;

import gameLogic.MultiplayerGameManager;
import gameLogic.character.Hero;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class MagicSphere extends AbstractUtilityObject
{
	public MagicSphere(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.MAGIC_SPHERE);
	}

	public MagicSphere(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
		setId(StaticConstantID.MAGIC_SPHERE);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			if (hero.getMagicEnergy() < hero.getMaximumMagicEnergy())
			{
				hero.modifyMagicEnergy(1);
			}
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);
			StaticConstantGame.gameManager.modifiyMagicSphereCollected(+1);

			SoundManager.playSound(SoundManager.MAGIC_SPHERE_CATCHED, 1, SoundManager.EFFECT);
		}
		if (StaticConstantGame.networkGame)
		{
			Hero hero2 = ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();
			if ((getX() == hero2.getX()) && (getY() == hero2.getY()))
			{
				if (hero.getMagicEnergy() < hero.getMaximumMagicEnergy())
				{
					hero.modifyMagicEnergy(1);
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
