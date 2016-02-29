package gameLogic.object;

import common.SoundManager;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.Hero;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Block extends AbstractUtilityObject
{

	private boolean movable;

	public Block(int xPosition, int yPosition)
	{
		super(xPosition, yPosition, 0);
		setMovable(true);
		setId(StaticConstantID.BLOCK);
	}

	public Block(int xPosition, int yPosition, boolean movable)
	{
		super(xPosition, yPosition, 0);
		this.movable = movable;
		setId(StaticConstantID.BLOCK);
	}

	public boolean move(Hero hero)
	{
		int x = getX() + hero.getCurrentDirection().getXSelected();
		int y = getY() + hero.getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(x, y);
		AbstractUtilityObject utilityObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(x, y);
		AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(x, y);

		if (sceneObject.getId() >= StaticConstantID.LIMITS_ACCESSIBLE)
		{

			if (character instanceof AbstractEnemy)
			{
				return false;
			}

			if (utilityObject instanceof EmptyUtility)
			{
				StaticConstantGame.gameManager.getConcreteWorld().swapCell(
						ConcreteWorld.CODE_OBJECT_WORLD, getX(), getY(), x, y);
				setX(x);
				setY(y);

				SoundManager.playSound(SoundManager.BLOCK_MOVED, 1, SoundManager.EFFECT);

				return true;
			}
		}

		return false;
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

	public void setMovable(boolean movable)
	{
		this.movable = movable;
	}

	public boolean isMovable()
	{
		return movable;
	}
}
