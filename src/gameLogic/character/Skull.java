package gameLogic.character;

import common.Direction;
import gameLogic.world.World;
import inteligenceAI.AbstractAI;
import staticConstant.StaticConstantID;

public class Skull extends AbstractEnemy
{

	public Skull(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);

		setId(StaticConstantID.SKULL);
		setCurrentDirection(new Direction(Direction.DOWN));
		setDamageFar(1);
		setDamageNear(1);
		setFactorFar(1.0f);
		setFactorNear(1.0f);
		setHearts(6);
		setMagicEnergy(10);
		setScores(50);
		setMaximumHeart(6);
		setMaximumMagicEnergy(10);
		setState(CharacterState.STAND);
	}

	public Skull(final int xPosition, final int yPosition, AbstractAI inteligence)
	{
		super(xPosition, yPosition, inteligence);

		setId(StaticConstantID.SKULL);
		setCurrentDirection(new Direction(Direction.DOWN));
		setDamageFar(1);
		setDamageNear(1);
		setFactorFar(0.5f);
		setFactorNear(0.5f);
		setHearts(2);
		setMagicEnergy(5);
		setScores(100);
		setMaximumHeart(6);
		setMaximumMagicEnergy(10);
		setState(CharacterState.STAND);
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
