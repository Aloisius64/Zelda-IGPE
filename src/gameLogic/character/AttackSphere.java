package gameLogic.character;

import common.Direction;
import common.SoundManager;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Button;
import gameLogic.object.FlameTower;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantWorld;

public class AttackSphere extends AbstractCharacter
{

	private boolean exploded = false;
	private final AbstractCharacter throwingCharacter;

	public AttackSphere(AbstractCharacter throwingCharacter)
	{
		super(throwingCharacter.getX() + throwingCharacter.getCurrentDirection().getXSelected(),
				throwingCharacter.getY() + throwingCharacter.getCurrentDirection().getYSelected());

		this.throwingCharacter = throwingCharacter;

		setCurrentDirection(new Direction(throwingCharacter.getCurrentDirection()
				.getTypeDirection()));

		if (!StaticConstantGame.enable3D)
		{
			StaticConstantGame.sphereManager.addSphere(this);
		}

		SoundManager.playSound(SoundManager.ATTACK_SPHERE_CREATED, 1, SoundManager.EFFECT);

		setExploded(false);
	}

	public AttackSphere(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setExploded(false);
		throwingCharacter = new EmptyCharacter(xPosition, yPosition);
	}

	protected boolean isAnEnemyOfHero()
	{
		return (getThrowingCharacter() instanceof AbstractEnemy);
	}

	protected boolean isFromHero()
	{
		return (getThrowingCharacter() instanceof Hero);
	}

	protected void explosion()
	{
		SoundManager.playSound(SoundManager.ATTACK_SPHERE_EXPLODED, 1, SoundManager.EFFECT);
		setExploded(true);
	}

	@Override
	public int attack(int typeAttack)
	{
		return 0;
	}

	@Override
	public int getScore()
	{
		return 0;
	}

	@Override
	public boolean move()
	{
		return false;
	}

	@Override
	public void update()
	{
		int xTarget = getX();
		int yTarget = getY();

		if (!isExploded())
		{
			AbstractCharacter characterTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellCharacterWorld(xTarget, yTarget);
			AbstractUtilityObject utilityObject = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellObjectWorld(xTarget, yTarget);
			AbstractObjectScene sceneTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellBaseWorld(xTarget, yTarget);

			if (sceneTarget.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
			{
				explosion();
				return;
			}

			int newX = getX() % StaticConstantWorld.X_DIMENSION;
			int newY = getY() % StaticConstantWorld.Y_DIMENSION;

			if (newX == 0 || newX == (StaticConstantWorld.X_DIMENSION - 1))
			{
				exploded = true;
				if (sceneTarget.getId() >= StaticConstantID.LIMITS_ACCESSIBLE)
				{
					StaticConstantGame.sphereManager.removeSphere(this);
				}

			}

			if (newY == 0 || newY == (StaticConstantWorld.Y_DIMENSION - 1))
			{
				exploded = true;
				if (sceneTarget.getId() >= StaticConstantID.LIMITS_ACCESSIBLE)
				{
					StaticConstantGame.sphereManager.removeSphere(this);
				}
			}

			if (isFromHero())
			{ // se una sfera dell'eroe colpisce un nemico
				if (characterTarget instanceof AbstractEnemy)
				{
					AbstractEnemy enemy = (AbstractEnemy) characterTarget;
					int totalDamage = (int) (getThrowingCharacter().getDamageFar() * getThrowingCharacter()
							.getFactorFar());
					enemy.modifyHearts(-totalDamage);
					explosion();
					return;
				}
				if (utilityObject instanceof Button)
				{
					Button button = (Button) utilityObject;
					button.setActive(!button.isActive());
					explosion();
					return;
				}
				if (utilityObject instanceof FlameTower)
				{
					((FlameTower) utilityObject).setOpen(true);
					explosion();
					return;
				}
			}
			else if (isAnEnemyOfHero())
			{ // se una sfera nemica colpisce l'eroe
				if (characterTarget instanceof Hero
						&& characterTarget.getState() != CharacterState.PHYSIC_ATTACK)
				{
					Hero hero = (Hero) characterTarget;
					int totalDamage = (int) (getThrowingCharacter().getDamageFar() * getThrowingCharacter()
							.getFactorFar());
					hero.modifyHearts(-totalDamage);
					hero.setState(CharacterState.HITTED);
					explosion();
					return;
				}
				else if (characterTarget instanceof Hero)
				{
					explosion();
					return;
				}
			}
		}
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	public boolean isExploded()
	{
		return exploded;
	}

	public void setExploded(boolean exploded)
	{
		this.exploded = exploded;
	}

	public AbstractCharacter getThrowingCharacter()
	{
		return throwingCharacter;
	}
}
