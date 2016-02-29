package gameLogic.character;

import inteligenceAI.AbstractAI;
import inteligenceAI.AdvancedAI;
import inteligenceAI.BasicAI;
import inteligenceAI.RandomAI;
import staticConstant.StaticConstantGame;

import common.Direction;

public class AdvancedAttackSphere extends AttackSphere
{
	private AbstractAI inteligence;

	public AdvancedAttackSphere(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		switch (StaticConstantGame.typeAI)
		{
			case 1:
				setInteligence(new BasicAI(this));
				break;
			case 2:
				setInteligence(new AdvancedAI(this));
				break;
			default:
				setInteligence(new RandomAI(this));
				break;
		}
	}

	public AdvancedAttackSphere(AbstractCharacter throwingCharacter)
	{
		super(throwingCharacter);
		switch (StaticConstantGame.typeAI)
		{
			case 1:
				setInteligence(new BasicAI(this));
				break;
			case 2:
				setInteligence(new AdvancedAI(this));
				break;
			default:
				setInteligence(new RandomAI(this));
				break;
		}
	}

	public AbstractAI getInteligence()
	{
		return inteligence;
	}

	public void setInteligence(AbstractAI inteligence)
	{
		this.inteligence = inteligence;
	}

	@Override
	public void update()
	{
		super.update();
		if (!isExploded())
		{
			if (inteligence != null)
				setCurrentDirection(inteligence.getNewDirection());
		}
		else
		{
			setCurrentDirection(new Direction(Direction.CENTER));
		}
	}
}
