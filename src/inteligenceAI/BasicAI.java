package inteligenceAI;

import common.Direction;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.CharacterState;

public class BasicAI extends AbstractAI
{

	public BasicAI()
	{
		super();
	}

	public BasicAI(AbstractCharacter enemy)
	{
		super(enemy);
	}

	private void setNextState()
	{
		int distance = getDistance();
		if (distance <= 1)
		{
			if (areInTheSameDirection())
			{
				correctDirection();
				getEnemy().setState(CharacterState.PHYSIC_ATTACK);
			}
			else
			{
				getEnemy().setState(CharacterState.WALK);
			}
		}
		else if (areInTheSameDirection())
		{
			correctDirection();
			getEnemy().setState(CharacterState.DISTANCE_ATTACK);
		}
		else if (distance <= 4)
		{
			getEnemy().setState(CharacterState.WALK);
		}
	}

	@Override
	public Direction getNewDirection()
	{
		return getEnemy().getCurrentDirection();
	}

	@Override
	public void update()
	{
		int state = getEnemy().getState();
		switch (state)
		{
			case CharacterState.STAND:
			{
				setNextState();
				break;
			}
			case CharacterState.PHYSIC_ATTACK:
			{
				getEnemy().setCurrentDirection(getDirection());
				setNextState();
				break;
			}
			case CharacterState.DISTANCE_ATTACK:
			{
				getEnemy().setCurrentDirection(getDirection());
				setNextState();
			}
				break;
			case CharacterState.WALK:
			{
				getEnemy().setCurrentDirection(getShortDirection());
				setNextState();
			}
				break;
			default:
				getEnemy().setState(CharacterState.STAND);
				setNextState();
				break;
		}
	}
}
