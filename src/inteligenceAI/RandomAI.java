package inteligenceAI;

import gameLogic.character.AbstractCharacter;

import java.util.Random;

public class RandomAI extends AbstractAI
{

	private final Random random = new Random();

	public RandomAI()
	{
		super();
	}

	public RandomAI(AbstractCharacter enemy)
	{
		super(enemy);
	}

	@Override
	public void update()
	{
		int number = random.nextInt(9);
		switch (number)
		{
			case 0:

			case 1:
				getEnemy().setCurrentDirection(random.nextInt(9));
				break;
			case 2:

			case 3:
				getEnemy().move();
				break;
			case 4:
			case 5:
				getEnemy().attack(0);
				break;
			case 6:
			case 7:
				getEnemy().attack(1);
				break;
			default:
				break;
		}
	}
}
