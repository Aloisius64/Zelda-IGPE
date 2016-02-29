package gameLogic.object;

import common.SoundManager;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import gameLogic.character.Hero;
import gameLogic.world.World;

public class TriforcePiece extends AbstractUtilityObject
{

	public TriforcePiece(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.TRIFORCE_PIECE);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			hero.setTriforcePieceCollected(hero.getTriforcePieceCollected() + 1);
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);

			SoundManager.playSound(SoundManager.TRIFORCE_PIECE_CATCHED, 1, SoundManager.EFFECT);
		}
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}
}
