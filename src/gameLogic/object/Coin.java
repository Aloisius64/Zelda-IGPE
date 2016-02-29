package gameLogic.object;

import common.SoundManager;

import gameLogic.character.Hero;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Coin extends AbstractUtilityObject
{

	public Coin(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
		setId(StaticConstantID.COIN);
	}

	public Coin(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
		setId(StaticConstantID.COIN);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			hero.modifyScores(this.getScore());
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);

			SoundManager.playSound(SoundManager.COIN_CATCHED, 1, SoundManager.EFFECT);
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
