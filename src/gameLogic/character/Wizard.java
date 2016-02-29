package gameLogic.character;

import common.Direction;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import inteligenceAI.AbstractAI;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Wizard extends AbstractEnemy
{

	public Wizard(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);

		setId(StaticConstantID.WIZARD);
		setCurrentDirection(new Direction(Direction.CENTER));
		setDamageFar(1);
		setDamageNear(1);
		setFactorFar(1.0f);
		setFactorNear(1.0f);
		setHearts(5);
		setMagicEnergy(-1);
		setScores(300);
		setMaximumHeart(4);
		setMaximumMagicEnergy(1);
		setState(CharacterState.STAND);
	}

	public Wizard(final int xPosition, final int yPosition, AbstractAI inteligence)
	{
		super(xPosition, yPosition, inteligence);

		setId(StaticConstantID.WIZARD);
		setCurrentDirection(new Direction(Direction.CENTER));
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
	public int attackOne()
	{
		if (getMagicEnergy() != 0)
		{

			int xTarget = getX() + getCurrentDirection().getXSelected();
			int yTarget = getY() + getCurrentDirection().getYSelected();

			AbstractObjectScene sceneTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellBaseWorld(xTarget, yTarget);

			AbstractCharacter characterTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellCharacterWorld(xTarget, yTarget);

			if (sceneTarget.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
			{
				return 0;
			}

			int returnValue = 0;
			if (characterTarget instanceof EmptyCharacter)
			{
				returnValue = 2;
			}
			else if (characterTarget instanceof Hero)
			{
				int totalDamage = (int) (getFactorFar() * getDamageFar());
				((Hero) characterTarget).modifyHearts(-totalDamage);
				returnValue = 1;
			}
			else
			{
				return 0;
			}

			modifyMagicEnergy(-1);
			return returnValue;
		}
		return 0;
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
